package Ihm;

import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import classe.Charge;
import classe.Chargement;
import db.DBCharge;

public class ChargementGraphique extends JPanel
{
	private static final long serialVersionUID = 6667463824918418794L;
	private DBCharge DB = new DBCharge();
	private ArrayList<Charge> listeCharge;
	private ChargeGraphique chargeGraphique;
	private JPanel panel = new JPanel();
	
	public ChargementGraphique ( Chargement chargementCreate) throws ClassNotFoundException, SQLException
	{
		System.out.println ("Classe : ChargementGraphique => Entrée dans chargementGraphique");
		System.out.println ("Classe : ChargementGraphique => Constructeur chargementGraphique");
		
		listeCharge = DB.selectToutesLesCharges(chargementCreate);
		
		System.out.println ("Classe : ChargementGraphique => Toutes les charges sont bien été selectionnées, taille : " + listeCharge.size());
		
		panel.setLayout(new GridLayout ( listeCharge.size(), 1));
		
		for ( int i = 0 ; i < listeCharge.size() ; i++)
		{
			System.out.println ("Classe : ChargementGraphique => Description de la charge numero " + i + " : " +  listeCharge.get(i).toString());
			System.out.println ("Classe : ChargementGraphique => listeCharge.get(i).getIdCharge() => " + listeCharge.get(i).getIdCharge() );
			
			if ( listeCharge.get(i).getStatut() == 0 )
				chargeGraphique = new ChargeGraphique(listeCharge.get(i).getIdCharge(), chargementCreate , 0);
			else
				chargeGraphique = new ChargeGraphique(listeCharge.get(i).getIdCharge(), chargementCreate , 1);
			
			panel.add(chargeGraphique);

		}
		add ( panel );
	}
	
	public void initialiser()
	{
		for ( int i = 0 ; i < listeCharge.size(); i++)	listeCharge.remove(i);
		
	}
	
	public int getTaille()
	{
		return listeCharge.size();
	}
	
	
	public int getNbChargeDansChargement ( Chargement chargementCreate )
	{
		return listeCharge.size();
	}
}
