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
<title>Untitled Site Widget</title>

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

</head>
<body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>

        </div>

      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Congress Thing</h1>
			<p>See who your congressional representatives vote with...and
				against.  Start by selecting your state and representative.</p>
			<p>
			<div >
				<select class="dropdown" id="state" onchange="showReps()">
					<option>state</option>
					<c:forEach var="s" items="${states}">
						<option>${s}</option>
					</c:forEach>
				</select>
				<select  class="dropdown" id="reps" onchange="showNewRepInfo()">
				
				</select>
			</div>
			</p>
		</div>
	</div>

	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
      	<div class="col-md-5">
      		<div id="detail">
      			<span></span>
      		</div>
      	</div>
      	<div class="col-md-8">
      	
      	</div>
      </div>
      <div class="row">
        <div class="col-md-5">
          <h2>With</h2>
          <p><span id="repName">Your rep</span> voted most like </p>
          <div id="sim">
          	<span>
          	
          	</span>
          </div>          
        </div>
        <div class="col-md-2"></div>
        <div class="col-md-6">
          <h2>Against</h2>
          <p>And voted least like</p>
          <div id="dis">
          	<span>
          	
          	</span>
          </div>
        </div>
      </div>

      <hr>

    </div> <!-- /container -->


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