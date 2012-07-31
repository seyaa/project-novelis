package classe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.Singleton;

public class Fonctions 
{
	private static Singleton s			= Singleton.getInstance();
	static java.sql.Timestamp dateCourante = getCurrentJavaSqlTimestamp();
	
	public static java.sql.Timestamp getCurrentJavaSqlTimestamp() 
	{
		java.util.Date date = new java.util.Date();
		return new java.sql.Timestamp(date.getTime());
	}

	public static boolean isInteger ( String chaine)
	{
		boolean isInteger;
		
		try 
		{
			Integer.parseInt(chaine);
			isInteger = true;
		}
		catch(NumberFormatException nFE) 
		{
			isInteger = false;
		}
		
		return isInteger;
	}
	
	
	public static int getIdCharge ( String charge ) throws SQLException
	{
		int id = 0;
		Statement stmt = s.getCon().createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT ID_LOAD FROM NOV3_CASTER_LOAD_PARAM WHERE NAME_LOAD = '" + charge + "'" );

		while ( rs.next() )
		id = rs.getInt("ID_LOAD");
		
		return id;
	}
	
	public static int getIdAlliage( int alliage ) throws SQLException
	{
		int id = 0;
		Statement stmt = s.getCon().createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT ID_ALLOY FROM NOV3_CASTER_ALLOY_PARAM WHERE NAME_ALLOY = '" + alliage + "'" );

		while ( rs.next() )
		id = rs.getInt("ID_ALLOY");
		
		return id;
	}
	
	
	public static void main ( String [] args ) throws SQLException
	{
		System.out.println ( getIdAlliage( 8906 ));
	}
}
