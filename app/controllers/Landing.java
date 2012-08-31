package controllers;

import jobs.EventHole;
import jobs.ScheduleKicker;
import play.mvc.*;

import views.html.*;

public class Landing extends Controller {
  
  public static Result index() {
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