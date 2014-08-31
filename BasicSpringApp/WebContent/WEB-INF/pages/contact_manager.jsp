<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact Manager</title>

<!-- Contact Manager CSS -->
<link rel="stylesheet"
	href="resources/css/contactman.css">

<!-- jQuery CDN -->
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

<!-- Contact Manager JavaScript -->
<script src="resources/js/contactman.js"></script>

</head>
<body>
	<h1>Contact Manager</h1>
	
	<!-- Main Contact Management Area -->
	<table>
		<tr>
			<!-- Contact Search Controls -->
			<td><input id="query" placeholder="contacts first name" />
				<button id="searchBtn">Search</button></td>
		</tr>
		<tr>
			<td>
				<button id="addBtn">Add Contact</button>
				<button id="deleteBtn" disabled>Remove Contact</button>
			</td>
		</tr>
		<tr>
			<td>
				<table id="addContactArea">
					<tr>
						<td><input id="firstName" class="addContactField"
							placeholder="first name" /></td>
					</tr>
					<tr>
						<td><input id="lastName" class="addContactField"
							placeholder="last name" /></td>
					</tr>
					<tr>
						<td><input type="tel" id="phoneNumber"
							class="addContactField" placeholder="phone number" /></td>
					</tr>
					<tr>
						<td><input type="email" id="emailAddr"
							class="addContactField" placeholder="johndoe@whatever.com" /></td>
					</tr>
					<tr>
						<td><button id="addContactBtn" class="addContactField">Done</button></td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- Contact List -->
		<tr>
			<td><select id="contactList" size="10">
			</select></td>
		</tr>
		<tr>
			<td>
				<button id="showAllBtn">Show All Contacts</button>
			</td>
		</tr>
	</table>
	
	<!-- Contact Information Area -->
	<table id="contactInformation">
		<tr>
			<td>First Name: <span id="f_name_val"></span></td>
		</tr>
		<tr>
			<td>Last Name: <span id="l_name_val"></span></td>
		</tr>
		<tr>
			<td>Phone Number: <span id="phone_num_val"></span></td>
		</tr>
		<tr>
			<td>Email Address: <span id="email_addr_val"></span></td>
		</tr>
	</table>
</body>
</html>