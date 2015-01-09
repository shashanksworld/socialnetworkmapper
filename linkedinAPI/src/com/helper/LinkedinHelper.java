package com.helper;

import java.util.ResourceBundle;

import com.data.LinkedData;

public class LinkedinHelper {
	
	static ResourceBundle resourceBundle= ResourceBundle.getBundle("linkedin");
	public String createAuthUrl(String clientId)
	{
		String authUrl="https://www.linkedin.com/uas/oauth2/authorization?response_type=code"+
            "&client_id="+resourceBundle.getString("APIKey")+
            "&scope="+"r_fullprofile%20r_emailaddress%20r_network"+
            "&state="+clientId.trim()+
            "&redirect_uri="+"http://socialnetworkmapper.appspot.com/linkedinCallBack.do";
		System.out.println(authUrl);
		return authUrl;
	}
	
	
	public String getToken(String code,String state)
	{
		System.out.println("################creating token URL...##############");
		String accessUrl="https://www.linkedin.com/uas/oauth2/accessToken"
            +"?grant_type=authorization_code"+
			"&redirect_uri="+"http://socialnetworkmapper.appspot.com/linkedinCallBack.do"+
            "&client_id="+resourceBundle.getString("APIKey")+
            "&client_secret="+resourceBundle.getString("SecretKey")+
            "&code="+code;
		System.out.println(accessUrl);
		
		String response =UrlFetchServiceUtil.httpRequest(accessUrl, "", "POST", "application/json", null);
		
		if(response!=null && !response.equals(""))
		{
			DataStoreService dss= new DataStoreService();
			LinkedData linObj= new LinkedData();
				linObj.setAccessToken(response);
				linObj.setState(state);
				linObj.setId(resourceBundle.getString("APIKey"));
				linObj.setTimestamp(System.currentTimeMillis());
			dss.storeAuthInfo(linObj);
			//linObj=null;
		}
		
		return response;
		
	}
	public static void logData(String info)
	{
		System.out.println(info);
	}

}
