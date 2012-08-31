package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

// 2012-08-20T14:48:56.923-07:00
@SuppressWarnings("serial")
@Entity 
public class ModeledSchedule extends Model {
    @Id
    public Long id;
    public String uid;
    public String json;
    
	
	public ModeledSchedule(String uid, String json) {
		this.uid = uid;
		this.json = json;
	}
	
	public String toString() {
		return uid+":"+json;
	}
	
    /**
     * Generic query helper for entity Event with id Long
     */
    public static Model.Finder<Long,ModeledSchedule> find = new Model.Finder<Long,ModeledSchedule>(Long.class, ModeledSchedule.class);
	
    public static ModeledSchedule get(String uid) {
    	return find.where().eq("uid", uid).findUnique();
    }

}
