package controllers;

import jobs.ConnectActor;

import models.ModeledSchedule;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Akka;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.Json;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.dispatch.Future;
import akka.pattern.Patterns;
import akka.util.Duration;
import akka.util.Timeout;

public class ApiSchedule extends Controller {


	static void setResponseHeaders() {
		response().setHeader("luckynumber", "999");
		response().setHeader("Access-Control-Allow-Origin", "*");
	}

	/**
	 * 
	 * GET /api/schedules/:uri
	 * 
	 * @return
	 */
	public static Result oldByUri(String uri) {
		String uid = uri+"-old";
		Logger.info("ApiSchedule:oldByUri uid:"+uid);
		ModeledSchedule schedule = ModeledSchedule.get(uid);
		if (null != schedule) {
			setResponseHeaders();
			return ok(Json.parse(schedule.json));
		} else {
			return status(404, "not found");
		}
	}

	/**
	 * 
	 * GET /api/schedules/:uri
	 * 
	 * @return
	 */
	public static Result byUri(String uri) {
		Logger.info("ApiSchedule:byUri uri:"+uri);
		ModeledSchedule schedule = ModeledSchedule.get(uri);
		if (null != schedule) {
			setResponseHeaders();
			return ok(Json.parse(schedule.json));
		} else {
			return status(404, "not found");
		}
	}

	public static Result go() {
		setResponseHeaders();
		return status(201);
	}
	
	/**
	 * 
	 * POST /api/schedules/:uri
	 * 
	 * @return
	 */
	public static Result apply(String uri) {
		Logger.info("ApiSchedule uri:" + uri);
		ModeledSchedule schedule = ModeledSchedule.get(uri);
		if (null != schedule) {
			Logger.info("updated schedule:" + uri);
			ActorRef worker = Akka.system().actorOf(new Props(ConnectActor.class));
			Future<Object> f = Patterns.ask(worker, schedule, new Timeout(Duration.parse("10 seconds")));
			Promise<Object> p = Akka.asPromise(f);
			setResponseHeaders();
			return async(p.map(new Function<Object, Result>() {
				public Result apply(Object response) {
					Logger.info("Got response:"+response.toString());
					return redirect("/go");
				}
			}));
		} else {
			return status(404, "not found");
		}
	}
	
}
