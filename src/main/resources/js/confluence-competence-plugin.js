
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
        	alert(input);
        }
        
        function test(){
        	var users = {"vaulues": ["nimi": "asd", "id": "1"]}
        	 $.ajax({
        	        type: "POST",
        	        url: "http://risto-virtualbox:1990/confluence/rest/competence/1.0/people/1",
        	        data: users,
        	        contentType: "application/json; charset=utf-8",
        	        dataType: "json",
        	        success: function(data){alert(data);},
        	        failure: function(errMsg) {
        	            alert(errMsg);
        	        }
        	  });
        }
        
