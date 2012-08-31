package models;

import java.util.List;

import com.google.common.collect.Lists;

public class Schedule {
	public String deviceId;
	public String locationId;
	public String programId;
	public List<Program> programs;
	
	public Schedule(String deviceId, String locationId, String programId) {
		this.deviceId = deviceId;
		this.locationId = locationId;
		this.programId = programId;
		this.programs = Lists.newArrayList();
		programs.add(new Program());
		programs.add(new Program());
		programs.add(new Program());
		programs.add(new Program());
		programs.add(new Program());
		programs.add(new Program());
		programs.add(new Program());
	}
	
	public Schedule () {
		this.programs = Lists.newArrayList();
	}
	
	public static Schedule findByUri(String uri) {
		return new Schedule(uri, "1", "1");
	}
	
	public void addProgram (Program program) {
		this.programs.add(program);
	}
	
	public List<Program> getPrograms () {
		return this.programs;
	}
	
}
