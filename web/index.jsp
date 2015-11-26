<%--suppress XmlUnboundNsPrefix --%>
<%@ page import="javax.xml.crypto.Data" %>
<%@ page import="com.palashbansal96.fb_like_predictor.FacebookErrorException" %>
<%@ page import="com.palashbansal96.fb_like_predictor.DataFetcher" %>
<%@ page import="com.google.appengine.labs.repackaged.org.json.JSONException" %>
<%@ page import="com.palashbansal96.fb_like_predictor.Feed" %>
<%--
  Created by IntelliJ IDEA.
  User: Palash
  Date: 11/19/2015
  Time: 6:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>FB Like predictor</title>
  </head>
  <body>

  <script>

	  // This is called with the results from from FB.getLoginStatus().
	  function statusChangeCallback(response) {
		  console.log('statusChangeCallback');
		  console.log(response);
		  // The response object is returned with a status field that lets the
		  // app know the current login status of the person.
		  // Full docs on the response object can be found in the documentation
		  // for FB.getLoginStatus().
		  if (response.status === 'connected') {
			  // Logged into your app and Facebook.
			  testAPI();
		  } else if (response.status === 'not_authorized') {
			  // The person is logged into Facebook, but not your app.
			  document.getElementById('status').innerHTML = 'Please log ' +
					  'into this app.';
		  } else {
			  // The person is not logged into Facebook, so we're not sure if
			  // they are logged into this app or not.
			  document.getElementById('status').innerHTML = 'Please log ' +
					  'into Facebook.';
		  }
	  }

	  // This function is called when someone finishes with the Login
	  // Button.  See the onlogin handler attached to it in the sample
	  // code below.
	  function checkLoginState() {
		  FB.getLoginStatus(function(response) {
			  statusChangeCallback(response);
		  });
	  }

	  window.fbAsyncInit = function() {
      FB.init({
        appId      : '1662642070684966',
        xfbml      : true,
        version    : 'v2.5'
      });

		  FB.getLoginStatus(function(response) {
			  statusChangeCallback(response);
		  });

	  };

    (function(d, s, id){
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) {return;}
      js = d.createElement(s); js.id = id;
      js.src = "//connect.facebook.net/en_US/sdk.js";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

  function testAPI() {
	  console.log('Welcome!  Fetching your information.... ');
	  console.log(FB.getAuthResponse()['accessToken']);
	  FB.api('/me', function(response) {
		  console.log('Successful login for: ' + response.name);
		  document.getElementById('status').innerHTML =
				  'Thanks for logging in, ' + response.name + '!';
	  });
  }

  </script>

  <%
	String myName = "Palash Bansal";
	  String json = "";
	  DataFetcher.setAccess_token("CAACEdEose0cBAEDSlJK5yr3gWWeexcxDoympQAk5JdN4Rlc0GIualTIe8T4j3CkkVYWSpc0EM2PAbjZAaCjvp3tUNSJ5xWPgopmtOZCCBCQ1wBK5xlas2qarRCwA6KxmuJlHWIyJKugrFiFjLHyeDofhHH9zxJmrnqoba4gjbJYIErC4yCwXfBuIAK6nbyLdKZC8pgHdAZDZD");
		  try {
			  //json = DataFetcher.getData("me/feed").toString();
			  json = (new Feed("me")).getPosts().get(2).getMessage();
		  } catch (JSONException e) {
			  e.printStackTrace();
		  }catch (FacebookErrorException e) {
			  json = e.getMessage();
		  }
  %>
  <div
		  class="fb-like"
		  data-share="true"
		  data-width="450"
		  data-show-faces="true">
  </div>
  Developed by <%=json%>
  <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
  </fb:login-button>

  <div id="status">
  </div>

  </body>
</html>
