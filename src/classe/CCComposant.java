package classe;

public class CCComposant {
	private int idAlliage;
	private int idComposant;
	private double tol_min;
	private double tol_max;
	private double tol_cible;

	public CCComposant(int idAlliage, int idComposant, double tol_min, double tol_max, double tol_cible) 
	{
		this.idAlliage = idAlliage;
		this.idComposant = idComposant;
		this.tol_min = tol_min;
		this.tol_max = tol_max;
		this.tol_cible = tol_cible;
	}

	public double getTol_min() 
	{
		return tol_min;
	}


	public double getTol_max() 
	{
		return tol_max;
	}


	public double getTol_cible() 
	{
		return tol_cible;
	}


	public int getIdAlliage() 
	{
		return idAlliage;
	}

	public int getIdComposant() 
	{
		return idComposant;
	}

	@Override
	public String toString() {
		return "CCComposant [idAlliage=" + idAlliage + ", idComposant="
				+ idComposant + ", tol_min=" + tol_min + ", tol_max=" + tol_max
				+ ", tol_cible=" + tol_cible + "]";
	}

	
}
