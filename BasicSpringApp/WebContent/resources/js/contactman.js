/*
 * contactman.js
 * Author: Ben Hansen
 * Email: ben@bensdevblog.com
 */


//Display all contacts on document load
$(document).ready(function(){
	$("#deleteBtn").prop("disabled",true);
	
	//Request contact list from server
	$.get("contact/show_all",function(data){
		//Populate the contact list with response data
		for(var i in data) {
			$("#contactList").append(new Option(data[i], i));
		}
	});
});

//Event Listeners
$(function(){
	
	//Search button clicked
	$("#searchBtn").click(function() {
		 //The clients query
		var query_str = $("#query").val();
		
		//Request query validation
		$.post("contact/search", {queryString: query_str},function(result){
			if(result == "success") {
				console.log("Search executed successfully.");
				$("#query").val(""); 
				$("#contactList").find('option').remove();  //Truncate previous contact list
				
				//Query has been validated, request contact list from server.
				$.get("contact/find_contacts",{firstName: query_str},function(result){
					for(var i in result) {
						$("#contactList").append(new Option(result[i], i));
					}
				});
			//Validation failed.
			} else {
				console.log("Search failed.");
			}
		});
	});
	
	//Add contact button clicked
	$("#addContactBtn").click(function() {
		
		//New contact values
		var first_name = $("#firstName").val();
		var last_name = $("#lastName").val();
		var phone_number = $("#phoneNumber").val();
		var email_addr = $("#emailAddr").val();
		
		//Contact model object
		var newContact = {
			firstName: first_name,
			lastName: last_name,
			phoneNumber: phone_number,
			emailAddr: email_addr
		};
		
		//Send add request
		$.post("contact/add", newContact,function(result){
			if(result == "success") {
				console.log("Contact added successfully.");
				
				//Reset field values
				$("#firstName").val("");
				$("#lastName").val("");
				$("#phoneNumber").val("");
				$("#emailAddr").val("");
				
				//Force refresh to display updated contact list
				location.reload();
			} else {
				console.log("Failed to add contact.");
			}
		});
	});
	
	//Show All button clicked
	$("#showAllBtn").click(function() {
		
		//Force refresh to display all contacts
		location.reload();
	});

	//Add button clicked
	$("#addBtn").click(function(){
		
		//Toggle new contact area display
		if($("#addContactArea").is(":visible")) {
			$("#addContactArea").hide();
		} else {
			$("#addContactArea").show();
		}
	});
	
	//Delete button clicked
	$("#deleteBtn").click(function() {
		var contact_list = document.getElementById('contactList');
		//Find selected contact
		for(var i in contact_list) {
			if(i <= contact_list.length) {
				//Selected contact located
				if(contact_list[i].selected == true){
					//Use contacts email address as unique identifier
					var identifier = $("#email_addr_val").text();
					
					//Request deletion of selected contact
					$.post("contact/delete_contact",{contactEmail: identifier},function(result){
						console.log(result);
						$("#deleteBtn").prop("disabled",true);
						
						//Force refresh
						location.reload();
					});
				}
			}
		}
	});
	
	//Contact selection change
	$("#contactList").change(function() {
		var contact_list = document.getElementById('contactList');
		//Find selected contact
		for(var i in contact_list) {
			if(i <= contact_list.length) {
				//Found selected contact
				if(contact_list[i].selected == true) {
					$("#deleteBtn").prop("disabled",false);
					var name = contact_list.options[i].text;
					console.log(contact_list.options[i].text);
					$("#contactInformation").show();
					
					//Request selected contacts information from server
					$.get("contact/display_contact",{contactName: name},function(data){
						$("#f_name_val").text(data[0]);
						$("#l_name_val").text(data[1]);
						$("#phone_num_val").text(data[2]);
						$("#email_addr_val").text(data[3]);
					});
				}
				else {
					continue;
				}
			}
		}
	});
});