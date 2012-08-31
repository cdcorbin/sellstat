package jobs;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
/**
 * @author dlange
 *
 */
public class ConnectActor extends UntypedActor {
	  LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	 
	  /**
	   */
	  public void onReceive(Object apiType) throws Exception {
		  log.info("Received apiType: {}", apiType);
		  if (apiType instanceof String) {
			  // response back to trigger
			  sender().tell(apiType.toString());
		  } else {
			  unhandled(apiType);
		  }
	  }
}
