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
	
	$("#detail").find('div').remove().end();
	
	var detail = "/detail?id=" + $("#reps").val();
	$.getJSON(detail, function(response) {
		
		var jqId = '#' + $("#reps").val();
		var parentId = $("#reps").val();
		
		$("#detail").append($('<div>',{
		    id: parentId					    
		}));
		
		$(jqId).append($('<img>', {
			src: "img/" + parentId + ".jpg"
		}));					

		var newElt = $('<ul/>',{
			'class':'list-group',
			width: '200px'
		});
		var a = [response.name, response.party,response.state];
		var b = ["name","party","state"];
		$(jqId).append(newElt);
		for (var j = 0; j < 3; j++) {
			var ne = $('<li/>',{
				'class':'list-group-item',
				html: b[j] + ':' + a[j]
			});
			$(newElt).append(ne);
		}				
		$(jqId).append(newElt);
		
	});
	
	var target = "/similarity?id=" + $("#reps").val();
	$("#repName").text($("#reps").find(":selected").text());
	
	$("#sim").find('span').remove().end();
	$("#sim").find('br').remove().end();
	$("#dis").find('span').remove().end();
	$("#dis").find('br').remove().end();
	
	$("#sim").append($('<span>', {
		id: 'sim1'
	}))
	$("#dis").append($('<span>', {
		id: 'dis1'
	}))
	
	$.getJSON(target, function(response) {
		$.each(response, function(key, v) {
			var count = 1;
			for(var i = 0; i < v.length;i++) {
					
				var jqId = '#' + v[i].id;
				var parentId = '#' + key + '1';
				
				$(parentId).append($('<div>',{
				    id: v[i].id					    
				}));
				
				$(jqId).append($('<img>', {
					src: "img/" + v[i].id + ".jpg"
				}));					

				var newElt = $('<ul/>',{
					'class':'list-group',
					width: '200px'
				});
				var a = [v[i].name, v[i].party,v[i].state,(v[i].count / 2)];
				var b = ["name","party","state","votes in common"];
				$(jqId).append(newElt);
				for (var j = 0; j < 4; j++) {
					var ne = $('<li/>',{
						'class':'list-group-item',
						html: b[j] + ':' + a[j]
					});
					$(newElt).append(ne);
				}				
				$(jqId).append(newElt);	
								
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