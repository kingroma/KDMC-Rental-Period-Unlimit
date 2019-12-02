package mongo;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;

public class Query {
	public static BasicDBObject getRentalDurationQuery() {
		BasicDBObject query = new BasicDBObject("basic.rental_period", 2147483647);
		
		return query;
	}
	
	public static BasicDBObject getProgramsById(String id) {
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		
		return query;
	}
	
	public static BasicDBObject getProviderById(String id) {
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
		
		return query;
	}
}
