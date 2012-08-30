package models;


public class ProgramSegment {
	public double heatingSetPoint;
	public double coolingSetPoint;
	public String timeOfDay;
	public String name;
	
	public ProgramSegment(double hsp, double csp, String tod, String name) {
		this.heatingSetPoint = hsp;
		this.coolingSetPoint = csp;
		this.timeOfDay = tod;
		this.name = name;
	}
}
