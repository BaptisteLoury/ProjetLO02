package App;

import java.util.HashSet;

public class Deck {

	private int nombreCartes;
	
	//Declaration de la collection Deck
	private HashSet <Cartes> deckDeCartes ; 
	
	public void creerJeuDeBase() {
		Cartes carreau1 = new Cartes(EnumTrophee.MajorityQuatre,Couleur.CARREAU,Valeur.AS);
		Cartes joker = new Cartes(EnumTrophee.BestJest,Couleur.JOKER,Valeur.JOKER);
		Cartes coeur1 = new Cartes(EnumTrophee.Joker,Couleur.COEUR,Valeur.AS);
		Cartes pique4 = new Cartes(EnumTrophee.LowestTrefle,Couleur.PIQUE,Valeur.QUATRE);
	}
	public void ajouterExtension() {
	
	}
	
	public Deck() {
		deckDeCartes = new HashSet<Cartes>();
		for (int indice = 0; indice<=jeuDeCartes.length; indice++) {
			Cartes carte = new Cartes ;
			deckDeCartes.add(carte) ;
		}
		//A FINIR : METTRE CARTES CRÉES DANS PARTIE DANS HASSET DECK
	}
	
	public void remplirDeck() {
		
	}

	public int getNombreCartes() {
		return nombreCartes;
	}

	public void setNombreCartes(int nombreCartes) {
		this.nombreCartes = nombreCartes;
	}
	
}
