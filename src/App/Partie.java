package App;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;

public class Partie {

	private LinkedList<Joueur> joueurs;
	private HashSet<Cartes> trophees;
	private Deck deck;
	
	public Partie() {
		this.creerPartie();
	}
	//Singleton
	private static Partie INSTANCE= null; 
	public static Partie  getInstance() {
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
		Cartes plusForteRecto = new Cartes(EnumTrophee.Joker,Couleur.JOKER,Valeur.JOKER);
		while (itj.hasNext()) {
			Joueur j = itj.next();
			if (j.getOffre().estOffreSuffisante())
				if (j.getOffre().getRecto().getValeur().ordinal() > plusForteRecto.getValeur().ordinal()) {
					plusForteOffre = j.getOffre();
				}
				else if (j.getOffre().getRecto().getValeur().ordinal() == plusForteRecto.getValeur().ordinal()) {
					// < parce que dans l'�num�ration Couleur, l'ordre va de la plus grand � la plus petite
					if (j.getOffre().getRecto().getCouleur().ordinal() < plusForteRecto.getCouleur().ordinal()) {
						plusForteOffre = j.getOffre();
						plusForteRecto = plusForteOffre.getRecto();
					}
				}
		}
		return plusForteOffre.getOffrant();

	}
	public void remettreEnJeuCarteOffre() {
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			if (j.getOffre().getRecto()==null ) {
				deck.recupererCarteRestante(j.getOffre().getVerso());
			}
			else {
				deck.recupererCarteRestante(j.getOffre().getRecto());
			}
		}
	}
	public void distribuer() {
		Iterator<Joueur> itj = joueurs.iterator();
		//Cas où ce n'est pas le dernier tour 
		if (!deck.isEmpty()) {
			while (itj.hasNext()) {
				Joueur j = (Joueur) itj.next();
				j.getMain().add(deck.getDeckCartes().pop());
				if(deck.getStackIntermediaire().isEmpty()) {
					j.getMain().add(deck.getDeckCartes().pop());
				}
				else {
					j.getMain().add(deck.getStackIntermediaire().pop());
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
		System.out.println("\n\n\n\n\n\n\n\n");
		Joueur joueurPlusFort = this.recupererPlusForteOffre();
		Joueur joueurSuivant = joueurPlusFort.choisirOffre(joueurs);
		for (int i=2;i<=joueurs.size();i++) {
			joueurSuivant = joueurSuivant.choisirOffre(joueurs);
			if (joueurSuivant == joueurPlusFort) {
				joueurSuivant = this.recupererPlusForteOffre();
			}
			
		}
		
	}
	public void dernierTour() {
		//Les cartes du stack intermediaire sont redistribuees en debut de tour, au dernier tour il reste donc les cartes non choisie
		//Ces cartes sont rendues a l offrant s il n y a plus de carte dans le deck
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			if (j.getOffre().getRecto()==null ) {
				j.getStack().add(j.getOffre().getVerso());
			}
			else {
				j.getStack().add(j.getOffre().getRecto());
			}
		}
	}
	/*public void attribuerTrophees() {
		
		
		//faire iterator sur stackintermediaire. Tant que pas empty, on continue
		
		Iterator<Cartes> itc = getDeck().getStackIntermediaire().iterator();
		while (itc.hasNext()) {
			
			Cartes carteTrophee = (Cartes) itc.next();
			
		switch(carteTrophee.getTrophee()) {
		case MajorityQuatre :
			//iterator sur chaque joueur. On compte pour chacun nombre de 4 dans leur stack.
			//CarteTrophee va vers ce joueur 
			Joueur joueurQuiALePlusDePoints = new Joueur();
			joueurQuiALePlusDePoints = joueurs.getFirst();
			Iterator<Joueur> itj = joueurs.iterator();
			while(itj.hasNext()) {
				Joueur j = (Joueur) itj.next();
				Iterator<Cartes> itcj = j.getStack().iterator();
				while(itcj.hasNext()) {
					Cartes carteSuivante = (Cartes) itcj.next();
					//Initialisation des points. Le joueur ayant le plus de points recupere ce trophee
					j.setPointsPourRecupererTrophee(0);
					if(carteSuivante.getValeur() == Valeur.QUATRE) {
						j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
					}
				}
			if (joueurQuiALePlusDePoints.getPointsPourRecupererTrophee()<j.getPointsPourRecupererTrophee()) {}
			joueurQuiALePlusDePoints = j;
			}
			//cas ou deux joueurs ont meme nombre de quatre  
			/*if (joueurQuiALePlusDePoints.getPointsPourRecupererTrophee()>0 &&
				joueurQuiALePlusDePoints.getPointsPourRecupererTrophee() == j.getPointsPourRecupererTrophee() &&
				joueurQuiALePlusDePoints.getStack().getCouleur(getValeur()== Valeur.QUATRE) = j;) {
				joueurQuiALePlusDePoints = j;
			}*/
		/*}
			break;
		case HighestCarreau :
			//Mon code
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
			//Mon code
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
		default:
		System.out.println("je n'ai pas trouv� le troph�e de la derniere carte");
		}
		}
	}*/
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
		this.constituerTrophee();
			


		/*scannerPseudo.close();

		sc.close();
		scExtension.close();*/
	}
	public void constituerTrophee() {
		trophees = new HashSet<Cartes>();
		// Le nombre de trophee depend du nombre de joueurs
		if (Joueur.getNbJoueurs()==3) {
			// Il change egalement si l extension est utilisee
			if (deck.getNombreCartes()==17) {
				trophees.add(deck.getDeckCartes().pop());
				trophees.add(deck.getDeckCartes().pop());
			}
			else {
				trophees.add(deck.getDeckCartes().pop());
				trophees.add(deck.getDeckCartes().pop());
				trophees.add(deck.getDeckCartes().pop());
			}
		}
		else {
			trophees.add(deck.getDeckCartes().pop());
		}
		Iterator<Cartes> itTrophee = trophees.iterator();
		System.out.print("Les troph�es de la partie seront : ");
		while (itTrophee.hasNext()) {
			Cartes t = itTrophee.next();
			System.out.print(t.getTrophee().toString()+"  ");
		}
		System.out.println();
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
		this.deck = deck;
	}
	public HashSet<Cartes> getTrophees() {
		return trophees;
	}
	
}
