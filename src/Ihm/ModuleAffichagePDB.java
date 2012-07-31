package Ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classe.Chargement;
import db.DBAlliage;

public class ModuleAffichagePDB extends JPanel
{
	private static final long serialVersionUID = 1L;

	private JPanel panelGeneral 			= new JPanel();
	private JPanel panelNord 				= new JPanel();
	private ModuleTableur tableurPiedDeBain ;

	private JLabel poids, alliage , numero ;
	
	public ModuleAffichagePDB( Chargement chargementCreate ) throws SQLException, ClassNotFoundException
	{
		tableurPiedDeBain = new ModuleTableur( 1 , 0 , null  , chargementCreate, "null");
		DBAlliage DBAlliage = new DBAlliage();
		panelGeneral.setLayout(new BorderLayout());
		
		poids 	= new JLabel("Poids : " + chargementCreate.getPoids_pdb_precedent() + "  kg");
		alliage = new JLabel("Alliage : " + DBAlliage.getAlliageChargementPrecedent(chargementCreate));
		numero = new JLabel("Numéro : " + (chargementCreate.getIdChargement() - 1) );
		
		panelNord.setLayout(new GridLayout(1, 3));
		panelNord.add(poids);
		panelNord.add(alliage);
		panelNord.add(numero);
		
		panelGeneral.add( panelNord , "North");
		panelGeneral.add( tableurPiedDeBain , "Center");
		
		panelGeneral.setBorder(BorderFactory.createTitledBorder("Caractéristiques du pied de bain précédent"));
		
		add ( panelGeneral );
	}
}
