package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import classe.Chargement;
import classe.Fonctions;

public class DBChargement
{
	private PreparedStatement ps_selectChargement;
	private PreparedStatement ps_insertChargement;
	private PreparedStatement ps_updateTotalLoading;
	private PreparedStatement ps_updateCoreLoading;
	private PreparedStatement ps_updateCoreUnLoading;

	ResultSet resultatSet;
	Statement stmt = null;
	
	Singleton s			= Singleton.getInstance();
	
	public DBChargement() throws ClassNotFoundException, SQLException
	{	
		ps_selectChargement = s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_LOADING WHERE ID_LOADING=?");   
	    
		ps_insertChargement = s.getCon().prepareStatement ("INSERT INTO NOV3_CASTER_LOADING ( ID_LOADING , TYPE_LOADING , SPAN ,  LOGINNAME_1 ,  LOGINNAME_2 , ID_ALLOY , DATE_LOADING, WEIGHT_LAST_PDB ) values(?,?,?,?,?,?,?,?)");
		
        ps_updateCoreLoading 	= s.getCon().prepareStatement ("UPDATE NOV3_CASTER_LOADING SET NB_CORE_LOADING = NB_CORE_LOADING + 1 WHERE ID_LOADING=?");
        ps_updateCoreUnLoading = s.getCon().prepareStatement  ("UPDATE NOV3_CASTER_LOADING SET NB_CORE_UNLOADING = NB_CORE_UNLOADING - 1 WHERE ID_LOADING=?");
   	}
	

	  public void insertChargement(Chargement c) throws SQLException
	  {
		try
		{
			ps_insertChargement.setInt			(1,c.getIdChargement());
			ps_insertChargement.setInt			(2,c.getTypeFour());
			ps_insertChargement.setString		(3,c.getRelais());
			ps_insertChargement.setString		(4,c.getNom1());
			ps_insertChargement.setString		(5,c.getNom2());
			ps_insertChargement.setInt			(6,c.getIdAlliage());
			ps_insertChargement.setTimestamp	(7,Fonctions.getCurrentJavaSqlTimestamp());
			ps_insertChargement.setInt			(8,c.getPoids_pdb_precedent());
			
			ps_insertChargement.executeUpdate();
     	} 
		catch(SQLException e)
		{
			System.out.println(e);
		}  
	  }
	  
		public int getNumeroDernierChargement() throws SQLException
		{
			Statement stmt = s.getCon().createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX ( NVL ( ID_LOADING , 0 ) ) FROM NOV3_CASTER_LOADING");
			  
			int numChargement = 0;
			
			while (rs.next())	numChargement = rs.getInt(1);
					
			return numChargement;
		}
		
	  public void updateTotalLoading(int poids , Chargement chargement) throws SQLException
	  {
	      ps_updateTotalLoading 	= s.getCon().prepareStatement ("UPDATE NOV3_CASTER_LOADING SET TOTAL_LOADING = TOTAL_LOADING + ? WHERE ID_LOADING=?");

	        
		  ps_updateTotalLoading.setInt(1 ,poids);
		  ps_updateTotalLoading.setInt(2 ,chargement.getIdChargement());

		  ps_updateTotalLoading.executeUpdate(); 
	  }
	  	  
	  public void updateNbCoreLoading(Chargement c) throws SQLException
	  {
		  ps_updateCoreLoading.setInt(1 ,c.getIdChargement());
		  ps_updateCoreLoading.executeUpdate(); 
	  }
	  
	  public void updateNbCoreUnLoading(Chargement c) throws SQLException
	  {
		  ps_updateCoreUnLoading.setInt(1 ,c.getIdChargement());
		  ps_updateCoreUnLoading.executeUpdate(); 
	  }
  
	public Chargement getChargement(int idChargement ) throws SQLException 
	{
		Chargement c = null;
		ps_selectChargement.setInt(1,idChargement);
		ResultSet rs = ps_selectChargement.executeQuery();
		
		if(rs.next())
		{
			c = new Chargement(	idChargement 						, 
								rs.getInt	("ID_ALLOY") 			, 
								rs.getString("LOGINNAME_1") 		, 
								rs.getString("LOGINNAME_2") 		, 
								rs.getInt	("NB_CORE_LOADING") 	, 
								rs.getInt	("NB_CORE_UNLOADING")  	,
								rs.getInt	("WEIGHT_LAST_PDB") 		 	);
		}
		return c;
	}
	
//	public static void main ( String [] args) throws SQLException, ClassNotFoundException 
//	{
//		DBChargement DB = new DBChargement();
//		
//		Chargement c = DB.getChargement(1);
//		
//		System.out.println ( c.toString());
//		
//	}
}
