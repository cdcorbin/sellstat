package tnop;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import location.Util;
import models.Program;
import models.ProgramSegment;
import models.Schedule;

import play.Logger;

public class ScheduleWriter {

	private String APIURL = "http://192.168.224.206:9080/api/rest/device-action";
	private String userName = "ccorbin@tendrilinc.com";
	private String password = "default";
	private Schedule schedule;
	private String locationId = "1";
	private String deviceId = "001db70000030169";
	
	public static void main (String[] args) {
		ScheduleWriter gms = new ScheduleWriter();
		try {
			gms.writeToTNOP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToTNOP () throws Exception {
		
		HttpURLConnection urlc;
		
		try {
			
			String query = buildScheduleString();
			URL url = new URL(APIURL);
			urlc = (HttpURLConnection) url.openConnection();
			String userPassword = userName + ":" + password;
			urlc.addRequestProperty("Emsauthtoken", userPassword);
			String contentType = "application/xml";
			urlc.addRequestProperty("Accept", contentType);
			urlc.addRequestProperty("Content-Type", contentType);
			urlc.setRequestProperty("Content-Length",Integer.toString(query.length()) );
		    urlc.setRequestMethod("POST"); 
		    urlc.setDoInput(true); 
		    urlc.setDoOutput(true);

		    OutputStream output = urlc.getOutputStream();  
		    output.write(query.getBytes());
		    
		    switch (urlc.getResponseCode()) {
		    
			    case HttpsURLConnection.HTTP_OK :
			    case HttpsURLConnection.HTTP_CREATED :
			    	handleSuccess(urlc);
			    	break;
			    default :
			    	handleFailure(urlc);
		    }
	    
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String buildScheduleString () {
		
		String S = "";
		S += "<?xml version='1.0'?>";
		S += "<setThermostatProgramRequest xmlns='http://platform.tendrilinc.com/tnop/extension/ems' deviceId='"+deviceId+"' locationId='"+locationId+"' >";
		S += "<data><thermostatProgram programId='0'>";
		
		List<Program> programs = schedule.getPrograms();
		Program program;
		List<ProgramSegment> segments;
		ProgramSegment segment;
		
		for (int i=0; i<programs.size(); i++) {
			
			program = programs.get(i);
			S += "<programDays dayOfWeek='"+program.getDayOfWeek().toUpperCase()+"'>";
			segments = program.getSegments();
			
			for(int j=0; j<segments.size(); j++) { 
			
				segment = segments.get(j);
				S += "<programSegment><heatingSetPoint>"+segment.getHeatingSetPoint()+"</heatingSetPoint><coolingSetPoint>"+segment.getCoolingSetPoint()+"</coolingSetPoint><timeOfDay>"+segment.getTimeOfDay()+":00</timeOfDay><name>"+segment.getName()+"</name></programSegment>";
			
			}
			
			S += "</programDays>";
		}
		
		S += "</thermostatProgram></data>";
		S += "</setThermostatProgramRequest>";
		return S;
		
	}
	
	private void handleSuccess (HttpURLConnection urlc) throws IOException {
		StringBuffer buffer = Util.getStringBufferFromResponse(urlc);
		Logger.info("Successfully sent schedule to TNOP");
		//System.out.println(buffer.toString());
	}
	
	private void handleFailure (HttpURLConnection urlc) throws IOException {
		StringBuffer buffer = Util.getStringBufferFromResponse(urlc);
		//System.out.println(buffer.toString());
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}