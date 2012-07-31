package classe;

import java.sql.Timestamp;

public class Charge 
{
	private int idCharge;
	private int idChargement;
	private int quantite;
	private String categorie_charge;
	private String type_charge;
	private int position_charge;
	private int statut;
	private Timestamp dateReception;
	private String provenance;
	private Timestamp dateAjout;
	private int idAlliage;
	
	
	public Charge(	int idCharge, int idChargement, int quantite, String categorie_charge, String type_charge, int position_charge,
					int statut	, Timestamp dateReception, String provenance, Timestamp dateAjout, int idAlliage) 
	{
		this.idCharge = idCharge;
		this.idChargement = idChargement;
		this.quantite = quantite;
		this.categorie_charge = categorie_charge;
		this.type_charge = type_charge;
		this.position_charge = position_charge;
		this.statut = statut;
		this.dateReception = dateReception;
		this.provenance = provenance;
		this.dateAjout = dateAjout;
		this.idAlliage = idAlliage;
	}
	
	public int getIdCharge() 
	{
		return idCharge;
	}
	
	public int getIdChargement() 
	{
		return idChargement;
	}
	
	public int getQuantite() 
	{
		return quantite;
	}
	
	public String getCategorie_charge() 
	{
		return categorie_charge;
	}
	
	public String getType_charge() 
	{
		return type_charge;
	}
	
	public int getPosition_charge() 
	{
		return position_charge;
	}
	
	public int getStatut() 
	{
		return statut;
	}
	
	public Timestamp getDateReception() 
	{
		return dateReception;
	}
	
	public String getProvenance() 
	{
		return provenance;
	}
	
	public Timestamp getDateAjout() 
	{
		return dateAjout;
	}
	
	public int getIdAlliage() 
	{
		return idAlliage;
	}

	@Override
	public String toString() {
		return "Charge [idCharge=" + idCharge + ", idChargement="
				+ idChargement + ", quantite=" + quantite
				+ ", categorie_charge=" + categorie_charge + ", type_charge="
				+ type_charge + ", position_charge=" + position_charge
				+ ", statut=" + statut + ", dateReception=" + dateReception
				+ ", provenance=" + provenance + ", dateAjout=" + dateAjout
				+ ", idAlliage=" + idAlliage + "]";
	}
	
	
	
}
