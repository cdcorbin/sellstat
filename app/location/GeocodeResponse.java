package location;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import location.GeocodeResults;

public class GeocodeResponse {
	public GeocodeResults[] results;
	public String status;
	public GeocodeResponse () {}
}
