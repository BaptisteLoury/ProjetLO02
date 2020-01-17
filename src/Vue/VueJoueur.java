/*
 * 
 */
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

import modele.Cartes;
import modele.Joueur;

public class VueJoueur implements Observer {
	
	/** Le joueur. */
	private Joueur joueur;
	
	/** la main. */
	private JPanel main;
	
	/** The carte graphique. */
	private LinkedList<VueCartes> carteGraphique;
	
	/**
	 * Instantiates a new vue joueur.
	 *
	 * @param joueur the joueur
	 */
	public VueJoueur(Joueur joueur) {
		this.joueur= joueur;
		main = new JPanel();
	}

	/**
	 * Update.
	 *
	 * @param o the o
	 * @param arg the arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Recuperer carte main.
	 */
	public void recupererCarteMain() {
		JLabel typeCarte = new JLabel(joueur.getPseudo());
		main.add(typeCarte);
		LinkedList<Cartes> carteJoueur = joueur.getMain();
		Iterator<Cartes> it = carteJoueur.iterator();
		while (it.hasNext()) {
			VueCartes vc = new VueCartes(it.next());
			carteGraphique.add(vc);
			final JLabel carte = vc.getImage();
			main.add(carte);
		}
	}

	/**
	 * Gets la main.
	 *
	 * @return la main
	 */
	public JPanel getMain() {
		return main;
	}

	/**
	 * Sets la main.
	 *
	 * @param main la new main
	 */
	public void setMain(JPanel main) {
		this.main = main;
	}
	
}
