package ai;

import models.Program;
import models.ProgramSegment;
import models.Schedule;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public final class ScheduleCreator {
	
	private static SimpleDateFormat dayFormatter = new SimpleDateFormat("EEE");
	private static SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
	private static Calendar calendar = Calendar.getInstance();
	private static double heatWake = 22.222;
	private static double coolWake = 24.444;
	private static double heatAway = 18.888;
	private static double coolAway = 28.888;
	private static double heatHome = 22.222;
	private static double coolHome = 24.444;
	private static double heatSleep = 18.888;
	private static double coolSleep = 28.888;
	
	public static Schedule makeSchedule (TreeMap<Integer, Double> probabilities) {
	
		Schedule schedule = new Schedule();
		schedule.deviceId = "1";
		schedule.locationId = "1";
		schedule.programId = "1";
		
		int lastDay = 0;
		double lastValue = 0;
		ProgramSegment wake = null;
		ProgramSegment away = null;
		ProgramSegment home = null;
		ProgramSegment sleep = null;

		
		for (Map.Entry<Integer, Double> entry : probabilities.entrySet()) {
			
			int day = entry.getKey()/1440+1;
			double value = Math.round(entry.getValue());
			Program program;
			
			if (day > lastDay) {
				System.out.println("ld:"+lastDay+"  d:"+day);
				
				calendar.set(Calendar.LONG, 0);
				calendar.set(Calendar.DAY_OF_WEEK, day);
				program = new Program();
				program.setDayOfWeek(dayFormatter.format(calendar.getTime()));
				
				wake = new ProgramSegment();
				wake.setName("Wake");
				away = new ProgramSegment();
				away.setName("GPS Away");
				home = new ProgramSegment();
				home.setName("GPS Home");
				sleep = new ProgramSegment();
				sleep.setName("Sleep");
				
				program.addSegment(wake);
				program.addSegment(away);
				program.addSegment(home);
				program.addSegment(sleep);
				
				schedule.addProgram(program);
				
				lastDay = day;
				lastValue = Math.round(value);
			
			} else {
				
				//makeWakeSegment(wake, calendar);
				
				int hour = entry.getKey()%1440/60;
				int minute = entry.getKey()%1440%60;
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
//				System.out.println("else lv:"+lastValue+"  v:"+value);
				if (lastValue > value) {
					System.out.println("AWAY " + "\t" + dayFormatter.format(calendar.getTime()) + "\t" + lastValue + "\t" + value + " " + timeFormatter.format(calendar.getTime()));
					away.setName("GPS Away");
					away.setCoolingSetPoint(coolAway);
					away.setHeatingSetPoint(heatAway);
					away.setTimeOfDay(timeFormatter.format(calendar.getTime()));
					wake.setName("Wake");
					wake.setCoolingSetPoint(coolWake);
					wake.setHeatingSetPoint(heatWake);
					calendar.set(Calendar.HOUR_OF_DAY, Math.max(calendar.get(Calendar.HOUR_OF_DAY)-2, 0));
					wake.setTimeOfDay(timeFormatter.format(calendar.getTime()));
				} else if (value > lastValue) {
					System.out.println("HOME " + "\t" + dayFormatter.format(calendar.getTime()) + "\t" + lastValue + "\t" + value + " " + timeFormatter.format(calendar.getTime()));
					home.setName("GPS Home");
					home.setCoolingSetPoint(coolHome);
					home.setHeatingSetPoint(heatHome);
					home.setTimeOfDay(timeFormatter.format(calendar.getTime()));
					sleep.setName("Sleep");
					sleep.setCoolingSetPoint(coolSleep);
					sleep.setHeatingSetPoint(heatSleep);
					calendar.set(Calendar.HOUR_OF_DAY, Math.min(calendar.get(Calendar.HOUR_OF_DAY)+4, 23));
					sleep.setTimeOfDay(timeFormatter.format(calendar.getTime()));
				}
				
				lastValue = value;

			}
		
		}
		
		return schedule;

	}

}
