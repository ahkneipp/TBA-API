package com.ahkneipp.TBA;

import java.util.HashMap;

import com.ahkneipp.util.Cachable;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Report implements Cachable
{
	HashMap<String, Object> data = null;
	Gson parser = new Gson();
	
	private String cacheKey = null;
	
	private long lastUpdated = 0;
	
	public Report(String JSON, String key)
	{
		this.parseJSON(JSON);
		this.cacheKey = key;
	}
	
	public String getFieldValue(String fieldName)
	{
		return this.data.get(fieldName).toString();
	}
	
	public Iterable<String> getFields()
	{
		return this.data.keySet();
	}
	
	public void parseJSON(String JSON)
	{
		this.data = this.parser.fromJson(JSON, new TypeToken<HashMap <String, Object>>() {
		}.getType());
	}
	
	public Report update(String json)
	{
		this.parseJSON(json);
		this.lastUpdated = System.currentTimeMillis()/1000;
		return this;
	}
	
	public long getLastUpdated()
	{
		return this.lastUpdated;
	}
	
	public String getUniqueKey()
	{
		return this.cacheKey;
	}
	
	public boolean equals(Object o)
	{
		return this.cacheKey.equals(((Report)(o)).getUniqueKey());
	}
	
}
