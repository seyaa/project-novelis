package Ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import classe.CCComposant;
import classe.Chargement;

public class ModuleTableur extends JPanel// implements TableModelListener 
{
	
	private DefaultTableModel modele 	= new  DefaultTableModel();
	private JTable  table;
	private JPanel panelCentral 		= new JPanel();
	private JPanel panelSouth 			= new JPanel();
	
	
	
	
	public DefaultTableModel getModele() {
		return modele;
	}


	public JTable getTable() {
		return table;
	}


	public JPanel getPanelCentral() {
		return panelCentral;
	}


	public JPanel getPanelSouth() {
		return panelSouth;
	}


	public ModuleTableur ( int nbLigne , int type , String[] tableau  , Chargement chargementCreate, String nomPremiereColonne ) 
    {			
		if ( type == 1)
		{
			table = new JTable(modele) 
			{ 
				public boolean isCellEditable(int row, int column) 
				{
					return true;	// dévérouillage de toutes les cases
				}
			};
		}	
		
		if ( type == 0)
		{ 
			table = new JTable(modele) 
			{
				public boolean isCellEditable(int row, int column) 
				{
					return  false;  // verrouillage de toutes les cases
				}
			};
		}
		
//		System.out.println (nomPremiereColonne );
		if ( ! nomPremiereColonne.equals("null"))
		modele.addColumn(nomPremiereColonne.toString()); // remplissage des colonnes de titre
		
		modele.addColumn("Cu");
		modele.addColumn("Mg");
		modele.addColumn("Fe");
		modele.addColumn("Mn");
		modele.addColumn("Si");
		modele.addColumn("Zn");
		modele.addColumn("Ti");
		modele.addColumn("PbCdHg");
		
		modele.setRowCount(nbLigne) ; // ajout du bon nombre de ligne
		
		if ( nbLigne == 4) table.setPreferredScrollableViewportSize(new Dimension(400, 64));  
		
		if ( nbLigne == 3) table.setPreferredScrollableViewportSize(new Dimension(400, 48));  
		
		if ( nbLigne == 1) table.setPreferredScrollableViewportSize(new Dimension(400, 16));  
		
		if ( tableau != null)
		{
			for ( int i = 0 ; i < tableau.length ; i++)
				modele.setValueAt(tableau[i], i, 0) ;                                                        
		}
		
		//modele.addTableModelListener(this);
		
		panelCentral.setLayout(new BorderLayout());
	

		panelSouth.add (new JScrollPane(table));

		panelCentral.add(panelSouth , "Center");
		
		
		add ( panelCentral);

    }

	
//	public void tableChanged(TableModelEvent e) 
//	{
//		// ON RECUPERE LA VALEUR DE LA CELLULE
//		
//		int ligne = table.getSelectedRow();
//		int colonne = table.getSelectedColumn();
//		Object cellule = table.getValueAt(ligne,colonne);
//		
//
//		System.out.println ( cellule );
//	}
	

	public void insertComposantDansTableau ( CCComposant composant ) throws SQLException
	{		
		// La tableau est représenté dans le même ordre que les ID des composants
		// CAD => Composant 1 dans la DB = CU ==> on insert donc en 1, 2 , 3 etc .. , soit l'idComposant
		
		modele.setValueAt( composant.getTol_min() 	, 0 , composant.getIdComposant() );
		modele.setValueAt( composant.getTol_cible() , 1 , composant.getIdComposant() );
		modele.setValueAt( composant.getTol_max() 	, 2 , composant.getIdComposant() );
	}
	

	public String getValue ( ModuleTableur tableau , int i )
	{
		return tableau.getTable().getValueAt(0,i).toString();
	}
	
	public void setValue ( ModuleTableur tableau , int i )
	{
		tableau.getTable().setValueAt(null,0,i);
	}

	public boolean verifLigneComplete ( int numero , ModuleTableur tableau )
	{
		boolean estComplet = true;
		JTable table = tableau.getTable();
		
		int size = table.getColumnCount();
		
		for ( int i = 0 ; i < (size-1) ; i++ )
		{
			Object cellule = table.getValueAt((numero -1),i);
			
			if ( cellule == null ) { estComplet = false; }
		}
		return estComplet;
	}
	 
	public boolean estComplet ( ModuleTableur tableau )
	{
		boolean estComplet = true;
		JTable table = tableau.getTable();
		
		int size = table.getColumnCount();
		
		//System.out.println ( "size : " + size );
		
		for ( int i = 0 ; i < (size-1) ; i++ )
		{
			Object cellule = table.getValueAt(0,i);
			
			if ( cellule == null ) { estComplet = false; }
		}
		return estComplet;
	}
	
	public ArrayList<String> listeValeurLigne ( int ligne , ModuleTableur tableau )
	{
		ArrayList<String> listeValeur = new ArrayList<String>();
		
		JTable table = tableau.getTable();
		
		int size = table.getColumnCount();
				
		for ( int i = 0 ; i <= (size-2) ; i++ )
		{
			String cellule = table.getValueAt((ligne-1),i).toString();
			
			//double valeur = Double.parseDouble(cellule); 
			
			listeValeur.add( cellule );
		}
		return listeValeur;
	}

}
	
//	public static void main ( String [] args) throws SQLException
//	{
//		
//		
//		DBComposant dbComposant = new DBComposant();
//		
//		ArrayList<CCComposant> listeCharge = new ArrayList<CCComposant>();
//		
//		listeCharge = dbComposant.getComposantDapresAlliage(1);
//		
//		ModuleTableur tableau = new ModuleTableur(3, 1, null, "test");
//		
//		for ( int i = 0 ; i < listeCharge.size() ; i++)
//		{
//			tableau.insertComposantDansTableau(listeCharge.get(i));
//		}
//		
//		
//		System.out.println ( listeCharge.toString());
//		
//		
//		
//		tableau.insertComposantDansTableau(listeCharge.get(0));
//		
//	}











	

	


      



