
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
        	var host = window.location.hostname;
        	var http = location.protocol;
        	alert(input);
        	$.ajax({
    	        type: "POST",
    	        url: http + host +":1990/confluence/rest/competence/1.0/addCompetence/1",
    	        data: input,
    	        contentType: "application/json",
    	        success: function(data){console.log(data);},
    	        failure: function(errMsg) {
    	            alert(errMsg);
    	        }
    	  });
        }
        
        function addUser(){
        	var name = document.getElementById("name").value;
        	var id = document.getElementById("id").value;
        	var users = {"id" : id, "nimi" : name};
        	var host = window.location.hostname;
        	var http = location.protocol;
        	 $.ajax({
        	        type: "POST",
        	        url: http + host +":1990/confluence/rest/competence/1.0/people/" + name + "/" + id,
        	        data: JSON.stringify(users),
        	        contentType: "application/json",
        	        success: function(data){console.log(data);},
        	        failure: function(errMsg) {
        	            alert(errMsg);
        	        }
        	  });
        }
        
        function getAllUsers(){
        	var users = {"values" : []};
        	var host = window.location.hostname;
        	var http = location.protocol;
        	 $.ajax({
        	        type: "GET",
        	        url: http + host + ":1990/confluence/rest/competence/1.0/people/",
        	        data: users,
        	        dataType: "json",
        	        success: function(data){console.log(data);},
        	        failure: function(errMsg) {
        	            alert(errMsg);
        	        }
        	  });
        }
        
        
