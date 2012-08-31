package models;

public class ProgramSegment {
	
	public double heatingSetPoint;
	public double coolingSetPoint;
	public String timeOfDay;
	public String name;
	
	public ProgramSegment () {
		
	}

	public double getHeatingSetPoint() {
		return heatingSetPoint;
	}

	public void setHeatingSetPoint(double heatingSetPoint) {
		this.heatingSetPoint = heatingSetPoint;
	}

	public double getCoolingSetPoint() {
		return coolingSetPoint;
	}

	public void setCoolingSetPoint(double coolingSetPoint) {
		this.coolingSetPoint = coolingSetPoint;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
