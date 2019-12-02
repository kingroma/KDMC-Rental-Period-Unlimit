package mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


public class MongoConnection {
	private String ip = "192.168.10.180";
	private int port = 27017;
	private MongoClient mongoClient = null; 
	private DB db = null ; 
	
	public MongoConnection(){
		try {
			mongoClient = new MongoClient(ip,port);
			db = mongoClient.getDB("windmill");
			System.out.println("start connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MongoConnection(String ip , String port) {
		try {
			this.ip = ip ; 
			this.port = Integer.parseInt(port) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		try {
			mongoClient = new MongoClient(this.ip,this.port);
			db = mongoClient.getDB("windmill");
			System.out.println("start connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DBCollection getCollection(String name) {
		return db.getCollection(name);
	}
	
	/**
	 * basic.rental_period group by 
	 * 
	 * ��� >> 
	 *  0
	 *  172800 2��
	 *	31536000 1�� 
	 *	2147483647 �����  // 5393
	 *	2592000 30��  
	 *	86400 24�� 
	 *	8899200 103�� 
	 *	259200 3��
	 *	8915940 103��
	 *	432000 5��
	 *
	 * 0 
	 * 172800 2��
	 * 31536000 1�� 
	 * 1296000 15��
	 * 2592000 30��
	 * 86400 24��
	 * 8899200 103��
	 * 8915940 103.19��
	 * 604800 7�� 
	 * 2147472000 24855 ��
	 * NumberLong(2147483647)
	 * 432000 5�� 
	 * 
	 * �Ⱓ�� �������� Ȯ���� ��� 
	 * 1. �����ϰ� Ȯ���Ұ�� 
	 * 		db.getCollection('program.products').find({'basic.rental_period':NumberLong(2147483647)})
	 * 
	 * 2. �ڼ��ϰ� Ȯ���Ұ�� // ������ ���ϰ� �߻��� �� ���� 
	 * 		var result = [];
			var cursor = db.getCollection('program.products').find({'basic.rental_period':NumberLong(2147483647)}) ;
			
			while ( cursor.hasNext() ){
				programProduct = cursor.next();
			
				var cid = programProduct.cid ;
				program = db.getCollection('programs').find({'_id':ObjectId(cid)}).next();
			
			
				var r = programProduct ;
				r.detail = program ;
			
				result.push(r)
			}
			
			result

	 * 
	 * 
	 * 
	 * Mysql �� PRODUCT_ID �� Ȯ���� ��� 
	 * db.getCollection('program.products').find({'_id':ObjectId(*PRODUCT_ID*)})
	 * �� ) db.getCollection('program.products').find({'_id':ObjectId('54994bf9d18e3617f592c1ca')}) 
	 * 
	 * Mysql �� CONTENT_ID �� Ȯ���� ���
	 *  db.getCollection('programs').find({'_id':ObjectId(*CONTENT_ID*)})
	 * �� ) db.getCollection('programs').find({'_id':ObjectId('54994bf9d18e3617f592c1c6')})
	 * 
	 * 
	 * */
	/*
	public void test1() {
		Set<String> set = new HashSet<String>();
		MongoCollection<Document> collection = db.getCollection("program.products");
		
		DBCursor cursor = collection.find();
		
		System.out.println("start test1");
		int count = 0 ;
		int all = cursor.count();
		while ( cursor.hasNext() ) {
			DBObject obj = cursor.next();
			DBObject basic = (DBObject)obj.get("basic");
			
			if ( basic != null ) {
				String rental_period = basic.get("rental_period").toString();
				if ( rental_period != null) {
					set.add(rental_period);					
				}
			}
			count ++ ;
			
			if ( count % 1000 == 0 )
				System.out.println(count + " / " + all);
		}
		
		for ( String s : set) {
			System.out.println(s);
		}
		
	}
	*/
	
}
