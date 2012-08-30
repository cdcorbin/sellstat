package controllers;

import jobs.EventHole;
import play.mvc.*;

import views.html.*;

public class Landing extends Controller {
  
  public static Result index() {
//	  EventHole.parse("data.txt");
	  return ok(landing.render());
  }
  
}