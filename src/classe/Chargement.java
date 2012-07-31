package classe;

import java.sql.Timestamp;

public class Chargement 
{
//	declaration d'une variable entier nommée idChargement
	private int idChargement;
//	declaration d'une variable entier nommée idAlliage
	private int idAlliage;
//	declaration d'une variable entier nommée id...
	private int typeFour;
//	declaration d'une variable entier nommée id...
	private int poidsTotalChargement;
//	declaration d'une variable entier nommée id...
	private Timestamp date;
//	declaration d'une variable entier nommée id...
	private String relais;
//	declaration d'une variable entier nommée id...
	private String nom1;
//	declaration d'une variable entier nommée id...
	private String nom2;
//	declaration d'une variable entier nommée id...
	private int nbMandrinsEnfournes;
//	declaration d'une variable entier nommée id...
	private int nbMandrinsRetires;
//	declaration d'une variable entier nommée id...
	private int poids_pdb_precedent;


	public Chargement(int idChargementParam , int idAlliageParam , String nom1Param , String nom2Param , int nbEnfourParam , int nbRetirParam, int poids_pdb_precedentParam ) 
	{
		// Initialise les variables private avec les valeurs des parametres
		// initialisation de tous les attributs private avec la valeur des parametre

		this.idChargement = idChargementParam;
		this.idAlliage = idAlliageParam;
		this.nom1 = nom1Param;
		this.nom2 = nom2Param;
		this.nbMandrinsEnfournes = nbEnfourParam;
		this.nbMandrinsRetires = nbRetirParam;
		this.poids_pdb_precedent = poids_pdb_precedentParam;
	}
	
	public Chargement( int numChargement , int typeFour, String relais , String nom1 , String nom2 , int idAliiage , int poids_pdb_precedent) 
	{
		// Initialise les variables private avec les valeurs des parametres
		// initialisation de tous les attributs private avec la valeur des parametre
		
		this.idChargement = numChargement;
		this.typeFour = typeFour;
		this.relais = relais;
		this.nom1 = nom1;
		this.nom2 = nom2;
		this.idAlliage = idAliiage;
		this.poids_pdb_precedent = poids_pdb_precedent;
	}

	// Méthode retournant un String
	// Retourne uniquement la valeur de l'attribut nom1 de l'objet crée
	
	public String getNom1() 
	{
		return nom1;
	}

	public void setNom1(String nom1) 
	{
		this.nom1 = nom1;
	}
	
	// Méthode retournant un entier
	// Retourne uniquement la valeur de l'attribut typeFour de l'objet crée
	// => private int typeFour;
	
	public int getTypeFour() 
	{
		return typeFour;
	}

	public int getPoids_pdb_precedent() 
	{
		return poids_pdb_precedent;
	}

	public String getNom2() 
	{
		return nom2;
	}

	public void setNom2(String nom2) 
	{
		this.nom2 = nom2;
	}

	public int getNbMandrinsEnfournes() 
	{
		return nbMandrinsEnfournes;
	}


	// Méthode retournant un String
	// Retourne la valeur de l'ensemble des attributs de l'objet sous forme d'une grosse chaine détaillée
	
	public String toString() 
	{
		String chaine = "";
	
		chaine = "Chargement [idChargement=" + idChargement + "\n, idAlliage="
		+ idAlliage + "\n, typeFour=" + typeFour
		+ "\n, poidsTotalChargement=" + poidsTotalChargement + "\n, date="
		+ date + "\n, relais=" + relais + "\n, nom1=" + nom1 + "\n, nom2="
		+ nom2 + "\n, nbMandrinsEnfournes=" + nbMandrinsEnfournes
		+ "\n, nbMandrinsRetires=" + nbMandrinsRetires + "]";
		
		return chaine;
	}

	public void setNbMandrinsEnfournes(int nbMandrinsEnfournes) 
	{
		this.nbMandrinsEnfournes = nbMandrinsEnfournes;
	}

	public int getNbMandrinsRetires() 
	{
		return nbMandrinsRetires;
	}

	public void setNbMandrinsRetires(int nbMandrinsRetires) 
	{
		this.nbMandrinsRetires = nbMandrinsRetires;
	}

	public int getIdChargement() 
	{
		return idChargement;
	}

	public int getIdAlliage() 
	{
		return idAlliage;
	}

	public int getPoidsTotalChargement() 
	{
		return poidsTotalChargement;
	}

	public Timestamp getDate() 
	{
		return date;
	}

	public String getRelais() 
	{
		return relais;
	}
}
