package jobs;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import play.libs.Json;
import play.Logger;
import models.Event;
import models.ModeledSchedule;
import models.Schedule;


public class ScheduleKicker {
	
	public static void refresh(String uri) {
		List<Event> events = Event.getEvents(uri);
		Schedule schedule = model(events);
		JsonNode node = Json.toJson(schedule);
		// upsert
		ModeledSchedule modeledSchedule = ModeledSchedule.get(uri);
		if (null == modeledSchedule) {
			modeledSchedule = new ModeledSchedule(uri, node.toString());
			modeledSchedule.save();
			Logger.info("Created:"+modeledSchedule.json);
		} else {
			modeledSchedule.json = node.toString();
			modeledSchedule.update();
			Logger.info("Updated:"+modeledSchedule.json);
		}
	}
	
	public static Schedule model(List<Event> events) {
		return new Schedule("1","1","1");
	}
}
