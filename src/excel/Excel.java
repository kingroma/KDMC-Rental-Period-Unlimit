package excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.ProgramProducts;
import model.Programs;
import model.Providers;

public class Excel {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfFile = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	// 워크북 생성 
	public XSSFWorkbook workbook = null ; 
    // 워크시트 생성
    public XSSFSheet sheet = null ; 
    
	private int rowIndex = 0 ;

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
	public Excel () {
		this.workbook = new XSSFWorkbook();
    	this.sheet = workbook.createSheet();
    	
    	for ( int i = 0 ; i < width.length ; i ++) {
    		this.sheet.setColumnWidth(i,  (int)(width[i] * this.sheet.getColumnWidth(i)));
    	}
    	addHeader();
    	
	}
	
	
	public void addHeader() {
		XSSFRow row = sheet.createRow(rowIndex);
		
		int cellIndex = 0 ;
		for ( String h : headers ) {
			XSSFCell cell = row.createCell(cellIndex);
			cell.setCellValue(h);
			cellIndex ++ ; 
		}
		
		rowIndex ++ ;
	}
	
	public void addOne(ProgramProducts pp , Programs prog , Providers prov ) {
		XSSFRow row = sheet.createRow(rowIndex);
		int cellIndex = 0 ;

//		"상품아이디", 				"판권", 			"컨텐츠 아이디" , 		"에셋 아이디" ,
		XSSFCell cell0 = row.createCell(cellIndex);
		cell0.setCellValue(pp.getProductId());
		cellIndex ++ ;
		
		XSSFCell cell1 = row.createCell(cellIndex);
		cell1.setCellValue(pp.getCclass());
		cellIndex ++ ;

		XSSFCell cell2 = row.createCell(cellIndex);
		cell2.setCellValue(pp.getCid());
		cellIndex ++ ;
		
		XSSFCell cell3 = row.createCell(cellIndex);
		cell3.setCellValue(pp.getServid());
		cellIndex ++ ;
		
		
		XSSFCell cell17 = row.createCell(cellIndex);
		try {
			cell17.setCellValue(sdf.format(prog.getProductionDate()));
		} catch (Exception e) {e.printStackTrace();}
		cellIndex ++ ;
		
		XSSFCell cell49 = row.createCell(cellIndex);
		cell49.setCellValue(prog.getOpenYear());
		cellIndex ++ ;
		
//		"컨텐츠 포멧",				"라이센스 시작일시",	"라이센스 종료일시"	, 	"타이틀" ,
		XSSFCell cell4 = row.createCell(cellIndex);
		cell4.setCellValue(pp.getContentFormat());
		cellIndex ++ ;
		
		XSSFCell cell5 = row.createCell(cellIndex);
		try {
			cell5.setCellValue(sdf.format(pp.getLicenseStart()));
		} catch (Exception e) {e.printStackTrace();}
		cellIndex ++ ;
		
		XSSFCell cell6 = row.createCell(cellIndex);
		try {
			cell6.setCellValue(sdf.format(pp.getLiecnseEnd()));
		} catch (Exception e) {e.printStackTrace();}
		cellIndex ++ ;
		
		XSSFCell cell7 = row.createCell(cellIndex);
		cell7.setCellValue(pp.getTitle());
		cellIndex ++ ;
		
//		"요약 내용",				"빌링 아이디" , 	"Preview period",	"Rental Period" ,
		XSSFCell cell8 = row.createCell(cellIndex);
		cell8.setCellValue(prog.getSynopsis());
		cellIndex ++ ;
		
		XSSFCell cell9 = row.createCell(cellIndex);
		cell9.setCellValue(pp.getPcode());
		cellIndex ++ ;
		
		XSSFCell cell10 = row.createCell(cellIndex);
		cell10.setCellValue(pp.getPreviewPeriod());
		cellIndex ++ ;
		
		XSSFCell cell11 = row.createCell(cellIndex);
		cell11.setCellValue(pp.getRentalPeriod());
		cellIndex ++ ;
		
		
		
//		"Currency",				"가격",			"할인율"	,			"parentalRating" ,
		XSSFCell cell12 = row.createCell(cellIndex);
		cell12.setCellValue(pp.getCurrency());
		cellIndex ++ ;
		
		XSSFCell cell13 = row.createCell(cellIndex);
		cell13.setCellValue(pp.getPrice());
		cellIndex ++ ;
		
		XSSFCell cell14 = row.createCell(cellIndex);
		cell14.setCellValue(pp.getDiscountRate());
		cellIndex ++ ;
		
		XSSFCell cell15 = row.createCell(cellIndex);
		cell15.setCellValue(pp.getParentalRating());
		cellIndex ++ ;
		
		// "locator",				"duration",		"encryption",		"Licensing_Window_Start" ,
		XSSFCell cell45 = row.createCell(cellIndex);
		cell45.setCellValue(pp.getLocator());
		cellIndex ++ ;
		
		XSSFCell cell46 = row.createCell(cellIndex);
		cell46.setCellValue(pp.getDuration());
		cellIndex ++ ;
		
		XSSFCell cell47 = row.createCell(cellIndex);
		cell47.setCellValue(pp.getEncryption());
		cellIndex ++ ;
		
		XSSFCell cell48 = row.createCell(cellIndex);
		cell48.setCellValue(pp.getLicesingWindowStart());
		cellIndex ++ ;
		
		XSSFCell cell32 = row.createCell(cellIndex);
		cell32.setCellValue(pp.getLicensingWindowEnd());
		cellIndex ++ ;
		
		
//		"production_country",	"production_date", 	"genres" , 		"directors_text" ,
		XSSFCell cell16 = row.createCell(cellIndex);
		cell16.setCellValue(prog.getProductionCountry());
		cellIndex ++ ;
		
		
		
		XSSFCell cell18 = row.createCell(cellIndex);
		cell18.setCellValue(prog.getGenres());
		cellIndex ++ ;
		
		XSSFCell cell19 = row.createCell(cellIndex);
		cell19.setCellValue(prog.getDirectorsText());
		cellIndex ++ ;
		
//		"배우" , 					"likes" , 			"dislikes" , 	"reviews" ,
		XSSFCell cell20 = row.createCell(cellIndex);
		cell20.setCellValue(prog.getActorsText());
		cellIndex ++ ;

		XSSFCell cell21 = row.createCell(cellIndex);
		cell21.setCellValue(prog.getLikes());
		cellIndex ++ ;
		
		XSSFCell cell22 = row.createCell(cellIndex);
		cell22.setCellValue(prog.getDislikes());
		cellIndex ++ ;
		
		XSSFCell cell23 = row.createCell(cellIndex);
		cell23.setCellValue(prog.getReviews());
		cellIndex ++ ;
		
//		"ratingTotal"      , 		"ratingReview" , 	"comment" , 	"score" ,
		XSSFCell cell24 = row.createCell(cellIndex);
		cell24.setCellValue(prog.getRatingTotal());
		cellIndex ++ ;

		XSSFCell cell25 = row.createCell(cellIndex);
		cell25.setCellValue(prog.getRatingReview());
		cellIndex ++ ;
		
		XSSFCell cell26 = row.createCell(cellIndex);
		cell26.setCellValue(prog.getComment());
		cellIndex ++ ;
		
		XSSFCell cell27 = row.createCell(cellIndex);
		cell27.setCellValue(prog.getScore());
		cellIndex ++ ;
	
		
		
//		"productType" , 		"contentType" ,  	"제공자" , 		"사이트 URL" ,
		XSSFCell cell28 = row.createCell(cellIndex);
		cell28.setCellValue(prog.getProductType());
		cellIndex ++ ;

		XSSFCell cell29 = row.createCell(cellIndex);
		cell29.setCellValue(prog.getContentType());
		cellIndex ++ ;
		
		XSSFCell cell30 = row.createCell(cellIndex);
		cell30.setCellValue(prov.getPid());
		cellIndex ++ ;
		
		XSSFCell cell31 = row.createCell(cellIndex);
		cell31.setCellValue(prov.getSiteUrl());
		cellIndex ++ ;
		
//		 "카테고리",		"에피소드 아이디",		"스튜디오" ,
		
		
		XSSFCell cell33 = row.createCell(cellIndex);
		cell33.setCellValue(pp.getCategory());
		cellIndex ++ ;
		
		XSSFCell cell34 = row.createCell(cellIndex);
		cell34.setCellValue(pp.getEpisodeId());
		cellIndex ++ ;
		
		XSSFCell cell35 = row.createCell(cellIndex);
		cell35.setCellValue(pp.getStudio());
		cellIndex ++ ;
		
		
//		"스튜디오 이름",			"LongTail_YN",	"Creation_Date",	"Copy_Protection" ,
		XSSFCell cell36 = row.createCell(cellIndex);
		cell36.setCellValue(pp.getStudioName());
		cellIndex ++ ;
		
		XSSFCell cell37 = row.createCell(cellIndex);
		cell37.setCellValue(pp.getLongTailYn());
		cellIndex ++ ;
		
		XSSFCell cell38 = row.createCell(cellIndex);
		cell38.setCellValue(pp.getCreationDate());
		cellIndex ++ ;
		
		XSSFCell cell39 = row.createCell(cellIndex);
		cell39.setCellValue(pp.getCopyProtection());
		cellIndex ++ ;
		
//		"ThreeDim_Content_Indicator", "Screen_Format" , "Audio_type" , "licenseTime" ,
		XSSFCell cell40 = row.createCell(cellIndex);
		cell40.setCellValue(pp.getThreeDimContentIndicator());
		cellIndex ++ ;
		
		XSSFCell cell41 = row.createCell(cellIndex);
		cell41.setCellValue(pp.getScreenFormat());
		cellIndex ++ ;
		
		XSSFCell cell42 = row.createCell(cellIndex);
		cell42.setCellValue(pp.getAudioType());
		cellIndex ++ ;
		
		XSSFCell cell43 = row.createCell(cellIndex);
		try {
			cell43.setCellValue(sdf.format(pp.getLicenseTime()));
		} catch (Exception e) {e.printStackTrace();}
		cellIndex ++ ;
		
//		"구매"
		XSSFCell cell44 = row.createCell(cellIndex);
		cell44.setCellValue(pp.getPurchases());
		cellIndex ++ ;
		
		
		rowIndex ++ ;
	}
	
	
	public void fileWrite() {
		String dateFormat = "";
		
		try {
			dateFormat = sdfFile.format(new Date());
		} catch (Exception e) {
		}
		
		String fileName = "./contents_list_"+dateFormat+".xlsx";
		File file = new File(fileName);
        OutputStream os = null ; 
        try {
            // fos = new FileOutputStream(file);
        	// FileOutputStream fos = null;
        	// OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
        	os = new FileOutputStream(file); 
            workbook.write(os);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // if(workbook!=null) workbook.();
                // if(fos!=null) fos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
