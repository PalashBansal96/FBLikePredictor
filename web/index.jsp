<%--suppress XmlUnboundNsPrefix --%>
<%--
  User: Palash
  Date: 11/19/2015
  Time: 6:07 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>FB Like predictor</title>
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
		  .a {
			  color: #0f0;
		  }
		  .c {
			  color: #0bc;
		  }
		  .b {
			  color: #ff0096;
		  }
	  </style>
  </head>
  <body>
  <%
	  String id="me";
	  if(request.getParameter("id")!=null){
		  id = request.getParameter("id");
	  }
  %>
  <script>
	  function statusChangeCallback(response) {
		  console.log('statusChangeCallback');
		  console.log(response);
		  if (response.status === 'connected') {
			  document.getElementById('status').innerHTML = 'Predicting.. Please wait, this may take a moment.';
			  window.location = "/predict.jsp?id=<%=id%>&access-token="+FB.getAuthResponse()['accessToken'];
		  } else if (response.status === 'not_authorized') {
			  document.getElementById('status').innerHTML = 'Please log ' +
					  'into this app.';
		  } else {
			  document.getElementById('status').innerHTML = 'Please log ' +
					  'into Facebook.';
		  }
	  }
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

  </script>

  <div id="console">
	<span class="line">
		<span class="a">predictor@IIIT-Delhi</span>
		:
		<span class="b">~</span>
		<span class="c">$</span>
		./predict
		<span class="c"><%=id%></span>
	</span>
	  <br><br><br>

  Testing Login.
  <fb:login-button scope="public_profile,email,user_posts" onlogin="checkLoginState();">
  </fb:login-button><br><br>
  <div id="status" class="b">asd</div>

  </div>
  </body>
</html>
