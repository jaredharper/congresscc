$(document).ready(function() {
	showNewRepInfo("A000371");
});

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

function showNewRepInfo(optionalId) {
	
	$("#detail").find('div').remove().end();
	
	var sourceId;
	if (optionalId)
		sourceId = optionalId;
	else
		sourceId = $("#reps").val();
	
	var detail = "/detail?id=" + sourceId;
	var jqId = '#' + sourceId;
	$.getJSON(detail, function(response) {

		$("#detail").append($('<div>',{
		    id: sourceId					    
		}));
		
		$(jqId).append($('<img>', {
			src: "img/" + sourceId + ".jpg",
			style: "cursor: pointer",
			click: function() {
				window.open("/leg/" + sourceId);
			}
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
	
	
	var target = "/similarity?id=" + sourceId;
	$.getJSON(target, function(response) {
		$.each(response, function(key, v) {
			var count = 1;
			for(var i = 0; i < v.length;i++) {
					
				var jqId = '#' + v[i].id;
				var bareId = v[i].id
				var parentId = '#' + key + '1';
				
				$(parentId).append($('<div>',{
				    id: v[i].id					    
				}));
				
				$(jqId).append($('<img>', {
					src: "img/" + v[i].id + ".jpg",
					style: "cursor: pointer",
					click: function() {
						window.open("/leg/" + bareId);
					}
				}));					

				var newElt = $('<ul/>',{
					'class':'list-group',
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
