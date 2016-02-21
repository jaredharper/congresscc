$(document).ready(function() {
	
});

function showReps() {
	var state = $("#state").val();
	$("#reps").find('option').remove().end();
	$.getJSON("/legislators?state=" + state, function(response) {
		$.each(response, function(key, val) {
			$('#reps').append($('<option>', {
			    value: val.id,
			    text: val.name
			}));
		});
	});
}

function showNewRepInfo() {
	var target = "/similarity?id=" + $("#reps").val();
	$("#sim").find('p').remove().end();
	$("#dis").find('p').remove().end();
	$.getJSON(target, function(response) {
		$.each(response, function(key, v) {
			for(var i = 0; i < v.length;i++) {
				$('#' + key).append($('<p>', {
				    id: v[i].id,
				    text: v[i].count
				}));				
			}
			
		});
	});	
}

function showChart() {
		
		var data = {
			labels : [ "January", "February", "March", "April", "May",
					"June", "July" ],
			datasets : [ {
				label : "My First dataset",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)",
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data : [ 65, 59, 80, 81, 56, 55, 40 ]
			}, {
				label : "My Second dataset",
				fillColor : "rgba(151,187,205,0.2)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "#fff",
				pointHighlightStroke : "rgba(151,187,205,1)",
				data : [ 28, 48, 40, 19, 86, 27, 90 ]
			} ]
		};

		// Get context with jQuery - using jQuery's .get() method.
		var ctx = document.getElementById("myChart").getContext("2d");
		var myLineChart = new Chart(ctx).Line(data, {
			scaleBeginAtZero : true
		});	

}