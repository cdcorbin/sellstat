package jobs;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import ai.LocationBasedScheduleCreator;
import ai.TimeStampedLocation;

import play.libs.Json;
import play.Logger;
import tnop.ScheduleWriter;
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
		schedule.savings = calcSavings();
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
			// resend dummy preset
			createPresetSchedule(uri);
			Logger.info("Updated:"+modeledSchedule.json);
		}
	}
	
	public static String calcSavings() {
		return "$34.60";
	}
	
	/**
	 * Create a dummy preset, update tstat, save in DB
	 * @param uid
	 */
	public static void createPresetSchedule(String uid) {
		Schedule schedule = new Schedule(uid);
		JsonNode node = Json.toJson(schedule);
		ModeledSchedule modeledSchedule = ModeledSchedule.get(uid+"-old");
		if (null == modeledSchedule) {
			modeledSchedule = new ModeledSchedule(uid+"-old", node.toString());
			modeledSchedule.save();
		}
		writeToTNOP(modeledSchedule);
		Logger.info("Created preset schedule for uid:"+uid);
	}
	
	public static Schedule model(List<Event> events, TimeStampedLocation home) {
		
		LocationBasedScheduleCreator creator = new LocationBasedScheduleCreator();
		creator.setEvents(events);
		creator.setHome(home);
		return creator.createSchedule();
	}
	private static void writeToTNOP(ModeledSchedule modeledSchedule) {
		Schedule schedule = Json.fromJson(Json.parse(modeledSchedule.json),
				Schedule.class);
		ScheduleWriter writer = new ScheduleWriter();
		writer.setSchedule(schedule);
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
