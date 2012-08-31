package location;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import play.libs.Json;

public class Altitude {

	public Altitude response = null;
	public static String LOCATIONS = "locations";
	public static String SENSOR = "sensor";
	public static String APIURL = "http://maps.googleapis.com/maps/api/elevation/json";
	public static Json json;
	public Location location;
	
	public Altitude () {
		
	}

	public void getAltitudeResponse () {
		
		Properties properties = addressToProperties();
		String query = Util.makeQueryString(properties);
		URL url;
		
		try {
			
			url = new URL(APIURL+"?"+query);
			System.out.println(url);
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		    
		    switch (urlc.getResponseCode()) {
			    case HttpURLConnection.HTTP_OK :
			    	handleAltitudeSuccess(urlc);
			    	break;
			    default :
			    	handleAltitudeFailure(urlc);
		    }
		    

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void handleAltitudeSuccess (HttpURLConnection urlc) throws IOException {
		StringBuffer buffer = Util.getStringBufferFromResponse(urlc);
		System.out.println(buffer);
		//response = json.parse(buffer.toString());
		//json.
	}
	
	private void handleAltitudeFailure (HttpURLConnection urlc) {
		
	}
	
	private Properties addressToProperties () {
		Properties properties = new Properties();
		properties.setProperty(LOCATIONS, location.lat + "," + location.lng);
		properties.setProperty(SENSOR, "false");
		return properties;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
}

//http://maps.googleapis.com/maps/api/elevation/json?locations=39.7391536,-104.9847034&sensor=true_or_false
