package Ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


import classe.*;
import db.DBAlliage;
import db.DBChargement;

public class ModuleCreationIdentite extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private JLabel textNom1 				= new JLabel("Nom1 :");	
	private JLabel textNom2 				= new JLabel("Nom2 :");
	private JLabel textAlliage				= new JLabel("Alliage :");
	private JLabel textRelais 				= new JLabel("Relais :");
	private JLabel textTypeFour				= new JLabel("Type de four :");
	private JLabel textPoidsPDB				= new JLabel("Poids pied de bain (Kg) :");
	
	private JComboBox choixAlliage 			= new JComboBox();
	private JComboBox choixRelais 			= new JComboBox();
	private JComboBox choixTypeFour 		= new JComboBox();
	
	private JTextField saisiNom1 			= new JTextField(6);
	private JTextField saisiNom2 			= new JTextField(6);
	private JTextField saisiPoidsPDB	 	= new JTextField(6);
	
	private JPanel panelCentral				= new JPanel();
	
	private JButton boutonValider 			= new JButton("Créer chargement");
	private JButton boutonAnnuler			= new JButton("Annuler");
	
	private static int reponse = 2;
	
	private FeuilleFour feuilleFour;
		
	int intTypeFour;
	
	GridBagConstraints gbc 				= new GridBagConstraints();
	
	int [] listeAlliage = { 1050 , 1200 , 8011, 8906, 8979, 8079, 8666, 8006, 3003, 3903, 3904 };
	public ModuleCreationIdentite( FeuilleFour feuilleFour )
	{
		this.feuilleFour = feuilleFour;
		panelCentral.setLayout(new GridBagLayout());
		
		/* GESTION RELAIS */
		
		choixRelais.addItem("Matin");
		choixRelais.addItem("Après-midi");
		choixRelais.addItem("Nuit");
		choixRelais.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(textRelais, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(choixRelais, gbc);

		
		/* GESTION NOM1 */

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(textNom1, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 15, 0, 0);
		saisiNom1.addActionListener(this);
		panelCentral.add(saisiNom1, gbc);

		
		/* GESTION NOM2 */
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(textNom2, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(10, 15, 0, 0);
		saisiNom2.addActionListener(this);
		panelCentral.add(saisiNom2, gbc);
		
		
		/* GESTION ALLIAGE */
		
		for ( int i = 0 ; i < listeAlliage.length ; i++)
			choixAlliage.addItem(listeAlliage[i]);
		
		choixAlliage.addActionListener(this);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(textAlliage, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(choixAlliage, gbc);

		
		/* GESTION TYPE DE FOUR */
		choixTypeFour.addItem("Thermcon");
		choixTypeFour.addItem("Demag");
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(textTypeFour, gbc);
		
	
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(choixTypeFour, gbc);
		choixTypeFour.addActionListener(this);

		
		/* GESTION TYPE DE FOUR */
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(textPoidsPDB, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(10, 15, 0, 0);
		saisiPoidsPDB.addActionListener(this);
		panelCentral.add(saisiPoidsPDB, gbc);

		
		/* VALIDATION - ANNULATION */
		
		boutonValider.addActionListener(this);
		boutonAnnuler.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(boutonValider, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.insets = new Insets(10, 15, 0, 0);
		panelCentral.add(boutonAnnuler, gbc);
		

		
		panelCentral.setBorder(BorderFactory.createTitledBorder("Identité du chargement"));
		panelCentral.setPreferredSize(new Dimension(300, 300));

		add ( panelCentral ) ;
		
		setVisible(true);
		
	}
	public boolean saisieFormulaireOk ( String []tab )
	{
		boolean formOk = true;
		
		for ( int i = 0 ; i < tab.length ; i++)
		{
			if ( tab[i].equals(""))
				formOk = false;
		}
		return formOk;
	}
			
	public void actionPerformed(ActionEvent e) 
	{
		String tabInfo[] = new String[6];

		// On stock le formulaire dans un tableau qu'on enverra a la feuilleFour
		if (e.getSource().equals(boutonValider))
		{   
			tabInfo[0] 	= saisiNom1.getText();
			tabInfo[1] 	= saisiNom2.getText();
			tabInfo[2] 	= saisiPoidsPDB.getText();
			tabInfo[3] 	= choixRelais.getSelectedItem().toString();
			tabInfo[4] 	= choixAlliage.getSelectedItem().toString();
			tabInfo[5]	= choixTypeFour.getSelectedItem().toString();
			
			if ( tabInfo[5].equals("Thermcon"))
			{
				intTypeFour = 1;
			}
			else
			{
				intTypeFour = 0;
			}

		     reponse = JOptionPane.showConfirmDialog(null, "Voulez vous créer ce nouveau chargement", "Confirmation de la création", JOptionPane.YES_NO_CANCEL_OPTION);			
		}
		
		if (e.getSource().equals(boutonAnnuler))
		{   
			remove(panelCentral);			
		}
		
		int idAlliage = 0 ;
		
		// Si le formulaire et correct et qu'on a validé
		// On envoie le tableau de saisie a la feuille principale pour créer le chargement
		if ( reponse == 0 )
		{
			if ( ! saisieFormulaireOk( tabInfo ) ) 
				JOptionPane.showMessageDialog(null, "Vous devez remplir tous les champs pour créer le chargement", "Erreur de saisie", JOptionPane.ERROR_MESSAGE); 	
			
			else
			{
				if ( ! Fonctions.isInteger(tabInfo[2]))
				{
					JOptionPane.showMessageDialog(null, "Le pied de bain ne doit comporter que les chiffres", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
				}
				else
				{

					try 
					{
						DBAlliage DBAlliage = new DBAlliage();
						idAlliage = DBAlliage.getIdAlliage(Integer.parseInt(tabInfo[4])); // On recupere l'id de l'alliage en fonction de la saisie
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					DBChargement DB = null;
					int num = 0;
					
					try 
					{
						DB = new DBChargement();
					} 
					catch (ClassNotFoundException e1) 
					{
						e1.printStackTrace();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}	
					
					try 
					{
						num = DB.getNumeroDernierChargement();
					} 
					catch (SQLException e2) 
					{
						e2.printStackTrace();
					}
					Chargement chargementCreate = new Chargement( num+1 , intTypeFour, tabInfo[3], tabInfo[0], tabInfo[1], idAlliage , Integer.parseInt(tabInfo[2]) );
					
					try 
					{
						DB.insertChargement(chargementCreate);
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					
					remove(panelCentral);
					try 
					{
						this.feuilleFour.afficherFenetrePrincipal ( chargementCreate );
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					catch (ClassNotFoundException z) 
					{
						z.printStackTrace();
					}
				}
			}
			
		}	
		 
	}

}
