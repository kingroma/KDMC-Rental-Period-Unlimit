package model;

import java.util.Date;
import java.util.List;

import com.mongodb.DBObject;

public class ProgramProducts {
	// ��ǰ ���̵� 
	private String productId = "";
	// �Ǳ�
	private String cclass = "" ;
	
	private String pid = "";
	// ����Ʈ ���̵�
	private String cid = "";
	// ���� ���̵�
	private String servid = ""; 

	private String contentFormat = "";  

	private Date licenseStart = null; // license_start
	
	private Date liecnseEnd = null; // license_end 

	private String title = ""; // basic.name

	private String description = ""; // 
	// billing id ���� ���õ� ID �ε�  
	private String pcode = "";
	// preview_period
	private String previewPeriod = ""; // basic.preview_period
	
	private String rentalPeriod = ""; // basic.rental_period
	// �޷��� ��ȭ�� 
	private String currency = ""; // basic.currency

	private String price = ""; // basic.price
	
	private String discountRate = ""; // basic.discountRate 
	
	private String parentalRating = ""; // basic.parentalRating
	// mpg ���� 
	private String locator = "";// location.locator
	
	private String duration = ""; // location.duration 
	// 
	private String encryption = ""; // 

	// ====== config.parameter.name ======
	private String licesingWindowStart = ""; // Licensing_Window_Start
	
	private String licensingWindowEnd = ""; // Licensing_Window_End 
	
	private String category = ""; // Category 
	
	private String episodeId = ""; // Episode_Id
	
	private String studio = ""; // Studio 
	
	private String studioName = ""; // Studio_Name
	
	private String longTailYn = ""; // LongTail_YN
	
	private String creationDate = ""; // Creation_Date
	
	private String copyProtection = ""; // Copy_Protection
	
	private String threeDimContentIndicator = ""; // ThreeDim_Content_Indicator
	
	private String screenFormat = ""; // Screen_Format
	
	private String audioType = ""; // Audio_type
	// ====== config.parameter.name ======
	private Date licenseTime = null ; // indexs.license_time
	
	private String purchases = ""; // indexs.purchases
	// links.type == program
	private String programId = "";
	// links.type == provider
	private String providerId = "";
	
	public ProgramProducts(){
		
	}
	public ProgramProducts(DBObject obj) {
		try {
			if ( obj != null ) {
				this.productId = 		obj.get("_id") != null ? obj.get("_id").toString() : "";
				this.cclass = 			obj.get("cclass") != null ? obj.get("cclass").toString() : "";
				this.pid = 				obj.get("pid") != null ? obj.get("pid").toString() : "";
				this.cid = 				obj.get("cid") != null ? obj.get("cid").toString() : "";
				this.servid = 			obj.get("servid") != null ? obj.get("servid").toString() : "";
				this.contentFormat = 	obj.get("content_format") != null ? obj.get("content_format").toString() : "";
				this.licenseStart = 	obj.get("license_start") != null ? (Date)obj.get("license_start") : null ;
				this.liecnseEnd = 		obj.get("license_end") != null ? (Date)obj.get("license_end") : null ;
				
				//		private String title = ""; // basic.name
				DBObject basic = (DBObject)obj.get("basic");
				if ( basic != null ) {
					this.title = 			basic.get("name") != null ? basic.get("name").toString() : "";
					this.description = 		basic.get("description") != null ? basic.get("description").toString() : "";
					this.pcode = 			basic.get("pcode") != null ? basic.get("pcode").toString() : "";
					this.previewPeriod = 	basic.get("preview_period") != null ? basic.get("preview_period").toString() : "";
					this.rentalPeriod = 	basic.get("rental_period") != null ? basic.get("rental_period").toString() : "";
					this.currency = 		basic.get("currency") != null ? basic.get("currency").toString() : "";
					this.price = 			basic.get("price") != null ? basic.get("price").toString() : "";
					this.discountRate = 	basic.get("discount_rate") != null ? basic.get("discount_rate").toString() : "";
					this.parentalRating = 	basic.get("parental_rating") != null ? basic.get("parental_rating").toString() : "";
				}
				
				DBObject location = (DBObject)obj.get("location");
				if ( location != null ) {
					this.locator = 			location.get("locator") != null ? location.get("locator").toString() : "";
					this.duration = 		location.get("duration") != null ? location.get("duration").toString() : "";
					this.encryption = 		location.get("encryption") != null ? location.get("encryption").toString() : "";
				}
				
				DBObject config = (DBObject)obj.get("config");
				if ( config != null && config.get("parameter") != null ) {
					List<DBObject> parameter = (List<DBObject>)config.get("parameter");
					
					for ( DBObject o : parameter ) {
						switch(o.get("name").toString()) {
						case "Licensing_Window_Start" :
							this.licesingWindowStart = 	o.get("value").toString();
							break;
						case "Licensing_Window_End":
							this.licensingWindowEnd = 	o.get("value").toString();
							break;
						case "Category":
							this.category = 			o.get("value").toString();
							break;
						case "Episode_Id":
							this.episodeId = 			o.get("value").toString();
							break;
						case "Studio":
							this.studio = 				o.get("value").toString();
							break;
						case "Studio_Name":
							this.studioName = 			o.get("value").toString();
							break;
						case "LongTail_YN":
							this.longTailYn = 			o.get("value").toString();
							break;
						case "Creation_Date":
							this.creationDate = 		o.get("value").toString();
							break;
						case "Copy_Protection":
							this.copyProtection = 		o.get("value").toString();
							break;
						case "ThreeDim_Content_Indicator":
							this.threeDimContentIndicator = o.get("value").toString();
							break;
						case "Screen_Format":
							this.screenFormat = 		o.get("value").toString();
							break;
						case "Audio_type":
							this.audioType = 			o.get("value").toString();
							break;
						default : 
							break;
						}
					}
				}
				
				DBObject indexs = (DBObject)obj.get("indexs");
				if ( indexs != null ) {
					this.licenseTime = 	indexs.get("license_time") 	!= null ? (Date)indexs.get("license_time") : null ;
					this.purchases = 	indexs.get("purchases")		!= null ? indexs.get("purchases").toString() : "" ;
				}
				
					
				
				List<DBObject> links = (List<DBObject>)obj.get("links");
				if (links != null ) {
					for ( DBObject o : links ) {
						switch(o.get("type").toString()) {
						case "program" :
							this.programId = o.get("_id").toString();
							break;
						case "provider" :
							this.providerId = o.get("_id").toString();
							break;
						default :
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String toString() {
		return null ;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCclass() {
		return cclass;
	}
	public void setCclass(String cclass) {
		this.cclass = cclass;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getServid() {
		return servid;
	}
	public void setServid(String servid) {
		this.servid = servid;
	}
	public String getContentFormat() {
		return contentFormat;
	}
	public void setContentFormat(String contentFormat) {
		this.contentFormat = contentFormat;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPreviewPeriod() {
		return previewPeriod;
	}
	public void setPreviewPeriod(String previewPeriod) {
		this.previewPeriod = previewPeriod;
	}
	public String getRentalPeriod() {
		return rentalPeriod;
	}
	public void setRentalPeriod(String rentalPeriod) {
		this.rentalPeriod = rentalPeriod;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getParentalRating() {
		return parentalRating;
	}
	public void setParentalRating(String parentalRating) {
		this.parentalRating = parentalRating;
	}
	public String getLocator() {
		return locator;
	}
	public void setLocator(String locator) {
		this.locator = locator;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEncryption() {
		return encryption;
	}
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}
	public String getLicesingWindowStart() {
		return licesingWindowStart;
	}
	public void setLicesingWindowStart(String licesingWindowStart) {
		this.licesingWindowStart = licesingWindowStart;
	}
	public String getLicensingWindowEnd() {
		return licensingWindowEnd;
	}
	public void setLicensingWindowEnd(String licensingWindowEnd) {
		this.licensingWindowEnd = licensingWindowEnd;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEpisodeId() {
		return episodeId;
	}
	public void setEpisodeId(String episodeId) {
		this.episodeId = episodeId;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getStudioName() {
		return studioName;
	}
	public void setStudioName(String studioName) {
		this.studioName = studioName;
	}
	public String getLongTailYn() {
		return longTailYn;
	}
	public void setLongTailYn(String longTailYn) {
		this.longTailYn = longTailYn;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getCopyProtection() {
		return copyProtection;
	}
	public void setCopyProtection(String copyProtection) {
		this.copyProtection = copyProtection;
	}
	public String getThreeDimContentIndicator() {
		return threeDimContentIndicator;
	}
	public void setThreeDimContentIndicator(String threeDimContentIndicator) {
		this.threeDimContentIndicator = threeDimContentIndicator;
	}
	public String getScreenFormat() {
		return screenFormat;
	}
	public void setScreenFormat(String screenFormat) {
		this.screenFormat = screenFormat;
	}
	public String getAudioType() {
		return audioType;
	}
	public void setAudioType(String audioType) {
		this.audioType = audioType;
	}
	public String getPurchases() {
		return purchases;
	}
	public void setPurchases(String purchases) {
		this.purchases = purchases;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProviderId() {
		return providerId;
	}
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	public Date getLicenseStart() {
		return licenseStart;
	}
	public void setLicenseStart(Date licenseStart) {
		this.licenseStart = licenseStart;
	}
	public Date getLiecnseEnd() {
		return liecnseEnd;
	}
	public void setLiecnseEnd(Date liecnseEnd) {
		this.liecnseEnd = liecnseEnd;
	}
	public Date getLicenseTime() {
		return licenseTime;
	}
	public void setLicenseTime(Date licenseTime) {
		this.licenseTime = licenseTime;
	}
	
	
}
