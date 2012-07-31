package db;

import java.sql.*;

public class Singleton
{
	private static Singleton instance;
	private Connection con;
    /*
    * La méthode getInstance n'est sûr pour les threads
    * (possibilité de créer plusieurs Singleton) donc
    * on a besoin de sécuriser celle-ci avec synchronized.
    */    
    public static synchronized Singleton getInstance()
    {
        if (instance == null)
        	instance = new Singleton();
        
        return instance;
    }
    
	private Singleton()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			con = DriverManager.getConnection("jdbc:oracle:oci8:@RUSSIGMAPTEST", "FLX92", "novelis" );
		}
		catch( Exception e ) 
		{
		     e.printStackTrace();
		}
	}

    
    public Connection getCon() 
    {
        return con;
    }
}