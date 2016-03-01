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

<script type="text/javascript">
$(document).ready(function() {
	showChart(${summary.demvotes},${summary.repvotes},${summary.bivote});
});
</script>
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
	<input type="hidden" value="${summary.demvotes}" id="demvotes"> 
	<input type="hidden" value="${summary.repvotes}" id="repvotes">
	<input type="hidden" value="${summary.bivote}" id="bivote">

    <div class="container">
    
    <div class="row">
	<div class="col-md-12 col-xs-12 jumbotron">
		<div class="container">
			<h1>Congress Canvasser</h1>
			<p>See who your congressional representatives vote with...and
				against.</p>
			<p></p>
		</div>
	</div>
	</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row text-center">

			<div class="col-md-3 col-xs-12 center-block">			
				<img src="img/${id}.jpg" />	
			</div>

			<div class="col-md-2 col-xs-12 center-block">
				<p><b>Success Rate:</b><br/></p>
				<h1><c:out value="${Math.round((summary.success * 1000) / 10)}%" /></h1>
				<br/><p>Bills passed/blocked.  Votes are counted only if they voted "yes" and it passed or voted "no" and it did not pass </p>
			</div>
	
			<div class="col-md-3 col-xs-12 center-block">			
				<canvas id="vChart" width="300" height="200"></canvas>
				<div id="vChartLegend"></div>			
			</div>			
			<div class="col-md-4 col-xs-12"></div>
		</div>
	</div>

	<hr>


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