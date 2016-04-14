
var hostAddress = window.location.href;
var split = hostAddress.split("/");
var http = split[0];
var address = split[2];

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
            var wordlist = ['JavaScript', 'TypeScript', 'Linux', 'HTML5', 'ikebana', 'smoke signals',
              'Spring', 'Java', 'Confluence', 'Lisp'];
            var list = wordlist.map(function(item) {
              return [item, Math.floor(1 + Math.random() * 20)];
            });
            var configuration = {
              fontFamily: 'Times, serif',
              hover: function(item, dimension, event) {
                if (item) {
                  console.log('Item hovered: ' + item);
                }
              },
              click: function(item, dimension, event) {
                console.log('Item clicked: ' + item);
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
        	ajaxRequest("POST",
        			http + "//" + address + "/confluence/rest/competence/1.0/addCompetence/1",
        			JSON.stringify(inputData), //Sends CompetenceModel object
        			"",
        			"application/json"
        	);
        }
        
        function addUser(){
        	var name = document.getElementById("name").value;
        	var id = document.getElementById("id").value;
        	var user = { id: ""};
        	//user.name = name;
        	user.id = id;
        	ajaxRequest("POST",
        			http + "//" + address + "/confluence/rest/competence/1.0/people/" + name + "/" + id,
        			JSON.stringify(user), //Sends PersonModel object with only id
        			"",
        			"application/json"
        	);
        }
        
        function getAllUsers(){
        	var users = {"values" : []};
        	ajaxRequest("GET",
        			http + "//" + address + "/confluence/rest/competence/1.0/people/",
        			users,
        			"json",
        			""
        	);
        }
        
        function ajaxRequest(method, requestUrl, requestData, requestDataType, requestContentType){
        	$.ajax({
    	        type: method,
    	        url: requestUrl,
    	        data: requestData,
    	        dataType: requestDataType,
    	        contentType: requestContentType,
    	        success: function(data){
    	        	console.log(data);
    	        	if(data != null){
    	        		return data;
    	        	}
    	        },
    	        failure: function(errMsg) {
    	            alert(errMsg);
    	        }
    	  });
        }
