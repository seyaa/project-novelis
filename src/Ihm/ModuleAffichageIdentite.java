package Ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classe.Chargement;
import db.DBAlliage;

public class ModuleAffichageIdentite extends JPanel
{
	private static final long serialVersionUID = 1L;
	private JPanel panelCentral = new JPanel();
	private JLabel nom1, nom2 , date,  relais, numero, alliage;
	
	GridBagConstraints gbc 				= new GridBagConstraints();
	
	
	public ModuleAffichageIdentite( Chargement chargementCreate) throws SQLException, ClassNotFoundException
	{
		Date maDateAvecFormat=new Date();
		SimpleDateFormat dateStandard = new SimpleDateFormat("dd/MM/yyyy");
		DBAlliage DBAlliage = new DBAlliage();
		String dateDuJour = dateStandard.format(maDateAvecFormat);
	
		panelCentral = new JPanel();
		nom1 = new JLabel("Nom1 : " + chargementCreate.getNom1());
		nom2 = new JLabel("Nom2 : " + chargementCreate.getNom2());
		date = new JLabel("Date : " + dateDuJour );
		relais = new JLabel("Relais : " + chargementCreate.getRelais());
		numero = new JLabel("Numéro : " + chargementCreate.getIdChargement());
		alliage = new JLabel("Alliage : " + DBAlliage.getNomAlliage( chargementCreate.getIdAlliage()));
			
		panelCentral.setLayout(new GridBagLayout());
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 70, 0, 15);
		panelCentral.add(nom1 , gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 15, 0, 15);
		panelCentral.add(relais , gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 15, 0, 70);
		panelCentral.add(date , gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 70, 0, 15);
		panelCentral.add(nom2 , gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 15, 0, 15);
		panelCentral.add(numero , gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 15, 0, 70);
		panelCentral.add(alliage , gbc);
		
		panelCentral.setBorder(BorderFactory.createTitledBorder("Identité"));
		add(panelCentral);
	}
}
