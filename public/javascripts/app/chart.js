

$(function () {

   $(document).ready(function() {
    
    var oldSchedule;
    var newSchedule;
    var containers = ["sundayContainer", "mondayContainer", "tuesdayContainer", "wednesdayContainer", "thursdayContainer", "fridayContainer", "saturdayContainer"];
	var buttons = ["sundayButton", "mondayButton", "tuesdayButton", "wednesdayButton", "thursdayButton", "fridayButton", "saturdayButton"];
	var weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	var twelveHour = ['12', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11'];
	var twentyfourHour = ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23'];

	function getTimeFromIndex(index) {
		
	}
	
	function jsonpCallback(data) {
		console.log("yep");
	}

	function getChartIndex(scheduleSlot) {
		var hourAndMinute = scheduleSlot.split(":");
		var index = hourAndMinute[0]*2;
		if (hourAndMinute[1] === "30") {
			index++;
		}
		return index;
	}
	
	function getTwelveHourTime(scheduleSlot){
		var hourAndMinute = scheduleSlot.split(":");
		index = $.inArray(hourAndMinute[0], twentyfourHour);
		var hour = twelveHour[index];
		var ampm = index < 12 ? 'AM' : 'PM';
		return ( hour + ':' + hourAndMinute[1] + ' ' + ampm);
	}
	
	function getButtonIndex(buttonId) {
		return $.inArray(buttonId, buttons);
	}
	
	function toggleActiveButton(buttonIndex) {
		for (var i = 0 ; i < buttons.length ; i++) {
			if ( i === buttonIndex) {
				var id = "#" + buttons[i];
				$(id).addClass("active");
		}
			else {
				var id = "#" + buttons[i];
				$(id).removeClass("active");
			}
		}	
		
		var degreesFarenheit = ' &#176;F';
		
		$('#dayOfWeek').html(weekday[buttonIndex] + "s");
		var newProgram = newSchedule.programs[buttonIndex];
		$('#wakeRecommendedTime').html(getTwelveHourTime(newProgram.segments[0].timeOfDay));
		$('#wakeRecommendedCoolingPoint').html(Math.round(1.8*newProgram.segments[0].coolingSetPoint +32) + degreesFarenheit);
		$('#awayRecommendedTime').html(getTwelveHourTime(newProgram.segments[1].timeOfDay));
		$('#awayRecommendedCoolingPoint').html(Math.round(1.8*newProgram.segments[1].coolingSetPoint +32) + degreesFarenheit);	
		$('#returnRecommendedTime').html(getTwelveHourTime(newProgram.segments[2].timeOfDay));
		$('#returnRecommendedCoolingPoint').html(Math.round(1.8*newProgram.segments[2].coolingSetPoint +32) + degreesFarenheit);	
		$('#sleepRecommendedTime').html(getTwelveHourTime(newProgram.segments[3].timeOfDay));
		$('#sleepRecommendedCoolingPoint').html(Math.round(1.8*newProgram.segments[3].coolingSetPoint +32) + degreesFarenheit);
		
		var oldProgram = oldSchedule.programs[buttonIndex];
		$('#wakeCurrentTime').html(getTwelveHourTime(oldProgram.segments[0].timeOfDay));
		$('#wakeCurrentCoolingPoint').html(Math.round(1.8*oldProgram.segments[0].coolingSetPoint +32) + degreesFarenheit);
		$('#awayCurrentTime').html(getTwelveHourTime(oldProgram.segments[1].timeOfDay));
		$('#awayCurrentCoolingPoint').html(Math.round(1.8*oldProgram.segments[1].coolingSetPoint +32) + degreesFarenheit);
		$('#returnCurrentTime').html(getTwelveHourTime(oldProgram.segments[2].timeOfDay));
		$('#returnCurrentCoolingPoint').html(Math.round(1.8*oldProgram.segments[2].coolingSetPoint +32) + degreesFarenheit);
		$('#sleepCurrentTime').html(getTwelveHourTime(oldProgram.segments[3].timeOfDay));
		$('#sleepCurrentCoolingPoint').html(Math.round(1.8*oldProgram.segments[3].coolingSetPoint +32) + degreesFarenheit);
		
		$('#thismuch').html(newSchedule.savings);
	}
	

	
	function getOldResult() {
		$.ajax( {url: 'http://metrics.tendrilinc.com/api/oldschedules/1',
				accept: 'application/json'})
				.done(function(data) {
					oldSchedule = data;
					var today = new Date().getDay();
					toggleActiveButton(today);
				 })
				 .fail(function(data) {
						alert("DOH2!");
			});
		}
	
	
		$('.dayButton').click(function(data) {
			var index = getButtonIndex($(this).attr("id"));
			toggleActiveButton(index);
		});


		$('#update').click(function(data) {
			var putResult = $.ajax( {url: 'http://metrics.tendrilinc.com/api/schedules/1',
								 type: 'POST',
								accept: 'application/json'})
			                   .done(function(data) {
									alert("You thermostat programs have been updated!");
								})
								.fail(function(data) {
									alert("DOH!");
								});
		});
	
	   var newResult = $.ajax( {url: 'http://metrics.tendrilinc.com/api/schedules/1',
	                         accept: 'application/json'})
	                   .done(function(data) {
							newSchedule = data;
							getOldResult();
						})
						.fail(function(data) {
							alert("DOH!");
						});
						

	
	//var schedule = {"deviceId":"1","locationId":"1","programId":"1","programs":[{"dayOfWeek":"Sun","segments":[{"heatingSetPoint":22,"coolingSetPoint":24,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":30,"coolingSetPoint":24,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":66.0,"coolingSetPoint":90.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Mon","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Tue","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Wed","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Thur","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Fri","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Sat","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]}]};
    
  })
});