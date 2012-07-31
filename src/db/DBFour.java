package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBFour 
{
	private static PreparedStatement ps_select;
	private static Singleton s	= Singleton.getInstance();

	public static int getIdFour( String four ) throws SQLException
	{
		int idComposant = 0;
		
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_FOUR_PARAM WHERE NAME_FOUR=?");  
		ps_select.setString(1,four);
		ResultSet rs = ps_select.executeQuery();
		
		if(rs.next())		
			idComposant = rs.getInt("ID_FOUR");
		
		return idComposant;
	}
	
	
	public static String getNomFour( int idFour ) throws SQLException
	{
		String nomFour = null;
		
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_FOUR_PARAM WHERE ID_FOUR=?");  
		ps_select.setInt(1,idFour);
		ResultSet rs = ps_select.executeQuery();
		
		if(rs.next())		
			nomFour = rs.getString("NAME_FOUR");
		
		return nomFour;
	}
	
	public static HashMap<String, Integer> selectAll() throws SQLException
	{
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_FOUR_PARAM");  
		ResultSet rs = ps_select.executeQuery();
		HashMap<String , Integer> selectAll = new HashMap<String , Integer>();
		
		while(rs.next())
			selectAll.put( rs.getString("NAME_FOUR"), rs.getInt("FOUR_CAPACITY"));
		
		return selectAll;
	}
	
	
	public static void main ( String [] args) throws SQLException
	{
		System.out.println (  getNomFour( 0 ) );
	}

	
}
