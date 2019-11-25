package App;
import java.util.Scanner;
import java.util.HashSet;

public class Partie {

	private HashSet<Joueur> joueurs;
	private Deck deck;
	private int nombreJoueur;
	private Partie() {
		
	}
	//Singleton
	private Partie INSTANCE= null; 
	public Partie getInstance () {
		if (this.INSTANCE== null) {
			this.INSTANCE = new Partie(); 
		}
		else {
			return this.INSTANCE;
		}
		return this.INSTANCE ;
	}
	public void classerLesJoueurs() {
	
	}
	public void creerPartie() {
	
		Scanner sc = new Scanner(System.in);
		Scanner scannerPseudo = new Scanner(System.in);
		System.out.println("A combien de joueurs allez vous jouer la partie ?");
		nombreJoueur = sc.nextInt();
		switch (nombreJoueur) {
		case 3:
			System.out.println("parfait");
			break;
		case 4:
			System.out.println("parfait");
			break;
		default:
			System.out.println("Il y'a un probleme avec le nombre de joueurs. relance le programme. \n");
	
		}
		System.out.println("Combien de joueurs humains serez-vous ? (Il doit être inférieur ou égal au nombre de joueurs totals");
		int nombreJoueurReel = sc.nextInt();
		joueurs = new HashSet<Joueur>();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = scannerPseudo.nextLine();
			Joueur j = new Joueur(pseudo);
			joueurs.add(j);
			System.out.println("Le joueur " + j.getPseudo() + " a �t� ajout� � la partie !");
		}
		
		for (int i = nombreJoueurReel + 1;i<=nombreJoueur;i++) {
			Joueur jv = new JoueurVirtuel("Joueur Virtuel " + i);
			joueurs.add(jv);
			System.out.println("Le Joueur Virtuel " + i + " a bien �t� ajout� � la partie !");
		}
		// Cr�ation du jeu de cartes de base + m�lange automatique
		deck = new Deck();
		//Paragraphe pour déterminer si oui ou non on joue à l'extension
		 
		Scanner scExtension = new Scanner(System.in);
		System.out.println("Voulez vous jouer à l'extension? (O/N)");
		String extension = scExtension.nextLine();
		// La r�ponse d'un joueur suite � la question n'est g�n�ralement pas un bool�en, il est plus instinctif de r�pondre par oui/o ou non/n
		switch(extension) {
		case "O":
			deck.ajouterExtension();
			System.out.println("Okay, tu as décidé de jouer à l'extension. \n") ; 
			break;
		case "N":
			System.out.println("Okay tu ne veux pas jouer à l'extension. J'en prends note ! \n") ; 
			break;
		default:
			System.out.println("La syntaxe de la r�ponse n'est pas correcte. Je pars du principe que tu ne veux pas ajouter l'extension !") ; 
			
		}
		

		scannerPseudo.close();
		sc.close();
		scExtension.close();
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public int getNombreJoueurs() {
		return nombreJoueur;
	}
	
}
