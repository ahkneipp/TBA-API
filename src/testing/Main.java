package testing;

import com.ahkneipp.TBA.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		java.io.BufferedReader authreader = new java.io.BufferedReader(new java.io.FileReader(new java.io.File(args[0])));
		String auth = authreader.readLine();
		TBAConnection conn = new TBAConnection(auth);
		conn.getTeamData(339);
		conn.getTeamData(836);
		authreader.close();
	}	
}
