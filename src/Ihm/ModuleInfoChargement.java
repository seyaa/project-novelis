package Ihm;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import classe.Chargement;
import db.DBChargement;

public class ModuleInfoChargement extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel panelGeneral = new JPanel();

	private ModuleInfoChargement( Chargement chargementCreate) throws SQLException, ClassNotFoundException
	{
		setSize( 500 , 500 );
		panelGeneral.setLayout ( new GridLayout ( 1,2 ));
		
		panelGeneral.add(new ModuleAffichageIdentite(chargementCreate));		
		panelGeneral.add(new ModuleAffichagePDB(chargementCreate));
		
	
		add ( panelGeneral);
		setVisible(true);
	}
	
	public static void main ( String [] args ) throws ClassNotFoundException, SQLException
	{
		DBChargement DB = new DBChargement();
		Chargement chargement = DB.getChargement(5);
		
		new ModuleInfoChargement(chargement);
	}
}
