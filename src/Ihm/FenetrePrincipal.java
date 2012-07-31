package Ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classe.CCComposant;
import classe.Chargement;
import db.DBComposant;

public class FenetrePrincipal extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private String tabPion[] 								= {"pion 1","pion 2","pion 3"};
	private String tabTolerance[] 							= {"Tol min","Tol cible" , "Tol max"};
	private String tabDernierPion[] 						= {"dernier pion"};
	
	private String tabCompositionValidee[] 					= {"Validé"};
	private String tabCompositionNonValidee[] 				= {"En cours"};
	
	private JPanel panelGeneral	 							= new JPanel();
	private JPanel panelDroit								= new JPanel(); 
	private JPanel panelHautDroit 							= new JPanel();
	private JPanel panelMilieuDroit		 					= new JPanel();
	private JPanel panelBasDroit 							= new JPanel(); 
	private JPanel panelPion 								= new JPanel();
	private JPanel panelGauche								= new JPanel();
	private	JPanel panelHautGauche 							= new JPanel();
	private	JPanel panelBasGauche 							= new JPanel();

	private JButton boutonValiderPion = new JButton("valider");
	
	private ModuleTableur  tableurPionPrecedent, tableurInsertionPion  , tableurTolerance, tableurCompoSimulee, tableurSimulTousComposant;

	private ModuleSaisieManuelle moduleManu;
	
	ModuleCheckList moduleCheckList = new ModuleCheckList();

	public FenetrePrincipal( Chargement chargementCreate ) throws SQLException, ClassNotFoundException
	{		
		moduleManu 					= new ModuleSaisieManuelle(chargementCreate);
		
		tableurPionPrecedent		= new ModuleTableur ( 1 , 0 , tabDernierPion 	, chargementCreate, "pion prec");
		tableurInsertionPion 		= new ModuleTableur ( 3 , 1 , tabPion 	 		, chargementCreate, "pion");
		tableurTolerance 			= new ModuleTableur ( 3 , 0 , tabTolerance 		, chargementCreate, "Tol");
		
		tableurCompoSimulee 		= new ModuleTableur ( 1 , 0 , tabCompositionNonValidee 	, chargementCreate , "?");
		tableurSimulTousComposant 	= new ModuleTableur ( 1 , 0 , tabCompositionValidee	, chargementCreate , "V");

		ArrayList<CCComposant> listeComposant = new ArrayList<CCComposant>();
		DBComposant dbComposant = new DBComposant();
		
		listeComposant = dbComposant.getComposantDapresAlliage(chargementCreate.getIdAlliage());
		
		for ( int i = 0 ; i < listeComposant.size() ; i++)
		
			tableurTolerance.insertComposantDansTableau(listeComposant.get(i));
		
		panelGeneral.setLayout	( new GridLayout(1,2));
		
		panelDroit.setLayout	( new BorderLayout());
		panelGauche.setLayout	( new GridLayout(2, 1));
	
		panelHautDroit.setLayout(new BorderLayout());
		panelBasDroit.setLayout	(new GridLayout(2, 1));
				
		panelHautDroit.add(new ModuleAffichageIdentite(chargementCreate) , "North");		
		panelHautDroit.add(new ModuleAffichagePDB(chargementCreate) 	 , "Center");
		
		panelHautDroit.setBorder(BorderFactory.createEtchedBorder());
		panelMilieuDroit.setBorder(BorderFactory.createTitledBorder("Check-list contenant les charges du four"));
		panelBasDroit.setBorder(BorderFactory.createTitledBorder("Composition validée et en cours de la check-list"));
		panelDroit.setBorder(BorderFactory.createEtchedBorder());
		panelGauche.setBorder(BorderFactory.createEtchedBorder());
		panelGauche.setBorder(BorderFactory.createEtchedBorder());
		
		panelMilieuDroit.add( moduleCheckList );
		
		panelBasDroit.add(tableurCompoSimulee);
		panelBasDroit.add(tableurSimulTousComposant);
		
		panelDroit.add ( panelHautDroit 	, "North");
		panelDroit.add ( panelMilieuDroit 	, "Center");
		panelDroit.add ( panelBasDroit 		, "South");
		
		panelHautGauche.add( new ModuleAjoutDechetV2( chargementCreate) );
		
		panelBasGauche.setLayout( new GridBagLayout() );
		GridBagConstraints gbc = new GridBagConstraints();

		panelPion.setLayout( new GridBagLayout());
		
		boutonValiderPion.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelPion.add ( tableurInsertionPion	, gbc);		
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelPion.add ( boutonValiderPion	 , gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelPion.add ( tableurPionPrecedent 	 , gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelBasGauche.add ( moduleManu 		 , gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelBasGauche.add ( panelPion 			 , gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelBasGauche.add ( tableurTolerance	 , gbc);
		
		tableurTolerance.setBorder(BorderFactory.createTitledBorder("Ajout d'un composant avec composition connue"));
		panelPion.setBorder(BorderFactory.createTitledBorder("Saisie et consulation des pions"));

		panelGauche.add ( panelHautGauche);
		panelGauche.add ( panelBasGauche);
		
		panelGeneral.add(panelGauche);
		panelGeneral.add(panelDroit);
		
		add (panelGeneral);
	}


	public void actionPerformed(ActionEvent e) 
	{
		int resultat;
		
		

		//If you're here, the return value was null/empty.
		//setLabel("Come on, finish the sentence!");

		
		if ( e.getSource().equals(boutonValiderPion)) 
		{
//			resultat = JOptionPane.showConfirmDialog(null, "Quel pion voulez-vous ajouter ?", "Confirmation d'ajout", JOptionPane.YES_NO_OPTION);
			
			Object[] possibilities = {"Pion 1", "Pion 2", "Pion 3"};
			String s = (String)JOptionPane.showInputDialog( null, "Quel pion voulez-vous ajouter ? ","Customized Dialog",  JOptionPane.PLAIN_MESSAGE, null, possibilities,"ham");

			//If a string was returned, say so.
			if ((s != null) && (s.length() > 0)) 
			{ 			   
			   int numero = Integer.parseInt(s.substring((s.length()-1), s.length()));


			   if ( tableurInsertionPion.verifLigneComplete ( numero , tableurInsertionPion )) 
			   {
				   JOptionPane.showMessageDialog(null, "Pion inséré", "Validation", JOptionPane.INFORMATION_MESSAGE);
			  
				   ArrayList<String> listeDouble = new ArrayList<String>();
				   
				   listeDouble = tableurInsertionPion.listeValeurLigne(numero, tableurInsertionPion);
				   
				   System.out.println ( listeDouble.toString() );
				   
				   for ( int i = 0 ; i < tabDernierPion.length ; i++)
				   {
					  // tableurPionPrecedent.setValue(tableurPionPrecedent, Integer.parseInt(listeDouble.get(i)));
				   }
				   
			   }
			   else
			   {
				   JOptionPane.showMessageDialog(null, "Impossible d'ajouter un pion incomplet", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);	
			   }
			}
		
		}
	}
}
