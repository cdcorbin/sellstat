package jobs;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import ai.LocationBasedScheduleCreator;
import ai.TimeStampedLocation;

import play.libs.Json;
import play.Logger;
import location.Address;
import location.Geocode;
import models.Event;
import models.ModeledSchedule;
import models.Schedule;


public class ScheduleKicker {
	
	public static void refresh(String uri) {
		List<Event> events = Event.getEvents(uri);
		// we'll want to get this from TNOP eventually
		TimeStampedLocation home = getHome();
		Schedule schedule = model(events, home);
		JsonNode node = Json.toJson(schedule);
		// upsert
		ModeledSchedule modeledSchedule = ModeledSchedule.get(uri);
		if (null == modeledSchedule) {
			modeledSchedule = new ModeledSchedule(uri, node.toString());
			modeledSchedule.save();
			// create dummy preset
			createPresetSchedule(uri);
			Logger.info("Created:"+modeledSchedule.json);
		} else {
			modeledSchedule.json = node.toString();
			modeledSchedule.update();
			Logger.info("Updated:"+modeledSchedule.json);
		}
	}
	
	public static void createPresetSchedule(String uid) {
		Schedule schedule = new Schedule(uid);
		JsonNode node = Json.toJson(schedule);
		ModeledSchedule modeledSchedule = new ModeledSchedule(uid+"-old", node.toString());
		modeledSchedule.save();
		Logger.info("Created preset schedule for uid:"+uid);
	}
	
	public static Schedule model(List<Event> events, TimeStampedLocation home) {
		
		LocationBasedScheduleCreator creator = new LocationBasedScheduleCreator();
		creator.setEvents(events);
		creator.setHome(home);
		return creator.createSchedule();
	}
	
	public static TimeStampedLocation getHome () {
		Geocode geocode = new Geocode();
		Address address = new Address();
		address.streetAddress = "623 Hartford Drive";
		address.cityState = "Boulder, CO";
		address.postalCode = "80305";
		address.region = "US";
		geocode.address = address;
		geocode.getGeocodeResponse();
		return new TimeStampedLocation(geocode.response.results[0].geometry.location.lat, geocode.response.results[0].geometry.location.lng);
	}
}
