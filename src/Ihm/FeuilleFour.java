package Ihm;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;


import classe.Chargement;

public class FeuilleFour extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JMenuItem itemAide, itemAPropos, itemEnregistrer, itemEnregistrerSous, itemNouveau, itemOuvrir, itemQuitter, itemRechercher, itemImprimer;
	private JTextField textField1;
	private String version = "V1";
	private boolean aEteSauvegarde = false;
	private ActionNouveauChargement actionNouveauChargement;
	private ActionOuvrir 			actionOuvrir;
	private ActionSauverSous 		actionSauverSous;
	private ActionQuitter 			actionQuitter;
	private ActionRechercher 		actionRechercher;
	private ActionSauver 			actionSauver;
	private ActionImprimer 			actionImprimer;
	private JPanel identiteChargement = new JPanel();
	private static int nbChargement = 0;
	
	public FeuilleFour() 
	{
		setTitle("Gestion des chargements");
		setSize(900	, 600);
		
		// PLEIN ECRAN
		//this.pack();
		//this.setDefaultLookAndFeelDecorated(true);
		//this.setExtendedState(this.MAXIMIZED_BOTH);
	
		setLayout ( new BorderLayout());
		
		// textField utilise pour la recherche
		textField1 = new JTextField("",10);
		textField1.addActionListener(this);
		
		//CREATION DES BOUTONS ACTIONS ET RACCOURCIS CLAVIER
		actionNouveauChargement = new ActionNouveauChargement	("Nouveau chargement", "pictures/nouveau.png", "Creer un nouveau fichier", KeyEvent.VK_N,this);
		actionOuvrir 			= new ActionOuvrir				("Ouvrir", "pictures/ouvrir.png", "Ouvrir un fichier", KeyEvent.VK_O,this);
		actionSauver			= new ActionSauver				("Enregistrer", "pictures/enregistrer.png", "Sauvegarder le fichier courant", KeyEvent.VK_S,this);
		actionSauverSous 		= new ActionSauverSous			("Enregistrer sous", "pictures/enregistrerSous.png", "Enregistrer sous un nouveau nom", KeyEvent.VK_S,this);
		actionQuitter 			= new ActionQuitter				("Quitter", "pictures/quitter.png", "Fermeture du programme", KeyEvent.VK_Q,this);
		actionRechercher 		= new ActionRechercher			("Rechercher chargement", "pictures/rechercher.png", "Recherche un chargement antérieur", KeyEvent.VK_F,this);
		actionImprimer	 		= new ActionImprimer			("Imprimer", "pictures/imprimer.png", "Imprimer le chargement", KeyEvent.VK_I,this);
	
		//CREATION DE BOUTONS
		JButton nouveau 		= new JButton(actionNouveauChargement); nouveau.setText("");
		JButton ouvrir 			= new JButton(actionOuvrir);			ouvrir.setText("");
		JButton enregistrer 	= new JButton(actionSauver);			enregistrer.setText("");
		JButton enregistrerSous = new JButton(actionSauverSous);		enregistrerSous.setText("");
		JButton quitter 		= new JButton(actionQuitter);			quitter.setText("");
		JButton rechercher 		= new JButton(actionRechercher);		rechercher.setText("");
		JButton imprimer 		= new JButton(actionImprimer);			imprimer.setText("");
		
		
		//CREATION DE LA BARRE DES MENUS
		JMenuBar menuBar 		= new JMenuBar();
		
		//CREATION DES MENUS
		JMenu menuFichier 		= new JMenu("Fichier");
		JMenu menuRechercher 	= new JMenu("Rechercher");
		JMenu menuAide 			= new JMenu("Aide");
		
		//creation des items + event sur les items
		itemNouveau 			= new JMenuItem(actionNouveauChargement); 	itemNouveau.addActionListener(this);
		itemOuvrir 				= new JMenuItem(actionOuvrir);				itemOuvrir.addActionListener(this);
		itemEnregistrer 		= new JMenuItem(actionSauver);				itemEnregistrer.addActionListener(this);
		itemImprimer	 		= new JMenuItem(actionImprimer);			itemImprimer.addActionListener(this);
		itemEnregistrerSous		= new JMenuItem(actionSauverSous);			itemEnregistrerSous.addActionListener(this);
		itemQuitter 			= new JMenuItem(actionQuitter);				itemQuitter.addActionListener(this);
		itemRechercher 			= new JMenuItem(actionRechercher);			itemRechercher.addActionListener(this);
		itemAide 				= new JMenuItem("Aide")	;					itemAide.addActionListener(this);
		itemAPropos 			= new JMenuItem("A propos")	;				itemAPropos.addActionListener(this);
		itemAide.setIcon (new ImageIcon("icones/help.png"));
		itemAPropos.setIcon	(new ImageIcon("icones/apropos.png"));
		
		//ajoute les items au menu Fichier
		menuFichier.add(itemNouveau);			menuFichier.addSeparator();
		menuFichier.add(itemOuvrir);			menuFichier.addSeparator();
		menuFichier.add(itemEnregistrer);		menuFichier.addSeparator();
		menuFichier.add(itemEnregistrerSous);	menuFichier.addSeparator();
		menuFichier.add(itemImprimer);			menuFichier.addSeparator();
		menuFichier.add(itemQuitter);
		
		//ajoute les items au menu Rechercher
		menuRechercher.add(itemRechercher)	;
		menuRechercher.addSeparator()		;
		
		//ajoute les items au menu Aide
		menuAide.add(itemAide);
		menuAide.addSeparator();		
		menuAide.add(itemAPropos);

		//ajout des menus a la barre
		menuBar.add(menuFichier);
		menuBar.add(menuRechercher);
		menuBar.add(menuAide);

		//rendre visible la barre des menus
		setJMenuBar(menuBar);
		setVisible(true);

		//GESTION DE LA FERMETURE DE LA FENETRE
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent evt) 
			{
				//booleen pour verifier que le fichier est sauvegarde avant de fermer
				if( aEteSauvegarde == true )
					quitter();
				else
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	
	public void afficherFenetrePrincipal ( Chargement chargementCreate ) throws SQLException, ClassNotFoundException
	{
		remove ( identiteChargement);
		
		FenetrePrincipal fenetrePrincipal = new FenetrePrincipal( chargementCreate );
		add( fenetrePrincipal, "Center");
			
		setVisible ( true);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		
		if (action.equals("Quitter")) dispose ();
		
		if(action.equals("Rechercher"))
			if ( textField1.getText().equals("")) 
				JOptionPane.showMessageDialog(null, "Vous devez remplir le champs pour effectuer la recherche", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
		
		if (action.equals("Ouvrir"))
		{
			try 	{ new ModuleOuvrir(); 	} 
			catch (IOException e1) 
					{e1.printStackTrace();	}	
		}
		
		if (action.equals("Imprimer")) 
			new ModuleImprimer();	
	
		if(action.equals("Aide"))
			JOptionPane.showMessageDialog(this, "L'aide est indisponible pour l'instant, \n debrouillez-vous seul ...");
		
		if(action.equals("A propos"))
			JOptionPane.showMessageDialog(this, "<html><center><h2>Utilitaire chargement V1 </h2> Copyright@ <br> Maxime Barbot <br>World Company <br></center></html>");
	}
	
	// RECHERCHER UN CHARGEMENT
	public void rechercher()
	{
		JComboBox selectionTypeRecherche = new JComboBox();
		
		selectionTypeRecherche.addItem("Nom");
		selectionTypeRecherche.addItem("Alliage");
		selectionTypeRecherche.addItem("Relais");
		
		JDialog recherc = new JDialog(this, "Rechercher", false);
		recherc.setLocation(800, 0);
		JPanel panel = new JPanel ();
		panel.add(textField1,BorderLayout.CENTER);
		
		JButton rechercher = new JButton("Rechercher");			rechercher.addActionListener(this);
		
		panel.add(selectionTypeRecherche,BorderLayout.SOUTH);
		panel.add(rechercher,BorderLayout.SOUTH);

		recherc.add(panel);
		recherc.pack();
		recherc.setVisible(true);
	}
	
	//FERMETURE DU FICHIER COURANT ET OUVERTURE D UN NOUVEAU FICHIER VIERGE
	public void nouveauChargement()
	{
		if ( nbChargement < 1)
		{
			ModuleCreationIdentite creationIdentite = new ModuleCreationIdentite(this);
//			CreationIdentite creationIdentite = new CreationIdentite(this);
			identiteChargement.add( creationIdentite );
			add ( identiteChargement);
			setVisible ( true);
			
			nbChargement = 1;
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Un chargement est déjà en cours de création", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	//SAUVEGARDER LE FICHIER COURANT + CHOISIR SON NOM ET EMPLACEMENT
	public void sauverSous()
	{
		//creation d un JFileChooser
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir","."));
		//creation d un entier
		int val = chooser.showSaveDialog(this);
		//si cette entier correspond a l option valider
		if(val == JFileChooser.APPROVE_OPTION)
		{
			//on recupere dans une string le chemin du fichier selectionne
			String s = chooser.getSelectedFile().getPath();
			//on ajoute .txt a la string s qui contient le chemin et le nom du fichier
			s = s.endsWith(".txt")?s:s+".txt";
		}
	}
	
	//FERMER L EDITEUR
	public void quitter()
	{
		int res = JOptionPane.showConfirmDialog(this, "Sauvegarder avant de quitter ?");
		//trois choix possible
		switch (res)
		{
			case JOptionPane.CANCEL_OPTION:
				//on clique sur annuler sa ferme simplement la "showConfirmDialog"
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				break;
			case JOptionPane.NO_OPTION:
				//on clique sur non sa ferme le programme
				System.exit(0);
				break;
			case JOptionPane.YES_OPTION:
				//on clique sur oui, sa sauvegarde et sa ferme le programme
				sauvegarder();
				System.exit(0);
				break;
		}	
		System.exit(0);
	}

	//SAUVEGARDER LE FICHIER COURANT
	public void sauvegarder()
	{
		//creation d un JFileChooser
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir","."));
		int val = chooser.showSaveDialog(this);
 
		if( val == JFileChooser.APPROVE_OPTION )
		{
			//recuperation du chemin et du nom du fichier
			String s = chooser.getSelectedFile().getPath();
			s = s.endsWith("")?s:s+"";
			//sauvegarde
		}		
		aEteSauvegarde = true;
		//modification du titre
		setTitle("Feuille chargement "+ version + "  : ");
	}
			
	//OUVERTURE D UN FICHIER
	public void ouvrir()
	{
		@SuppressWarnings("unused")
		boolean bool = false;
		
		int res = JOptionPane.showConfirmDialog(this, "Sauvegarder avant d'ouvrir un nouveau fichier ?");

		switch (res)
		{
			case JOptionPane.CANCEL_OPTION:
				break;
			case JOptionPane.NO_OPTION:
				bool = true;
				break;
			case JOptionPane.YES_OPTION:
				bool = true;
				break;
		}

	}
	
	//IMPRESSION DU CHARGEMENT
	public void imprimer()
	{
		@SuppressWarnings("unused")
		Graphics gImpr;
		
		Properties props = new Properties();
		props.setProperty("awt.print.paperSize", "a4");
		props.setProperty("awt.print.destination", "printer");
		
		PrintJob demandeDImpression = getToolkit().getPrintJob (this, "Impression", props);
		if (demandeDImpression != null) 
		{
		    gImpr = demandeDImpression.getGraphics();
		    demandeDImpression.end();
		}
	}

	public static void main ( String []args)
	{
	
		
		new FeuilleFour();
	}
}
