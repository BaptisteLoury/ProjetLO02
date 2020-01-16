package Vue;

import java.util.LinkedList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import modele.Joueur;
import modele.Partie;

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
