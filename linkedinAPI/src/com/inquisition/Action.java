package com.inquisition;
import java.io.IOException;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data.LinkedData;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.helper.DataStoreManager;
import com.helper.DataStoreService;
import com.helper.LinkedinHelper;
import com.helper.UrlFetchServiceUtil;
import com.linkedin.LinkedInService;

@Controller
public class Action {
	
	//ResourceBundle resourceBundle= ResourceBundle.getBundle("GaReportConstant");
	static LinkedinHelper linHelper=new LinkedinHelper();
	
	@RequestMapping("/fetchUrl.do")
	public void fetchURL(HttpServletRequest req, HttpServletResponse res)
	{
		System.out.println("inside fetch url....");
		String clientId=req.getParameter("clientId");
		String redirect=linHelper.createAuthUrl(clientId);
		try {
			
			res.sendRedirect(redirect);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/linkedinCallBack.do")
	public String linkedinCallBack(HttpServletRequest req, HttpServletResponse res)
	{
		System.out.println("################got call back##############");
		String code=req.getParameter("code");
		String state=req.getParameter("state");
			
		try {
			logData("code ::"+code);
			logData("state ::"+state);
			String token=linHelper.getToken(code,state);
			LinkedInService linService = new  LinkedInService();
			linService.getProfile(state, token);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "home";
	}
	@RequestMapping("/fetchPeople.do")
	public void fetchPeople(HttpServletRequest req, HttpServletResponse res)
	{
		String clientId=req.getParameter("clientId");
		logData("client id:"+clientId);
		
		
		
		try {
			LinkedInService linService = new  LinkedInService();
			DataStoreService dss= new DataStoreService();
			LinkedData linData=dss.getAuthInfo(clientId);
			
			if(linData!=null && linData.getAccessToken()!=null)
			{	String access=null;
				JSONObject accesstoken= new JSONObject(linData.getAccessToken());
				
				String profile=linService.getProfile(clientId,accesstoken.get("access_token").toString());
				res.getWriter().println(profile);
				
			}
			else
			{
				fetchURL(req,res);
			}
		}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping("/fetchConnections.do")
	public void fetchConnections(HttpServletRequest req, HttpServletResponse res)
	{
		String clientId=req.getParameter("clientId");
		logData("client id:"+clientId);
		
		try {
			LinkedInService linService = new  LinkedInService();
			DataStoreService dss= new DataStoreService();
			LinkedData linData=dss.getAuthInfo(clientId);
			
			if(linData!=null && linData.getAccessToken()!=null)
			{	String access=null;
				JSONObject accesstoken= new JSONObject(linData.getAccessToken());
				
				String connections=linService.getConnections(clientId,accesstoken.get("access_token").toString());
				res.getWriter().println(connections);
				
			}
			else
			{
				fetchURL(req,res);
			}
		}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@RequestMapping("/fetchConnectionsById.do")
	public void fetchConnectionById(HttpServletRequest req, HttpServletResponse res)
	{
		String connId=req.getParameter("connectionId");
		String clientId=req.getParameter("clientId");
		logData("client id:"+clientId);
		logData("connectionId:"+connId);
		
		try {
			LinkedInService linService = new  LinkedInService();
			DataStoreService dss= new DataStoreService();
			LinkedData linData=dss.getAuthInfo(clientId);
			
			if(linData!=null && linData.getAccessToken()!=null)
			{	String access=null;
				JSONObject accesstoken= new JSONObject(linData.getAccessToken());
				
				String connections=linService.getConnectionsById(connId,accesstoken.get("access_token").toString(),connId);
				res.getWriter().println(connections);
				
			}
			else
			{
				fetchURL(req,res);
			}
		}  catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@RequestMapping("/fetchDSData.do")
	public void fetchDSData(HttpServletRequest req, HttpServletResponse res)
	{
		String clientId=req.getParameter("clientId");
		DataStoreService dss= new DataStoreService();
		LinkedData linData=dss.getAuthInfo(clientId);
		try {
			res.getWriter().println(linData.getAccessToken());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/redirector.do")
	public String redirect(HttpServletRequest req, HttpServletResponse res)
	{
		return "home";
		
	}
	
	public static void logData(String info)
	{
		System.out.println("###############Logger###################");
		System.out.println(info);
	}

}
