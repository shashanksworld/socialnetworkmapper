package com.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class LinkedData {
	
	
	
	@PrimaryKey
	private String id=null;
	
	@Persistent
	private String state=null;
	
	@Persistent
	private String accessToken=null;
	
	@Persistent
	private long timestamp=0l;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Persistent(serialized = "true")
	private byte[] value;
	
	@Persistent
	private String nextDSOKey;	
	
	
	public byte[] getValue() {
		return value;
	}
	public void setValue(byte[] value) {
		this.value = value;
	}
	public String getNextDSOKey() {
		return nextDSOKey;
	}
	public void setNextDSOKey(String nextDSOKey) {
		this.nextDSOKey = nextDSOKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	
	
	

}
