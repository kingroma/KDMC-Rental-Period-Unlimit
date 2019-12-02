package excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import model.ProgramProducts;
import model.Programs;
import model.Providers;

public class Csv {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String sep = "||";
	private String line = "\n";
	private int columnSize = -1 ; 
	private StringBuilder sb = new StringBuilder();
	
	private String[] headers = {
			"상품아이디", 				"판권", 			"컨텐츠 아이디" , 		"에셋 아이디" , 
			"제작일" , "오픈 년도" ,
			"컨텐츠 포멧",				"라이센스 시작일시",	"라이센스 종료일시"	, 	"타이틀" , 
			"요약 내용",				"빌링 아이디" , 	"Preview period",	"Rental Period" , 
			"Currency",				"가격",			"할인율"	,			"parentalRating" , 
			"locator",				"duration",		"encryption",		"Licensing_Window_Start" ,
			"Licensing_Window_End" , 
			
			"production_country",	"genres" , 		"directors_text" , 
			"배우" , 					"likes" , 			"dislikes" , 	"reviews" , 
			"ratingTotal" , 		"ratingReview" , 	"comment" , 	"score" , 
			"productType" , 		"contentType" ,  	"제공자" , 		"사이트 URL" , 
			
			"카테고리",		"에피소드 아이디",		"스튜디오" , 
			"스튜디오 이름",			"LongTail_YN",	"Creation_Date",	"Copy_Protection" ,
			"ThreeDim_Content_Indicator", "Screen_Format" , "Audio_type" , "licenseTime" , 
			"구매"
		};
	
	private float[] width = {
			2,2,2,2,
			2,2,
			2,2,2,2,
			2,2,2,2,
			2,2,2,2,
			2,2,2,2,
			2 , 
			
			2,2,2,
			2,2,2,2,
			2,2,2,2,
			2,2,2,2,
			
			2,2,2,
			2,2,2,2,
			2,2,2,2,
			2
		};
	
	private void addHeader() {
		for ( int i = 0 ; i < columnSize ; i ++ ) {
			String h = headers[i];
			sb.append(h);
			
			if ( i != columnSize - 1 ) {
				sb.append(sep);
			}
		}
		sb.append(line);
	}
	
	public Csv () {
		columnSize = headers.length;
		addHeader();
	}
	
	private void sbAppend(String value) {
		sb.append(value);
	}
	
	private void addSep() {
		sb.append(sep);
	}
	
	private void addLine() {
		sb.append(line);
	}
	
	public void addOne(ProgramProducts pp , Programs prog , Providers prov ) {

//		"상품아이디", 				"판권", 			"컨텐츠 아이디" , 		"에셋 아이디" ,
		sbAppend(pp.getProductId());
		addSep();
		
		sbAppend(pp.getCclass());
		addSep();

		sbAppend(pp.getCid());
		addSep();
		
		sbAppend(pp.getServid());
		addSep();
		
		
		try {
			sbAppend(sdf.format(prog.getProductionDate()));
		} catch (Exception e) {e.printStackTrace();}
		addSep() ;
		
		sbAppend(prog.getOpenYear());
		addSep() ;
		
//		"컨텐츠 포멧",				"라이센스 시작일시",	"라이센스 종료일시"	, 	"타이틀" ,
		sbAppend(pp.getContentFormat());
		addSep() ;
		
		try {
			sbAppend(sdf.format(pp.getLicenseStart()));
		} catch (Exception e) {e.printStackTrace();}
		addSep() ;
		
		try {
			sbAppend(sdf.format(pp.getLiecnseEnd()));
		} catch (Exception e) {e.printStackTrace();}
		addSep() ;
		
		sbAppend(pp.getTitle());
		addSep() ;
		
//		"요약 내용",				"빌링 아이디" , 	"Preview period",	"Rental Period" ,
		sbAppend(prog.getSynopsis());
		addSep() ;
		
		sbAppend(pp.getPcode());
		addSep() ;
		
		sbAppend(pp.getPreviewPeriod());
		addSep() ;
		
		sbAppend(pp.getRentalPeriod());
		addSep() ;
		
		
		
//		"Currency",				"가격",			"할인율"	,			"parentalRating" ,
		sbAppend(pp.getCurrency());
		addSep() ;
		
		sbAppend(pp.getPrice());
		addSep() ;
		
		sbAppend(pp.getDiscountRate());
		addSep() ;
		
		sbAppend(pp.getParentalRating());
		addSep() ;
		
		// "locator",				"duration",		"encryption",		"Licensing_Window_Start" ,
		sbAppend(pp.getLocator());
		addSep() ;
		
		sbAppend(pp.getDuration());
		addSep() ;
		
		sbAppend(pp.getEncryption());
		addSep() ;
		
		sbAppend(pp.getLicesingWindowStart());
		addSep() ;
		
		sbAppend(pp.getLicensingWindowEnd());
		addSep() ;
		
		
//		"production_country",	"production_date", 	"genres" , 		"directors_text" ,
		sbAppend(prog.getProductionCountry());
		addSep() ;
		
		
		
		sbAppend(prog.getGenres());
		addSep() ;
		
		sbAppend(prog.getDirectorsText());
		addSep() ;
		
//		"배우" , 					"likes" , 			"dislikes" , 	"reviews" ,
		sbAppend(prog.getActorsText());
		addSep() ;

		sbAppend(prog.getLikes());
		addSep() ;
		
		sbAppend(prog.getDislikes());
		addSep() ;
		
		sbAppend(prog.getReviews());
		addSep() ;
		
//		"ratingTotal"      , 		"ratingReview" , 	"comment" , 	"score" ,
		sbAppend(prog.getRatingTotal());
		addSep() ;

		sbAppend(prog.getRatingReview());
		addSep() ;
		
		sbAppend(prog.getComment());
		addSep() ;
		
		sbAppend(prog.getScore());
		addSep() ;
	
		
		
//		"productType" , 		"contentType" ,  	"제공자" , 		"사이트 URL" ,
		sbAppend(prog.getProductType());
		addSep() ;

		sbAppend(prog.getContentType());
		addSep() ;
		
		sbAppend(prov.getPid());
		addSep() ;
		
		sbAppend(prov.getSiteUrl());
		addSep() ;
		
//		 "카테고리",		"에피소드 아이디",		"스튜디오" ,
		
		
		sbAppend(pp.getCategory());
		addSep() ;
		
		sbAppend(pp.getEpisodeId());
		addSep() ;
		
		sbAppend(pp.getStudio());
		addSep() ;
		
		
//		"스튜디오 이름",			"LongTail_YN",	"Creation_Date",	"Copy_Protection" ,
		sbAppend(pp.getStudioName());
		addSep() ;
		
		sbAppend(pp.getLongTailYn());
		addSep() ;
		
		sbAppend(pp.getCreationDate());
		addSep() ;
		
		sbAppend(pp.getCopyProtection());
		addSep() ;
		
//		"ThreeDim_Content_Indicator", "Screen_Format" , "Audio_type" , "licenseTime" ,
		sbAppend(pp.getThreeDimContentIndicator());
		addSep() ;
		
		sbAppend(pp.getScreenFormat());
		addSep() ;
		
		sbAppend(pp.getAudioType());
		addSep() ;
		
		try {
			sbAppend(sdf.format(pp.getLicenseTime()));
		} catch (Exception e) {e.printStackTrace();}
		addSep() ;
		
//		"구매"
		sbAppend(pp.getPurchases());
		
		addLine();
		
	}
	
	
	public void fileWriteUTF8() {
		String dateFormat = "";
		
		try {
			dateFormat = sdfFile.format(new Date());
		} catch (Exception e) {
		}
		
		String fileName = "./contents_list_"+dateFormat+"_UTF8.csv";
		File file = new File(fileName);
        OutputStream os = null ;
        OutputStreamWriter osw = null ; 
        try {
            // fos = new FileOutputStream(file);
        	// FileOutputStream fos = null;
        	// OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
        	os = new FileOutputStream(file);
        	osw = new OutputStreamWriter(os, "UTF8");
            osw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // if(workbook!=null) workbook.();
                // if(fos!=null) fos.close();
            	osw.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void fileWriteEUCKR() {
		String dateFormat = "";
		
		try {
			dateFormat = sdfFile.format(new Date());
		} catch (Exception e) {
		}
		
		String fileName = "./contents_list_"+dateFormat+"_EUCKR.csv";
		File file = new File(fileName);
        OutputStream os = null ;
        OutputStreamWriter osw = null ; 
        try {
            // fos = new FileOutputStream(file);
        	// FileOutputStream fos = null;
        	// OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
        	os = new FileOutputStream(file);
        	osw = new OutputStreamWriter(os,"EUC-KR");
            osw.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // if(workbook!=null) workbook.();
                // if(fos!=null) fos.close();
            	osw.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
