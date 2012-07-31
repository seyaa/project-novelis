package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import classe.CCComposant;


public class DBComposant 
{	
	private static Singleton s	= Singleton.getInstance();
	private static PreparedStatement ps_select;
	
	public static int getIdComposant( String composant ) throws SQLException
	{
		int idComposant = 0;
		
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_COMPONENT_PARAM WHERE NAME_ALLOY=?");  
		ps_select.setString(1,composant);
		ResultSet rs = ps_select.executeQuery();
		
		if(rs.next())		
			idComposant = rs.getInt("ID_COMPONENT");
		
		return idComposant;
	}
	
	
	
	
	
	public String getNomComposant( int idComposant ) throws SQLException
	{
		String nomComposant = "";
		
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_COMPONENT_PARAM WHERE ID_COMPONENT=?");  
		ps_select.setInt(1,idComposant);
		ResultSet rs = ps_select.executeQuery();
		
		if(rs.next())		
			nomComposant = rs.getString("NAME_COMPONENT");
		
		return nomComposant;
	}
	
	public ArrayList<CCComposant> getComposantDapresAlliage(int idAlli ) throws SQLException
	{
		PreparedStatement ps_selectChargement = s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_COMPONENT_TOL WHERE ID_ALLOY=? ");
		
		ps_selectChargement.setInt(1,idAlli);
		
		ResultSet rs = ps_selectChargement.executeQuery();
	 
		int idComposant , idAlliage;
		double tol_min, tol_cible, tol_max;
		ArrayList<CCComposant> listeComposant = new ArrayList<CCComposant>();
			
		while (rs.next())
		{
			idAlliage 	= rs.getInt("ID_ALLOY");
			idComposant = rs.getInt("ID_COMPONENT");
			tol_min 	= rs.getDouble("TOL_MIN");
			tol_cible 	= rs.getDouble("TOL_TARGET");
			tol_max 	= rs.getDouble("TOL_MAX");
			
			CCComposant identiteComposant = new CCComposant(idAlliage, idComposant, tol_min, tol_max, tol_cible);
			
			listeComposant.add ( identiteComposant );
		}
		return listeComposant;
	}
	
	
	public static HashMap<Integer, String> selectAll() throws SQLException
	{
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_COMPONENT_PARAM");  
		ResultSet rs = ps_select.executeQuery();
		HashMap<Integer , String> selectAll = new HashMap<Integer , String>();
		
		while(rs.next())
			selectAll.put(rs.getInt("ID_COMPONENT"), rs.getString("NAME_COMPONENT"));
		
		return selectAll;
	}
	


}
