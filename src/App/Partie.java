package App;
import java.util.Scanner;

public class Partie {

	protected Joueur[] joueurs = new Joueur[5];
	
	public Partie() {
		
	}
	public void creerJeuDeBase() {
		Cartes[] jeuDeBase = new Cartes[32];
		int ordreTrophee = 0;
		for (int ind1 = 0;ind1<=3;ind1++) {
			for (int ind2 = 0;ind2<=3;ind2++) {
				jeuDeBase[ordreTrophee] = new Cartes(EnumTrophee.values()[ordreTrophee],Couleur.values()[ind1],Valeur.values()[ind2]);
				System.out.println(jeuDeBase[ordreTrophee].getCouleur() +" "+ jeuDeBase[ordreTrophee].getTrophee() +" "+ jeuDeBase[ordreTrophee].getValeur());
				ordreTrophee++;
			}
		}
		/*Cartes joker = new Cartes(EnumTrophee.BestJest,Couleur.JOKER,Valeur.JOKER);
		Cartes coeur1 = new Cartes(EnumTrophee.Joker,Couleur.COEUR,Valeur.AS);
		Cartes pique4 = new Cartes(EnumTrophee.LowestTrefle,Couleur.PIQUE,Valeur.QUATRE);*/
	}
	public void ajouterExtension() {
	
	}
	public void classerLesJoueurs() {
	
	}
	public void creerPartie() {
	
		Scanner sc = new Scanner(System.in);
		Scanner scannerPseudo = new Scanner(System.in);
		System.out.println("A combien de joueurs allez vous jouer la partie ?");
		int nombreJoueur = sc.nextInt();
		System.out.println("Combien de joueurs humains serez-vous ? (Il doit être inférieur ou égal au nombre de joueurs totals");
		int nombreJoueurReel = sc.nextInt();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = scannerPseudo.nextLine();
			joueurs[i] = new Joueur(pseudo);
			System.out.println(joueurs[i].getPseudo());
		}
		for (int i = nombreJoueurReel + 1;i<=nombreJoueur;i++) {
			joueurs[i] = new JoueurVirtuel("Bot#" + i);
			System.out.println("Cr�ation du " + joueurs[i].getPseudo());
		}
		//Paragraphe pour déterminer si oui ou non on joue à l'extension
		boolean extension ; 
		Scanner scextension = new Scanner(System.in);
		System.out.println("Voulez vous jouer à l'extension? (boolean)");
		extension = scextension.nextBoolean() ; 
		if (extension == true) {
			ajouterExtension();
		}
		else if (extension == false) {
			System.out.println("Okay tu ne veux pas jouer à l'extension. J'en prends note ! \n") ; 
		}	
		sc.close();
		scannerPseudo.close();

	}
}
