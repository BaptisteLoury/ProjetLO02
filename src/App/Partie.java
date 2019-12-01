package App;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class Partie {

	private LinkedList<Joueur> joueurs;
	private Deck deck;
	
	public Partie() {
		
	}
	//Singleton
	private static Partie INSTANCE= null; 
	public static Partie  getInstance () {
		if (INSTANCE== null) {
			INSTANCE = new Partie(); 
		}
		else {
			return INSTANCE;
		}
		return INSTANCE ;
	}

	public Joueur recupererPlusForteOffre() {
		Iterator<Joueur> itj = joueurs.iterator();
		Offre plusForteOffre = joueurs.getFirst().getOffre(); 
		while (itj.hasNext()) {
			Joueur j = itj.next();
			if (j.getOffre().estOffreSuffisante())
				if (j.getOffre().getRecto().getValeur().ordinal() > plusForteOffre.getRecto().getValeur().ordinal()) {
					plusForteOffre = j.getOffre();
				}
				else if (j.getOffre().getRecto().getValeur().ordinal() == plusForteOffre.getRecto().getValeur().ordinal()) {
					// < parce que dans l'�num�ration Couleur, l'ordre va de la plus grand � la plus petite
					if (j.getOffre().getRecto().getCouleur().ordinal() < plusForteOffre.getRecto().getCouleur().ordinal()) {
						plusForteOffre = j.getOffre();
					}
				}
		}
		return plusForteOffre.getOffrant();

	}
	
	public void distribuer() {
		Iterator<Joueur> itj = joueurs.iterator();
		//Cas où ce n'est pas le dernier tour 
		if (!deck.isEmpty()) {
			while (itj.hasNext()) {
				Joueur j = (Joueur) itj.next();
				j.getMain().add(deck.getDeckCartes().pop());
				System.out.println("compteur1");
				if(deck.getStackIntermediaire().isEmpty()) {
					j.getMain().add(deck.getDeckCartes().pop());
					System.out.println("compteur2");
				}
				else {
					j.getMain().add(deck.getStackIntermediaire().pop());
					System.out.println("compteur3");
				}
			}
		}
		//Cas ou c'est le dernier tour 
		else {
			while (itj.hasNext()) {
				Joueur j = (Joueur) itj.next();
				j.getMain().add(deck.getStackIntermediaire().pop());
			}
		}
	}
	public void tour() {
		this.distribuer();
		//Dans l'ordre, les joueurs font leur offre
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			j.faireOffre();
		}
		Joueur joueurPlusFort = this.recupererPlusForteOffre();
		Joueur joueurSuivant = joueurPlusFort.choisirOffre(joueurs);
		for (int i=2;i<=joueurs.size();i++) {
			joueurSuivant = joueurSuivant.choisirOffre(joueurs);
			//Je ne suis pas sur que ce if serve � quelque chose. 
			//Comment le joueur suivant pourrait etre en meme temps le joueur le plus fort
			//Alors que la methode getPlusFort n'est meme pas rappele
			
			if (joueurSuivant == joueurPlusFort) {
				joueurSuivant = this.recupererPlusForteOffre();
				System.out.println("test");
			}
			
		}
		
	}
	public void dernierTour() {
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			//Il y a surement une maniere plus simple mais pour l instant j ai pas trouve
			Cartes derniereCarte = new Cartes();
			derniereCarte = getDeck().getStackIntermediaire().pop();
			j.getStack().add(derniereCarte);
		}
	}
	public void attribuerTrophees() {
		
		
		//faire iterator sur stackintermediaire. Tant que pas empty, on continue
		
		Iterator<Cartes> itc = getDeck().getStackIntermediaire().iterator();
		while (itc.hasNext()) {
			
			Cartes carteTrophee = (Cartes) itc.next();
			
		switch(carteTrophee.getTrophee()) {
		case MajorityQuatre :
			//iterator sur chaque joueur. On compte pour chacun nombre de 4 dans leur stack.
			//CarteTrophee va vers ce joueur 
			Joueur joueurQuiALePlusDeQuatre = new Joueur();
			joueurQuiALePlusDeQuatre = joueurs.getFirst();
			Iterator<Joueur> itj4 = joueurs.iterator();
			while(itj4.hasNext()) {
				Joueur j = (Joueur) itj4.next();
				Iterator<Cartes> itcj = j.getStack().iterator();
				while(itcj.hasNext()) {
					Cartes carteSuivante = (Cartes) itcj.next();
					//Initialisation des points. Le joueur ayant le plus de points recupere ce trophee
					j.setPointsPourRecupererTrophee(0);
					if(carteSuivante.getValeur() == Valeur.QUATRE) {
						j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
					}
				}
				
				if (joueurQuiALePlusDeQuatre.getPointsPourRecupererTrophee()<j.getPointsPourRecupererTrophee()) {
				joueurQuiALePlusDeQuatre = j;	
				}
				//cas ou deux joueurs ont meme nombre de quatre  
				/*
				 * if (joueurQuiALePlusDeQuatre.getPointsPourRecupererTrophee()>0 &&
					joueurQuiALePlusDeQuatre.getPointsPourRecupererTrophee() == j.getPointsPourRecupererTrophee()) {
					//Boucle pour trouver qui a highest 
					j.getStack().equals(Couleur.PIQUE && Valeur.QUATRE);
					joueurQuiALePlusDeQuatre = j;
				}
				*/
			}
			joueurQuiALePlusDeQuatre.getStack().add(carteTrophee);
			
		
			break;
		case HighestCarreau :
			//gagnat est celui qui a as de carreau seulement 
			//sinon gagnant est celui qui a quatre de carreau
			Joueur JoueurQuiALePlusHautCarreau = new Joueur();
			JoueurQuiALePlusHautCarreau = joueurs.getFirst();
			Iterator<Joueur> itjhc = joueurs.iterator();
			while(itjhc.hasNext()) {
				Joueur j = (Joueur) itjhc.next();
				Iterator<Cartes> itchc = j.getStack().iterator();
				while (itchc.hasNext()) {
					Cartes carteSuivante = (Cartes) itchc.next();
					j.setPointsPourRecupererTrophee(0);
					if(carteSuivante.getCouleur() == Couleur.CARREAU) {
						j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
					}
				}
				//if joueur a as et seulement as 
				if (j.getStack().contains(Valeur.AS) && j.getPointsPourRecupererTrophee()==1) {
					JoueurQuiALePlusHautCarreau = j;
				}
				//sinon on donne � celui qui a quatre de carreau automatiquement
				else if (j.getStack().equals(Valeur.QUATRE) && equals(Couleur.CARREAU)) {
					JoueurQuiALePlusHautCarreau = j;
				}
			}
			JoueurQuiALePlusHautCarreau.getStack().add(carteTrophee);
			
			break;
		case LowestCarreau :
			//Mon code
			break;
		case BestJestNoJoke :
			//Mon code
			break;
		case HighestTrefle :
			//Mon code
			break;
		case MajorityTrois :
			Joueur joueurQuiALePlusDeTrois = new Joueur();
			joueurQuiALePlusDeTrois = joueurs.getFirst();
			Iterator<Joueur> itj3 = joueurs.iterator();
			while(itj3.hasNext()) {
				Joueur j = (Joueur) itj3.next();
				Iterator<Cartes> itcj = j.getStack().iterator();
				while(itcj.hasNext()) {
					Cartes carteSuivante = (Cartes) itcj.next();
					//Initialisation des points. Le joueur ayant le plus de points recupere ce trophee
					j.setPointsPourRecupererTrophee(0);
					if(carteSuivante.getValeur() == Valeur.TROIS) {
						j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
					}
				}
				
				if (joueurQuiALePlusDeTrois.getPointsPourRecupererTrophee()<j.getPointsPourRecupererTrophee()) {
				joueurQuiALePlusDeTrois = j;	
				}
				//cas ou deux joueurs ont meme nombre de trois  
				/*
				 * if (joueurQuiALePlusDeTrois.getPointsPourRecupererTrophee()>0 &&
					joueurQuiALePlusDeTrois.getPointsPourRecupererTrophee() == j.getPointsPourRecupererTrophee() &&
					joueurQuiALePlusDeTrois.getStack().get(Valeur.TROIS). == Valeur.TROIS) = j) {
					joueurQuiALePlusDeTrois = j;
				}
				*/
			} 
			joueurQuiALePlusDeTrois.getStack().add(carteTrophee);
			break;
		case MajorityDeux :
			//Mon code
			break;
		case LowestTrefle :
			//Mon code
			break;
		case HighestPique :
			//Mon code
			break;
		case LowestCoeur :
			//Mon code
			break;
		case HighestCoeur :
			//Mon code
			break;
		case LowestPique :
			//Mon code
			break;
		case Joker :
			//Mon code
			break;
		case BestJest :
			//Mon code
			break;
			
		default :
		System.out.println("je n'ai pas trouv� le troph�e de la derniere carte");
		}
		}
	}
	public void creerPartie() {
	
		Scanner sc = new Scanner(System.in);
		Scanner scannerPseudo = new Scanner(System.in);
		System.out.println("A combien de joueurs allez vous jouer la partie ?");
		int nombreJoueur = sc.nextInt();
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
		joueurs = new LinkedList<Joueur>();
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
		char extension = scExtension.nextLine().charAt(0);
		switch(extension) {
		case 'O':
			deck.ajouterExtension();
			System.out.println("Okay, tu as décidé de jouer à l'extension. \n") ; 
			System.out.println(deck.toString());
			System.out.println(deck.getNombreCartes());
			break;
		case 'N':
			System.out.println("Okay tu ne veux pas jouer à l'extension. J'en prends note ! \n") ; 
			System.out.println(deck.toString());
			System.out.println(deck.getNombreCartes());
			break;
		default:
			System.out.println("La syntaxe de la r�ponse n'est pas correcte. Je pars du principe que tu ne veux pas ajouter l'extension !") ; 
			
		}
		


		/*scannerPseudo.close();

		sc.close();
		scExtension.close();*/
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
}
