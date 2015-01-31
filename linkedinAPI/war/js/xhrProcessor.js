/**
 * 
 */
var userProfile=null;
var userJSON=null;
function processXHR(parms,url) {
	
	var isSynchronous = false;
	if (window.XMLHttpRequest) {
		request = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		request = new ActiveXObject("Msxml2.XMLHTTP");
	} else {
		request = new ActiveXObject("Microsoft.XMLHTTP");
	}
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			console.log("Successful Request");
			var responseText = request.responseText;
			
			if(url=="/fetchConnections.do?")
			{	connections=responseText;
				if(connections!=null)
				{
					jsonData=(new X2JS()).xml_str2json(connections).connections;	
					console.log("data fetched");
					if(jsonData.person.length>0)
						plotNodes();
				}
			}
			
			if(url=="/fetchPeople.do?")
				{
				userProfile=responseText;
				userJSON=(new X2JS()).xml_str2json(userProfile);
				document.getElementById("userProfile").innerHTML="Name:"+userJSON.person["first-name"]+" "+userJSON.person["last-name"]+"<br>"+userJSON.person["headline"];
				}
			
			}
		}
			request.open('POST', url, true);
			request.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			
			request.send(parms);
		}
		
		function fetchXHRData()
		{
			params="clientId="+getClientInfo();
			processXHR(params,"/fetchConnections.do?");
			
		}
		function fetchLoginProfile()
		{
			params="clientId="+getClientInfo();
			processXHR(params,"/fetchPeople.do?");
			
			
		}
		
		
		function getClientInfo()
		{
			
			if(localStorage.getItem("SocialNetworkMapperId")==null)
			{
				localStorage.setItem("SocialNetworkMapperId",generateUUID());
				return localStorage.getItem("SocialNetworkMapperId"); 
			}
			else
				{
				return localStorage.getItem("SocialNetworkMapperId");
				}
		
		}
		
		function generateUUID() {
		    var d = new Date().getTime();
		    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
		        var r = (d + Math.random()*16)%16 | 0;
		        d = Math.floor(d/16);
		        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
		    });
		    return uuid;
		};
		function loadLocalData()
		{
				jsonData=(new X2JS()).xml_str2json(localStorage.getItem("connections")).connections
		}
		function clearLocalData()
		{
			localStorage.removeItem("SocialNetworkMapperId");
		}
		
		
		