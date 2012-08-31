package contollers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.CREATED;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;
import static play.test.Helpers.PUT;
import static play.test.Helpers.charset;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.routeAndCall;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

import java.util.Map;


import org.codehaus.jackson.JsonNode;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.collect.Maps;

import play.libs.Json;
import play.mvc.Result;
import play.test.FakeApplication;
import play.test.FakeRequest;
import play.test.Helpers;


public class ApiEventTest {
	public static FakeApplication app;
	
	@BeforeClass
	public static void setup() {
		app = Helpers.fakeApplication();
		try {
		Helpers.start(app);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void add() {
       running(fakeApplication(), new Runnable() {
           public void run() {
        	   System.out.println("Running test");
        	   Map<String,String> map = Maps.newHashMap();
               map.put("uri", "1");
               map.put("lat", "-100.0");
               map.put("lng", "40.0");
               map.put("evtime", "2012-08-30T22:00:00.000-07.00");
               JsonNode node = Json.toJson(map);
        	   FakeRequest fakeRequest = new FakeRequest(POST, "/api/events")
        	   			.withJsonBody(node);
        	   Result result = routeAndCall(fakeRequest);

        	   assertThat(status(result)).isEqualTo(CREATED);
        	   // verify has evolution
           }
        });
	}
}
