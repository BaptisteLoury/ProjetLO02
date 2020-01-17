/*
 * 
 */
package Vue;

import java.util.LinkedList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.awt.color.*;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controleur.JestControleur;
import App.*;
import Joueurs.Joueur;

public class VuePartie implements Observer {
	
	/** The modele. */
	private Partie modele;
	
	/** The joueurs. */
	private LinkedList<Joueur> joueurs;
	
	/** The vue joueurs. */
	private LinkedList<VueJoueur> vueJoueurs;
	
	/** The fenetre. */
	private JFrame fenetre;
	
	/** The log. */
	private JTextArea log;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The deck. */
	private JLabel deck;
	
	/** The stack intermediaire. */
	private JLabel stackIntermediaire;
	
	/**
	 * Instantiates a new vue partie.
	 *
	 * @param modele the modele
	 */
	public VuePartie(final Partie modele) {
		this.setModele(modele);
		
		joueurs = Partie.getJoueurs();
		
		fenetre = new JFrame("JEST");
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);
		

		fenetre.pack();
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre.setBackground(Color.ORANGE);
		fenetre.setVisible(true);
		fenetre.setLocationRelativeTo(null);
	}

	/**
	 * Update.
	 *
	 * @param arg0 the arg 0
	 * @param arg1 the arg 1
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Prompt.
	 *
	 * @param msg the msg
	 */
	public void prompt(String msg) {
		log.append(msg + "\n");
	}
	
	/**
	 * Gets the modele.
	 *
	 * @return the modele
	 */
	public Partie getModele() {
		return modele;
	}

	/**
	 * Sets the modele.
	 *
	 * @param modele the new modele
	 */
	public void setModele(Partie modele) {
		this.modele = modele;
	}

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public JTextArea getLog() {
		return log;
	}

	/**
	 * Sets the log.
	 *
	 * @param log the new log
	 */
	public void setLog(JTextArea log) {
		this.log = log;
	}
	
	
}
