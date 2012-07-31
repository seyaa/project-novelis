package Ihm;

import java.awt.Frame;
import java.awt.Panel;

import javax.swing.JFrame;

import classe.Chargement;

public class Test {
	
	public static void main ( String [] args) 
	{

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Chargement chargement = new Chargement(1, 1, "ok", "ko", 1, 2, 151);
		Panel panel = new Panel();
		panel.add( new ModuleAjoutDechetV2( chargement) );
		frame.setSize(700	, 900);
		frame.setVisible(true);
		frame.add(panel);
		frame.pack();
	
	}
	

}
