package ai;

import models.Schedule;

public class LocationBasedScheduleCreator {
	
	
	private TimeStampedLocation home;
	private TimeStampedLocation[] locationData; 
	
	public LocationBasedScheduleCreator () {
		
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
