package ai;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import play.Logger;

public class TimeStampedLocation {
	
	private static double r = 6378.7;
	private double latitude;
	private double longitude;
	private double latitudeRad;
	private double longitudeRad;
	private String dateTimeStamp;
	private Calendar dateTime = Calendar.getInstance();
	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS-07:00");
	//2012-08-30T05:17:57.547-07:00
	//the timezone offset thing is a hack
	
	public TimeStampedLocation () {
		
	}
	
	public TimeStampedLocation (double lat, double lon, String ts) {
		setLatitude(lat);
		setLongitude(lon);
		setDateTimeStamp(ts);
	}
	
	public TimeStampedLocation (double lat, double lon) {
		setLatitude(lat);
		setLongitude(lon);
		this.dateTimeStamp = formatter.format(dateTime.getTime());
	}
	
	public int distance (double lat, double lon) {
		// Calc distance between lat and lon using Haversine Formula
		double latRad = Math.toRadians(lat);
		double lonRad = Math.toRadians(lon);
		double dLon = lonRad - longitudeRad;
		double dLat = latRad - latitudeRad;
		double a = Math.pow((Math.sin(dLat/2.0)),2.0) + Math.cos(latitudeRad) * Math.cos(latRad) * Math.pow((Math.sin(dLon/2.0)),2.0);
		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		int d = (int)(r * c * 1000);
		return d;
	}
	
	public int distance (TimeStampedLocation location) {
		return distance(location.latitude, location.longitude);
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
		this.latitudeRad = Math.toRadians(latitude);
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
		this.longitudeRad = Math.toRadians(longitude);
	}
	
	public String getDateTimeStamp() {
		return dateTimeStamp;
	}

	public void setDateTimeStamp(String dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
		parseDate(dateTimeStamp);
	}
	
	public Calendar getDateTime() {
		return dateTime;
	}
	
	private void parseDate (String dateTimeStamp) {
		try {
			//Logger.info(dateTimeStamp);
			this.dateTime.setTime((Date)formatter.parse(dateTimeStamp));
			//this.dateTime = Calendar.getInstace().setDate((Date)formatter.parse(dateTimeStamp));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
