package controllers;

import jobs.EventHole;
import jobs.ScheduleKicker;
import play.mvc.*;

import views.html.*;

public class Landing extends Controller {
  
	static void setResponseHeaders() {
		response().setHeader("luckynumber", "999");
		response().setHeader("Access-Control-Allow-Origin", "*");
	}

  public static Result index() {
	  setResponseHeaders();
	  return ok(landing.render());
  }

  public static Result load() {
	  EventHole.parse("data.txt");
	  return ok(landing.render());
  }
  
  public static Result refresh(String uri) {
	  ScheduleKicker.refresh(uri);
	  return ok(landing.render());
  }
  
}