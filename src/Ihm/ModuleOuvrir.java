package Ihm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

public class ModuleOuvrir 
{
	public ModuleOuvrir() throws IOException 
	{
		JFileChooser dialogue = new JFileChooser(".");
		PrintWriter sortie;
		File fichier;
	
		if (dialogue.showOpenDialog(null) ==  JFileChooser.APPROVE_OPTION) 
		{
			fichier = dialogue.getSelectedFile();
			sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
			sortie.close();
		}
		System.exit(0);
	}
}
