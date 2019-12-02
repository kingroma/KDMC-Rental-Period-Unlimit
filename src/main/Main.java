package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import excel.Csv;
import excel.Excel;
import model.ProgramProducts;
import model.Programs;
import model.Providers;
import mongo.MongoConnection;
import mongo.Query;

/**
 * select *
 * from t_product_single 
 * where rental = '2147483647' and license_end > now() limit 1;
 * 
 * mysql t_product_single.product_id == mongo program.products._id
 * 
 * Rental period 가 영구 소장일경우를 뽑아내는 List
 * */
public class Main {
	private static Properties properties = new Properties();
	private static String mongoIp = "";
	private static String mongoPort = "";
	public static void main(String[] args) {
		String rootPath = System.getProperty("user.dir");
		try {
			File file = new File(rootPath+"/config.properties");
			InputStream is = new FileInputStream(file);
			
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mongoIp = properties.get("mongo.ip").toString();
		mongoPort = properties.get("mongo.port").toString();
		
		System.out.println("mongo target >> "+mongoIp + ":" + mongoPort);
		
		process();
	}
	
	public static void process() {
		MongoConnection mongoConnection = new MongoConnection(mongoIp,mongoPort);

		Excel excel = new Excel();
		Csv csv = new Csv();
		
		DBCollection programProductsCollection = mongoConnection.getCollection("program.products");
		DBCursor programProductsCursor = programProductsCollection.find(Query.getRentalDurationQuery());
		
		int programProductCount = programProductsCursor.count();
		System.out.println("Program Count "+programProductCount);
		
		int index = 0;
		int printIndex = programProductCount / 20;
		while ( programProductsCursor.hasNext() ) {
			ProgramProducts progPro = new ProgramProducts(programProductsCursor.next());	
			Programs prog = null ;
			Providers prov = null ;
			
			String programId = progPro.getProgramId();
			String providerId = progPro.getProviderId();
			
			if ( programId != null && !programId.isEmpty() ) {
				DBCollection programsCollection = mongoConnection.getCollection("programs");
				DBCursor programsCursor = programsCollection.find(Query.getProgramsById(programId));
				
				prog = new Programs(programsCursor.next());
			}
			
			if ( providerId != null && !providerId.isEmpty() ) {
				DBCollection providersCollection = mongoConnection.getCollection("providers");
				DBCursor providersCursor = providersCollection.find(Query.getProviderById(providerId));
				
				prov = new Providers(providersCursor.next());
			}
			
			excel.addOne(progPro, prog, prov);
			csv.addOne(progPro, prog, prov);
			
			index ++ ;
			
			if ( index > 5 )
				break;
			
			if ( index % printIndex == 0 ) 
				System.out.println(index + " / " + programProductCount );
		}
		System.out.println(programProductCount + " / " + programProductCount);
		
		System.out.println("write excel");
		excel.fileWrite();
		System.out.println("write excel finish");
		
		System.out.println("write csv");
		csv.fileWriteUTF8();
		csv.fileWriteEUCKR();
		System.out.println("write csv finish");
		
		System.out.println("program shutdown");
	}

}






