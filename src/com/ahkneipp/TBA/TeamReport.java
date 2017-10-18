package com.ahkneipp.TBA;

import java.io.IOException;
import java.util.HashMap;
//TODO IOExceptions will be thrown too often, find a way to prevent them
public class TeamReport extends Report
{
	/**
	 * Constructs a new TeamReport with data from The Blue Alliance, 
	 * using the teamId in the form the API expects.  If id is malformed,
	 * data will not be properly returned, use the
	 * TeamReport(int) constructor instead.
	 * @param teamId
	 * 		The API team ID of the team.  Requires format of "frc" + <em>team number</em>
	 */
	public TeamReport(String teamId)
	{
		super("team/" + teamId);
	}
	/**
	 * Constructs a new Team Report with data from team <em>teamNumber</em>
	 * @param teamNumber
	 * 		The team's official FIRST Robotics Competition number.
	 * 		I.E. Kilroy Robotics'  Team number is 339
	 */
	public TeamReport(int teamNumber)
	{
		this("frc" + Integer.toString(teamNumber));
	}
	/**
	 * Constructs a new TeamReport with data from The Blue Alliance for default team 339
	 */
	public TeamReport()
	{
		this("frc339");
	}
	/**
	 * Returns any arbitrary field the TBA data contains, or null if the field doesn't exist
	 * @param fieldKey
	 * 		The name of the data field.  For example, the fieldKey to get the team number is "team_number"
	 * @return
	 * 		The content of the field corresponding to key <<fieldKey>>,
	 * 		or null if it doesn't exist
	 * @throws IOException
	 * 		If the app fails to connect to TBA
	 */
	public String getField(String fieldKey) throws IOException
	{
		this.update();
		return this.data.get(fieldKey);
	}
	//TODO does it really return null ever?
	/**
	 * 
	 * @return
	 * 		The team number of the team this object describes, according to TBA
	 * 		Or null if no successful connection has been made
	 * @throws IOException
	 * 		If the app fails to connect to TBA
	 */
	public String getTeamNumber() throws IOException
	{
		return this.getField("team_number");
	}
	/**
	 * @return
	 * 		The nickname of the team this object describes, according to TBA
	 * 		For example, the nickname for team 339 would be Kilroy Robotics
	 * @throws IOException
	 * 		If the app fails to connect to TBA
	 */
	public String getTeamNickName() throws IOException
	{
		return this.getField("nickname");
	}
	/**
	 * @return
	 * 		The country of origin of the team this object describes.
	 * @throws IOException
	 * 		If the app fails to connect to TBA
	 */
	public String getTeamCountry() throws IOException
	{
		return this.getField("country_name");
	}
	/**
	 * @return
	 * 		The general portion of the mailing address of the team
	 * 		For example, Team 339's location is:
	 * 		Stafford, Virginia 22554, USA
	 * @throws IOException
	 * 		If the app fails to connect to TBA
	 */
	public String getTeamLocation() throws IOException
	{
		return this.getField("location");
	}
	/**
	 * @return
	 * 		The region in which the team this object describes
	 * 		competes.  For example, team 339's region is Virginia
	 * @throws IOException
	 * 		If the app fails to connect to TBA
	 */
	public String getTeamRegion() throws IOException
	{
		return this.getField("region");
	}
	//TODO for testing purposes for now, may remove in future.
	public HashMap<String,String> getData()
	{
		return this.data;
	}
}