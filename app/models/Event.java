package models;


import java.util.List;

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
	
	public Event(String uid, double lng, double lat, String ev) {
		this.uid = uid;
		this.latitude = lat;
		this.longitude = lng;
		this.eventat = ev;
	}
	
	public String toString() {
		return latitude+":"+longitude+" "+eventat;
	}
	
    /**
     * Generic query helper for entity Event with id Long
     */
    public static Model.Finder<Long,Event> find = new Model.Finder<Long,Event>(Long.class, Event.class);
	
    public static List<Event> getEvents(String uid) {
    	List<Event> events = find.where().eq("uid", uid).findList();
    	return events;
    }

}
