package testing;

import com.ahkneipp.TBA.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		String auth = new java.io.BufferedReader(new java.io.FileReader(new java.io.File(args[0]))).readLine();
		TeamReport rep = new TeamReport(836, auth);
		System.out.println(rep.getTeamNumber());
		System.out.println(rep.getTeamLocation());
		System.out.println(rep.getField("motto"));	
		System.out.println(rep.getData());
	}	
}
