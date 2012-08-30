package models;

import java.util.List;

import com.google.common.collect.Lists;

public class Program {
	public String dayOfWeek;
	public List<ProgramSegment> segments;
	
	public Program(String dow) {
		this.dayOfWeek = dow;
		this.segments = Lists.newArrayList();
		segments.add(new ProgramSegment(22.0, 22.0, "08:30", "WakeUp"));
		segments.add(new ProgramSegment(22.0, 22.0, "10:30", "Away"));
		segments.add(new ProgramSegment(22.0, 22.0, "17:30", "Home"));
		segments.add(new ProgramSegment(22.0, 22.0, "22:30", "Sleep"));
	}
}
