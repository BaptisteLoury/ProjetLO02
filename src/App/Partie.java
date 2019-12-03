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
	
		Iterator<Cartes> itc = getTrophees().iterator();
		getTrophees();
		while (itc.hasNext()) {
			
			Cartes carteTrophee = (Cartes) itc.next();

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
				System.out.println("Le joueur "+joueurQuiALePlusDeQuatre.getPseudo()+" remporte le trophee MajorityQuatre");
				break;
			case HighestCarreau :
				//gagnat est celui qui a as de carreau seulement 
				//sinon gagnant est celui qui a quatre de carreau
				Joueur JoueurQuiALePlusHautCarreau = new Joueur();
				JoueurQuiALePlusHautCarreau = joueurs.getFirst();
				Iterator<Joueur> itjhc = joueurs.iterator();
				boolean sortirhc = false;
				while(itjhc.hasNext() && sortirhc == false) {
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
						sortirhc = true;
					}
					//sinon on donne � celui qui a quatre de carreau automatiquement
					
					Cartes quatreDeCarreau = new Cartes (EnumTrophee.BestJestNoJoke, Couleur.CARREAU,Valeur.QUATRE);
					if (j.getStack().contains(quatreDeCarreau)) {
						JoueurQuiALePlusHautCarreau = j;
					}
				}
				JoueurQuiALePlusHautCarreau.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusHautCarreau.getPseudo()+" remporte le trophee HighestCarreau");
				
				break;
			case LowestCarreau :
				Joueur JoueurQuiALePlusBasCarreau = new Joueur();
				JoueurQuiALePlusBasCarreau = joueurs.getFirst();
				Iterator<Joueur> itjlc = joueurs.iterator();
				boolean sortirlc = false;
				while(itjlc.hasNext() && sortirlc ==false) {
					Joueur j = (Joueur) itjlc.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.CARREAU) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					
					Cartes asDeCarreau = new Cartes (EnumTrophee.MajorityQuatre, Couleur.CARREAU,Valeur.AS);
					//boolean au cas ou pour ne pas rentrer dans deux if. 
					
					//Signifie que joueur a plusieurs carreaux et que as vaut donc 1. c est le plus petit 
					if (j.getStack().contains(asDeCarreau) && j.getPointsPourRecupererTrophee()>1) {
						JoueurQuiALePlusBasCarreau = j;
						sortirlc = true; 
					}
					//sinon on donne � celui qui a deux de carreau automatiquement
				
					Cartes deuxDeCarreau = new Cartes (EnumTrophee.HighestTrefle, Couleur.CARREAU,Valeur.DEUX);
					if (j.getStack().contains(deuxDeCarreau)) {
						JoueurQuiALePlusBasCarreau = j;
					}
				}
				JoueurQuiALePlusBasCarreau.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusBasCarreau.getPseudo()+" remporte le trophee LowestCarreau");
				
				break;
			case BestJestNoJoke :
				//Mon code
				break;
			case HighestTrefle :
				Joueur JoueurQuiALePlusHautTrefle = new Joueur();
				JoueurQuiALePlusHautTrefle = joueurs.getFirst();
				Iterator<Joueur> itjht = joueurs.iterator();
				boolean sortirht =false ; 
				while(itjht.hasNext() && sortirht == false) {
					Joueur j = (Joueur) itjht.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.TREFLE) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					//if joueur a as et seulement as 
					Cartes asDeTrefle = new Cartes (EnumTrophee.HighestPique, Couleur.TREFLE,Valeur.AS);
					if (j.getStack().contains(asDeTrefle) && j.getPointsPourRecupererTrophee()==1) {
						JoueurQuiALePlusHautTrefle = j;
						sortirht = true;
					}
					//sinon on donne � celui qui a quatre de carreau automatiquement
					Cartes quatreDeTrefle = new Cartes (EnumTrophee.LowestPique, Couleur.TREFLE,Valeur.QUATRE);
					if (j.getStack().contains(quatreDeTrefle)) {
						JoueurQuiALePlusHautTrefle = j;
					}
				}
				JoueurQuiALePlusHautTrefle.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusHautTrefle.getPseudo()+" remporte le trophee HighestTrefle");
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
				Joueur JoueurQuiALePlusBasTrefle = new Joueur();
				JoueurQuiALePlusBasTrefle = joueurs.getFirst();
				Iterator<Joueur> itjlt = joueurs.iterator();
				boolean sortirlt = false;
				while(itjlt.hasNext() && sortirlt ==false) {
					Joueur j = (Joueur) itjlt.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.TREFLE) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					
					Cartes asDeTrefle = new Cartes (EnumTrophee.HighestPique, Couleur.TREFLE,Valeur.AS);
					//boolean au cas ou pour ne pas rentrer dans deux if. 
					
					//Signifie que joueur a plusieurs Treflex et que as vaut donc 1. c est le plus petit 
					if (j.getStack().contains(asDeTrefle) && j.getPointsPourRecupererTrophee()>1) {
						JoueurQuiALePlusBasTrefle = j;
						sortirlt = true; 
					}
					//sinon on donne � celui qui a deux de Trefle automatiquement
				
					Cartes deuxDeTrefle = new Cartes (EnumTrophee.LowestPique, Couleur.TREFLE,Valeur.DEUX);
					if (j.getStack().contains(deuxDeTrefle)) {
						JoueurQuiALePlusBasTrefle = j;
					}
				}
				JoueurQuiALePlusBasTrefle.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusBasTrefle.getPseudo()+" remporte le trophee LowestTrefle");
				
				break;
			case HighestPique :
				Joueur JoueurQuiALePlusHautPique = new Joueur();
				JoueurQuiALePlusHautPique = joueurs.getFirst();
				Iterator<Joueur> itjhp = joueurs.iterator();
				boolean sortirhp = false;
				while(itjhp.hasNext()&&sortirhp ==false) {
					Joueur j = (Joueur) itjhp.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.PIQUE) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					//if joueur a as et seulement as 
					Cartes asDePique = new Cartes (EnumTrophee.HighestTrefle, Couleur.PIQUE,Valeur.AS);
					if (j.getStack().contains(asDePique) && j.getPointsPourRecupererTrophee()==1) {
						JoueurQuiALePlusHautPique = j;
						sortirhp =true;
					}
					//sinon on donne � celui qui a quatre de carreau automatiquement
					Cartes quatreDePique = new Cartes (EnumTrophee.LowestTrefle, Couleur.PIQUE,Valeur.QUATRE);
					if (j.getStack().contains(quatreDePique)) {
						JoueurQuiALePlusHautPique = j;
					}
				}
				JoueurQuiALePlusHautPique.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusHautPique.getPseudo()+" remporte le trophee HighestPique");
				break;
			case LowestCoeur :
				Joueur JoueurQuiALePlusBasCoeur = new Joueur();
				JoueurQuiALePlusBasCoeur = joueurs.getFirst();
				Iterator<Joueur> itjlcoeur = joueurs.iterator();
				boolean sortirlcoeur = false;
				while(itjlcoeur.hasNext() && sortirlcoeur ==false) {
					Joueur j = (Joueur) itjlcoeur.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.COEUR) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					
					Cartes asDeCoeur = new Cartes (EnumTrophee.Joker, Couleur.COEUR,Valeur.AS);
					//boolean au cas ou pour ne pas rentrer dans deux if. 
					
					//Signifie que joueur a plusieurs Coeurx et que as vaut donc 1. c est le plus petit 
					if (j.getStack().contains(asDeCoeur) && j.getPointsPourRecupererTrophee()>1) {
						JoueurQuiALePlusBasCoeur = j;
						sortirlcoeur = true; 
					}
					//sinon on donne � celui qui a deux de Coeur automatiquement
				
					Cartes deuxDeCoeur = new Cartes (EnumTrophee.Joker, Couleur.COEUR,Valeur.DEUX);
					if (j.getStack().contains(deuxDeCoeur)) {
						JoueurQuiALePlusBasCoeur = j;
					}
				}
				JoueurQuiALePlusBasCoeur.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusBasCoeur.getPseudo()+" remporte le trophee LowestCoeur");
				
				break;
			case HighestCoeur :
				Joueur JoueurQuiALePlusHautCoeur = new Joueur();
				JoueurQuiALePlusHautCoeur = joueurs.getFirst();
				Iterator<Joueur> itjhcoeur = joueurs.iterator();
				boolean sortirhcoeur = false;
				while(itjhcoeur.hasNext()&& sortirhcoeur==false) {
					Joueur j = (Joueur) itjhcoeur.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.COEUR) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					//if joueur a as et seulement as 
					Cartes asDeCoeur = new Cartes (EnumTrophee.Joker, Couleur.COEUR,Valeur.AS);
					if (j.getStack().contains(asDeCoeur) && j.getPointsPourRecupererTrophee()==1) {
						JoueurQuiALePlusHautCoeur = j;
						sortirhcoeur = true;
					}
					//sinon on donne � celui qui a quatre de carreau automatiquement
					Cartes quatreDeCoeur = new Cartes (EnumTrophee.Joker, Couleur.COEUR,Valeur.QUATRE);
					if (j.getStack().contains(quatreDeCoeur)) {
						JoueurQuiALePlusHautCoeur = j;
					}
				}
				JoueurQuiALePlusHautCoeur.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusHautCoeur.getPseudo()+" remporte le trophee HighestCoeur");
				break;
			case LowestPique :
				Joueur JoueurQuiALePlusBasPique = new Joueur();
				JoueurQuiALePlusBasPique = joueurs.getFirst();
				Iterator<Joueur> itjlp = joueurs.iterator();
				boolean sortirlp = false;
				while(itjlp.hasNext() && sortirlp ==false) {
					Joueur j = (Joueur) itjlp.next();
					j.setPointsPourRecupererTrophee(0);
					Iterator<Cartes> itchc = j.getStack().iterator();
					while (itchc.hasNext()) {
						Cartes carteSuivante = (Cartes) itchc.next();
						if(carteSuivante.getCouleur() == Couleur.PIQUE) {
							j.setPointsPourRecupererTrophee(j.getPointsPourRecupererTrophee() + 1);
						}
					}
					
					Cartes asDePique = new Cartes (EnumTrophee.HighestTrefle, Couleur.PIQUE,Valeur.AS);
					//boolean au cas ou pour ne pas rentrer dans deux if. 
					
					//Signifie que joueur a plusieurs Piquex et que as vaut donc 1. c est le plus petit 
					if (j.getStack().contains(asDePique) && j.getPointsPourRecupererTrophee()>1) {
						JoueurQuiALePlusBasPique = j;
						sortirlp = true; 
					}
					//sinon on donne � celui qui a deux de Pique automatiquement
				
					Cartes deuxDePique = new Cartes (EnumTrophee.MajorityTrois, Couleur.PIQUE,Valeur.DEUX);
					if (j.getStack().contains(deuxDePique)) {
						JoueurQuiALePlusBasPique = j;
					}
				}
				JoueurQuiALePlusBasPique.getStack().add(carteTrophee);
				System.out.println("Le joueur "+JoueurQuiALePlusBasPique.getPseudo()+" remporte le trophee LowestPique");
				
				break;
			case Joker :
				Joueur joueurQuiALeJoker = new Joueur();
				joueurQuiALeJoker = joueurs.getFirst();
				Iterator<Joueur> itjJ = joueurs.iterator();
				boolean sortieJoker = false ; 
				while (itjJ.hasNext() && sortieJoker == false) {
					Joueur j = (Joueur) itjJ.next();
					Iterator<Cartes> itcJ = j.getStack().iterator();
					while (itcJ.hasNext()) {
						Cartes carteSuivante = itcJ.next();
						if (carteSuivante.getTrophee()==EnumTrophee.BestJest && carteSuivante.getCouleur()==Couleur.JOKER 
								&& carteSuivante.getValeur()==Valeur.JOKER) {
							sortieJoker =true;
							joueurQuiALeJoker = j; 
						}
					}	
				}
				joueurQuiALeJoker.getStack().add(carteTrophee);
				System.out.println("Le joueur "+joueurQuiALeJoker.getPseudo()+" remporte le trophee Joker");
				
				
				break;
			case BestJest :
				//Mon code
				break;
			default:
			System.out.println("je n'ai pas trouvé le trophée de la derniere carte");
			}
			//Afichage des mains 
			
		}
		Iterator<Joueur> itfin = joueurs.iterator();
		while (itfin.hasNext()){
			Joueur j = (Joueur) itfin.next();
			System.out.println(j.getStack());
			
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
		//getTrophees().toString();
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
