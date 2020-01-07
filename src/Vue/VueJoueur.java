package Vue;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Joueurs.Joueur;

public class VueJoueur implements Observer {
	
	private Joueur joueur;
	
	private JPanel main;
	
	private LinkedList<VueCartes> carteGraphique;
	
	public VueJoueur(Joueur joueur) {
		this.joueur= joueur;
		//this.nom = new JLabel(joueur.getNom());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
