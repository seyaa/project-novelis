package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classe.Chargement;
import classe.Fonctions;
import classe.Pion;

public class DBPion 
{
	private static Singleton s;
	private PreparedStatement ps_insertPion;
	private PreparedStatement ps_selectPion;
	
	public DBPion()
	{
		s	= Singleton.getInstance();
	}
	
	public void insertPion( int idPion , Chargement chargementCreate, int etatDuPion ) throws SQLException
	{
		ps_insertPion = s.getCon().prepareStatement ("INSERT INTO NOV3_CASTER_PION ( ID_PION , ID_LOADING , DATE_PION, STATE_PION) values(?,?,?,?)");  
		
		ps_insertPion.setInt 		( 1 , idPion );
		ps_insertPion.setInt 		( 2 , chargementCreate.getIdChargement() );
		ps_insertPion.setTimestamp 	( 3 , Fonctions.getCurrentJavaSqlTimestamp() );
		ps_insertPion.setInt 		( 4 , etatDuPion );
		
		ps_insertPion.executeUpdate();
	}
	
	public Pion selectPion ( int idPion , int idChargement ) throws SQLException
	{
		ps_selectPion = s.getCon().prepareStatement ("SELECT * FROM NOV3_CASTER_PION WHERE ID_PION=? AND ID_LOADING=?");  
		ps_selectPion.setInt(1, idPion);
		ps_selectPion.setInt(2, idChargement);
		
		ResultSet rs = ps_selectPion.executeQuery();
		
		Pion tmp = null;
		
		while(rs.next()) 
		{	
			tmp = new Pion(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3) , rs.getInt(4));
		}
		
		return tmp;
	}
	
	
	public static void main ( String [] args ) throws SQLException, ClassNotFoundException
	{
		DBPion DB = new DBPion();
		//DBChargement DBChargement = new DBChargement();
				
		//Chargement chargement = DBChargement.getChargement(1);
	
		Pion p =  DB.selectPion(1, 1);
		
		System.out.println (p );
		
		//DB.insertPion(1, chargement);
	}

}
