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
		                                <li><a href="/">Home</a></li>
		                                <li class="active"><a href="/top">Top 3</a></li>
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
			<p>The top three legislators based on success (most "yes" votes passing or "no" votes failing) and cooperation.</p>
			<div>
			<p>Select another year:
			<select class="dropdown" id="years" onchange="topYear()">
				<c:forEach var="y" items="#{years}">
					<option>${y}</option>
				</c:forEach>
			</select>					
			</p>
			</div>
		</div>
	</div>
	</div>

		<c:if test="${isNormal eq true}">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<div style="width: 50%; margin: 0 auto">
					<script async
						src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
					<!-- second -->
					<ins class="adsbygoogle"
						style="display: inline-block; width: 728px; height: 90px"
						data-ad-client="ca-pub-2577969905607332" data-ad-slot="4847497007"></ins>
					<script>
						(adsbygoogle = window.adsbygoogle || []).push({});
					</script>
				</div>
			</div>
			<div class="col-md-2 col-xs-12"></div>
		</div>
		</c:if>
		
		<div class="row text-center">
			<h2>Most successful</h2>
			<c:forEach var="s" items="${top}">
				<div class="col-md-4 col-sm-6 col-xs-12 center-block">
					<img id="img${s.id}" src="/img/${s.id}.jpg" style="cursor: pointer" onclick="window.open('/leg/${year}/${s.id}')">
					<ul class="list-group center-block" style="width: 200px;">
					<li class="list-group-item">name:${s.name}</li>
					<li class="list-group-item">party:${s.party}</li>
					<li class="list-group-item">state:${s.state}</li>
					<li class="list-group-item">success:${Math.round((Double.parseDouble(s.count) * 1000) / 10)}%</li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<div class="row text-center">
			<h2>Most cooperative Republicans</h2>	
			<c:forEach var="s" items="${rep}">
				<div class="col-md-4 col-sm-6 col-xs-12 center-block">
					<img id="img${s.id}" src="/img/${s.id}.jpg" style="cursor: pointer" onclick="window.open('/leg/${year}/${s.id}')">
					<ul class="list-group center-block" style="width: 200px;">
					<li class="list-group-item">name:${s.name}</li>
					<li class="list-group-item">party:${s.party}</li>
					<li class="list-group-item">state:${s.state}</li>
					<li class="list-group-item">voted with Democrats:${s.count}</li>
					</ul>
				</div>
			</c:forEach>
		</div>
		<div class="row text-center">		
			<h2>Most cooperative Democrats</h2>			
			<c:forEach var="s" items="${dem}">
				<div class="col-md-4 col-sm-6 col-xs-12 center-block">
					<img id="img${s.id}" src="/img/${s.id}.jpg" style="cursor: pointer" onclick="window.open('/leg/${year}/${s.id}')">
					<ul class="list-group center-block" style="width: 200px;">
					<li class="list-group-item">name:${s.name}</li>
					<li class="list-group-item">party:${s.party}</li>
					<li class="list-group-item">state:${s.state}</li>
					<li class="list-group-item">voted with Republicans:${s.count}</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>

  </body>
</html>