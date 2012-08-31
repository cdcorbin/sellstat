package ai;

import java.util.List;

import models.Event;
import models.Schedule;

public class LocationBasedScheduleCreator {
	
	
	private TimeStampedLocation home;
	private TimeStampedLocation[] locationData; 
	
	public LocationBasedScheduleCreator () {
		
	}
	
	public void setEvents (List<Event> events) {
		
		locationData = new TimeStampedLocation[events.size()];
		Event event;
		
		for (int i=0; i<events.size(); i++) {
			event = events.get(i);
			locationData[i] = new TimeStampedLocation(event.latitude, event.longitude, event.eventat);
		}
	}

	public Schedule createSchedule () {
	
		Bucket[] bucketData = new Bucket[locationData.length];
		
		for (int i=0; i<locationData.length; i++) {
			TimeStampedLocation loc = locationData[i];
			Bucket bucket = Bucketer.bucket(loc.getDateTime(), loc.distance(home));	
			bucketData[i] = bucket;
		}
		
		OccupantModel model = new OccupantModel();
		model.setTrainingData(bucketData);
		model.train();
		
		Schedule schedule = ScheduleCreator.makeSchedule(model.getProbabilities());
		
		return schedule;
		
	}
	
	public void setHome (TimeStampedLocation home) {
		this.home = home;
	}

}
