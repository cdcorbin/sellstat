package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Landing extends Controller {
  
  public static Result index() {
    return ok(landing.render());
  }
  
}