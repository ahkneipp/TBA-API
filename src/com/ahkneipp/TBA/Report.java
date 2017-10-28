package com.ahkneipp.TBA;

import java.util.HashMap;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class Report
{
	HashMap<String, Object> data = null;
	Gson parser = new Gson();
	public Report(String JSON)
	{
		this.parseJSON(JSON);
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
	
}
