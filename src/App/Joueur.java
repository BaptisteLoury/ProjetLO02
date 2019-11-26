package App;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Joueur {

	private String pseudo;
	//Etrange d'avoir deux attributs nombreJoueurs
	private static int nbJoueurs = 0;
	private LinkedList<Cartes> main;
	private Offre offre;
	
	public Joueur() {
		
	}
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
		main = new LinkedList<Cartes>();
	}

	public void faireOffre()  {
		Scanner scCarteOffre = new Scanner(System.in);
		System.out.println("Au tour de "+ this.getPseudo() +" de proposer une offre.");
		this.afficherIndiceCartes();
		System.out.println("Donner l'indice de la carte à mettre en recto. (1 ou 2)");
		int indiceRecto = scCarteOffre.nextInt();
		System.out.println("Donner l'indice de la carte à mettre en verso. (1 ou 2 mais différent de la réponse précédente)");
		int indiceVerso = scCarteOffre.nextInt();
		scCarteOffre.close();
		if ((indiceVerso != indiceRecto) && (indiceVerso ==1 || indiceVerso ==2) && (indiceRecto ==1 || indiceVerso ==2)) {
			offre = new Offre(main.get(main.size()-2+indiceRecto),main.get(main.size()-2+indiceVerso),this);
			main.remove(main.get(main.size()-2+indiceRecto));
			main.remove(main.get(main.size()-2+indiceVerso));
		}
		else {
			System.out.println("Les indices renseignés ne sont pas correctes, veuillez à nouveau compléter votre offre.");
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
			System.out.println("Vous devez récupérer une carte de votre offre.");
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
				System.out.println("Mauvais indice entré.");
				this.choisirOffre(joueurs);
			}
		}
	}
	
	public Cartes choisirCarte(Joueur joueur) {
		Cartes carteRestante = new Cartes();
		Scanner scCarte = new Scanner(System.in);
		System.out.println(joueur.getOffre().toString());
		System.out.println("Tapez 1 pour récupérer la carte recto et 2 pour la carte verso.");
		int choixCartes = scCarte.nextInt();
		if (choixCartes != 1 && choixCartes != 2) {
			System.out.println("Mauvais indice entré.");
			this.choisirCarte(joueur);
		}
		else {
			
			if (choixCartes ==1) {
				main.add(joueur.getOffre().getRecto());
				carteRestante = joueur.getOffre().getVerso();
			}
			else {
				main.add(joueur.getOffre().getVerso());
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
		return pseudo;
	}
	public String afficherIndiceCartes() {
		StringBuffer sb = new StringBuffer();
		sb.append("1");
		sb.append(" : ");
		sb.append(main.get(main.size()-1).toString());
		sb.append(" - 2");
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
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pseudo);
		sb.append(" : ");
		sb.append(main.toString());
		return sb.toString();
	}
}
