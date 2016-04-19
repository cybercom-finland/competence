var hostAddress = window.location.href;
var split = hostAddress.split("/");
var http = split[0];
var address = split[2];
var currentUser;
var ajaxResult;
getCurrentUser();

$( document ).ready(function(){
	makeWordCloud();
});

function refreshAutocomplete() {
          var dataList = jQuery("#competenceAutocomplete");
          var input = jQuery("#competenceField");
          jQuery.get("$req.contextPath/rest/competence/1.0/autocomplete.json",
            {prefix: input.val()}, function( data ) {
              console.log("Got data: " + JSON.stringify(data.value));
              dataList.empty();
              data.value.sort().forEach(function(item) {
                dataList.append('<option value="' + item + '">' + item + '</option>');
              });
          });
        }

function makeWordCloud() {
	if (window.WordCloud) {
		var wordlist = ajaxResult;
		console.log(wordlist);
		var list;
		list = Object.keys(wordlist).map(function(value, index) {
			var listItem = [value, Math.floor(1 + Math.random() * 20)];
			return listItem;
	    });
		console.log(JSON.stringify(list));
	    var configuration = {
		    fontFamily: 'Times, serif',
		    hover: function(item, dimension, event) {
			    if (item) {
			    	console.log('Item hovered: ' + item);
			    }
		    },
		    click: function(item, dimension, event) {
			    console.log('Item clicked: ' + item);
			    craftModal(item , "person");
		    },
		    weightFactor: function(size) {
		    	return Math.log(size + 1) * 30;
		    },
		    list: list
	    };
	    WordCloud(document.getElementById('tagCloud'), configuration );
    } else {
    	setTimeout(makeWordCloud(), 500);
    }
}
        
        function addCompetence(){
        	var input = document.getElementById("competenceField").value;
        	var inputData = { name: input };
        	$.ajax({
    	        type: "POST",
    	        url: http + "//" + address + "/confluence/rest/competence/1.0/addCompetence/" + currentUser.id,
    	        data: JSON.stringify(inputData), //Sends CompetenceModel object
    	        contentType: "application/json",
    	        success: function(data){
    	        	if(data != null){
    	        		ajaxResult = data;
    	        		makeWordCloud();
    	        	}
    	        },
    	        failure: function(errMsg) {
    	            alert(errMsg);
    	        }
    	  });
        }
        
        function addUser(){
        	$.ajax({
    	        type: "POST",
    	        url: http + "//" + address + "/confluence/rest/competence/1.0/people/"+ currentUser.id,
    	        data: JSON.stringify(currentUser), //Sends PersonModel object with only id
    	        contentType:"application/json; charset=utf-8",
    	        dataType:"json",
    	        success: function(data){
    	        	if(data != null){
    	        		ajaxResult = data;
    	        	}
    	        	getCompetences();
    	        },
    	        failure: function(errMsg) {
    	            alert(errMsg);
    	        }
    	  });
        }
        
        function getAllUsers(){
        	var users = {"values" : []};
        	$.ajax({
    	        type: "GET",
    	        url: http + "//" + address + "/confluence/rest/competence/1.0/people/",
    	        data: users,
    	        dataType: "json",
    	        success: function(data){
    	        	if(data != null){
    	        		ajaxResult = data;
    	        	}
    	        },
    	        failure: function(errMsg) {
    	            alert(errMsg);
    	        }
    	  });
        }
        
       
        
        function getCurrentUser() {
			var user = { id : "", name : ""};
		    $.ajax({
		    	url: "/confluence/rest/prototype/1/user/current",
			    type: 'GET',
			    dataType: 'json',
			    async: false,
			    success: function(data) {
			        user.name = data.name;
			        user.id = AJS.Data.get("remote-user-key");
			        currentUser = user;
			        console.log(currentUser);
			        console.log(JSON.stringify(currentUser));
			        addUser();
			        
		        	}
		        });
        }
        
        function getCompetences(){
        	$.ajax({
	            type: "GET",
	            url: http + "//" + address + "/confluence/rest/competence/1.0/person/competences/"+ currentUser.id,
	            dataType: "json",
	            success: function(data){
	            	if(data != null){
	            		ajaxResult = data;
	            		makeWordCloud();
	            	}
	            },
	            failure: function(errMsg) {
	                alert(errMsg);
	            }
	        });
        }
        
        function craftModal(item, type) {
	        if(type == "person"){
	        	var htmlString = item ;
	            $( "#modalHeader" ).html(htmlString);
	            
	            htmlString = "<div>" + "Endosement score: " + "</div>";
	            htmlString += "<div>" + "Endosers: " + "</div>";
	            $( "#modalContent" ).html(htmlString);
	            
	            htmlString = "person's competence";
	            $( "#modalFooter" ).html(htmlString);
	            
	            AJS.dialog2("#demo-dialog").show();
        	}
        }
        
        function debug(){
        	var users = {"values" : []};
        	ajaxRequest("GET",
        			http + "//" + address + "/confluence/rest/competence/1.0/debug/",
        			"",
        			"json",
        			""
        	);        	
        	setTimeout(
        			function(){
        				console.log(ajaxResult);
        	        	console.log(JSON.stringify(ajaxResult));
        				alert(JSON.stringify(ajaxResult));
        			}, 2000);
        }
        
