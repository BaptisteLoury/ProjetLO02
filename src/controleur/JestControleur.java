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
		dialog.setVisible(true);
		//int nbTotal = dialog.getNbTotal();
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
