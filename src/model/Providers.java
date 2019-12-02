package model;

import java.util.List;

import com.mongodb.DBObject;

public class Providers {
	private String pid = "";
	private String cclass = "";
	private String siteUrl = "";
	private String name = "";
	
	public Providers() {}
	
	public Providers(DBObject obj) {
		if ( obj != null ) {
			this.pid 		= obj.get("pid") != null ? obj.get("pid").toString() : "" ;
			this.cclass 	= obj.get("cclass") != null ? obj.get("cclass").toString() : "" ;
			this.siteUrl 	= obj.get("siteurl") != null ? obj.get("siteurl").toString() : "" ;
			
			if ( obj.get("name") != null ) {
				List<DBObject> name = (List<DBObject>)obj.get("name");
				
				for ( DBObject o : name ) {
					if ( o != null ) {
						String text = o.get("text").toString();
						this.name += text ;
					}
				}
			}
		}
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCclass() {
		return cclass;
	}

	public void setCclass(String cclass) {
		this.cclass = cclass;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
