package com.ahkneipp.TBA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

public class Report
{
	//TODO
	//The version number, application name, and agent requesting data from TBA, will change to be constructor arguments.
	public static final String VERSION = "v0.1";
	public static final String APP_NAME = "339Scouting";
	public static final String AGENT = "AHK";
	public Report()
	{
		this("");
	}
	
	public Report(String requestArg)
	{
		try
		{
			this.realURL = new URL(BASE_REQUEST.concat(requestArg));
			this.conn = (HttpsURLConnection) this.realURL.openConnection();
			this.conn.setRequestMethod("GET");
			this.conn.addRequestProperty("User-Agent", APP_NAME + "/" + VERSION);
			this.conn.addRequestProperty("X-TBA-App-Id", AGENT + ":" + APP_NAME + ":" + VERSION);
			this.conn.setInstanceFollowRedirects(true);
			this.conn.setIfModifiedSince(0);
			this.dataGetter = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		catch(MalformedURLException e)
		{
			System.out.println("BAD URL: EXITING");
			System.exit(1);
		}
		catch(ProtocolException e)
		{
			System.out.println("Failed to connect: Quitting (Temporary measure)");
			System.exit(1);
		}
		catch(IOException e)
		{
			System.out.println("An unknown error occured");
			e.printStackTrace();
			System.exit(1);
		}	
	}	
	public void update() throws IOException
	{
		//TODO probably really slow, and also memory intensive
		conn.disconnect();
		conn.getInputStream().close();
		this.conn = (HttpsURLConnection) this.realURL.openConnection();
		this.conn.setRequestMethod("GET");
		this.conn.addRequestProperty("User-Agent", APP_NAME + "/" + VERSION);
		this.conn.addRequestProperty("X-TBA-App-Id", AGENT + ":" + APP_NAME + ":" + VERSION);
		this.conn.setInstanceFollowRedirects(true);
		this.conn.setIfModifiedSince(this.lastRetrievalMillis);
		this.dataGetter = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		//we didn't get a 304 not modified
		if(!this.conn.getHeaderField(0).substring(9,12).equals("304"))
		{
			this.dataGetter = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder in = new StringBuilder();
			String[] tokens = null;
			while(this.dataGetter.ready())
			{
				char c = (char)dataGetter.read();
				in.append(c);
			}
			tokens = tokenize(in.toString());
			for(int i = 0;i < tokens.length-1; i+=2)
			{
				data.put(tokens[i], tokens[i+1]);
			}
		}			
		this.lastRetrievalMillis = this.requestCal.getTimeInMillis();
	}
	
	private String[] tokenize(String inputData)
	{
		String[] tokens = inputData.split("(: )|(, \")");
		for(int i = 0; i < tokens.length; i++)
		{
			tokens[i] =tokens[i].replaceAll("[\"{}]","");
		}
		return tokens;
	}
	
	//endpoint for all API communications
	protected final String BASE_REQUEST = "https://www.thebluealliance.com/api/v2/";
	//The real url for our requests.
	private URL realURL = null;
	//Array to hold all the returned data keys (first dimension) and values (second dimension)
	//TODO update comment and consider making private and adding protected getter.
	protected HashMap<String, String> data = new HashMap<String, String>();
	//The URL connection to keep between checks.
	private HttpsURLConnection conn = null;
	//Reader to pull stuff off the network stream
	private BufferedReader dataGetter = null;
	//Calendar object to make sure we don't use too much bandwidth.
	private Calendar requestCal = Calendar.getInstance();
	//Data for ifModifiedSince header.
	private long lastRetrievalMillis = 0;
}
