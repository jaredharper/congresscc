/**
 * (re)populate the dropdown list of
 * available representatives based on the
 * selected state
 */
function showReps(senateOnly) {
	var state = $("#state").val();
	var year = $("#year").val();
	$("#reps").find('option').remove().end();
	$('#reps').append($('<option>'));
	$.getJSON("/legislators/" + year + "/" + state, function(response) {
		$.each(response, function(key, val) {
			if(((senateOnly == true) && (val.id.length == 4)) || senateOnly != true) {
				$('#reps').append($('<option>', {
				    value: val.id,
				    text: val.name
				}));				
			}
		});
	});
}

/**
 * Go to the "top 3" page for the selected year
 */
function topYear() {
	var year = "/top/" + $("#years").val();
	window.location.assign(year);
}

/**
 * Show the affiliation/success charts
 * 
 */
function showChart(dem,rep,bi,success,sChart,vChart) {
	
	var voteData = [ {
		value : rep,
		color : "#FF0000",
		fillColor: "#FF0000",
		highlight : "#FF0000",
		label : "Voted with GOP majoirty"
	}, {
		value : dem,
		color : "#4000FF",
		fillColor : "#4000FF",
		highlight : "#4000FF",
		label : "Voted with Dem majority"
	}, {
		value : bi,
		color : "#F7FE2E",
		fillColor : "#F7FE2E",
		highlight : "#F7FE2E",
		label : "Voted with bipartisan majority"
	} ];
	var ctx = $("#"+vChart).get(0).getContext("2d");
	var myDoughnutChart = new Chart(ctx).Doughnut(voteData,{
	    animation: true,
	    animationSteps: 60
	});	

	var remainder = 100 - success;
	var sData = [ {
		value : success,
		color : "#00FF00",
		highlight : "#00FF00",
		label : "% Won"
	}, {
		value : remainder,
		color : "#000000",
		highlight : "#000000",
		label : "% Lost"
	}];
	var stx = $("#"+sChart).get(0).getContext("2d");
	var myBarChart = new Chart(stx).Pie(sData, {
		animation: true
	});
}

/**
 * Open the detail view in a new window
 * 
 */
function openRepWindow(id,year) {
	window.open("/leg/" + year + "/" + id);
}

/**
 * Display the selected legislator's info underneath
 * the main row in the detail view
 * 
 */
function showOpponentInfo() {
	
	$("#opponentImg").find('img').remove().end();
	$("#opponentContainer").show();
	
	var id = $("#reps").val()
	var year = $("#year").val()
	
	// Show the pic
	asyncImageLoader(id, "#opponentImg", year);
	
	// Fetch and display the detail info
	var target = "/summary/" + year + "/" + id;
	$.getJSON(target, function(response) {		
		$("#voTarget").find('canvas').remove().end();
		$("#voTarget").append($('<canvas>', {
			id: 'osChart',
			width: 325,
			height: 200
		}));
		$("#vcTarget").find('canvas').remove().end();
		$("#vcTarget").append($('<canvas>', {
			id: 'ovChart',
			width: 325,
			height: 200
		}));
		var s = Math.round((response.success * 1000) / 10);
		showChart(response.demvotes,response.repvotes,response.bivote,s,"osChart","ovChart");	
	});
	
}

/**
 * Wrapper for the loading gif / portrait logic
 * 
 * @param sourceId - representative's ID
 * @param jqId - the object this will be appended to
 * @param year - the year used for linking to detail view
 */
function asyncImageLoader(sourceId,jqId,year) {
	
	var ni = $('<img>', {
		id: "img" + sourceId,
		src: "/img/loader.gif",
		style: "cursor: pointer"
	});
	
	// Loading indicator for image
	$(ni).attr("data-src","/img/" + sourceId + ".jpg");
	$(ni).loadImage();
	$(jqId).append(ni);
	
	// Pic is a link to the voter's details page
	if (year) {
		$("#img" + sourceId).click(function(val) {
			window.open("/leg/" + year + "/" + sourceId);
		});		
	}	
}

/**
 * Based on the selected criteria, get the chosen representative and display
 * their information, then get their sim/dims from the similarity matrix and
 * display those as well
 * 
 * TODO refactor horrid abomination into something else
 * 
 * @param optionalId -
 *            optional id to prepopulate home page
 */
function showNewRepInfo(optionalId, page) {
	
	// Clean out the old rep
	$("#detail").find('div').remove().end();
	
	// Get the new rep's id
	var sourceId;
	if (optionalId)
		sourceId = optionalId;
	else
		sourceId = $("#reps").val();
	
	var year = $("#year").val();
	
	// ajax call to populate the rep's data
	var detail = "/detail/" + year + "/" + sourceId;
	var jqId = '#' + sourceId;
	$.getJSON(detail, function(response) {
		
		// The parent div and their 200px portrait
		$("#detail").append($('<div>',{
		    id: sourceId					    
		}));		
		asyncImageLoader(sourceId, jqId, year);

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
	
	// Clean out old similar/dissimilar elements
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
	var target;
	if (page == "home")
		target = "/similarity/" + year + "/" + sourceId;
	else if (page == "candidate")
		target = "/comparison/" + year + "/" + sourceId;
	var counter = 0;
	$.getJSON(target, function(response) {
		$.each(response, function(key, v) {
			var count = 1;
			for(var i = 0; i < v.length;i++) {
					
				var jqId = '#' + v[i].id;
				var bareId = v[i].id
				var parentId = '#' + key + '1';
				
				// Parent div and the 200px portrait
				$(parentId).append($('<div>',{
				    id: v[i].id,
				    'class' : 'col-md-4 col-sm-6 col-xs-12'
				}));				
				asyncImageLoader(v[i].id, jqId, year);
				
				// Chart containing their affiliation
				var newElt = $('<ul/>',{
					'class':'list-group center-block',
					width: '200px'
				});
				var a = [v[i].name, v[i].party,v[i].state,v[i].count];
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
