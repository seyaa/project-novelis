package Ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classe.Chargement;

public class ModuleCodeBarre extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	private GridBagConstraints c = new GridBagConstraints();
	
	private JLabel motAlliage	 				= new JLabel ("");
	private JLabel motPoids 					= new JLabel ("");
	private JLabel motAjouter 					= new JLabel ("Recherche : ");
	private ModuleTableur tableauComposition;
	private JTextField saisieCodeBarre 			= new JTextField(6);
	private JButton boutonValider				= new JButton("Rechercher");
	private JButton boutonAnnuler				= new JButton("");
	private JPanel panelPrincipal 				= new JPanel ();
	boolean estValide					= false;
	
	public ModuleCodeBarre( Chargement chargementCreate)
	{
		tableauComposition	= new ModuleTableur(1, 0, null, chargementCreate, "null");
			
		panelPrincipal.setLayout ( new GridBagLayout());

		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 3, 0, 0);
		panelPrincipal.add ( motAjouter , c );
	
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 3, 0, 0);
		panelPrincipal.add ( saisieCodeBarre, c );
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 15, 0, 0);
		panelPrincipal.add ( motPoids , c );
		
		c.gridx = 1;
		c.gridy = 3;
		c.weightx = 1;
		panelPrincipal.add ( motAlliage , c );
		
		c.gridx = 6;
		c.gridy = 0;
		c.insets = new Insets(0, 15, 0, 15);
		boutonValider.addActionListener(this);
		panelPrincipal.add ( boutonValider , c );
		
		c.gridx = 7;
		c.gridy = 0;
		boutonAnnuler.addActionListener(this);
		boutonAnnuler.setVisible(false);
		panelPrincipal.add ( boutonAnnuler , c );
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		panelPrincipal.add ( tableauComposition , c);
		tableauComposition.setVisible(false);
			
		panelPrincipal.setBorder(BorderFactory.createEtchedBorder());
		panelPrincipal.setBorder(BorderFactory.createTitledBorder(" Recherche composant avec code barre "));
		
		add( panelPrincipal) ;
	}

	

	public void actionPerformed(ActionEvent e) 
	{
		String contenuBoutonValider = boutonValider.getText();
		
		if (contenuBoutonValider.equals("Rechercher") )
		{
			if ( ! saisieCodeBarre.getText().equals("") )
			{
				motPoids.setText("Poids : ");
				motAlliage.setText("Alliage : ");
				tableauComposition.setVisible(true);
				boutonValider.setText("Ajouter");
				boutonAnnuler.setText("Annuler");
				boutonAnnuler.setVisible(true);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Vous devez saisir un code barre pour effectuer la recherche", "Erreur de saisie", JOptionPane.ERROR_MESSAGE); 
			}
		}
		
		if (e.getSource().equals(boutonAnnuler) )
		{
			motPoids.setText("");
			motAlliage.setText("");
			boutonValider.setText("Rechercher");
			tableauComposition.setVisible(false);	
		}
	}
	
}
