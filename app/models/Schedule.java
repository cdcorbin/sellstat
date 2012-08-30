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
		programs.add(new Program("Sun"));
		programs.add(new Program("Mon"));
		programs.add(new Program("Tue"));
		programs.add(new Program("Wed"));
		programs.add(new Program("Thur"));
		programs.add(new Program("Fri"));
		programs.add(new Program("Sat"));
	}
	
	public static Schedule findByUri(String uri) {
		return new Schedule(uri, "1", "1");
	}
}