package classe;

import java.sql.Timestamp;

public class Pion 
{
	private int idPion;
	private int idChargement;
	private Timestamp datePion;
	private int etatDuPion;
	
	public Pion ( int idPion , int idChargement , Timestamp pionDate, int etatPion )
	{
		this.idPion 		= idPion;
		this.idChargement 	= idChargement;
		this.datePion 		= pionDate;
		this.etatDuPion		= etatPion;
	}

	public int getEtatPion() 
	{
		return etatDuPion;
	}
	
	public void getEtatPion( int etat) 
	{
		etatDuPion = etat;
	}
	
	public int getIdPion() 
	{
		return idPion;
	}

	public int getIdChargement() 
	{
		return idChargement;
	}

	public Timestamp getDatePion() 
	{
		return datePion;
	}

	public String toString() 
	{
		return "Pion [idPion=" + idPion + ", idChargement=" + idChargement + ", datePion=" + datePion + ", etatDuPion=" + etatDuPion + "]";
	}
}
