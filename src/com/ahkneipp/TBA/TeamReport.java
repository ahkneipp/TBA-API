package com.ahkneipp.TBA;

public class TeamReport extends Report
{

	public TeamReport(String JSON)
	{
		super(JSON);
		System.out.println(JSON);
		for(String key: this.data.keySet())
			System.out.println(key + ":" + this.data.get(key) + ":" + ((this.data.get(key) != null) ? this.data.get(key).getClass() : "null"));
	}
	
}