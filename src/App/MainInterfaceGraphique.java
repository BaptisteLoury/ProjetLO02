/*
 * 
 */
package App;
import java.util.Iterator;

import Joueurs.Joueur;
import Visit.VisitorJest;
import Vue.*;
import controleur.JestControleur;

public class MainInterfaceGraphique implements Runnable {
	
	/**
	 * Instantie un nouveau main interface graphique.
	 */
	public MainInterfaceGraphique() {
		
		VisitorJest monVisiteur = new VisitorJest();
		FenetreVariante variante = new FenetreVariante(null,"Choix du mode de jeu",true);
		if (variante.getJeuBase().getState()) {
			Partie partie = Partie.getInstance();
			System.out.println("base");
		}
		else if (variante.getVariante1().getState()) {
			Variante1 partie = Variante1.getInstance();
			System.out.println("1");
		}
		else {
			Variante2 partie = Variante2.getInstance();
			System.out.println("2");
		}
		//partie.accept(monVisiteur);
		Partie.getInstance().init();
		VuePartie vuePartie = new VuePartie(Partie.getInstance());
		JestControleur jestControleur = new JestControleur(Partie.getInstance(),vuePartie);
		if (variante.isExtension()) {
			Partie.getInstance().getDeck().ajouterExtension();
		}
		Partie.getInstance().constituerTrophee();
		int nbTour = 1;
		while (Partie.getInstance().getDeck().getDeckCartes().size()>=Joueur.getNbJoueurs()) {
			System.out.println("TOUR # "+ nbTour);
			if (nbTour!=1) {
				Partie.getInstance().remettreEnJeuCarteOffre();
			}
			jestControleur.tour();
			nbTour++;
		}
		jestControleur.finPartie();
	}
	
	/**
	 * Run.
	 */
	@Override
	public void run() {
		new MainInterfaceGraphique();
		
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Thread partie = new Thread(new MainInterfaceGraphique());

	}

	

}
