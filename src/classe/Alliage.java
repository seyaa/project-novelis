package classe;


public class Alliage 
{
	private int idAlliage;
	private String nomAlliage;
	
	public Alliage(int idAlliage, String nomAlliage)
	{
		this.idAlliage = idAlliage;
		this.nomAlliage = nomAlliage;
	}

	public String getNomAlliage() 
	{
		return nomAlliage;
	}

	public void setNomAlliage(String nomAlliage) 
	{
		this.nomAlliage = nomAlliage;
	}

	public int getIdAlliage() 
	{
		return idAlliage;
	}
}
