package location;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import play.libs.Json;

public class Geocode {
	
	public GeocodeResponse response = null;
	public Address address;
	public String userAgent = "Mozilla";
	public static String STREETADDRESS = "address";
	public static String REGION = "region";
	public static String SENSOR = "sensor";
	public static String APIURL = "http://maps.googleapis.com/maps/api/geocode/json";
	
	public Geocode () {

	}

	public void getGeocodeResponse () {
		
		Properties properties = addressToProperties();
		String query = Util.makeQueryString(properties);
		URL url;
		
		try {
			
			url = new URL(APIURL+"?"+query);
			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		    
		    switch (urlc.getResponseCode()) {
			    case HttpURLConnection.HTTP_OK :
			    	handleGeocodeSuccess(urlc);
			    	break;
			    default :
			    	handleGeocodeFailure(urlc);
		    }
		    

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void handleGeocodeSuccess (HttpURLConnection urlc) throws IOException {
		StringBuffer buffer = Util.getStringBufferFromResponse(urlc);
		response = Json.fromJson(Json.parse(buffer.toString()), GeocodeResponse.class);
	}
	
	private void handleGeocodeFailure (HttpURLConnection urlc) {
		// awwww shiiiit
	}
	
	private Properties addressToProperties () {
		Properties properties = new Properties();
		properties.setProperty(STREETADDRESS, address.streetAddress + ", " + address.cityState + ", " + address.postalCode);
		properties.setProperty(REGION, address.region);
		properties.setProperty(SENSOR, "false");
		return properties;
	}
	
	public static void main (String[] args) {
		Geocode geocode = new Geocode();
		Address address = new Address();
		address.streetAddress = "623 Hartford Drive";
		address.cityState = "Boulder, CO";
		address.postalCode = "80305";
		address.region = "US";
		geocode.address = address;
		geocode.getGeocodeResponse();
	}
	
}
