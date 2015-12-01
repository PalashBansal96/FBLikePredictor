<%@ page import="com.palashbansal96.fb_like_predictor.Feed" %>
<%@ page import="com.palashbansal96.fb_like_predictor.DataFetcher" %><%--
  User: Palash
  Date: 12/2/2015
  Time: 1:25 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Predictor</title>
</head>
<body>
<%
	String access_token = request.getParameter("access-token");
	System.out.println("Access Token: "+ access_token);
	DataFetcher.setAccess_token(access_token);
	try {
		Feed feed = new Feed("/me");
		out.write(feed.getPosts().get(0).getMessage());
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</body>
</html>
