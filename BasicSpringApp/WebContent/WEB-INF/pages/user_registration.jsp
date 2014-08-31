<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>

<!-- User Registration CSS -->
<link rel="stylesheet" href="resources/css/usereg.css" />

<!-- jQuery CDN -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<!-- User Registration JavaScript -->
<script src="resources/js/usereg.js"></script>
</head>
<body>
	<h1>User Registration</h1>
	<!-- User Registration Form -->
	<table>
		<tbody>
			<tr>
				<td><label>Username</label></td>
				<td><input id="userName" /></td>
				<td><span id="usr_err"></span></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input type="password" id="password" /></td>
				<td><span id="pwd_err"></span></td>
			</tr>
			<tr>
				<td><label>Password x2</label></td>
				<td><input type="password" id="password_match" /></td>
				<td><span id="pwd_match_err"></span></td>
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><input type="email" id="email" /></td>
				<td><span id="email_err"></span></td>
			</tr>
			<tr>
				<td><label>Phone</label></td>
				<td><input type="tel" id="phone" /></td>
				<td><span id="phone_err"></span></td>
			</tr>
		</tbody>
	</table>
	<div id="submit">
		<button id="submitBtn">Submit</button>
	</div>
	<!-- Result from server -->
	<h3 id="result"></h3>
</body>
</html>