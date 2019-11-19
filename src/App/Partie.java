package App;
import java.util.Scanner;

public class Partie {

	protected Joueur[] joueurs = new Joueur[4];
	
	public Partie() {
		
	}
	public void creerJeuDeBase() {
		Cartes carreau1 = new Cartes(EnumTrophee.MajorityQuatre,Couleur.CARREAU,Valeur.AS);
		Cartes joker = new Cartes(EnumTrophee.BestJest,Couleur.JOKER,Valeur.JOKER);
		Cartes coeur1 = new Cartes(EnumTrophee.Joker,Couleur.COEUR,Valeur.AS);
		Cartes pique4 = new Cartes(EnumTrophee.LowestTrefle,Couleur.PIQUE,Valeur.QUATRE);
	}
	public void ajouterExtension() {
	
	}
	public void classerLesJoueurs() {
	
	}
	public void creerPartie() {
	
		Scanner sc = new Scanner(System.in);
		System.out.println("À combien de joueurs allez vous jouer la partie ?");
		int nombreJoueur = sc.nextInt();
		System.out.println("Combien de joueurs humains serez-vous ?");
		int nombreJoueurReel = sc.nextInt();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = sc.nextLine();
			joueurs[i] = new Joueur(pseudo);
			System.out.println(joueurs[i].getPseudo());
		}
	}
}
