<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World!</title>
<style>
	body {
		text-align:center;
	}
	
	#site_nav {
		margin: 0 auto;
	}
	
	.link {
		display:inline;
		margin: 5px 5px;
	}
	
	.link a {
		font-size:20px;
		font-weight:bold;
	}
	
	.link a:hover {
		font-size:25px;
	}
</style>
</head>
<body>
<h1>${msg}</h1>
<div id="site_nav">
	<div class="link"><a href="contact_manager">Contact Manager</a></div>
	<div class="link"><a href="user_registration">User Registration</a></div>
</div>
</body>
</html>