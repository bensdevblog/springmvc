/*
 * usereg.js
 * Author: Ben Hansen
 * Email: ben@bensdevblog.com
 */


/*
 * FIELD INDEX REF:
 * 	0 - user name
 * 	1 - password
 * 	2 - password x2
 *	3 - email
 *	4 - phone
 */
var input_fields = [];
var error_fields = [];

$(document).ready(function(){
	//Input field assignment
	input_fields[0] = $("#userName");
	input_fields[1] = $("#password");
	input_fields[2] = $("#password_match");
	input_fields[3] = $("#email");
	input_fields[4] = $("#phone");
	
	//Error field assignment
	error_fields[0] = $("#usr_err");
	error_fields[1] = $("#pwd_err");
	error_fields[2] = $("#pwd_match_err");;
	error_fields[3] = $("#email_err");
	error_fields[4] = $("#phone_err");

});

//Validate the request form
function isFormValid() {
	var has_errors = false;
	for(var i in input_fields) {
		if(isEmpty(input_fields[i])) {
			error_fields[i].text("Must not be empty");
			error_fields[i].show();
			has_errors = true;
		} else {
			if(isTooShort(input_fields[i])) {
				error_fields[i].text("Must be 4 chars minimum");
				error_fields[i].show();
				has_errors = true;
			}
		}
		
		if(i == 1) {
			if(!isValidPass(input_fields[i],input_fields[2])) {
				error_fields[i].text("Passwords must match");
				error_fields[2].text("Passwords must match");
				error_fields[i].show();
				error_fields[2].show();
				has_errors = true;
			}
		}
		
		if(i == 3) { //Email input field
			if(!isValidEmail(input_fields[i])) {
				error_fields[i].text("Enter a valid email address");
				error_fields[i].show();
				has_errors = true;
			}
		}
	}
	if(has_errors) {
		return false;
	} else {
		return true;
	}
	
	
	function isValidEmail(field) {
		var email_str = field.val();
		for(var i = 0; i < email_str.length; i++) {
			if(email_str.charAt(i) == '@') {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
	
	function isValidPass(pass1,pass2) {
		if(pass1.val() != pass2.val()) {
			return false;
		} else {
			return true;
		}
	}
	
	function isTooShort(field) {
		if(field.val().length < 4) {
			return true;
		} else {
			return false;
		}
	}
	
	function isEmpty(field) {
		if(field.val().length == 0) {
			return true;
		} else {
			return false;
		}
	}
};

function clearErrorFields() {
	for(var i = 0; i < error_fields.length; i++) {
		error_fields[i].hide();
	}
}

function clearInputFields() {
	for(var i = 0; i < input_fields.length; i++) {
		input_fields[i].val("");
	}
}

function doPost() {
	//New user object model
	var newUser = {
		userName: input_fields[0].val(),
		password: input_fields[1].val(),
		email: input_fields[3].val(),
		phone: input_fields[4].val()
	};
	
	$.post("registration/add_user",newUser,function(result){
		$("#result").text(result);
	});
};

$(function() {
	$("#submitBtn").click(function(){
		clearErrorFields();
		if(isFormValid()) {
			console.log("For validated.. Submitting..");
			clearErrorFields();
			clearInputFields();
			doPost();
		} else {
			console.log("Form invalid");
		}
	});
});
