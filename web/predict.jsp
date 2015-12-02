<%@ page import="com.palashbansal96.fb_like_predictor.Feed" %>
<%@ page import="com.palashbansal96.fb_like_predictor.DataFetcher" %>
<%@ page import="com.palashbansal96.fb_like_predictor.Predict" %><%--
  User: Palash
  Date: 12/2/2015
  Time: 1:25 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Predictor</title>
	<style type="text/css">
		body {
			background-color: #000
		}
		#console {
			font-family: courier, monospace;
			color: #fff;
			width:750px;
			margin-left:auto;
			margin-right:auto;
			margin-top:100px;
			font-size:14px;
		}
		.line {
			margin: -20px;
		}
		a {
			color: #0bc;
			text-decoration: none;
		}
		#a {
			color: #0f0;
		}
		#c {
			color: #0bc;
		}
		#b {
			color: #ff0096;
		}
	</style>
</head>
<body>
<div id="console">
	<span class="line">
		<span id="a">predictor@IIIT-Delhi</span>
		:
		<span id="b">~</span>
		<span id="c">$</span>
		./predict
		<span id="c"><%=request.getParameter("id")%></span>
	</span>
	<br><br><br>
	Testing Login.
	<br><br>
	<div id="b">Predicting.. Please wait, this may take a moment.</div> <br><br><br>
	<%
		String access_token = request.getParameter("access-token");
		System.out.println("Access Token: "+ access_token);
		DataFetcher.setAccess_token(access_token);
		try {
			Predict predict = new Predict();
			predict.predict(request.getParameter("id"), out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
</div>
</body>
</html>
