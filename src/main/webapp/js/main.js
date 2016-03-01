/**
 * (re)populate the dropdown list of
 * available representatives based on the
 * selected state
 */
function showReps() {
	var state = $("#state").val();
	$("#reps").find('option').remove().end();
	$('#reps').append($('<option>'));
	$.getJSON("/legislators?state=" + state, function(response) {
		$.each(response, function(key, val) {
			$('#reps').append($('<option>', {
			    value: val.id,
			    text: val.name
			}));
		});
	});
}

/**
 * Show the affiliation/success charts
 * 
 */
function showChart(dem,rep,bi,success) {	
	var voteData = [ {
		value : rep,
		color : "#FF0000",
		highlight : "#FF0000",
		label : "Voted with GOP majoirty"
	}, {
		value : dem,
		color : "#4000FF",
		highlight : "#4000FF",
		label : "Voted with Dem majority"
	}, {
		value : bi,
		color : "#00FF00",
		highlight : "#00FF00",
		label : "Voted with bipartisan majority"
	} ];
	var ctx = $("#vChart").get(0).getContext("2d");
	var myDoughnutChart = new Chart(ctx).Doughnut(voteData,{
	    animation: true,
	    animationSteps: 60
	});	
}

function openRepWindow(id) {
	window.open("/leg?id=" + id);
}

/**
 * Based on the selected criteria, get the chosen representative and display
 * their information, then get their sim/dims from the similarity matrix and
 * display those as well
 * 
 * TODO refactor
 * 
 * @param optionalId
 */
function showNewRepInfo(optionalId) {
	
	// Clean out the old rep
	$("#detail").find('div').remove().end();
	
	// Get the new rep's id
	var sourceId;
	if (optionalId)
		sourceId = optionalId;
	else
		sourceId = $("#reps").val();
	
	// ajax call to populate the rep's data
	var detail = "/detail?id=" + sourceId;
	var jqId = '#' + sourceId;
	$.getJSON(detail, function(response) {
		
		// The parent div and their 200px portrait
		$("#detail").append($('<div>',{
		    id: sourceId					    
		}));		
		$(jqId).append($('<img>', {
			src: "img/" + sourceId + ".jpg",
			style: "cursor: pointer; class: center-block",
			click: function() {
				window.open("/leg?id=" + sourceId);
			}
		}));					

		// The chart containing their affiliation
		var newElt = $('<ul/>',{
			'class':'list-group center-block',
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
	
	// Clean out old sim/dim
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
	
	// Get the sim/dim for the selected rep and
	// populate their info as well
	var target = "/similarity?id=" + sourceId;
	$.getJSON(target, function(response) {
		$.each(response, function(key, v) {
			var count = 1;
			for(var i = 0; i < v.length;i++) {
					
				var jqId = '#' + v[i].id;
				var bareId = v[i].id
				var parentId = '#' + key + '1';
				
				// Parent div and the 200px portrait
				$(parentId).append($('<div>',{
				    id: v[i].id					    
				}));
				$(jqId).append($('<img>', {
					id: "img" + v[i].id,
					src: "img/" + v[i].id + ".jpg",
					style: "cursor: pointer"
				}));			
				
				// Pic is a link to the voter's details page
				$("#img" + v[i].id).click(function(val) {
					window.open("/leg?id=" + $(this).parent().attr('id'));
				});
				
				// Chart containing their affiliation
				var newElt = $('<ul/>',{
					'class':'list-group center-block',
					width: '200px'
				});
				var a = [v[i].name, v[i].party,v[i].state,v[i].count / 2];
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
