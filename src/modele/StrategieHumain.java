
/*
 *Cette stratégie concerne les joueurs humains. C'est bien à eux de chosir la carte qu'ils désirent prendre. 
 */
package modele;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class StrategieHumain implements Strategie{

	/** Le joueur. */
	private Joueur joueur;
	
	/**
	 * Instantie une nouvelle strategie humain.
	 *
	 * @param joueur the joueur
	 */
	public StrategieHumain(Joueur joueur) {
		this.joueur = joueur;
	}
	
	/**
	 * Strategie faire offre.
	 * 
	 */
	@Override
	public void strategieFaireOffre() {
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("Au tour de "+ joueur.getPseudo() +" de proposer une offre.");

		System.out.println(joueur.afficherIndiceCartes());
		System.out.println("Donner l'indice de la carte a mettre en recto. (0 ou 1)");
		Scanner scCarteOffre = new Scanner(System.in);
		int indiceRecto = scCarteOffre.nextInt();
		if (indiceRecto ==0){
			joueur.setOffre(new Offre(joueur.getMain().get(0),joueur.getMain().get(1),joueur));
			joueur.getMain().clear();
		}
		else if (indiceRecto==1){

			joueur.setOffre(new Offre(joueur.getMain().get(1),joueur.getMain().get(0),joueur));
			joueur.getMain().clear();

		}
		else {
			System.out.println("Les indices renseignes ne sont pas correctes, veuillez ï¿½ nouveau complï¿½ter votre offre.");
			this.strategieFaireOffre();
		}
	}
	
	/**
	 * Strategie choisir offre.
	 *
	 * @param joueurs the joueurs
	 * @return the joueur
	 */
	@Override
	public Joueur strategieChoisirOffre(LinkedList<Joueur> joueurs) {
		Scanner scOffre = new Scanner(System.in);
		Iterator<Joueur> itj = joueurs.iterator();
		int nombreOffreSuffisante = 0;
		System.out.println("Le joueur "+joueur.getPseudo()+" doit choisir une offre.");
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != joueur) {
				System.out.println(joueurs.indexOf(j) +" : " +j.getOffre().toString());
				nombreOffreSuffisante++;
			}
		}
		if (nombreOffreSuffisante == 0) {
			System.out.println("Le joueur "+joueur.getPseudo()+" doit recuperer une carte de sa propre offre.");
			this.strategieChoisirCarte(joueur);
			return joueur;
			//this.setOffreChoisie(this.getOffre());
		}
		else {
			
			System.out.println("Quelle offre avez vous choisi ? (indice du joueur)");
			int choixOffre = scOffre.nextInt();
			if(choixOffre<=joueurs.size() && choixOffre != joueurs.indexOf(joueur)) {
				System.out.println("Vous avez choisi l'offre de "+ joueurs.get(choixOffre).getPseudo());
				this.strategieChoisirCarte(joueurs.get(choixOffre));
				return joueurs.get(choixOffre);
				//this.setOffreChoisie(joueurs.get(choixOffre).getOffre());
			}
			else {
				System.out.println("Mauvais indice entre.");
				this.strategieChoisirOffre(joueurs);
				return this.strategieChoisirOffre(joueurs);
			}
		}
	}
	
	/**
	 * Strategie choisir carte.
	 *
	 * @param joueurPioche the joueur pioche
	 */
	public void strategieChoisirCarte(Joueur joueurPioche) {
		Scanner scCarte = new Scanner(System.in);
		System.out.println(joueurPioche.getOffre().toString());
		System.out.println("Tapez 1 pour recuperer la carte recto et 2 pour la carte verso.");
		int choixCartes = scCarte.nextInt();
		if (choixCartes != 1 && choixCartes != 2) {
			System.out.println("Mauvais indice entre.");
			this.strategieChoisirCarte(joueurPioche);
		}
		else {
			
			if (choixCartes ==1) {
				joueur.getStack().add(joueurPioche.getOffre().getRecto());
				joueurPioche.getOffre().setRecto(null);
			}
			else {
				joueur.getStack().add(joueurPioche.getOffre().getVerso());
				joueurPioche.getOffre().setVerso(null);
			}
			
		}
		joueurPioche.getOffre().setOffreSuffisante(false);
	}
}
