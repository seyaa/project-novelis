package classe;

public class Four 
{
	private int id_four;
	private int capacite_four;
	private String nom_four;
	
	public Four(int id_four, int capacite_four, String nom_four) 
	{
		this.id_four = id_four;
		this.capacite_four = capacite_four;
		this.nom_four = nom_four;
	}

	public int getCapacite_four() 
	{
		return capacite_four;
	}

	public void setCapacite_four(int capacite_four)
	{
		this.capacite_four = capacite_four;
	}

	public String getNom_four() 
	{
		return nom_four;
	}

	public void setNom_four(String nom_four) 
	{
		this.nom_four = nom_four;
	}

	public int getId_four() 
	{
		return id_four;
	}
}
