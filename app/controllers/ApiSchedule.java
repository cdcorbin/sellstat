package controllers;

import models.Schedule;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import static play.libs.Json.toJson;

public class ApiSchedule extends Controller {


	static void setResponseHeaders() {
		response().setHeader("luckynumber", "999");
	}

	/**
	 * 
	 * GET /api/schedules/:uri
	 * 
	 * @return
	 */
	public static Result byUri(String uri) {
		Logger.info("ApiSchedule:byUri uri:"+uri);
		Schedule schedule = Schedule.findByUri(uri);
		if (null != schedule) {
			setResponseHeaders();
			return ok(toJson(schedule));
		} else {
			return status(404, "not found");
		}
	}

	/**
	 * 
	 * POST /api/schedules/:uri
	 * 
	 * @return
	 */
	public static Result apply(String uri) {
		Logger.info("ApiSchedule uri:" + uri);
		Schedule schedule = Schedule.findByUri(uri);
		if (null != schedule) {
			Logger.info("updated schedule:" + uri);
			setResponseHeaders();
			return status(200);
		} else {
			return status(404, "not found");
		}
	}

}
