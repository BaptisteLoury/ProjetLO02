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
			offre = new Offre(stack.get(0),stack.get(1),this);
			main.remove(stack.getFirst());
			main.remove(stack.getLast());
		}
		else if (indiceRecto==1){
			offre = new Offre(stack.get(1),stack.get(0),this);
			main.remove(stack.getFirst());
			main.remove(stack.getLast());

		}
		else {
			System.out.println("Les indices renseign�s ne sont pas correctes, veuillez � nouveau compl�ter votre offre.");
			this.faireOffre();
		}
		
	}
	public void choisirOffre(LinkedList<Joueur> joueurs) {
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
		}
		else {
			System.out.println("Quelle offre avez vous choisi ? (indice du joueur)");
			int choixOffre = scOffre.nextInt();
			if(choixOffre<=joueurs.size() && choixOffre != joueurs.indexOf(this)) {
				System.out.println("Vous avez choisi l'offre de "+ joueurs.get(choixOffre).getPseudo());
				this.choisirCarte(joueurs.get(choixOffre));
				scOffre.close();
			}
			else {
				System.out.println("Mauvais indice entr�.");
				this.choisirOffre(joueurs);
			}
		}
	}
	
	public Cartes choisirCarte(Joueur joueur) {
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
				carteRestante = joueur.getOffre().getVerso();
			}
			else {
				stack.add(joueur.getOffre().getVerso());
				carteRestante = joueur.getOffre().getRecto();
			}
			scCarte.close();
			
		}
		joueur.getOffre().setOffreSuffisante(false);
		return carteRestante;
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
		sb.append(stack.getFirst().toString());
		sb.append(" | 1");
		sb.append(" : ");
		sb.append(stack.getLast().toString());
		return sb.toString();
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public static int getNbJoueurs() {
		return nbJoueurs;
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
