package model;

import java.util.Date;
import java.util.List;

import com.mongodb.DBObject;

public class Programs {
	private String synopsis = ""; // synopsis.text     array
	
	private String productionCountry = ""; // production_country    
	private Date productionDate = null; // production_date     
	private String genres = ""; // genres   array
	
	private String directorsText = ""; // directors_text
	private String actorsText = ""; // actors_text
	
	private String runTime = ""; // config.parameter.name == Runt_Time
	private String producer = ""; // config.parameter.name == Producer 
	
	private String likes = ""; // rates.likes 
	private String dislikes = ""; // reates.dislikes
	private String reviews = ""; // rates.reviews
	private String ratingTotal = ""; // rates.rating_total
	private String ratingReview = ""; // rates.rating_review
	
	private String comment = ""; // comment
	private String score = ""; // score;
	private String openYear = ""; //open_year
	
	private String productType = ""; // productType 
	private String contentType = ""; // contentType 
	
	public Programs() {
		
	}
	
	public Programs(DBObject obj) {
		try {
			if ( obj != null ) {
				if ( obj.get("synopsis") != null ) {
					List<DBObject> synopsis = (List<DBObject>)obj.get("synopsis");
					for ( DBObject o : synopsis ) {
						if ( o.get("text") != null ) {
							String text = o.get("text").toString();
							this.synopsis += text;
						}
					}
				}
				
				this.productionCountry =	obj.get("production_country") != null ? obj.get("production_country").toString() : "" ; 
				this.productionDate = 		obj.get("production_date") != null ? (Date)obj.get("production_date") : null ;
				
				if ( obj.get("genres") != null ) {
					List<String> genres = (List<String>)obj.get("genres");
					for ( String o : genres ) {
						if ( o != null ) {
							String text = o.toString();
							this.genres = text;
						}
					}
				}
				
				this.directorsText =	obj.get("directors_text") != null ? obj.get("directors_text").toString() : "" ;
				this.actorsText =		obj.get("actors_text") != null ? obj.get("actors_text").toString() : "" ;
				
				String runTime = ""; // config.parameter.name == Runt_Time
				String producer = ""; // config.parameter.name == Producer 
				
				DBObject config = (DBObject)obj.get("config");
				if ( config != null && config.get("parameter") != null ) {
					List<DBObject> parameter = (List<DBObject>)config.get("parameter");
					for ( DBObject o : parameter ) {
						if ( o != null ) {
							switch(o.get("name").toString()) {
							case "Run_Time" :
								this.runTime = o.get("value").toString();
							case "Producer" :
								this.producer = o.get("value").toString();
							default :
								break;
							}
						}
					}
				}
				
				DBObject rates = (DBObject)obj.get("rates");
				if ( rates != null ) {
					this.likes =	rates.get("likes") != null ? rates.get("likes").toString() : "" ;
					this.dislikes =	rates.get("dislikes") != null ? rates.get("dislikes").toString() : "" ;
					this.reviews =	rates.get("reviews") != null ? rates.get("reviews").toString() : "" ;
					this.ratingTotal =	rates.get("rating_total") != null ? rates.get("rating_total").toString() : "" ;
					this.ratingReview =	rates.get("rating_review") != null ? rates.get("rating_review").toString() : "" ;
				}
				
				this.comment =		obj.get("comment") != null ? obj.get("comment").toString() : "" ;
				this.score =		obj.get("score") != null ? obj.get("score").toString() : "" ;
				
				this.productType =		obj.get("product_type") != null ? obj.get("product_type").toString() : "" ;
				this.contentType =		obj.get("content_type") != null ? obj.get("content_type").toString() : "" ;
				this.openYear =			obj.get("open_year") != null ? obj.get("open_year").toString() : "" ;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getProductionCountry() {
		return productionCountry;
	}

	public void setProductionCountry(String productionCountry) {
		this.productionCountry = productionCountry;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getDirectorsText() {
		return directorsText;
	}

	public void setDirectorsText(String directorsText) {
		this.directorsText = directorsText;
	}

	public String getActorsText() {
		return actorsText;
	}

	public void setActorsText(String actorsText) {
		this.actorsText = actorsText;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public String getDislikes() {
		return dislikes;
	}

	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getRatingTotal() {
		return ratingTotal;
	}

	public void setRatingTotal(String ratingTotal) {
		this.ratingTotal = ratingTotal;
	}

	public String getRatingReview() {
		return ratingReview;
	}

	public void setRatingReview(String ratingReview) {
		this.ratingReview = ratingReview;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getOpenYear() {
		return openYear;
	}

	public void setOpenYear(String openYear) {
		this.openYear = openYear;
	}
	
	
	
}
