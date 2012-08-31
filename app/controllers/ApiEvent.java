package controllers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonNode;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import models.Event;
import models.Schedule;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.toJson;

public class ApiEvent extends Controller {


	static void setResponseHeaders() {
		response().setHeader("luckynumber", "999");
	}

	/**
	 * 
	 * GET /api/events/:uri
	 * 
	 * @return
	 */
	public static Result get(String uri) {
		Logger.info("ApiEvent:get uri:"+uri);
		if (null != uri) {
			List<Event> events = Event.getEvents(uri);
			setResponseHeaders();
			return ok(toJson(events));
		} else {
			return status(404, "not found");
		}
	}

	/**
	 * 
	 * POST /api/events
	 * 
	 * @return
	 */
	public static Result addPost() {
    	Form<Event> form = form(Event.class).bindFromRequest();
    	Event added = form.get();
		Logger.info("ApiEvent:add event:"+added);
		if (null != added) {
			added.save();
			setResponseHeaders();
			return status(200);
		} else {
			return status(404, "not found");
		}
	}

	/**
	 * 
	 * POST /api/events
	 * 
	 * @return
	 */
	public static Result add() {
		Map<String, String> validParams = validateBody(ImmutableSet.of("uri","lat","lng","evtime"));
		if (null != validParams) {
			double latd = Double.parseDouble(validParams.get("lat"));
			double lngd = Double.parseDouble(validParams.get("lng"));
			Event ev = new Event(validParams.get("uri"), latd, lngd, validParams.get("evtime"));
			ev.save();
			Logger.info("ApiEvent:add event:"+ev);
			setResponseHeaders();
			return status(200);
		}
		return status(401, "bad request");
	}
	
	static Map<String,String> validateBody(Set<String> src) {
		Map<String,String> valid = Maps.newHashMap();
		JsonNode json = request().body().asJson();
		Logger.info("json:"+json);
		for (String key : src) {
			String val = json.findPath(key).getTextValue();
			if (null != val) {
				valid.put(key, val);
			}
		}
		// make sure they are equal
		if (src.size() == valid.size()) {
			return valid;
		}
		return null;
	}	
}
