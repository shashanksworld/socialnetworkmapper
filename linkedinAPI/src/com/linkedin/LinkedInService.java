package com.linkedin;

import java.io.IOException;
import java.util.HashMap;

import com.data.LinkedData;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.helper.DataStoreService;
import com.helper.UrlFetchServiceUtil;

public class LinkedInService {

	public String getProfile(String clientId,String token)
	{	
		
		try {
				
				System.out.println("found auth data");
				HashMap<String,String> headers=new HashMap<String,String>();
				headers.put("Host", "api.linkedin.com");
				headers.put("Connection","Keep-Alive");
				headers.put("Authorization","Bearer "+token);
				
				String response =UrlFetchServiceUtil.httpRequest("https://api.linkedin.com/v1/people/~?", "", "GET", "application/json", headers);
				return response;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "";
	}
	public String getConnections(String clientId,String token)
	{	
		
		try {
				
				System.out.println("found auth data");
				HashMap<String,String> headers=new HashMap<String,String>();
				headers.put("Host", "api.linkedin.com");
				headers.put("Connection","Keep-Alive");
				headers.put("Authorization","Bearer "+token);
				
				String response =UrlFetchServiceUtil.httpRequest("https://api.linkedin.com/v1/people/~/connections:(headline,id,first-name,last-name,num-connections)?", "", "GET", "application/json", headers);
				return response;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "";
	}
	public String getConnectionsById(String clientId,String token,String connId)
	{	
		
		try {
				
				System.out.println("found auth data");
				HashMap<String,String> headers=new HashMap<String,String>();
				headers.put("Host", "api.linkedin.com");
				headers.put("Connection","Keep-Alive");
				headers.put("Authorization","Bearer "+token);
				
				String response =UrlFetchServiceUtil.httpRequest("https://api.linkedin.com/v1/people/id="+connId, "", "GET", "application/json", headers);
				return response;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return "";
	}
}
