package ai;

import java.util.Calendar;

public final class Bucketer {
	
	public static Bucket bucket (Calendar calendar, int distance) {
		
		double seconds = Math.floor((double)calendar.get(Calendar.SECOND)/60.0)*60.0;
		calendar.set(Calendar.SECOND, (int) seconds);
		double minutes = Math.floor((double)calendar.get(Calendar.MINUTE)/30.0)*30.0;
		calendar.set(Calendar.MINUTE, (int) minutes);
		Bucket bucket = new Bucket();
		bucket.setDay(calendar.get(Calendar.DAY_OF_WEEK));
		int absoluteMinute = calendar.get(Calendar.MINUTE) + calendar.get(Calendar.HOUR_OF_DAY)*60; 
		bucket.setMinute(absoluteMinute);
		bucket.setDistance(distance);
		return bucket;
		
	}

}
