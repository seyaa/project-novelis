package Ihm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionNouveauChargement extends AbstractAction implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	private FeuilleFour feuilleFour;

	public ActionNouveauChargement(String menu, String icone, String aide, int clavier, FeuilleFour feuilleFour) 
	{
		super(menu, new ImageIcon(icone));
		putValue(Action.SHORT_DESCRIPTION, aide);
		KeyStroke cle = KeyStroke.getKeyStroke(clavier, Event.CTRL_MASK);
		putValue(Action.ACCELERATOR_KEY, cle);
		this.feuilleFour = feuilleFour;
	}

	public void actionPerformed(ActionEvent e)
	{
		this.feuilleFour.nouveauChargement();
	}
}
