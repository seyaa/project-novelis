package Ihm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import classe.Charge;
import classe.Chargement;
import classe.ComposantMesure;
import classe.Fonctions;
import db.DBCharge;
import db.DBComposantMesure;

public class ModuleSaisieManuelle extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JPanel panelGeneral, panelNord, panelSud, panelBouton, panelValidation;
	private ButtonGroup bgBouton			= new ButtonGroup();
	private JRadioButton boutonExtVert		= new JRadioButton("Ext Vert");
	private JRadioButton boutonExtNoir		= new JRadioButton("Ext Noir");
	private JRadioButton boutonSows			= new JRadioButton("Sows");
	private ModuleTableur tableurSaisieManu;
	private JButton boutonValider 			= new JButton("Ajouter");
	private JLabel textPoidsSaisieManu		= new JLabel("Poids : ");
	private JTextField saisiManuPoids			= new JTextField(6);
	private Chargement chargementCreate;
	
	public ModuleSaisieManuelle (Chargement chargementCreate)
	{	
		panelGeneral 	= new JPanel();
		panelNord 		= new JPanel();
		panelSud 		= new JPanel();
		panelBouton 	= new JPanel();
		panelValidation = new JPanel();
	
		this.chargementCreate = chargementCreate;
		
		tableurSaisieManu 		= new ModuleTableur ( 1 , 1 , null , chargementCreate, "null");
		panelGeneral.setLayout ( new GridLayout(2,1));
			
		panelNord.setLayout ( new BorderLayout());
		panelSud.setLayout ( new GridLayout( 1 , 2 ));

		panelBouton.setLayout(new GridLayout( 1 , 3 ));
			
		bgBouton.add ( boutonExtNoir );
		bgBouton.add ( boutonExtVert );
		bgBouton.add ( boutonSows );
		
		boutonExtNoir.addActionListener(this);
		boutonExtVert.addActionListener(this);
		boutonSows.addActionListener(this);

		panelBouton.add (boutonExtNoir );
		panelBouton.add (boutonExtVert );
		panelBouton.add (boutonSows );
					
		panelValidation.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		saisiManuPoids.addActionListener(this);
		boutonValider.addActionListener(this);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 15, 0, 0);
		panelValidation.add(textPoidsSaisieManu , gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 3, 0, 0);
		panelValidation.add( saisiManuPoids , gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 15, 0, 10);
		panelValidation.add( boutonValider , gbc);

		panelSud.add( panelBouton );
		panelSud.add( panelValidation );
		
		panelGeneral.add( tableurSaisieManu );
		panelGeneral.add( panelSud );
		
		panelGeneral.setBorder(BorderFactory.createEtchedBorder());
		panelGeneral.setBorder(BorderFactory.createTitledBorder("Ajout d'un composant avec composition connue"));
		
		add ( panelGeneral);
	}

	public void actionPerformed(ActionEvent e)
	{
		Charge charge;
		String poidsText = saisiManuPoids.getText() , type;
		int poids , id_charge = 0, id_chargement = 0, position;
		
		DBComposantMesure dbComposantMesure = null;
		DBCharge DBCharge = null;
		
        if(boutonExtNoir.isSelected()) 		type = "ExtNoir";
        else if(boutonExtVert.isSelected()) type = "ExtVert";
        else if(boutonSows.isSelected()) 	type = "sows";
        else 								type = "";
        
        if ( e.getSource().equals(boutonValider))
        {
        	if (  Fonctions.isInteger(poidsText) && ! type.equals(""))
        	{	
    			poids = Integer.parseInt(saisiManuPoids.getText());
        		
        		if ( tableurSaisieManu.estComplet(tableurSaisieManu))
        		{
        			int resultat = JOptionPane.showConfirmDialog(null, "Ajouter " + type  + " ( " + poids + " kg ) au chargement ? ", "Confirmation d'ajout", JOptionPane.YES_NO_OPTION);			

	        		if ( resultat == 0 )
	    			{
						try 
						{
							Timestamp date_ajout 	= Fonctions.getCurrentJavaSqlTimestamp();
							DBCharge				= new DBCharge();
							id_chargement 			= chargementCreate.getIdChargement();
							dbComposantMesure 		= new DBComposantMesure();
							id_charge 				= DBCharge.getNumeroDerniereIdCharge() + 1 ;
							ModuleCheckList.majCheckList ( chargementCreate );
							id_chargement 			= chargementCreate.getIdChargement();
							position				= DBCharge.getNumeroDernierePosition(chargementCreate); // on l'incrémentera par la suite
							id_charge				= DBCharge.getNumeroDerniereIdCharge();
							
							charge 					= new Charge((id_charge+1), id_chargement, poids, type, "dechet", (position+1), 0, null, null, date_ajout, 0  );
							
							DBCharge.insertCharge(chargementCreate, charge);
							
							ModuleCheckList.majCheckList ( chargementCreate );
						} 
						catch (ClassNotFoundException e3) 
						{
							e3.printStackTrace();
						} 
						catch (SQLException e3) 
						{
							e3.printStackTrace();
						} 
						catch (ParseException e3) 
						{
							e3.printStackTrace();
						}
	
	        			for ( int i = 0 ; i < 8 ; i++)
	        			{
	        				//valeurDeLaCase = tableurSaisieManu.getValue(tableurSaisieManu , i);
		        				
	        				try 
	        				{
	        					ComposantMesure composantMesure = new ComposantMesure(id_charge , id_chargement, id_charge , "test", 0.12);
	        					
								dbComposantMesure.insertComposantMesure ( composantMesure );
								ModuleCheckList.majCheckList(chargementCreate);
							} 
	        				catch (SQLException e1) 
	        				{
								e1.printStackTrace();
							}
	        				catch (ClassNotFoundException e1)
	        				{
								e1.printStackTrace();
							}
	        			}
	    			}
        		}
        		else
        		{
        			JOptionPane.showMessageDialog(null, "Le tableau doit être complet", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        	else
        	{
        		JOptionPane.showMessageDialog(null, "Saisie incomplète", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
        	}
        }
    }
 }

