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
import App.*;

public class VueJoueur implements Observer {
	
	private Joueur joueur;
	
	private JPanel main;
	
	private LinkedList<VueCartes> carteGraphique;
	
	public VueJoueur(Joueur joueur) {
		this.joueur= joueur;
		main = new JPanel();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
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

	public JPanel getMain() {
		return main;
	}

	public void setMain(JPanel main) {
		this.main = main;
	}
	
}
