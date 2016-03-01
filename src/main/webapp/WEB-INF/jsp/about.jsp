<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Congress Canvasser</title>

<script src="js/jquery-2.2.0.min.js"></script>
<script src="js/Chart.min.js"></script>
<script src="js/main.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script>
	(function(i, s, o, g, r, a, m) {
		i['GoogleAnalyticsObject'] = r;
		i[r] = i[r] || function() {
			(i[r].q = i[r].q || []).push(arguments)
		}, i[r].l = 1 * new Date();
		a = s.createElement(o), m = s.getElementsByTagName(o)[0];
		a.async = 1;
		a.src = g;
		m.parentNode.insertBefore(a, m)
	})(window, document, 'script', '//www.google-analytics.com/analytics.js',
			'ga');

	ga('create', 'UA-74295003-1', 'auto');
	ga('send', 'pageview');
</script>

</head>
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

		<!-- Example row of columns -->
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
			The site is currently in alpha, with 2015 data loaded and a limited subset of its features pubilcally available.  Check back
			this week (March 1-4) to see new features and data as they are phased in.  
			</p>
			</div>

		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>