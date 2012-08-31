package models;

import java.util.List;

import com.google.common.collect.Lists;

public class Program {
	public String dayOfWeek;
	public List<ProgramSegment> segments ;
	
	public Program() {
		this.segments = Lists.newArrayList();
	}

	//"heatingSetPoint":22.222,"coolingSetPoint":24.444,"timeOfDay":"08:00","name":"Wake"
	public Program(String dow) {
		this.dayOfWeek = dow;
		this.segments = Lists.newArrayList();
		if ("Sun".equals(dow) || "Sat".equals(dow)) {
			this.segments.add(new ProgramSegment(23.0, 25.0, "09:00", "Wake"));
			this.segments.add(new ProgramSegment(20.0, 25.0, "12:00", "Away"));
			this.segments.add(new ProgramSegment(23.0, 25.0, "17:30", "Home"));
			this.segments.add(new ProgramSegment(23.0, 25.0, "22:00", "Sleep"));
		} else {
			this.segments.add(new ProgramSegment(23.0, 25.0, "06:00", "Wake"));
			this.segments.add(new ProgramSegment(20.0, 25.0, "09:00", "Away"));
			this.segments.add(new ProgramSegment(23.0, 25.0, "17:30", "Home"));
			this.segments.add(new ProgramSegment(23.0, 25.0, "22:00", "Sleep"));
		}
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public void addSegment(ProgramSegment segment) {
		segments.add(segment);
	}
	
}
