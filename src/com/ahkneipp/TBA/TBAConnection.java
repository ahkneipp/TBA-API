package com.ahkneipp.TBA;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.ahkneipp.util.WebsiteReader;

public class TBAConnection
{
	private final String authKey;
	private final String BASE_REQUEST = "https://www.thebluealliance.com/api/v3/";
	public static final String VERSION = "v0.1";
	public static final String APP_NAME = "FRCScoutingToBeNamed";
	public static final String AGENT = "AHK";
	
	
	public TBAConnection(String authKey)
	{
		this.authKey = authKey;
	}
	
	private HttpsURLConnection openTBAConnection(String requestArg)
	{
		HttpsURLConnection conn = null;
		try
		{
			URL requestURL = new URL(BASE_REQUEST.concat(requestArg));
			conn = (HttpsURLConnection) requestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.addRequestProperty("User-Agent", APP_NAME + "/" + VERSION);
			conn.addRequestProperty("X-TBA-App-Id", AGENT + ":" + APP_NAME + ":" + VERSION);
			conn.addRequestProperty("X-TBA-Auth-Key", this.authKey);
			conn.setInstanceFollowRedirects(true);
			conn.setIfModifiedSince(0);
			conn.connect();
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
		return conn;
	}
	
	public TeamReport getTeamData(int teamNumber) throws IOException
	{
		HttpsURLConnection conn = openTBAConnection("team/frc"+teamNumber);
		WebsiteReader tempReader = new WebsiteReader(conn.getInputStream());
		TeamReport retVal = new TeamReport(tempReader.readWebsite());
		tempReader.close();
		return retVal;
	}
	
}
