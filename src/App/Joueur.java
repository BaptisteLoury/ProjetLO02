package App;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Joueur {

	private String pseudo;
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
		this.toStringMain();
		System.out.println("Donner l'indice de la carte mise en recto.");
		int indiceRecto = scCarteOffre.nextInt();
		System.out.println("Donner l'indice de la carte mise en verso.");
		int indiceVerso = scCarteOffre.nextInt();
		scCarteOffre.close();
		//offre = new Offre(recto,verso,this.getPseudo();
	}
	public void choisirCarte()  {
		//cartesProposees a definir dans faireOffre
		//main.add(cartesProposees);
	}	
	public String getPseudo() {
		return pseudo;
	}
	public String toStringMain() {
		Iterator<Cartes> it = main.iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Cartes carte = it.next();
			sb.append(it.hashCode());
			sb.append(" : ");
			sb.append(carte.toString());
		}
		return sb.toString();
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public static int getNbJoueurs() {
		return nbJoueurs;
	}
	public static void setNbJoueurs(int nbJoueurs) {
		Joueur.nbJoueurs = nbJoueurs;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pseudo);
		sb.append(" : ");
		sb.append(main);
		return sb.toString();
	}
}
