<%@include file="head.jsp" %>
<body>
	<script type="text/javascript">
	$(document).ready(function() {
		var s = Math.round((${summary.success} * 1000) / 10);
		showChart(${summary.demvotes},${summary.repvotes},${summary.bivote},s,"sChart","vChart");
	});
	</script>

	<input type="hidden" value="${summary.demvotes}" id="demvotes"> 
	<input type="hidden" value="${summary.repvotes}" id="repvotes">
	<input type="hidden" value="${summary.bivote}" id="bivote">
	<input type="hidden" value="${year}" id="year">
    
    <div class="container">

    <div class="row navbar-wrapper">
                
                        <div class="navbar navbar-inverse navbar-static-top" role="navigation">
                                <div class="container">
                                        <div class="navbar-header">
                                                <button type="button" class="navbar-toggle" data-toggle="collapse"
                                                        data-target=".navbar-collapse">
                                                        <span class="sr-only">Toggle navigation</span> <span
                                                                class="icon-bar"></span> <span class="icon-bar"></span> <span
                                                                class="icon-bar"></span>
                                                </button>
                                        </div>
                                        <div class="navbar-collapse collapse">
                                                <ul class="nav navbar-nav">
                                                        <li class="active"><a href="/">Home</a></li>
                                                        <li><a href="/top">Top 3</a></li>
                                                        <li><a href="/candidates">Compare to 2016 candidates</a></li>
                                                        <li><a href="/about">About</a></li>
                                                        <li><a href="/contact">Contact</a></li>
                                                </ul>
                                        </div>
                                </div>
                        </div>

                
    </div>
    
    <div class="row">
	<div class="col-md-12 col-xs-12 jumbotron">
		<div class="container">
			<h1>con.gress</h1>			
			<p>This detail view shows the win/loss ratio and party line voting.  A win is counted as voting "yes" on a bill that passed or "no" on one that didn't</p>
			<p>You can compare Representative ${detail.name} (${detail.party}) from ${detail.state} to another member of Congress using the dropdown below:</p>
			<p>
			<div>
				<select class="dropdown" id="state" onchange="showReps(0)">
					<option>state</option>
					<c:forEach var="s" items="${states}">
						<option>${s}</option>
					</c:forEach>
				</select>
				<select  class="dropdown" id="reps" onchange="showOpponentInfo()">
				</select>
			</div>
			</p>
			<p>Or you can click <a href="/dev/${year}/${id}">here</a> to see the specific bills where ${detail.name} voted against the party line.</p>
		</div>
	</div>
	</div>
	</div>

	<div class="container">

		<div class="row text-center">

			<div class="col-md-1 col-xs-12"></div>

			<div class="col-md-3 col-xs-12 center-block">			
				<img src="/img/${id}.jpg" />	
			</div>
			
			<div class="col-md-4 col-s-6 col-xs-12 center-block">
				<p><b>Win/Loss Ratio:</b><br/></p>
				<canvas id="sChart" width="300" height="200"></canvas>
				<!-- <h1><c:out value="${Math.round((summary.success * 1000) / 10)}%" /></h1> -->				
			</div>
	
			<div class="col-md-4 col-s-6 col-xs-12 center-block">			
				<p><b>Votes by party affiliation:</b></p>
				<canvas id="vChart" width="300" height="200"></canvas>
				<div id="vChartLegend"></div>			
			</div>		
			
		</div>
		
		<div class="row text-center" style="display:none" id="opponentContainer">

			<div class="col-md-1 col-xs-12"></div>

			<div class="col-md-3 col-xs-12 center-block" id="opponentImg">			
	
			</div>
			
			<div class="col-md-4 col-s-6 col-xs-12 center-block" id="voTarget">
				<p><b>Win/Loss Ratio:</b><br/></p>
				<canvas id="osChart" width="325" height="200"></canvas>
				<!-- <h1><c:out value="${Math.round((summary.success * 1000) / 10)}%" /></h1> -->				
			</div>
	
			<div class="col-md-4 col-s-6 col-xs-12 center-block" id="vcTarget">			
				<p><b>Votes by party affiliation:</b></p>
				<canvas id="ovChart" width="325" height="200"></canvas>
				<div id="vChartLegend"></div>			
			</div>					
		</div>
	</div>
  </body>
</html>