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
  </head>
  <body>

  <script>
	  function statusChangeCallback(response) {
		  console.log('statusChangeCallback');
		  console.log(response);
		  if (response.status === 'connected') {
			  window.location = "/predict.jsp?access-token="+FB.getAuthResponse()['accessToken'];
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

  Please Login into Facebook
  <fb:login-button scope="public_profile,email,user_posts" onlogin="checkLoginState();">
  </fb:login-button>
  <div id="status"></div>

  </body>
</html>
