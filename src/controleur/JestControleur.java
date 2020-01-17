/*
 * 
 */
package controleur;
import java.util.Iterator;
import java.util.Scanner;

import Vue.*;
import modele.Joueur;
import modele.Offre;
import modele.Partie;
import modele.StrategieAvancee;
import modele.StrategieBasique;
import modele.StrategieHumain;

public class JestControleur {

	/** La partie. */
	private static Partie partie;
	
	/** La vue partie. */
	private static VuePartie vuePartie;
	
	/**
	 * Instantiates a new jest controleur.
	 *
	 * @param partie the partie
	 * @param vuePartie the vue partie
	 */
	public JestControleur(Partie partie, VuePartie vuePartie) {
		setPartie(partie);
		setVuePartie(vuePartie);
	}
	
	/**
	 * Debuter partie.
	 */
	public static void debuterPartie() {
		FenetreDebutPartie dialog = new FenetreDebutPartie(null,"Parametres de la partie",true);
		int nbTotal = dialog.getNbTotal();
		int nbReel = dialog.getNbReel();
		for (int i=1;i<=nbReel;i++) {
			System.out.println("i");
			FenetreCreerJoueur fjoueur = new FenetreCreerJoueur(null,"Creation d'un joueur",true);
			String nom = fjoueur.getNom().getText();
			Joueur j = new Joueur(nom);
			j.effectuerStrategie(new StrategieHumain(j));
			j.setHumain(true);
			Partie.getJoueurs().add(j);
		}
		for (int i = nbReel + 1;i<=nbTotal;i++) {
			FenetreJoueurVirtuel fjv = new FenetreJoueurVirtuel(null,"Strategie",true);
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
	
	/**
	 * Tour.
	 */
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
				FenetreAttente attenteFaireOffre = new FenetreAttente(null,"En attente...",true,j);
				DialogFaireOffre faireOffre = new DialogFaireOffre(null,"Faire une offre",true,j);
				j.setOffre(new Offre(faireOffre.getRecto(),faireOffre.getVerso(),j));
				j.getMain().clear();
				
			}
			
		}
		System.out.println("\n\n\n\n\n\n\n\n");
		Joueur joueurPlusFort = Partie.getInstance().recupererPlusForteOffre();
		Joueur joueurSuivant = joueurPlusFort;
		DialogChoisirOffre choisirOffre;
		if (joueurPlusFort.isHumain()) {
			choisirOffre = new DialogChoisirOffre(null,"Choisir une offre",true,joueurPlusFort);
			joueurSuivant = choisirOffre.getJoueurPioche();
			FenetreAttente attente = new FenetreAttente(null,"En attente...",true,joueurSuivant);
		}
		else {
			joueurSuivant = joueurPlusFort.choisirOffre(Partie.getJoueurs());
		}
		
		for (int i=2;i<=Partie.getJoueurs().size();i++) {
			if (joueurSuivant.isHumain()) {
				choisirOffre = new DialogChoisirOffre(null,"Choisir une offre",true,joueurSuivant);
				joueurSuivant = choisirOffre.getJoueurPioche();
				FenetreAttente attente = new FenetreAttente(null,"En attente...",true,joueurSuivant);
			}
			else {
				joueurSuivant = joueurSuivant.choisirOffre(Partie.getJoueurs());
			}
			if (joueurSuivant == joueurPlusFort) {
				joueurSuivant = Partie.getInstance().recupererPlusForteOffre();
			}
			
		}
	}
	
	/**
	 * Fin partie.
	 */
	public void finPartie() {
		Partie.getInstance().dernierTour();
		Partie.getInstance().attribuerTrophees();
		Partie.getInstance().calculDesPoints();
		donnerLesResultatsGraphique();
		
	}
	
	/**
	 * Donner les resultats graphique.
	 */
	public void donnerLesResultatsGraphique() {
		FenetreResultat resultat = new FenetreResultat(null,"Resultat",true);
	}
	
	/**
	 * Gets the partie.
	 *
	 * @return the partie
	 */
	public static Partie getPartie() {
		return partie;
	}

	/**
	 * Sets the partie.
	 *
	 * @param partie the new partie
	 */
	public static void setPartie(Partie partie) {
		JestControleur.partie = partie;
	}

	/**
	 * Gets the vue partie.
	 *
	 * @return the vue partie
	 */
	public static VuePartie getVuePartie() {
		return vuePartie;
	}

	/**
	 * Sets the vue partie.
	 *
	 * @param vuePartie the new vue partie
	 */
	public static void setVuePartie(VuePartie vuePartie) {
		JestControleur.vuePartie = vuePartie;
	}
	
}
