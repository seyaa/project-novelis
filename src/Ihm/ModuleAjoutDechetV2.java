package Ihm;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import classe.Charge;
import classe.Chargement;
import classe.Fonctions;
import db.DBAlliage;
import db.DBCharge;
import db.DBComposant;
import db.DBComposantMesure;

public class ModuleAjoutDechetV2 extends JPanel implements ActionListener , ItemListener
{
	private static final long serialVersionUID = 1L;
	private int cptLigne = 1;
	private JButton boutonAddLigne ;
	private JPanel panelGeneral, panelCodebarre, panelDroit ,panelAjout , panelGaucheBas2, panelGauche, panelDroitHaut,panelGaucheCentre, panelGaucheBas ,  panelGaucheHaut , panelDroitCentre;
	private JButton boutonValider;
	private static ArrayList <LigneSaisieDechet> listeSaisieDechet; 
	
	private final int nbLigne = 5;
	
	private static JLabel labelResultat 		= new JLabel(" Total : 0 kg");
	private JLabel labelPoidsQte 				= new JLabel("              poids      x    quantité");
	private JLabel labelInformation				= new JLabel("");
	private JLabel labelType					= new JLabel("");

	private CheckboxGroup groupeCategorieDechet = new CheckboxGroup();
	
	private Checkbox checkBoxAmma 				= new Checkbox("Amma"	,groupeCategorieDechet	,false);
	private Checkbox checkBoxLingot 			= new Checkbox("Lingot"	,groupeCategorieDechet	,true );
	private Checkbox checkBoxDechet 			= new Checkbox("Déchet"	,groupeCategorieDechet	,false);
  
	private JComboBox selectionType 			= new JComboBox();
	private JComboBox selectionAlliage 			= new JComboBox();

	private String categorie 					= "";
	private String type							= "";
	private int alliage							= 0;

	private String[] tabAmma 					= { "Si", "As50", "AFe80", "AMn85", "Zn", "Cu"} ;
	private String[] tabLingot					= { "16kg" , "T" } ;

	int resultat;

	private String[] tabDechet = 	{	"ExtVert","ExtNoir","bobinot3C","rives3C","compacteJaune","compacteVert",
												"compacteRouge","compacteBleu","compacteViolet","compacteBlanc","rivesDm","bobineATremper","Q190" };
	
	private int[] tabAlliage					= { 1050, 8011 ,8906 ,8979 ,8079 ,8666 ,8006 ,1200 ,3003 ,3903, 3904};
	
	private Chargement chargementCreate;
	
	private ModuleCodeBarre moduleCodebarre;
	
	public ModuleAjoutDechetV2( Chargement chargementCreate)
	{		
		//moduleCodebarre = new ModuleCodeBarre(chargementCreate);
		
		this.chargementCreate = chargementCreate;
			
		panelGeneral		= new JPanel();
		panelDroit 			= new JPanel();
		panelDroitCentre 	= new JPanel();
		panelGaucheBas 		= new JPanel();
		panelGaucheBas2 	= new JPanel();
		panelGaucheCentre 	= new JPanel();
		panelGaucheHaut 	= new JPanel();
		panelGauche 		= new JPanel();
		panelDroitHaut		= new JPanel();
		panelAjout			= new JPanel();
		panelCodebarre 		= new JPanel();
		
		//panelCodebarre.add ( moduleCodebarre );
		listeSaisieDechet 	= new ArrayList<LigneSaisieDechet>();
		
		boutonValider 		= new JButton("Valider");
		
		
		panelAjout.setLayout( new GridLayout(1,2));
		
		// TITRE PANEL 
		panelGaucheHaut.setBorder(BorderFactory.createTitledBorder	("1 - Choix de la catégorie à ajouter"));
		panelGaucheCentre.setBorder(BorderFactory.createTitledBorder("2 - Choix du type à ajouter"));
		panelGaucheBas.setBorder(BorderFactory.createTitledBorder	("Renseignement sur l'ajout"));
		panelDroit.setBorder(BorderFactory.createTitledBorder		("3 - Ajout du poids et quantité "));
		panelGaucheBas2.setBorder(BorderFactory.createTitledBorder	("Validation"));
		
		// PARTIE GAUCHE	
		panelGauche.setLayout( new GridLayout(4,1));
		panelGaucheHaut.setLayout( new GridLayout(1,3));
		panelGaucheBas.setLayout ( new GridLayout(3, 1));
		panelGaucheCentre.setLayout( new FlowLayout());
		panelGeneral.setLayout ( new BorderLayout());
		panelGaucheBas2.setLayout ( new FlowLayout());
		
		panelGaucheHaut.add( checkBoxAmma   ); checkBoxAmma.addItemListener(this);
		panelGaucheHaut.add( checkBoxLingot ); checkBoxLingot.addItemListener(this);
		panelGaucheHaut.add( checkBoxDechet ); checkBoxDechet.addItemListener(this);

		panelGaucheBas.add ( labelInformation );
		panelGaucheBas.add ( labelType );
		panelGaucheBas.add ( labelResultat);

		panelGaucheBas2.add ( boutonValider );
		
		panelGauche.add ( panelGaucheHaut  );
		panelGauche.add ( panelGaucheCentre );
		panelGauche.add ( panelGaucheBas  );
		panelGauche.add ( panelGaucheBas2);
		
		// PARTIE DROITE
		
		
		

		panelDroit.setLayout		( new BorderLayout()	);
		panelDroitCentre.setLayout 	( new GridLayout(5, 1)	);
		panelDroitHaut.setLayout 	( new BorderLayout()	);
	    
		for ( int i = 0 ; i < 5 ; i++ ) 
		{
			LigneSaisieDechet ligne = new LigneSaisieDechet(chargementCreate);
			listeSaisieDechet.add( ligne );
			panelDroitCentre.add( ligne );
		}
	
		panelDroitHaut.add( labelPoidsQte  , "North");
		
		
	
				
		boutonValider.addActionListener(this);

		selectionType.addActionListener(this);
		selectionAlliage.addActionListener(this);
		
		panelDroit.add ( panelDroitCentre ,"Center");
		panelDroit.add ( panelDroitHaut ,"North");
		
		// PANEL GENERAL
		panelAjout.add ( panelGauche);
		panelAjout.add ( panelDroit);
			
		panelGeneral.add(panelCodebarre , "North");
		panelGeneral.add(panelAjout , "Center");
		
		add ( panelGeneral );
	}
	
	public static void majPoids(Chargement chargementCreate)
	{
		labelResultat.setText("Total : " + getResultatListe() + " kg");
	}

	public void revalidate() 
	{
		super.revalidate();
	};
	
	public static int getResultatListe()
	{
		int resultat = 0;
		
		for ( int i = 0 ; i < listeSaisieDechet.size() ; i++ )
		{
			resultat += listeSaisieDechet.get(i).getResultat();
		}
		return resultat;
	}

	public boolean aUnAlliage()
	{	
		return alliage != 0;
	}
	
	public void actionPerformed(ActionEvent e) 
	{		
		labelResultat.setText("Total : " + getResultatListe() + " kg");
		
		if (e.getSource().equals(selectionAlliage) )
		{			
			try
			{
				alliage = Integer.parseInt(selectionAlliage.getSelectedItem().toString());	
				System.out.println ( alliage ) ;
			}
			catch (Exception y) 
			{

			}
		}
		
		if (e.getSource().equals(selectionType) )
		{			
			try
			{
				type = selectionType.getSelectedItem().toString();	
				System.out.println ( type ) ;
			}
			catch (Exception y) 
			{

			}
		}

		
		
		
		if ( aUnAlliage()) 	labelType.setText("Type : " + getType() + "    -     Alliage : " + getAlliage());
		
		else				labelType.setText("Type : " + getType() );
		
		labelResultat.setText("Total : " + getResultatListe() + " kg");
		
		if ( e.getSource().equals(boutonValider))
		{
			int position = 0;
			
			if ( getResultatListe() == 0) JOptionPane.showMessageDialog(null, "Impossible d'ajouter une charge nulle au chargement", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);			
			
			else
			{
				resultat = JOptionPane.showConfirmDialog(null, "Ajouter " + categorie  + " ( " + getResultatListe() + " kg de " + type + ") au chargement ? ", "Confirmation d'ajout", JOptionPane.YES_NO_OPTION);			

				if ( resultat == 0 )
				{
					Charge charge = null;
					int id_charge = 0; int id_chargement = 0; int id_alliage = 0; int poids = 0; 
					Timestamp date_ajout = Fonctions.getCurrentJavaSqlTimestamp();
					
					DBCharge dbCharge = null ;
					DBAlliage dbAlliage = null;
					DBComposantMesure dbComposantMesure = null;
					DBComposant dbComposant = null;
					
					try 
					{
						dbAlliage = new DBAlliage();
					} 
					catch (ClassNotFoundException e2) 
					{
						e2.printStackTrace();
					} 
					catch (SQLException e2) 
					{
						e2.printStackTrace();
					}
					
					try 
					{
						dbCharge = new DBCharge();
						dbComposantMesure = new DBComposantMesure();
						dbComposant = new DBComposant();
												
						id_chargement 	= chargementCreate.getIdChargement();
						poids 			= getResultatListe();
						id_alliage 		= dbAlliage.getIdAlliage(getAlliage());
						id_chargement 	= chargementCreate.getIdChargement();
						position		= dbCharge.getNumeroDernierePosition(chargementCreate); // on l'incrémentera par la suite
						id_charge		= dbCharge.getNumeroDerniereIdCharge();			
							
						System.out.println ( "Classe ajoutDechetV2 => id charge : " + (id_charge+1));
						System.out.println ( "Classe ajoutDechetV2 => id chargement : " +id_chargement);
						System.out.println ( "Classe ajoutDechetV2 => poids : " +poids);
						System.out.println ( "Classe ajoutDechetV2 => type : " +type);
						System.out.println ( "Classe ajoutDechetV2 => categorie : " +categorie);
						System.out.println ( "Classe ajoutDechetV2 => position : " +(position+1));
						System.out.println ( "Classe ajoutDechetV2 => etat : " + 0 );
						System.out.println ("Classe ajoutDechetV2 => date ajout  : " +date_ajout);
						
						charge = new Charge((id_charge+1), id_chargement, poids, type, categorie, (position+1), 0, null, null, date_ajout, id_alliage  );
						
						System.out.println ( "Classe ajoutDechetV2 => Insertion de la charge : ");
						dbCharge.insertCharge(chargementCreate, charge);
						System.out.println ( "Classe ajoutDechetV2 => charge insérée : ");
						ModuleCheckList.majCheckList ( chargementCreate );
						System.out.println ( "Classe ajoutDechetV2 => Mise a jour effectuée ");
					} 
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					} 
					catch (ParseException r)
					{
						r.printStackTrace();
					}
					

				}
			}
		}
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getCategorie()
	{
		return this.categorie;
	}
	
	public int getAlliage()
	{
		return this.alliage;
	}

	public void itemStateChanged(ItemEvent e) 
	{      
		alliage = 0;
		type = "";
		String s = (String) (e.getItem());
		viderlisteSelection(selectionType);

		 if (  s.equals("Lingot") )
		 {
			 selectionAlliage.setVisible(false);
			 
			 categorie = "lingot";
		
			 for ( int i = 0 ; i < tabLingot.length ; i++)		selectionType.addItem(tabLingot[i]); 
			 
			 type = selectionType.getSelectedItem().toString() ;
			 panelGaucheCentre.add(selectionType);
			 
			 labelType.setText( " Type : " + type);
		 }
		 
		 if (  s.equals("Amma") )
		 {	
			 selectionAlliage.setVisible(false);
			 
			 categorie = "amma";
		
			 for ( int i = 0 ; i < tabAmma.length ; i++)	 	selectionType.addItem(tabAmma[i]);
			 
			 type = selectionType.getSelectedItem().toString() ;
			 panelGaucheCentre.add(selectionType);
			 labelType.setText( " Type : " + type);
		 }
		 
		 if (  s.equals("Déchet") )
		 {
			 categorie = "dechet";
			 
			 for ( int i = 0 ; i < tabDechet.length ; i++)		selectionType.addItem(tabDechet[i]);
			
			 panelGaucheCentre.add(selectionType);
			 
			 viderlisteSelection(selectionAlliage);
			 
			 for ( int i = 0 ; i < tabAlliage.length ; i++) 	selectionAlliage.addItem ( tabAlliage[i]);
			 
			 type = selectionType.getSelectedItem().toString();
			 alliage = Integer.parseInt( selectionAlliage.getSelectedItem().toString() );

			 labelType.setText("Type : " + getType() + "    -     Alliage : " + getAlliage());
			 
			 selectionAlliage.setVisible(true);
			 panelGaucheCentre.add ( selectionAlliage );
		 }
		 
		 labelResultat.setText("Total : " + getResultatListe()+ " kg");
		 labelInformation.setText("Ajout : " + categorie);
	}
	
	public void viderlisteSelection ( JComboBox selectionType)
	{
		DefaultComboBoxModel modele = (DefaultComboBoxModel)selectionType.getModel();
		modele.removeAllElements();
	}
}
