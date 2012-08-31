package jobs;

import java.util.Map;

import com.google.common.collect.Maps;

import play.Logger;
import play.Play;
import play.libs.WS;
import play.libs.F.Promise;
import play.libs.WS.Response;
import play.libs.WS.WSRequestHolder;
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
		  if (apiType instanceof String) {
			  // response back to trigger
			  String url = "http://metrics.tendrilinc.com/api/events/1";
			  Map<String,String> params = Maps.newHashMap();
			  sender().tell(singleQuery(url, params));
		  } else {
			  unhandled(apiType);
		  }
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
