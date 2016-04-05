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
		                                <li><a href="/top">Top 3</a></li>
		                                <li><a href="/candidates">Compare to 2016 candidates</a></li>
		                                <li class="active"><a href="/about">About</a></li>
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
			<p>See who your congressional representatives vote with...and
				against.</p>
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
		<div class="row">

			<div class="col-md-12 col-xs-12">
			<h2>About the site</h2>
			<p>
			Con.gress was conceived and built as a tool to empower voters.  It allows anyone to see, at a glance, how their
			representative votes.  Not just whether they vote along party lines, but the individual representatives they vote most (and least) like.
			</p>
			<p>
			The site is currently in alpha, with 1991-2015 data loaded and a limited subset of its features available.
			</p>
			<p>
			Raw data comes from <a href="https://www.govtrack.us/">Govtrack.us</a>
			</p>
			</div>

		</div>
	</div>
  </body>
</html>