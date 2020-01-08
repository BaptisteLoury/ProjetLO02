package controleur;
import java.util.Scanner;

import App.*;
import Joueurs.Joueur;
import Strategies.StrategieAvancee;
import Strategies.StrategieBasique;
import Vue.*;

public class JestControleur {

	private static Partie partie;
	private static VuePartie vuePartie;
	
	public JestControleur(Partie partie, VuePartie vuePartie) {
		setPartie(partie);
		setVuePartie(vuePartie);
	}
	
	public static void debuterPartie() {
		FenetreDebutPartie dialog = new FenetreDebutPartie(null,"Paramètres de la partie",true);
		int nbTotal = dialog.getNbTotal();
		int nbReel = dialog.getNbReel();
		for (int i=1;i<=nbReel;i++) {
			System.out.println("i");
			FenetreCreerJoueur fjoueur = new FenetreCreerJoueur(null,"Création d'un joueur",true);
			String nom = fjoueur.getNom().getText();
			Joueur j = new Joueur(nom);
			Partie.getJoueurs().add(j);
		}
		for (int i = nbReel + 1;i<=nbTotal;i++) {
			FenetreJoueurVirtuel fjv = new FenetreJoueurVirtuel(null,"Stratégie",true);
			String strategie = fjv.getStringStrategie();
			switch (strategie) {
			case "Basique":
				
					Joueur jvB = new Joueur("Joueur Virtuel " + i);
					Partie.getJoueurs().add(jvB);
					jvB.effectuerStrategie(new StrategieBasique(jvB));
					System.out.println("Le Joueur Virtuel " + i + " a bien a ete ajoute dans la partie !!");
				
				break;
			case "Avancee":
				
					Joueur jvA = new Joueur("Joueur Virtuel " + i);
					Partie.getJoueurs().add(jvA);
					jvA.effectuerStrategie(new StrategieAvancee(jvA));
					System.out.println("Le Joueur Virtuel " + i + " a bien a ete ajoute dans la partie !!");
				
				break;
			default:
				System.out.println("Oh non tu as fait une erreur de frappe !") ;
			}
		}
		
	}

	public static Partie getPartie() {
		return partie;
	}

	public static void setPartie(Partie partie) {
		JestControleur.partie = partie;
	}

	public static VuePartie getVuePartie() {
		return vuePartie;
	}

	public static void setVuePartie(VuePartie vuePartie) {
		JestControleur.vuePartie = vuePartie;
	}
	
}
