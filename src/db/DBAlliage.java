package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import classe.Chargement;

public class DBAlliage 
{	
	static Singleton s	;
	static ResultSet rs;
	static PreparedStatement ps_selectChargement, ps_select, ps_selectIDAlliage, ps_selectNomAlliage;
	
	public DBAlliage() throws ClassNotFoundException, SQLException
	{
		s	= Singleton.getInstance();
	}

	// type de retour : entier  
	
	// Utilisation de la méthode getAlliageChargementPrecedent ( ailleurs )
	//	1 - Création d'un chargement 				=> Chargement chargementCreate = new Chargement ( num+1 , tabInfo[0] , .. , ... , 2 , " test" ... );	
	//	2 - Initialisation de la connexion 			=> DBAlliage dbAlliage = new DBAlliage();
	//	3 - Application de la methode ratachée 		=> dbAlliage.getAlliageChargementPrecedent( chargementCreate );
		
	public int getAlliageChargementPrecedent(Chargement c ) throws SQLException
	{

		String requete = "SELECT * FROM NOV3_CASTER_LOADING WHERE ID_LOADING=?";
		
		ps_selectChargement = s.getCon().prepareStatement (requete);
		
		ps_selectChargement.setInt(1,c.getIdChargement() - 1);
		
		rs = ps_selectChargement.executeQuery();
	 
		int alliageChargementPrecement = 0;
		int idAlliage = 0;
			
		while (rs.next())		idAlliage = rs.getInt("ID_ALLOY");
		  
		alliageChargementPrecement = getNomAlliage(idAlliage);		  
		 
		return alliageChargementPrecement;
	}

	// Retourne le couple de clé ( Alliage , Color )
	public  HashMap<Integer, String> selectAll() throws SQLException
	{
		String requete = "SELECT * FROM NOV3_CASTER_ALLOY_PARAM";
		
		PreparedStatement ps_select 	= s.getCon().prepareStatement (requete);  
		
		rs = ps_select.executeQuery();
		
		HashMap<Integer , String> liste = new HashMap<Integer , String>();
		
		while(rs.next())	liste.put(rs.getInt("NAME_ALLOY"), rs.getString("COLOR"));
		
		return liste;
	}
	
	// Retourne l'id de l'alliage en fonction du nom passé en parametre
	public int getIdAlliage( int nomAlliage ) throws SQLException
	{
		int idAlliage = 0 ;
		
		String requete = "SELECT ID_ALLOY FROM NOV3_CASTER_ALLOY_PARAM WHERE NAME_ALLOY = ?";
		
		ps_selectIDAlliage 	= s.getCon().prepareStatement (requete);  
		
		ps_selectIDAlliage.setInt(1,nomAlliage);
		
		rs = ps_selectIDAlliage.executeQuery();
		
		if(rs.next())	idAlliage = rs.getInt(1);
		
		
		return idAlliage;
	}
	
	
	
	// Retourne le nom l'alliage en fonction de l'id passée en parametre
	public int getNomAlliage( int idAlliage ) throws SQLException
	{
		int nomAlliage = 0;
		
		String requete = "SELECT NAME_ALLOY FROM NOV3_CASTER_ALLOY_PARAM WHERE ID_ALLOY = ?";
		
		ps_selectNomAlliage 	= s.getCon().prepareStatement (requete);  
		
		ps_selectNomAlliage.setInt(1,idAlliage);
		
		rs = ps_selectNomAlliage.executeQuery();
		
		if(rs.next())		nomAlliage = rs.getInt("NAME_ALLOY");
		
		return nomAlliage;
	}

}
