package Vue;

import java.util.LinkedList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

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
	
	private JFrame fenetre;
	
	private JLabel deck;
	
	private JLabel stackIntermediaire;
	
	public VuePartie(final Partie modele) {
		this.setModele(modele);
		
		joueurs = Partie.getJoueurs();
		
		fenetre = new JFrame("JEST");
		fenetre.setLayout(new BorderLayout());
		fenetre.setResizable(true);
		
		JPanel panelTapis = new JPanel();
		JLabel imgTapis = new JLabel();
		
		
		deck = new JLabel(new ImageIcon("img/deckRempli.png"));
		stackIntermediaire = new JLabel(new ImageIcon("img/deckVide.png")); 
		
		fenetre.setLayout(new GridLayout());
		fenetre.add(imgTapis);
		fenetre.add(deck);
		fenetre.add(stackIntermediaire);
		
		fenetre.pack();
		fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH);
		fenetre.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	public Partie getModele() {
		return modele;
	}

	public void setModele(Partie modele) {
		this.modele = modele;
	}
	
	
}
