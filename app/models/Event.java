package models;


import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

// 2012-08-20T14:48:56.923-07:00
@SuppressWarnings("serial")
@Entity 
public class Event extends Model {
    @Id
    public Long id;

    public String uid;
    
	public double latitude;
	
	public double longitude;
	
	public String eventat;
	
	
	public Event(double lat, double lng, String ev) {
		this.uid = "1";
		this.latitude = lat;
		this.longitude = lng;
		this.eventat = ev;
	}
	
	public String toString() {
		return latitude+":"+longitude+" "+eventat;
	}
}
