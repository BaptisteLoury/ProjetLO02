package App;

import java.util.HashSet;

public class Deck {

	private int nombreCartes;
	
	//Declaration de la collection Deck
	private HashSet <Cartes> deckDeCartes ; 
	
	public Deck() {
		deckDeCartes = new HashSet<Cartes>();
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
