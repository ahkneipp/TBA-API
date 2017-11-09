package com.ahkneipp.TBA;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.ahkneipp.util.Cache;
import com.ahkneipp.util.WebsiteReader;

public class TBAConnection
{
	private final String authKey;
	private final String BASE_REQUEST = "https://www.thebluealliance.com/api/v3/";
	private final Cache cache = new Cache();
	public static final String VERSION = "v0.1";
	public static final String APP_NAME = "FRCScoutingToBeNamed";
	public static final String AGENT = "AHK";	 
	
	public TBAConnection(String authKey)
	{
		this.authKey = authKey;
	}
	
	private HttpsURLConnection openTBAConnection(String requestArg, long ifModifiedSince) throws MalformedURLException, ProtocolException, IOException
	{
		HttpsURLConnection conn = null;
		URL requestURL = new URL(BASE_REQUEST.concat(requestArg));
		conn = (HttpsURLConnection) requestURL.openConnection();
		conn.setRequestMethod("GET");
		conn.addRequestProperty("User-Agent", APP_NAME + "/" + VERSION);
		conn.addRequestProperty("X-TBA-App-Id", AGENT + ":" + APP_NAME + ":" + VERSION);
		conn.addRequestProperty("X-TBA-Auth-Key", this.authKey);
		conn.setInstanceFollowRedirects(true);
		conn.setIfModifiedSince(ifModifiedSince);
		conn.connect();
		return conn;
	}
	
	public TeamReport getTeamData(int teamNumber) throws IOException
	{
		String requestCode = "team/frc"+teamNumber;
		HttpsURLConnection conn = openTBAConnection(requestCode, this.cache.keyLastUpdated(requestCode));
		WebsiteReader tempReader = new WebsiteReader(conn.getInputStream());
		TeamReport retVal;
		if(this.cache.keyIsCached(requestCode))
		{
			if(conn.getResponseCode() == 304)
			{
				retVal = (TeamReport) this.cache.get(requestCode);
			}
			retVal =  (TeamReport) this.cache.get(requestCode).update(tempReader.readWebsite());
		}
		retVal = new TeamReport(tempReader.readWebsite(),requestCode);
		this.cache.add(retVal);
		tempReader.close();
		return retVal;
	}
	
	
}
