package App;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Joueur {

	private String pseudo;

	private static int nbJoueurs = 0;
	private LinkedList<Cartes> main;
	private LinkedList<Cartes> stack;
	private Offre offre;
	private Offre offreChoisie;
	
	public Joueur() {
		
	}
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
		main = new LinkedList<Cartes>();

		stack = new LinkedList<Cartes>();

	}

	public void faireOffre()  {
		System.out.println("Au tour de "+ this.getPseudo() +" de proposer une offre.");

		System.out.println(this.afficherIndiceCartes());
		System.out.println("Donner l'indice de la carte � mettre en recto. (0 ou 1)");
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
			System.out.println("Les indices renseign�s ne sont pas correctes, veuillez � nouveau compl�ter votre offre.");
			this.faireOffre();
		}
		
	}
	public Joueur choisirOffre(LinkedList<Joueur> joueurs) {
		Scanner scOffre = new Scanner(System.in);
		Iterator<Joueur> itj = joueurs.iterator();
		int nombreOffreSuffisante = 0;
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != this) {
				System.out.println(joueurs.indexOf(j) +" : " +j.getOffre().toString());
				nombreOffreSuffisante++;
			}
		}
		if (nombreOffreSuffisante == 0) {
			System.out.println("Vous devez r�cup�rer une carte de votre offre.");
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
				System.out.println("Mauvais indice entr�.");
				this.choisirOffre(joueurs);
				return this.choisirOffre(joueurs);
			}
		}
	}
	
	public void choisirCarte(Joueur joueur) {
		Cartes carteRestante = new Cartes();
		Scanner scCarte = new Scanner(System.in);
		System.out.println(joueur.getOffre().toString());
		System.out.println("Tapez 1 pour r�cup�rer la carte recto et 2 pour la carte verso.");
		int choixCartes = scCarte.nextInt();
		if (choixCartes != 1 && choixCartes != 2) {
			System.out.println("Mauvais indice entr�.");
			this.choisirCarte(joueur);
		}
		else {
			
			if (choixCartes ==1) {
				stack.add(joueur.getOffre().getRecto());
			}
			else {
				stack.add(joueur.getOffre().getVerso());
			}
			
		}
		joueur.getOffre().setOffreSuffisante(false);
	}
	
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
		sb.append(main.toString());
		return sb.toString();
	}
	public LinkedList<Cartes> getStack() {
		return stack;
	}
	
}
