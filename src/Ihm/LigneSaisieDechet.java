package Ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classe.Chargement;

public class LigneSaisieDechet extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JLabel textMultiplier;
	private JLabel textEgal;
	private JTextField saisiePoids;
	private JTextField saisieMultiplicateur;
	private JButton supprimer ;
	
	private Chargement chargementCreate;
	
	private static int numeroLigne = 0;
	private int position;
	
	private int resultat;
	boolean estSupprime = false;
	JPanel panelPrincipal;
	ArrayList<LigneSaisieDechet> liste;
	
	public LigneSaisieDechet (ArrayList<LigneSaisieDechet> listeSaisieDechet)
	{
		position = numeroLigne;
		numeroLigne++;
		this.liste = listeSaisieDechet;
		//this.chargementCreate = chargement;
		panelPrincipal 			= new JPanel();
		textMultiplier 			= new JLabel("x");
		saisieMultiplicateur 	= new JTextField("1", 5);
		saisiePoids 			= new JTextField(5);
		textEgal	 			= new JLabel( "=" );
		resultat				= 0;
		supprimer 				= new JButton("x");
		ArrayList<LigneSaisieDechet> liste;
		panelPrincipal.add ( supprimer );
		panelPrincipal.add ( saisiePoids );
		panelPrincipal.add ( textMultiplier );
		panelPrincipal.add ( saisieMultiplicateur );
		panelPrincipal.add ( textEgal );
		
		saisiePoids.addActionListener(this);
		saisieMultiplicateur.addActionListener(this);
		supprimer.addActionListener(this);
		
		add ( panelPrincipal );
	}

	public void setResultat ( int resultat)
	{
		this.resultat = resultat;
	}
	
	public int getResultat ()
	{
		return resultat;
	}
	
	public boolean aUnResultat()
	{
		return resultat != 0;
	}

	public void actionPerformed(ActionEvent e)
	{		
		if ( e.getSource().equals(saisieMultiplicateur) || e.getSource().equals(saisiePoids) )
		{
			int  multiplicateur = 0 , poids = 0;
			try 
			{
				multiplicateur = Integer.parseInt(saisieMultiplicateur.getText());
			} 
			catch(NumberFormatException nfe) 
			{
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un nombre pour la quantité.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
			}
			
			try 
			{
				poids = Integer.parseInt(saisiePoids.getText());
			} 
			catch(NumberFormatException nfe) 
			{
				JOptionPane.showMessageDialog(null, "Veuillez rentrer un nombre pour le poids.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
			}
			
			if ( poids == 0 || multiplicateur == 0)
			{
				textEgal.setText(" = 0");
			}
			else
			{
				resultat = poids * multiplicateur;
				textEgal.setText("= " + resultat + " kg");
				setResultat(resultat);
			}
		}
		
		if ( e.getSource().equals(supprimer))
		{
			panelPrincipal.removeAll();
			numeroLigne--;
			System.out.println("Suppression en : " + numeroLigne);
			System.out.println("Taille : " + liste.size());
			liste.remove(numeroLigne);
			//System.out.println ("\n TAILLE: " +liste.size());
			//System.out.println ("\nNUM LIGNE: " +numeroLigne);
			//System.out.println ("\nPOSITION : " + position);
			panelPrincipal.setVisible(false);
			
			add ( panelPrincipal );
			estSupprime = true;
		}
		
		
		
		ModuleAjoutDechetV2.majPoids(chargementCreate);
		
	}
	
	public void revalidate() 
	{
		super.revalidate();
	};
	
	public boolean estSupprime()
	{
		return estSupprime;
	}
}
