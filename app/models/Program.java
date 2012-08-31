package models;

import java.util.List;

import com.google.common.collect.Lists;

public class Program {
	public String dayOfWeek;
	public List<ProgramSegment> segments ;
	
	public Program() {
		this.segments = Lists.newArrayList();
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
