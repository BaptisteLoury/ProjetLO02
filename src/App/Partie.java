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
					// < parce que dans l'ï¿½numï¿½ration Couleur, l'ordre va de la plus grand ï¿½ la plus petite
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
		//Cas oÃ¹ ce n'est pas le dernier tour 
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
	public void attribuerTrophees() {
		
		//faire iterator sur stackintermediaire. Tant que pas empty, on continue
	
		Iterator<Cartes> itc = getDeck().getStackIntermediaire().iterator();
		while (itc.hasNext()) {
			
			Cartes carteTrophee = (Cartes) itc.next();
			carteTrophee.toString();
			switch(carteTrophee.getTrophee()) {
			case MajorityQuatre :
				//iterator sur chaque joueur. On compte pour chacun nombre de 4 dans leur stack.
				//CarteTrophee va vers ce joueur 
				Joueur joueurQuiALePlusDeQuatre = new Joueur();
				joueurQuiALePlusDeQuatre = joueurs.getFirst();
				Iterator<Joueur> itj = joueurs.iterator();
				while(itj.hasNext()) {
					Joueur j = (Joueur) itj.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itcj = j.getStack().iterator();
					while(itcj.hasNext()) {
						Cartes carteSuivante = (Cartes) itcj.next();
						//Initialisation des points. Le joueur ayant le plus de points recupere ce trophee
						
						if(carteSuivante.getValeur() == Valeur.QUATRE) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
				if (joueurQuiALePlusDeQuatre.getPointsPourRecupererTrophee()<j.getPointsPourRecupererTrophee()) {
					joueurQuiALePlusDeQuatre = j;	
				}
				
				
				//cas ou deux joueurs ont meme nombre de quatre  
				if (joueurQuiALePlusDeQuatre.getPointsPourRecupererTrophee()>0 &&
					joueurQuiALePlusDeQuatre.getPointsPourRecupererTrophee() == j.getPointsPourRecupererTrophee() ) {
					//J'instancie une nouvelle carte pour voir si elle est presente dans le jeu du joueur j 
					Cartes quatreDePique = new Cartes(EnumTrophee.LowestTrefle,Couleur.PIQUE,Valeur.QUATRE);
					if(j.getStack().contains(quatreDePique))
					joueurQuiALePlusDeQuatre = j;
				}
				}
				joueurQuiALePlusDeQuatre.getStack().add(carteTrophee);
				System.out.println("Le joueur "+joueurQuiALePlusDeQuatre.getPseudo()+"remporte le trophee MajorityQuatre");
				break;
			case HighestCarreau :
				//gagnat est celui qui a as de carreau seulement 
				//sinon gagnant est celui qui a quatre de carreau
				Joueur JoueurQuiALePlusHautCarreau = new Joueur();
				JoueurQuiALePlusHautCarreau = joueurs.getFirst();
				Iterator<Joueur> itjhc = joueurs.iterator();
				while(itjhc.hasNext()) {
					Joueur j = (Joueur) itjhc.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.CARREAU) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					//if joueur a as et seulement as 
					Cartes asDeCarreau = new Cartes (EnumTrophee.MajorityQuatre, Couleur.CARREAU,Valeur.AS);
					if (j.getStack().contains(asDeCarreau) && j.getPointsPourRecupererTrophee()==1) {
						JoueurQuiALePlusHautCarreau = j;
					}
					//sinon on donne � celui qui a quatre de carreau automatiquement
					Cartes quatreDeCarreau = new Cartes (EnumTrophee.BestJestNoJoke, Couleur.CARREAU,Valeur.QUATRE);
					if (j.getStack().contains(quatreDeCarreau)) {
						JoueurQuiALePlusHautCarreau = j;
					}
				}
				JoueurQuiALePlusHautCarreau.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusHautCarreau.getPseudo()+"remporte le trophee MajorityQuatre");
				
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
				Joueur joueurQuiALePlusDeTrois= new Joueur();
				joueurQuiALePlusDeTrois= joueurs.getFirst();
				Iterator<Joueur> itjm3 = joueurs.iterator();
				while(itjm3.hasNext()) {
					Joueur j = (Joueur) itjm3.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itcj = j.getStack().iterator();
					while(itcj.hasNext()) {
						Cartes carteSuivante = (Cartes) itcj.next();
						//Initialisation des points. Le joueur ayant le plus de points recupere ce trophee
						
						if(carteSuivante.getValeur() == Valeur.TROIS) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
				if (joueurQuiALePlusDeTrois.getPointsPourRecupererTrophee()<j.getPointsPourRecupererTrophee()) {
					joueurQuiALePlusDeTrois= j;	
				}
				
				
				//cas ou deux joueurs ont meme nombre de quatre  
				if (joueurQuiALePlusDeTrois.getPointsPourRecupererTrophee()>0 &&
					joueurQuiALePlusDeTrois.getPointsPourRecupererTrophee() == j.getPointsPourRecupererTrophee() ) {
					//J'instancie une nouvelle carte pour voir si elle est presente dans le jeu du joueur j 
					Cartes troisDePique = new Cartes(EnumTrophee.MajorityDeux,Couleur.PIQUE,Valeur.TROIS);
					if(j.getStack().contains(troisDePique))
					joueurQuiALePlusDeTrois= j;
				}
				}
				joueurQuiALePlusDeTrois.getStack().add(carteTrophee);
				System.out.println("Le joueur "+joueurQuiALePlusDeTrois.getPseudo()+"remporte le trophee MajorityTrois");
				break;
			case MajorityDeux :
				Joueur joueurQuiALePlusDeDeux= new Joueur();
				joueurQuiALePlusDeDeux= joueurs.getFirst();
				Iterator<Joueur> itjm2 = joueurs.iterator();
				while(itjm2.hasNext()) {
					Joueur j = (Joueur) itjm2.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itcj = j.getStack().iterator();
					while(itcj.hasNext()) {
						Cartes carteSuivante = (Cartes) itcj.next();
						//Initialisation des points. Le joueur ayant le plus de points recupere ce trophee
						
						if(carteSuivante.getValeur() == Valeur.DEUX) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
				if (joueurQuiALePlusDeDeux.getPointsPourRecupererTrophee()<j.getPointsPourRecupererTrophee()) {
					joueurQuiALePlusDeDeux= j;	
				}
				
				
				//cas ou deux joueurs ont meme nombre de quatre  
				if (joueurQuiALePlusDeDeux.getPointsPourRecupererTrophee()>0 &&
					joueurQuiALePlusDeDeux.getPointsPourRecupererTrophee() == j.getPointsPourRecupererTrophee() ) {
					//J'instancie une nouvelle carte pour voir si elle est presente dans le jeu du joueur j 
					Cartes deuxDePique = new Cartes(EnumTrophee.MajorityTrois,Couleur.PIQUE,Valeur.DEUX);
					if(j.getStack().contains(deuxDePique))
					joueurQuiALePlusDeDeux= j;
				}
				}
				joueurQuiALePlusDeDeux.getStack().add(carteTrophee);
				System.out.println("Le joueur "+joueurQuiALePlusDeDeux.getPseudo()+"remporte le trophee MajorityDeux");
				
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
			System.out.println("je n'ai pas trouvé le trophée de la derniere carte");
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
		System.out.println("Combien de joueurs humains serez-vous ? (Il doit Ãªtre infÃ©rieur ou Ã©gal au nombre de joueurs totals");
		int nombreJoueurReel = sc.nextInt();
		joueurs = new LinkedList<Joueur>();
		for (int i = 1;i<=nombreJoueurReel;i++) {
			System.out.println("Donner le pseudo du joueur "+ i +" :");
			String pseudo = scannerPseudo.nextLine();
			Joueur j = new Joueur(pseudo);
			joueurs.add(j);
			System.out.println("Le joueur " + j.getPseudo() + " a ï¿½tï¿½ ajoutï¿½ ï¿½ la partie !");
		}
		
		for (int i = nombreJoueurReel + 1;i<=nombreJoueur;i++) {
			Joueur jv = new JoueurVirtuel("Joueur Virtuel " + i);
			joueurs.add(jv);
			System.out.println("Le Joueur Virtuel " + i + " a bien ï¿½tï¿½ ajoutï¿½ ï¿½ la partie !");
		}
		// Crï¿½ation du jeu de cartes de base + mï¿½lange automatique
		deck = new Deck();
		
		//Paragraphe pour dÃ©terminer si oui ou non on joue Ã  l'extension
		 
		Scanner scExtension = new Scanner(System.in);
		System.out.println("Voulez vous jouer Ã  l'extension? (O/N)");
		char extension = scExtension.nextLine().charAt(0);
		switch(extension) {
		case 'O':
			deck.ajouterExtension();
			System.out.println("Okay, tu as dÃ©cidÃ© de jouer Ã  l'extension. \n") ; 
			System.out.println(deck.toString());
			System.out.println(deck.getNombreCartes());
			break;
		case 'N':
			System.out.println("Okay tu ne veux pas jouer Ã  l'extension. J'en prends note ! \n") ; 
			System.out.println(deck.toString());
			System.out.println(deck.getNombreCartes());
			break;
		default:
			System.out.println("La syntaxe de la rï¿½ponse n'est pas correcte. Je pars du principe que tu ne veux pas ajouter l'extension !") ; 
			
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
		System.out.print("Les trophées de la partie seront : ");
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
