package controleur;
import App.*;
import Joueurs.Joueur;
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
		if (nbReel!=nbTotal) {
			
		}
		FenetreJoueurVirtuel fjv = new FenetreJoueurVirtuel(null,"Stratégie",true);
		
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
