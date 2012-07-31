package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import classe.ComposantMesure;

public class DBComposantMesure
{
	private Singleton s;
	private PreparedStatement ps_insertComposantMesure;
	private PreparedStatement ps_deleteComposantMesure;
	
	public DBComposantMesure()
	{
		s	= Singleton.getInstance();
	}
	
	public void insertComposantMesure( ComposantMesure composant ) throws SQLException
	{
		ps_insertComposantMesure = s.getCon().prepareStatement ("INSERT INTO NOV3_CASTER_COMPONENT_MESURE ( ID_COMPONENT_MESURE , ID_LOAD_MESURE , ID_COMPONENT, LIBELLE, TAUX ) values(?,?,?,?,?)");  
		
		ps_insertComposantMesure.setInt 	( 1 , composant.getId_composant_mesure()	);
		ps_insertComposantMesure.setInt 	( 2 , composant.getId_charge_mesure()			 	);
		ps_insertComposantMesure.setInt	 	( 3 , composant.getId_composant()   		);
		ps_insertComposantMesure.setString 	( 4 , composant.getLibelle()        	 	);
		ps_insertComposantMesure.setDouble 	( 5 , composant.getTaux()         			);
		
		ps_insertComposantMesure.executeUpdate();
	}
	

	public int getNumeroDerniereIdComposantMesure() throws SQLException
	{
		Statement stmt = s.getCon().createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT MAX ( NVL ( ID_COMPONENT_MESURE , 0 ) ) FROM NOV3_CASTER_COMPONENT_MESURE");
		  
		int idComposantMesure = 0;
		
		while (rs.next()) 	idComposantMesure = rs.getInt(1);
				
		return idComposantMesure;
	}
	
	
	public int getNumeroDerniereIdChargeMesuree() throws SQLException
	{
		Statement stmt = s.getCon().createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT MAX ( NVL ( ID_LOAD_MESURE , 0 ) ) FROM NOV3_CASTER_COMPONENT_MESURE");
		  
		int idChargeMesuree = 0;
		
		while (rs.next())	idChargeMesuree = rs.getInt(1);
				
		return idChargeMesuree;
	}
	
	
	public void deleteComposantMesure( int idCharge ) throws SQLException
	{
		ps_deleteComposantMesure = s.getCon().prepareStatement ("DELETE FROM NOV3_CASTER_COMPONENT_MESURE WHERE ID_LOAD_MESURE=?"); 
		
		ps_deleteComposantMesure.setInt ( 1, idCharge );
	}
}
