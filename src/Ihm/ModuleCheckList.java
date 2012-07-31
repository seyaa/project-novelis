package Ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import classe.Charge;
import classe.Chargement;
import db.DBCharge;
import db.DBChargement;

public class ModuleCheckList extends JPanel
{
	static ChargementGraphique chargementGraphique;
	
	private static final long serialVersionUID = 1L;

	static JPanel panelGeneral 				= new JPanel();
	static JPanel panelCentre 				= new JPanel();
	static JPanel panelSud					= new JPanel();
	static JLabel labelTextPoidsTotal=new JLabel("");
	static JLabel labelTextPoidsValide=new JLabel("");

	private static String textTousLesComposants = "Charge totale de tous les composants : ";
	private static String textComposantsValides = "Charge totale des composants validés : ";
	static int poidsTotal;

	static int poidsChargesValidees;
	static int idCharge;
	
	JLabel labelType= new JLabel("");
	JLabel labelEtat= new JLabel("");
	
	static ArrayList<Charge> listCharge 	= new ArrayList<Charge>();
	
	static DBCharge dbCharge;
	static DBChargement dbChargement;
	Chargement chargementCreate;
	static GridBagConstraints gbc 				= new GridBagConstraints();
	
	public ModuleCheckList () 
	{	
		panelGeneral.setLayout( new BorderLayout());
		panelCentre.setLayout(	new GridBagLayout());
		panelSud.setLayout(new GridBagLayout());
		
		panelCentre.setBorder(BorderFactory.createTitledBorder("Composant de la check-list"));
		panelSud.setBorder(BorderFactory.createTitledBorder("Poids de la check-list"));
		
		add ( panelGeneral);
	}
	
	public static void majCheckList ( Chargement chargementCreate ) throws ClassNotFoundException, SQLException
	{		
		System.out.println ("Classe CheckList => entrée dans maj checklist");
		dbCharge = new DBCharge(); 
		dbChargement = new DBChargement();  
				
		System.out.println ("Classe CheckList => Creation de chargementGraphique ");
		chargementGraphique = new ChargementGraphique ( chargementCreate );
		System.out.println ("Classe CheckList => Fin de création de chargement graphique");
	
		panelCentre.setVisible(true);
		panelCentre.removeAll();
		panelCentre.add ( chargementGraphique );

		int nbCharge = chargementGraphique.getNbChargeDansChargement(chargementCreate) ;
		
		System.out.println ( "Classe CheckList => Nb Charge dans chargement : " + nbCharge);
		
		poidsTotal 				= dbCharge.selectPoidsDesCharges(0, chargementCreate);
		poidsChargesValidees 	= dbCharge.selectPoidsDesCharges(1, chargementCreate);
				
		labelTextPoidsTotal.setText(textTousLesComposants + poidsTotal + " kg");	
		labelTextPoidsValide.setText(textComposantsValides + poidsChargesValidees + " kg");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelSud.add(labelTextPoidsTotal, gbc);
				
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelSud.add ( labelTextPoidsValide, gbc);
				
		panelGeneral.add(panelCentre 	, "Center");
		panelGeneral.add(panelSud 		, "South");
		
		System.out.println ("MISE A JOUT DU POIDS TOTAL EN COURS ... ");
		
		int poids = dbCharge.selectPoidsDesCharges ( 1 , chargementCreate);
		dbChargement.updateTotalLoading(poids , chargementCreate);
		
		System.out.println ("MISE A JOUT DU POIDS TOTAL EFFECTUEE => " + poids);
		
		panelGeneral.validate(); 
		panelGeneral.updateUI();
		
	}
}
