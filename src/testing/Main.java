package testing;

import com.ahkneipp.TBA.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		TeamReport rep = new TeamReport(836);
		System.out.println(rep.getTeamNumber());
		System.out.println(rep.getTeamLocation());
		System.out.println(rep.getField("motto"));	
		System.out.println(rep.getData());
	}	
}
