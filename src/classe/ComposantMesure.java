package classe;

public class ComposantMesure 
{
	private int id_composant_mesure;
	private int id_charge_mesure;
	private int id_composant;
	private String libelle;
	private double taux;
	
	public ComposantMesure(int id_composant_mesure, int id_charge, int id_composant, String libelle, double taux) 
	{
		this.id_composant_mesure = id_composant_mesure;
		this.id_charge_mesure = id_charge;
		this.id_composant = id_composant;
		this.libelle = libelle;
		this.taux = taux;
	}

	
	
	public int getId_composant_mesure() 
	{
		return id_composant_mesure;
	}

	public int getId_charge_mesure() 
	{
		return id_charge_mesure;
	}

	public int getId_composant() 
	{
		return id_composant;
	}

	public String getLibelle() 
	{
		return libelle;
	}

	public double getTaux() 
	{
		return taux;
	}

	public String toString() 
	{
		return "ComposantMesure [id_composant_mesure=" + id_composant_mesure
				+ ", id_charge=" + id_charge_mesure + ", id_composant=" + id_composant
				+ ", libelle=" + libelle + ", taux=" + taux + "]";
	}
}
