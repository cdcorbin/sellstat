package location;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import location.Location;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Geometry {
	public Location location;
	public Geometry () {}
}
