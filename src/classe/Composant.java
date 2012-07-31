package classe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.Singleton;

public class Composant 
{
	private int idComposant;
	private String nomComposant;

	private static Singleton s	= Singleton.getInstance();
	private static PreparedStatement ps_select;
	
	public Composant(int idComposant, String nomComposant) 
	{
		this.idComposant = idComposant;
		this.nomComposant = nomComposant;
	}

	public static int getIdComposant( String composant ) throws SQLException
	{
		int idComposant = 0;
		
		ps_select 	= s.getCon().prepareStatement ("SELECT * FROM NOV3_COMPONENT_PARAMETER WHERE NAME_COMPONENT=?");  
		ps_select.setString(1,composant);
		ResultSet rs = ps_select.executeQuery();
		
		if(rs.next())		
			idComposant = rs.getInt("ID_COMPONENT");
		
		return idComposant;
	}
	
	public String getNomComposant() {
		return nomComposant;
	}

	public void setNomComposant(String nomComposant) {
		this.nomComposant = nomComposant;
	}

	public int getIdComposant() {
		return idComposant;
	}

}
