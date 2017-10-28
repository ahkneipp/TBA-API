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
	
	public Report()
	{
		
	}
	
	public String getFieldValue(String fieldName)
	{
		return null;
	}
	
	public void parseJSON(String JSON)
	{
		this.data = this.parser.fromJson(JSON, new TypeToken<HashMap <String, Object>>() {
		}.getType());
	}
	
}
