<%@include file="head.jsp" %>
<body>
	<script src="/js/image.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		showNewRepInfo("A000371","home");
	});
	</script>
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
			<p>See who your representatives vote with... and
				against.  Start by selecting your state and representative.				
			</p>		
			<p>
				<c:choose>
					<c:when test="${isMobile eq true}">Tap</c:when>
					<c:when test="${isTablet eq true}">Tap</c:when>
					<c:otherwise>Click</c:otherwise>
				</c:choose> the portrait to see their details.
			</p>	
			<div>
				<select class="dropdown" id="year" onchange="showReps(0)">
					<c:forEach var="y" items="#{years}">
						<option>${y}</option>
					</c:forEach>
				</select>
				<select class="dropdown" id="state" onchange="showReps(0)">
					<option>state</option>
					<c:forEach var="s" items="${states}">
						<option>${s}</option>
					</c:forEach>
				</select>
				<select  class="dropdown" id="reps" onchange="showNewRepInfo(0,'home')">
				
				</select>
			</div>
		</div>
	</div>
	<div id="mainContent">
		<div class="row text-center">
			<div class="col-md-4 col-xs-12 center-block"></div>
			<div class="col-md-4 col-xs-12 center-block">
				<p>&nbsp;</p>
				<div id="detail" class="center-block">
					<span></span>
				</div>
			</div>
			<div class="col-md-4 col-xs-12 center-block"></div>
		</div>		
		<div class="row text-center">		
			<div class="col-md-12 col-xs-12 center-block">
				<h2>With</h2>
				<p>
					<span id="repName">Your rep</span> voted most like
				</p>
				<div id="sim" class='center-block'>
					<span> </span>
				</div>
			</div>		
		</div>		
		<div class="row text-center">		
			<div class="col-md-12 col-xs-12 center-block">
				<h2>Against</h2>
				<p>And voted least like</p>
				<div id="dis" class="center-block">
				<span> </span>
				</div>
			</div>		
		</div>		
	</div>

  </body>
</html>