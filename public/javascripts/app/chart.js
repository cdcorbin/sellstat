

$(function () {

   $(document).ready(function() {
    
    var containers = ["sundayContainer", "mondayContainer", "tuesdayContainer", "wednesdayContainer", "thursdayContainer", "fridayContainer", "saturdayContainer"];
	var buttons = ["sundayButton", "mondayButton", "tuesdayButton", "wednesdayButton", "thursdayButton", "fridayButton", "saturdayButton"];
	var weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	
	function getTimeFromIndex(index) {
		
	}

	function getChartIndex(scheduleSlot) {
		var hourAndMinute = scheduleSlot.split(":");
		var index = hourAndMinute[0]*2;
		if (hourAndMinute[1] === "30") {
			index++;
		}
		return index;
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
	}
	
	function getChartContainerId(index) {
		return containers(index);
	}
	
	function toggleContainers(index) {
		for ( var i = 0 ; i < containers.length ; i++ ) {
			if ( i === index) {
				var id = "#" + containers[i];
				$(id).show();
			}
			else {
				var id = "#" + containers[i];
				$(id).hide();
			}
		}
	}
	
	$('.dayButton').click(function(data) {
		var index = getButtonIndex($(this).attr("id"));
		toggleContainers(index);
		toggleActiveButton(index);
	})
	
	function makeChart(programIndex) {
		var categories = ['12AM','','','','','', '', '','','', '', '','6AM', '','', '','','', '', '', '','', '', '','12PM', '','','','','', '', '','','', '', '','6PM', '','', '','','', '', '', '','', '', ''];
		var coolingPoints = new Array();
		var heatingPoints = new Array();
		for ( var i = 0 ; i < 48 ; i++ ) {
			coolingPoints[i] = '';
			heatingPoints[i] = '';
		}
		//get x-axis values ready (testing on Sunday program)
		var program = schedule.programs[programIndex];

		var wakeUpTime = program.segments[0].timeOfDay;
		var coolingPoint = program.segments[0].coolingSetPoint;
		var heatingPoint = program.segments[0].heatingSetPoint;
		var index = getChartIndex(wakeUpTime); 
		coolingPoints[index] = coolingPoint;
		heatingPoints[index] = heatingPoint;

		var awayTime = program.segments[1].timeOfDay;
		var coolingPoint = program.segments[1].coolingSetPoint;
		var heatingPoint = program.segments[1].heatingSetPoint;
		var index = getChartIndex(awayTime); 
		coolingPoints[index] = coolingPoint;
		heatingPoints[index] = heatingPoint;

		var homeTime = program.segments[2].timeOfDay;
		var coolingPoint = program.segments[2].coolingSetPoint;
		var heatingPoint = program.segments[2].heatingSetPoint;
		var index = getChartIndex(homeTime); 
		coolingPoints[index] = coolingPoint;
		heatingPoints[index] = heatingPoint;

		var sleepTime = program.segments[3].timeOfDay;
		var coolingPoint = program.segments[3].coolingSetPoint;
		var heatingPoint = program.segments[3].heatingSetPoint;
		var index = getChartIndex(sleepTime); 
		coolingPoints[index] = coolingPoint;
		heatingPoints[index] = heatingPoint;

    	var chart;
        chart = new Highcharts.Chart({
            chart: {
                renderTo: containers[programIndex],
                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: 'T-Stat Schedule',
                x: -20 //center
            },
			credits : {
			    enabled : false
			},
            subtitle: {
                text: weekday[programIndex],
                x: -20
            },
            xAxis: {
                categories: categories
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                },
				min: 7,
				max: 32,
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        this.x +': '+ this.y +'°C';
                },
				enabled: true
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -10,
                y: 100,
                borderWidth: 0
            },
            series: [{
                name: 'Cooling Points',
                data: coolingPoints
            }, {
                name: 'Heating Points',
                data: heatingPoints
            }]
        });
	}
	
	var schedule = {"deviceId":"1","locationId":"1","programId":"1","programs":[{"dayOfWeek":"Sun","segments":[{"heatingSetPoint":22,"coolingSetPoint":24,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":30,"coolingSetPoint":24,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":66.0,"coolingSetPoint":90.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Mon","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Tue","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Wed","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Thur","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Fri","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]},{"dayOfWeek":"Sat","segments":[{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"08:30","name":"WakeUp"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"10:30","name":"Away"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"17:30","name":"Home"},{"heatingSetPoint":22.0,"coolingSetPoint":22.0,"timeOfDay":"22:30","name":"Sleep"}]}]};
    var chartCount = containers.length;
    for ( var i = 0 ; i < chartCount ; i++ ) {
	  	makeChart(i);
    }
   
    var today = new Date().getDay();
    toggleContainers(today);
	toggleActiveButton(today);
  })
});