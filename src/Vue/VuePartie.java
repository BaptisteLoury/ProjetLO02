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
	
	private Partie modele;
	
	private LinkedList<Joueur> joueurs;
	
	private LinkedList<VueJoueur> vueJoueurs;
	
	private JFrame fenetre;
	
	private JTextArea log;
	private JScrollPane scrollPane;
	
	private JLabel deck;
	
	private JLabel stackIntermediaire;
	
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

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	public void prompt(String msg) {
		log.append(msg + "\n");
	}
	public Partie getModele() {
		return modele;
	}

	public void setModele(Partie modele) {
		this.modele = modele;
	}

	public JTextArea getLog() {
		return log;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}
	
	
}
