package Ihm;

import java.awt.Graphics;
import java.awt.PrintJob;
import java.util.Properties;
import javax.swing.JFrame;

public class ModuleImprimer extends JFrame 
{  
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Graphics gImpr;

	public ModuleImprimer() 
	{
		Properties props = new Properties();
		props.setProperty("awt.print.paperSize", "a4");
		props.setProperty("awt.print.destination", "printer");
		
		PrintJob demandeDImpression = getToolkit().getPrintJob (this, "Impression", props);
		if (demandeDImpression != null) 
		{
		    gImpr = demandeDImpression.getGraphics();
		    demandeDImpression.end();
		}
	}
}