package App;
import java.util.Scanner;
import java.util.HashSet;

public class Partie {

	private HashSet<Joueur> joueurs;
	private Deck deck;
	
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
		joueurs = new HashSet<Joueur>();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = scannerPseudo.nextLine();
			Joueur j = new Joueur(pseudo);
			joueurs.add(j);
			System.out.println("Le joueur " + j.getPseudo() + " a été ajouté à la partie !");
		}
		
		for (int i = nombreJoueurReel + 1;i<=nombreJoueur;i++) {
			Joueur jv = new JoueurVirtuel("Joueur Virtuel " + i);
			joueurs.add(jv);
			System.out.println("Le Joueur Virtuel " + i + " a bien été ajouté à la partie !");
		}
		// Création du jeu de cartes de base + mélange automatique
		deck = new Deck();
		//Paragraphe pour dÃ©terminer si oui ou non on joue Ã  l'extension
		 
		Scanner scExtension = new Scanner(System.in);
		System.out.println("Voulez vous jouer Ã  l'extension? (O/N)");
		String extension = scExtension.nextLine();
		// La réponse d'un joueur suite à la question n'est généralement pas un booléen, il est plus instinctif de répondre par oui/o ou non/n
		switch(extension) {
		case "O":
			deck.ajouterExtension();
			break;
		case "N":
			System.out.println("Okay tu ne veux pas jouer Ã  l'extension. J'en prends note ! \n") ; 
			break;
		default:
			System.out.println("La syntaxe de la réponse n'est pas correcte. Je pars du principe que tu ne veux pas ajouter l'extension !") ; 
			
		}
		/*boolean extension = scExtension.nextBoolean() ;
		if (extension == true) {
			deck.ajouterExtension();
		}
		else if (extension == false) {
			System.out.println("Okay tu ne veux pas jouer Ã  l'extension. J'en prends note ! \n") ; 
		}	*/
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
}
