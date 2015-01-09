package com;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

@SuppressWarnings("serial")
public class InquisitionServlet {
	public static void main(String arg[])
	{
			try {
				String jsonString="{\"access_token\":\"AQVFaFUzwmNmIVH-EAMRT4FynM9McQXBEdhd0iADTUWw6kVQ_E13R9bojJ3DjEishYvPfDJArwFvY68Shjy8s9zuXpu6MrdDcGBthNW0OCzB6MmcpcTiP9moRA0zn27L2kbRbAwF0j_YC92zPBb5iSgeqGb6E9B3tJiGjDZxrQqTX5Gmcuw\",\"expires_in\":5139471}";
				JSONObject access= new JSONObject(jsonString);
				
				System.out.println(access.get("access_token"));
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
