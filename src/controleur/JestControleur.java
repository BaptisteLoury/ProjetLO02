package controleur;
import java.util.Iterator;
import java.util.Scanner;

import App.*;
import Joueurs.Joueur;
import Strategies.StrategieAvancee;
import Strategies.StrategieHumain;
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
			j.effectuerStrategie(new StrategieHumain(j));
			j.setHumain(true);
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
					jvB.setHumain(false);
					System.out.println("Le Joueur Virtuel " + i + " a bien a ete ajoute dans la partie !!");
				
				break;
			case "Avancee":
				
					Joueur jvA = new Joueur("Joueur Virtuel " + i);
					Partie.getJoueurs().add(jvA);
					jvA.effectuerStrategie(new StrategieAvancee(jvA));
					jvA.setHumain(false);
					System.out.println("Le Joueur Virtuel " + i + " a bien a ete ajoute dans la partie !!");
				
				break;
			default:
				System.out.println("Oh non tu as fait une erreur de frappe !") ;
			}
		}
		
	}
	public void tour() {
		Partie.getInstance().distribuer();
		//Dans l'ordre, les joueurs font leur offre
		Iterator<Joueur> itj = Partie.getJoueurs().iterator();
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (!j.isHumain()) {
				j.faireOffre();
			}
			else {
				DialogFaireOffre faireOffre = new DialogFaireOffre(null,"Faire une offre",true,j);
				j.setOffre(new Offre(faireOffre.getRecto(),faireOffre.getVerso(),j));
				j.getMain().clear();
			}
			
		}
		System.out.println("\n\n\n\n\n\n\n\n");
		Joueur joueurPlusFort = Partie.getInstance().recupererPlusForteOffre();
		Joueur joueurSuivant;
		DialogChoisirOffre choisirOffre;
		if (joueurPlusFort.isHumain()) {
			choisirOffre = new DialogChoisirOffre(null,"Choisir une offre",true,joueurPlusFort);
			joueurSuivant = choisirOffre.getJoueurPioche();
		}
		else {
			joueurSuivant = joueurPlusFort.choisirOffre(Partie.getJoueurs());
		}
		
		for (int i=2;i<=Partie.getJoueurs().size();i++) {
			if (joueurSuivant.isHumain()) {
				
				choisirOffre = new DialogChoisirOffre(null,"Choisir une offre",true,joueurSuivant);
				joueurSuivant = choisirOffre.getJoueurPioche();
			}
			else {
				joueurSuivant = joueurSuivant.choisirOffre(Partie.getJoueurs());
			}
			if (joueurSuivant == joueurPlusFort) {
				joueurSuivant = Partie.getInstance().recupererPlusForteOffre();
			}
			
		}
	}
	public void finPartie() {
		Partie.getInstance().dernierTour();
		Partie.getInstance().attribuerTrophees();
		Partie.getInstance().calculDesPoints();
		donnerLesResultatsGraphique();
		
	}
	public void donnerLesResultatsGraphique() {
		System.out.println("Résultats de la partie :");
		Iterator<Joueur> itj = Partie.getJoueurs().iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			vuePartie.prompt(j.getPseudo()+" : "+j.getScoreFinal());
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
