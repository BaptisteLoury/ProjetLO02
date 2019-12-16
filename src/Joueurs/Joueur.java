package Joueurs;
import java.util.LinkedList;
import java.util.Scanner;

import App.Cartes;
import App.Offre;

import java.util.Iterator;

public abstract class Joueur {

	private String pseudo;

	private static int nbJoueurs = 0;
	protected LinkedList<Cartes> main;
	protected LinkedList<Cartes> stack;
	protected Offre offre;
	protected Offre offreChoisie;
	protected int pointsPourRecupererTrophee;
	protected boolean possessionAs = false;
	protected boolean possessionQuatre = false;
	protected boolean possessionDeux = false;
	protected int scoreFinal = 0;
	
	
	public Joueur () {}	
	
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
		main = new LinkedList<Cartes>();

		stack = new LinkedList<Cartes>();

	}

	public abstract void faireOffre() ; /*{
		//Pour ne pas voir les cartes des autres
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("Au tour de "+ this.getPseudo() +" de proposer une offre.");

		System.out.println(this.afficherIndiceCartes());
		System.out.println("Donner l'indice de la carte a mettre en recto. (0 ou 1)");
		Scanner scCarteOffre = new Scanner(System.in);
		int indiceRecto = scCarteOffre.nextInt();
		if (indiceRecto ==0){
			offre = new Offre(main.get(0),main.get(1),this);
			main.clear();
		}
		else if (indiceRecto==1){

			offre = new Offre(main.get(1),main.get(0),this);
			main.clear();

		}
		else {
			System.out.println("Les indices renseignes ne sont pas correctes, veuillez � nouveau compl�ter votre offre.");
			this.faireOffre();
		}
		
	}*/
	public abstract Joueur choisirOffre(LinkedList<Joueur> joueurs); /*{ 
		Scanner scOffre = new Scanner(System.in);
		Iterator<Joueur> itj = joueurs.iterator();
		int nombreOffreSuffisante = 0;
		System.out.println("Le joueur "+this.getPseudo()+" doit choisir une offre.");
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != this) {
				System.out.println(joueurs.indexOf(j) +" : " +j.getOffre().toString());
				nombreOffreSuffisante++;
			}
		}
		if (nombreOffreSuffisante == 0) {
			System.out.println("Le joueur "+this.getPseudo()+" doit recuperer une carte de sa propre offre.");
			this.choisirCarte(this);
			return this;
			//this.setOffreChoisie(this.getOffre());
		}
		else {
			
			System.out.println("Quelle offre avez vous choisi ? (indice du joueur)");
			int choixOffre = scOffre.nextInt();
			if(choixOffre<=joueurs.size() && choixOffre != joueurs.indexOf(this)) {
				System.out.println("Vous avez choisi l'offre de "+ joueurs.get(choixOffre).getPseudo());
				this.choisirCarte(joueurs.get(choixOffre));
				return joueurs.get(choixOffre);
				//this.setOffreChoisie(joueurs.get(choixOffre).getOffre());
			}
			else {
				System.out.println("Mauvais indice entre.");
				this.choisirOffre(joueurs);
				return this.choisirOffre(joueurs);
			}
		}
	}*/
	
	/*public abstract void choisirCarte(Joueur joueur); {
		Scanner scCarte = new Scanner(System.in);
		System.out.println(joueur.getOffre().toString());
		System.out.println("Tapez 1 pour recuperer la carte recto et 2 pour la carte verso.");
		int choixCartes = scCarte.nextInt();
		if (choixCartes != 1 && choixCartes != 2) {
			System.out.println("Mauvais indice entre.");
			this.choisirCarte(joueur);
		}
		else {
			
			if (choixCartes ==1) {
				stack.add(joueur.getOffre().getRecto());
				joueur.getOffre().setRecto(null);
			}
			else {
				stack.add(joueur.getOffre().getVerso());
				joueur.getOffre().setVerso(null);
			}
			
		}
		joueur.getOffre().setOffreSuffisante(false);
	}*/
	
	public Offre getOffre() {
		return offre;
	}
	public LinkedList<Cartes> getMain() {
		return main;
	}
	public void setMain(LinkedList<Cartes> main) {
		this.main = main;
	}
	public String getPseudo() {
		return this.pseudo;
	}
	public String afficherIndiceCartes() {
		StringBuffer sb = new StringBuffer();
		sb.append("0");
		sb.append(" : ");
		sb.append(main.getFirst().toString());
		sb.append(" | 1");
		sb.append(" : ");
		sb.append(main.getLast().toString());
		return sb.toString();
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public static int getNbJoueurs() {
		return nbJoueurs;
	}
	
	public Offre getOffreChoisie() {
		return offreChoisie;
	}
	public void setOffreChoisie(Offre offreChoisie) {
		this.offreChoisie = offreChoisie;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pseudo);
		sb.append(" : ");
		sb.append(stack.toString());
		return sb.toString();
	}
	public LinkedList<Cartes> getStack() {
		return stack;
	}
	public int getPointsPourRecupererTrophee() {
		return pointsPourRecupererTrophee;
	}
	public void setPointsPourRecupererTrophee(int pointsPourRecupererTrophee) {
		this.pointsPourRecupererTrophee = pointsPourRecupererTrophee;
	}
	public boolean isPossessionAs() {
		return possessionAs;
	}
	public void setPossessionAs(boolean possessionAs) {
		this.possessionAs= possessionAs;
	}
	public boolean isPossessionQuatre() {
		return possessionQuatre;
	}
	public void setPossessionQuatre(boolean possessionQuatre) {
		this.possessionQuatre = possessionQuatre;
	}
	public boolean isPossessionDeux() {
		return possessionDeux;
	}
	public void setPossessionDeux(boolean possessionDeux) {
		this.possessionDeux = possessionDeux;
	}
	public int getScoreFinal() {
		return scoreFinal;
	}
	public void setScoreFinal(int scoreFinal) {
		this.scoreFinal = scoreFinal;
	}
	
}
