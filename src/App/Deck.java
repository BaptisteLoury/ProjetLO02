package App;
import java.util.LinkedList;;

public class Deck {

	private int nombreCartes;
	
	private LinkedList<Cartes> deckCartes;
	
	public Deck() {
		
	}
	public void creerJeuDeBase() {
		int trophee = 0;
		for (Couleur ind1 : Couleur.values()) {
			for (Valeur ind2 : Valeur.values()) {
				Cartes carte = new Cartes(EnumTrophee.values()[trophee],ind1,ind2);
				deckCartes.add(carte);
				trophee++;
			}
		}
		Cartes joker = new Cartes(EnumTrophee.BestJest,Couleur.JOKER,Valeur.JOKER);
		deckCartes.add(joker);
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
