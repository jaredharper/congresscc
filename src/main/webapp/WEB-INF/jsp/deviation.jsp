<%@include file="head.jsp" %>
<body>
    
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
			<p>This detail view provides links to legislation where Representative ${detail.name} (${detail.party}) voted outside their party majority</p>
			<p>
			</p>
		</div>
	</div>
	</div>
	</div>

	<div class="container">

		<div class="row text-center">

			<div class="col-md-12 col-xs-12 center-block">			
				<img src="/img/${id}.jpg" />	
			</div>
			
		</div>
		<br/>
		<c:forEach var="s" items="${urls}">
			<div class="row text-center">
				<a class="lead" href="${s.value}">${s.key}</a>
			</div>		
		</c:forEach>			
	</div>
  </body>
</html>