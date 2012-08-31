package location;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import location.Geometry;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GeocodeResults {
	public String formatted_address;
	public Geometry geometry;
	public GeocodeResults () {}
}
