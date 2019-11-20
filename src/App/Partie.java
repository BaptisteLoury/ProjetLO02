package App;
import java.util.Scanner;

//pour les accents 
import java.awt.*;


public class Partie {

	protected Joueur[] joueurs = new Joueur[5];
	
	public Partie() {
		
	}
	
	public void classerLesJoueurs() {
	
	}
	public void creerPartie() {
	
		//Création des joueurs 
		Scanner sc = new Scanner(System.in);
		Scanner scannerPseudo = new Scanner(System.in);
		System.out.println("À combien de joueurs allez vous jouer la partie ?");
		int nombreJoueur = sc.nextInt();
		System.out.println("Combien de joueurs humains serez-vous ? (Il doit être inférieur ou égal au nombre de joueurs totals");
		int nombreJoueurReel = sc.nextInt();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = scannerPseudo.nextLine();
			joueurs[i] = new Joueur(pseudo);
			System.out.println(joueurs[i].getPseudo());
		}
		sc.close();
		scannerPseudo.close();
		for (int i = nombreJoueurReel + 1;i<=nombreJoueur;i++) {
			joueurs[i] = new JoueurVirtuel("Bot#" + i);
			System.out.println("Cr�ation du " + joueurs[i].getPseudo());
		}

		//Paragraphe pour déterminer si oui ou non on joue à l'extension
		boolean extension ; 
		Scanner scextension = new Scanner(System.in);
		System.out.println("Voulez vous jouer à l'extension? (boolean)");
		extension = scextension.nextBoolean() ; 
		scextension.close();
		if (extension == true) {
			ajouterExtension();
		}
		else if (extension == false) {
			System.out.println("Okay tu ne veux pas jouer à l'extension. J'en prends note ! \n") ; 
		}	
		

	}
}
