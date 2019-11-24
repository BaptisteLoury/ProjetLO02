package App;
import java.util.Scanner;
import java.util.HashSet;

public class Partie {

	protected HashSet<Joueur> joueurs;
	
	public Partie() {
		
	}
	public void classerLesJoueurs() {
	
	}
	public void creerPartie() {
	
		Scanner sc = new Scanner(System.in);
		Scanner scannerPseudo = new Scanner(System.in);
		System.out.println("A combien de joueurs allez vous jouer la partie ?");
		int nombreJoueur = sc.nextInt();
		System.out.println("Combien de joueurs humains serez-vous ? (Il doit Ãªtre infÃ©rieur ou Ã©gal au nombre de joueurs totals");
		int nombreJoueurReel = sc.nextInt();
		sc.close();
		joueurs = new HashSet<Joueur>();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = scannerPseudo.nextLine();
			Joueur j = new Joueur(pseudo);
			joueurs.add(j);
			System.out.println("Le joueur " + j.getPseudo() + " a été ajouté à la partie !");
		}
		scannerPseudo.close();
		for (int i = nombreJoueurReel + 1;i<=nombreJoueur;i++) {
			Joueur jv = new JoueurVirtuel("Joueur Virtuel " + i);
			joueurs.add(jv);
			System.out.println("Le Joueur Virtuel " + i + " a bien été ajouté à la partie !");
		}
		// Création du jeu de cartes de base + mélange automatique
		Deck deck = new Deck();
		//Paragraphe pour dÃ©terminer si oui ou non on joue Ã  l'extension
		 
		Scanner scExtension = new Scanner(System.in);
		System.out.println("Voulez vous jouer Ã  l'extension? (boolean)");
		boolean extension = scExtension.nextBoolean() ;
		scExtension.close();
		if (extension == true) {
			deck.ajouterExtension();
		}
		else if (extension == false) {
			System.out.println("Okay tu ne veux pas jouer Ã  l'extension. J'en prends note ! \n") ; 
		}	

	}
}
