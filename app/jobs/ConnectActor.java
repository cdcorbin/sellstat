package jobs;

import java.util.Map;

import models.ModeledSchedule;
import models.Schedule;

import play.Logger;
import play.Play;
import play.libs.Json;
import play.libs.WS;
import play.libs.F.Promise;
import play.libs.WS.Response;
import play.libs.WS.WSRequestHolder;
import tnop.ScheduleWriter;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
/**
 * @author dlange
 *
 */
public class ConnectActor extends UntypedActor {
	  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	 
		protected String getUser() {
			return Play.application().configuration().getString("tnop.user");
		}
		protected String getPassword() {
			return Play.application().configuration().getString("tnop.password");
		}

	  /**
	   */
	  public void onReceive(Object apiType) throws Exception {
		  log.info("Received apiType: {}", apiType);
		  if (apiType instanceof ModeledSchedule) {
			  ModeledSchedule schedule = (ModeledSchedule)apiType;
			  // response back to trigger
			  sender().tell("ok");
			  Logger.info("ConnectActor:onReceive replied to sender");
			  writeToTNOP(schedule);
			  Logger.info("ConnectActor:onReceive pushed to connect complete");
		  } else {
			  unhandled(apiType);
		  }
	  }
	  
	  
	private static void writeToTNOP(ModeledSchedule modeledSchedule) {
		Schedule schedule = Json.fromJson(Json.parse(modeledSchedule.json),
				Schedule.class);
		ScheduleWriter writer = new ScheduleWriter();
		writer.setSchedule(schedule);
	}
	  
	protected String singleQuery(String query, Map<String, String> params) {
		Logger.info("Query:" + query);
		WSRequestHolder req = WS.url((String) query);
		// add params
		for (String key : params.keySet()) {
			req.setQueryParameter(key, params.get(key));
		}
		final Promise<Response> remoteCall = req.setAuth(getUser(),
				getPassword(), com.ning.http.client.Realm.AuthScheme.BASIC)
				.get();
		String res = remoteCall.get().getBody();
		Logger.info("Response:"+res);
		return res;
	}
}
