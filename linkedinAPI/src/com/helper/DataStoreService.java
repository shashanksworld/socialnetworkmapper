package com.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.TypeReference;

import com.data.LinkedData;
import com.helper.JSONCharacterEscapes;

public class DataStoreService {

	static final ObjectMapper mapper = new ObjectMapper();
	 //static AppCacheManager appcachemanager= new AppCacheManager();
	static {
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		mapper.getJsonFactory().setCharacterEscapes(new JSONCharacterEscapes());
		
	}
	
	
	public String storeAuthInfo(LinkedData obj)
	{
		try {
			String json=convertObjectToJson(obj);
			System.out.println(json);
			DataStoreManager.set(obj.getState(), obj.getId(), json.getBytes());
			System.out.println("Data Persisted");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;	
	}
	public LinkedData getAuthInfo(String clientId)
	{
		try {
			//String json=convertObjectToJson(obj);
			//System.out.println(json);
			byte[] objData= DataStoreManager.get(clientId);
			String authInfo=new String(objData);
			System.out.println("data fetched from, data store::"+authInfo);
			LinkedData linData=convertJsonToObject(authInfo);
			if(linData!=null)
			{
				System.out.println("linkedData constructed");
			}
			return linData;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;	
	}
	
	
	public String convertObjectToJson( Object linkedData ) throws JsonGenerationException , JsonMappingException , IOException
    {
    	
    	Writer writer = new StringWriter();
    	String result = "";
    	mapper.writeValue( writer , linkedData );
    	result = writer.toString();
    	return result;
    }
    
    public static LinkedData convertJsonToObject( String obj )	throws JsonParseException ,
	JsonMappingException ,
	IOException
	{
    	LinkedData dataObj = mapper.readValue( obj ,
		new TypeReference <LinkedData>()
		{
		} );
		return dataObj;
	}
	
}
