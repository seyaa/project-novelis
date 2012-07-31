package Ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import classe.CCComposant;
import classe.Charge;
import classe.Chargement;
import classe.ComposantMesure;
import db.DBCharge;
import db.DBChargement;
import db.DBComposant;
import db.DBComposantMesure;

public class ChargeGraphique extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	int etat, idCharge, idChargement, poids, idTypeCharge, idAlliage, position;
	JButton boutonValider, boutonSupprimer, boutonValide;
	JPanel panel ;
	boolean estSupprime, estValide;
	Timestamp dateAjout;
	DBChargement DBChargement;
	private Chargement chargementCreate;
	Charge charge = null;
	private DBCharge DBCharge = new DBCharge();
	
	public ChargeGraphique ( int idChargeParam , Chargement chargementCreateParam , int etat ) throws ClassNotFoundException, SQLException
	{			
		this.idCharge = idChargeParam;
		this.idChargement = chargementCreateParam.getIdChargement();
		this.chargementCreate = chargementCreateParam;
		
		System.out.println ("Classe : ChargeGraphique => Constructeur charge graphique");
		System.out.println ("Classe : ChargeGraphique => id charge " 		+ idCharge);
		System.out.println ("Classe : ChargeGraphique => id chargement " 	+ idChargement);
		System.out.println ("Classe : ChargeGraphique => Selection de la charge en cours");
		
		charge = DBCharge.selectCharge(idCharge, idChargement);
		
		System.out.println ("Classe : ChargeGraphique => Selection terminée");
		System.out.println ("Classe : ChargeGraphique => Description charge graphique : ");
		System.out.println (charge.toString());
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
				
		this.boutonSupprimer = new JButton("x");
		this.boutonValider = new JButton("?");
		this.boutonValide = new JButton("validé");
		this.panel = new JPanel();
		this.estValide = false;
		this.estSupprime = false;	
		
		JLabel labelTypeCharge = new JLabel ( charge.getCategorie_charge() + " : " + charge.getQuantite() +" kg");
		
		if ( etat == 0)
		{
			panel.add(boutonSupprimer);
			panel.add(boutonValider);
		}
		else
			panel.add(boutonValide);
		
		boutonSupprimer.addActionListener(this);
		boutonValider.addActionListener(this);
		
		panel.add(labelTypeCharge);
		panel.setBorder(BorderFactory.createEtchedBorder());
		
		add ( panel );
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		int reponse;
		
		String nomBouton = e.getActionCommand();
		
		if (nomBouton.equals("x"))
		{
			reponse = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer cette charge de la liste ?", "Supression de la charge", JOptionPane.YES_NO_CANCEL_OPTION);	
			
			if ( reponse == 0)
			{		
				try 
				{					
					DBCharge.supressionCharge(idCharge, idChargement);
					JOptionPane.showConfirmDialog(null, "Charge supprimée.", "Supression", JOptionPane.PLAIN_MESSAGE);
					
					ModuleCheckList.majCheckList ( chargementCreate );
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				}
				panel.setVisible(false);
			}
		}
		
		if (nomBouton.equals("?"))
		{
			reponse = JOptionPane.showConfirmDialog(null, "Voulez vous valider cette charge dans le chargement ?", "Confirmation de la charge", JOptionPane.YES_NO_CANCEL_OPTION);	
			
			if ( reponse == 0)
			{
				try 
				{					
					System.out.println ("VALIDATION DE LA CHARGE EN COURS " );
					System.out.println (" ID CHARGE : " + idCharge );
					System.out.println (" ID CHARGEMENT : " + idChargement );
					DBCharge.validationCharge(idCharge, idChargement);
					JOptionPane.showConfirmDialog(null, "Charge validée.", "Validation", JOptionPane.PLAIN_MESSAGE);
					System.out.println (" MAJ PROCHAINE ");
					
					ModuleCheckList.majCheckList(chargementCreate);
					
					DBComposantMesure dbComposantMesure = new DBComposantMesure();
					DBComposant dbComposant = new DBComposant();
					
					int derniereIdChargeMesure = dbComposantMesure.getNumeroDerniereIdChargeMesuree();
					
					if ( charge.getIdAlliage() != 0 )
					{
						ArrayList<CCComposant> listeComposant = dbComposant.getComposantDapresAlliage(charge.getIdAlliage());
						
						System.out.println ( "CLASSE ChargeGraphique => SIZE liste composant : " + listeComposant.size() );
						System.out.println ( "CLASSE ChargeGraphique => ID ALLIAGE : " + charge.getIdAlliage() );
						
						for ( int i = 0 ; i < listeComposant.size() ; i++)
						{
							double tol_cible = listeComposant.get(i).getTol_cible();
							int idComposant = listeComposant.get(i).getIdComposant();
							String libelle = dbComposant.getNomComposant(idComposant);
							int derniereIdComposantMesure = dbComposantMesure.getNumeroDerniereIdComposantMesure();
							
							ComposantMesure composantMesure = new ComposantMesure((derniereIdComposantMesure+1), (derniereIdChargeMesure+1), idComposant, libelle, tol_cible);
						
							System.out.println ( "Insertion de : " + composantMesure.toString());
							
							dbComposantMesure.insertComposantMesure(composantMesure);
								
							System.out.println ( "Insertion OK");	
						}
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				catch (ClassNotFoundException e1) 
				{
					
					e1.printStackTrace();
				}
				boutonSupprimer.setVisible(false);
				boutonValider.setText("validé");
			}
		}
	}
	
	public String getDateFormatStandard ( Timestamp date)
	{
		String dateStandard = date + "";
		dateStandard = dateStandard.substring(0, dateStandard.length() - 4);
		
		return dateStandard;
	}

	public int getIdCharge()
	{
		return idCharge;
	}
		
	public int getIdChargement()
	{
		return idChargement;
	}
	
	public Timestamp getDateAjout()
	{
		return dateAjout;
	}
	
	public int getPosition()
	{
		return position;
	}

	public int getIdTypeCharge()
	{
		return idTypeCharge;
	}
	
	public int getPoids()
	{
		return poids;
	}
	
	public int getEtat()
	{
		return etat;
	}
	
	public int getIdAlliage()
	{
		return idAlliage;
	}
		
	public void supprimerCharge ()
	{
		estSupprime = false;
	}
	
	public void estValide()
	{
		estValide = false;
	}
}
