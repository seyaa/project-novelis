package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import classe.Charge;
import classe.Chargement;

public class DBCharge
{
	private static PreparedStatement  ps_selectPoidsChargesValidees,ps_selectPoidsToutesLesCharges;
	
	ResultSet resultatSet;
	Statement stmt = null;
	Chargement chargementCreate;
	Singleton s	;
	
	public DBCharge() throws ClassNotFoundException, SQLException
	{
		s	= Singleton.getInstance();
	}
		
	public void insertCharge(Chargement chargementCreate , Charge charge ) throws ParseException, SQLException, ClassNotFoundException
	{
		String requete = "INSERT INTO NOV3_CASTER_LOAD ( ID_LOAD, ID_LOADING, QUANTITY, CATEGORY_LOAD, TYPE_LOAD, POSITION_LOAD, STATE_CHECK_LIST, DATE_RECEPTION, ORIGIN, DATE_ADD, ID_ALLOY  ) values(?,?,?,?,?,?,?,?,?,?,?)" ;
		
		PreparedStatement ps_insertCharge = s.getCon().prepareStatement (requete);  																																																							

		try
		{			
			ps_insertCharge.setInt		(1,charge.getIdCharge());
			ps_insertCharge.setInt		(2,chargementCreate.getIdChargement());
			ps_insertCharge.setInt		(3,charge.getQuantite());
			ps_insertCharge.setString	(4,charge.getCategorie_charge());
			ps_insertCharge.setString	(5,charge.getType_charge());
			ps_insertCharge.setInt		(6,charge.getPosition_charge());
			ps_insertCharge.setInt		(7,charge.getStatut());
			ps_insertCharge.setTimestamp(8,charge.getDateReception());
			ps_insertCharge.setString	(9,charge.getProvenance());
			ps_insertCharge.setTimestamp(10,charge.getDateAjout());
			ps_insertCharge.setInt		(11,charge.getIdAlliage());
		
			ps_insertCharge.executeUpdate();
		} 
		catch(SQLException e)
		{
			System.out.println(e);
		}  
	}
	
	
	public String getNomCharge ( int idCharge ) throws SQLException
	{
		String requete = "SELECT NAME_LOAD FROM NOV3_CASTER_LOAD_PARAM WHERE ID_LOAD_PARAM = '" + idCharge + "'" ;
		
		String nom = "";
		Statement stmt = s.getCon().createStatement();
		
		ResultSet rs = stmt.executeQuery(requete);

		while ( rs.next() ) nom = rs.getString("NAME_LOAD");
		
		return nom;
	}
			
	public int getIdCharge ( String charge ) throws SQLException
	{
		String requete = "SELECT ID_LOAD_PARAM FROM NOV3_CASTER_LOAD_PARAM WHERE NAME_LOAD = '" + charge + "'";
		
		int id = 0;
		
		Statement stmt = s.getCon().createStatement();
		
		ResultSet rs = stmt.executeQuery(requete );

		while ( rs.next() )	id = rs.getInt("ID_LOAD_PARAM");
		
		return id;
	}
		

		
	public void supressionCharge ( int idCharge , int idChargement) throws SQLException
	{
		String requete = "DELETE FROM NOV3_CASTER_LOAD WHERE ID_LOAD = ? AND ID_LOADING = ?";
				
		PreparedStatement ps_supressionCharge = s.getCon().prepareStatement(requete);
		
		ps_supressionCharge.setInt(1 , idCharge);
		ps_supressionCharge.setInt(2 , idChargement);
		
		ps_supressionCharge.executeUpdate(); 
	}
		
	public int selectPoidsDesCharges ( int etat , Chargement chargementCreate) throws SQLException
	{
		System.out.println ("Classe DBCHARGE => Entrée dans selectPoids" );
		System.out.println ("Classe DBCHARGE => ETAT => " + etat );
		System.out.println ("Classe DBCHARGE => ID CHARGEMENT : " + chargementCreate.getIdChargement() );
		
		ps_selectPoidsToutesLesCharges = s.getCon().prepareStatement ("SELECT QUANTITY FROM NOV3_CASTER_LOAD WHERE ID_LOADING=? ");
		ps_selectPoidsChargesValidees = s.getCon().prepareStatement ("SELECT QUANTITY FROM NOV3_CASTER_LOAD WHERE ID_LOADING=? AND STATE_CHECK_LIST = ?");

		int poids = 0;	
		ResultSet rs;
		
		if ( etat == 0 )
		{
			ps_selectPoidsToutesLesCharges.setInt(1, chargementCreate.getIdChargement());
			rs = ps_selectPoidsToutesLesCharges.executeQuery();
		}
		else
		{
			ps_selectPoidsChargesValidees.setInt(1, chargementCreate.getIdChargement());
			ps_selectPoidsChargesValidees.setInt(2, etat);
			rs = ps_selectPoidsChargesValidees.executeQuery();
		}
		
		while ( rs.next() )	poids += rs.getInt("QUANTITY");
		
		return poids;
	}
	
	public ArrayList<Charge> selectToutesLesCharges(Chargement chargementCreate) throws SQLException 
	{		
		ArrayList<Charge> listeCharge = new ArrayList<Charge>();
		
		String requete = "SELECT * FROM NOV3_CASTER_LOAD WHERE ID_LOADING=?";
		
		PreparedStatement ps_selectAllCharge = s.getCon().prepareStatement (requete);
		
		ps_selectAllCharge.setInt(1, chargementCreate.getIdChargement());
		
		ResultSet rs = ps_selectAllCharge.executeQuery();
				
		while(rs.next())
		{			
			Charge charge = new Charge
								( 
									rs.getInt("ID_LOAD")				,
									rs.getInt("ID_LOADING")				,	 
									rs.getInt("QUANTITY")				, 
									rs.getString("CATEGORY_LOAD") 		, 
									rs.getString("TYPE_LOAD") 			, 
									rs.getInt("POSITION_LOAD")			, 
									rs.getInt("STATE_CHECK_LIST")		, 
									rs.getTimestamp("DATE_RECEPTION")	,
									rs.getString("ORIGIN") 				, 
									rs.getTimestamp("DATE_ADD")			, 
									rs.getInt("POSITION_LOAD")
								);
			
			listeCharge.add(charge);
		}
		return listeCharge;
	}
	
	public int getNumeroDernierePosition(Chargement chargementCreate) throws SQLException
	{	 
		String requete = "SELECT MAX ( NVL ( POSITION_LOAD, 0 ) ) FROM NOV3_CASTER_LOAD WHERE ID_LOADING=?";
		
		PreparedStatement ps_selectNumDernierePosition = s.getCon().prepareStatement (requete);
		
		ps_selectNumDernierePosition.setInt(1,chargementCreate.getIdChargement());
		
		ResultSet rs = ps_selectNumDernierePosition.executeQuery();
		
		int position = 0;
		
		while (rs.next()) 	position = rs.getInt(1);
		
		return position;
	}
	
	public int getNumeroDerniereIdCharge() throws SQLException
	{
		Statement stmt = s.getCon().createStatement();
		
		String requete = "SELECT MAX ( NVL ( ID_LOAD , 0 ) ) FROM NOV3_CASTER_LOAD";
		
		ResultSet rs = stmt.executeQuery(requete);
		  
		int idCharge = 0;
		
		while (rs.next())	idCharge = rs.getInt(1);
				
		return idCharge;
	}
	
	public int selectNombreDeCharges(Chargement chargementCreate) throws SQLException 
	{	
		String requete = "SELECT * FROM NOV3_CASTER_LOAD WHERE ID_LOADING=?";
		
		PreparedStatement ps_selectAllCharge = s.getCon().prepareStatement (requete);
		
		ps_selectAllCharge.setInt(1,chargementCreate.getIdChargement());
		
		ResultSet rs = ps_selectAllCharge.executeQuery();
		
		int cpt = 0 ;
				
		while(rs.next())	cpt++;
			
		return cpt;
	}
	
	public int selectNombreDeChargesValidees(Chargement chargementCreate) throws SQLException 
	{	
		String requete = "SELECT * FROM NOV3_CASTER_LOAD WHERE ID_LOADING=? AND STATE_CHECK_LIST = 1";
		
		PreparedStatement ps_selectChargesValidees = s.getCon().prepareStatement (requete);
		
		ps_selectChargesValidees.setInt(1,chargementCreate.getIdChargement());
		ResultSet rs = ps_selectChargesValidees.executeQuery();
		int cpt = 0 ;
				
		while(rs.next())	cpt++;
			
		return cpt;
	}
	
	public Charge selectCharge(int idChargeParam , int idChargementParam) throws SQLException 
	{	
		
		int idCharge, idChargement;
		
		idCharge = idChargeParam;
		idChargement = idChargementParam;
		
		System.out.println ( " Classe : DBCharge => Entrée dans la méthode [SELECT CHARGE]");
		System.out.println ( " Classe : DBCharge => ID charge : " + idCharge);
		System.out.println ( " Classe : DBCharge => ID chargement : " + idChargement);
		
		int poids, position, etat, id_alliage; 
		String categorie, type, provenance  ;
		Timestamp date_recep, date_ajout ;

		String requete = "SELECT * FROM NOV3_CASTER_LOAD WHERE ID_LOADING=? AND ID_LOAD=?";
		
		PreparedStatement ps_selectCharge = s.getCon().prepareStatement (requete);
		
		ps_selectCharge.setInt(1,idChargement);
		ps_selectCharge.setInt(2,idCharge);
		ResultSet rs = ps_selectCharge.executeQuery();
		Charge charge = null;
				
		while(rs.next())
		{
			idCharge 		= rs.getInt("ID_LOAD")				;	
			idChargement 	= rs.getInt("ID_LOADING")			;	 
			poids 			= rs.getInt("QUANTITY")				;
			categorie 		= rs.getString("CATEGORY_LOAD") 	;
			type 			= rs.getString("TYPE_LOAD") 		; 
			position 		= rs.getInt("POSITION_LOAD")		;
			etat 			= rs.getInt("STATE_CHECK_LIST")		; 
			date_recep 		= rs.getTimestamp("DATE_RECEPTION")	;
			provenance 		= rs.getString("ORIGIN") 			; 
			date_ajout 		= rs.getTimestamp("DATE_ADD")		; 
			id_alliage 		= rs.getInt("ID_ALLOY")				;
			
			charge = new Charge(idCharge, idChargement, poids, categorie, type, position, etat, date_recep, provenance, date_ajout, id_alliage);
		}
		
		System.out.println ( " \nClasse : DBCharge => Charge crée : " + charge.toString());
		
		return charge;
	}
	
	public  void validationCharge ( int idCharge , int idChargement) throws SQLException
	{
		String requete = "UPDATE NOV3_CASTER_LOAD SET STATE_CHECK_LIST = 1 WHERE ID_LOAD = ? AND ID_LOADING = ?";
		
		PreparedStatement ps_validationCharge = s.getCon().prepareStatement(requete);

		
	
		ps_validationCharge.setInt(1 , idCharge);
		ps_validationCharge.setInt(2 , idChargement);
		
		ps_validationCharge.executeUpdate(); 
	}
	public static void main ( String [] args) throws ClassNotFoundException, SQLException
	{
//		DBChargement DBChargement = new DBChargement();
//		DBCharge 	 DBCharge 	  = new DBCharge();
		
		//Chargement chargementCreate = DBChargement.getChargement(7);
		
//		DBCharge.validationCharge(26, 15);
		
//		System.out.println ( charge.toString() );
		
		//System.out.println ( chargementCreate.toString() );
	//	System.out.println ( DBCharge.getNumeroDernierePosition(chargementCreate) );
		
		//System.out.println ( DBCharge.selectNombreDeCharges(chargementCreate) );
		//System.out.println ( DBCharge.getNumeroDerniereIdCharge() );
		
		//System.out.println ( DBCharge.getNumeroDernierChargement() );
		
	}
}
