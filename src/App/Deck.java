/*
 * 
 */
package App;
import java.util.LinkedList;


public class Deck {
	
	/** Le deck cartes. */
	protected LinkedList<Cartes> deckCartes;
	
	/** Le stack intermediaire.
	 * Le stack intermediaire est un ensemble de cartes composé des cartes non-piochées pendant le tour précédent et des cartes ajoutées depuis le deck
	 *  */
	protected LinkedList<Cartes> stackIntermediaire;

	/** Le nombre cartes. */
	private int nombreCartes;
	
	/**
	 * Instantie un nouveau deck.
	 */
	public Deck() {
		this.creerJeuDeBase();
		this.melanger();
		stackIntermediaire = new LinkedList<Cartes>();
	}
	
	/**
	 * Creer un jeu de base.
	 */
	public void creerJeuDeBase() {
		int trophee = 0;
		deckCartes = new LinkedList<Cartes>();
		for (Couleur c : Couleur.values()) {
			for (Valeur v : Valeur.values()) {
				if (c != Couleur.JOKER && v != Valeur.JOKER && v !=Valeur.CINQ) {
					Cartes carte = new Cartes(EnumTrophee.values()[trophee],c,v);
					deckCartes.add(carte);
					//Comme un terme ne peut etre prï¿½sent qu'une seule fois dans une ï¿½numï¿½ration et que les Cartes coeurs ont toutes le mï¿½me trophï¿½e,
					// ï¿½ savoir Joker, lorsque la boucle arrive ï¿½ la crï¿½ation des cartes coeurs il ne fait plus avancer le trophï¿½e et toutes les cartes coeurs
					// ont pour trophï¿½e joker
					if (c != Couleur.COEUR) {
						trophee++;
					}
				}
			}
		}
		Cartes joker = new Cartes(EnumTrophee.BestJest,Couleur.JOKER,Valeur.JOKER);
		deckCartes.add(joker);
		nombreCartes = deckCartes.size();
	}
/**
 * Ajoute l'extension au jeu de base
 */
	
	public void ajouterExtension() {
		for (Couleur c : Couleur.values()) {
			if (c != Couleur.JOKER) {
				Cartes carte = new Cartes(EnumTrophee.Joker,c,Valeur.CINQ);
				deckCartes.push(carte);
				nombreCartes = deckCartes.size();
			}
		
		}
		this.melanger();
		
	}
	
	/**
	 * Melanger.
	 */
	public void melanger() {
		for (int i = 0;i < nombreCartes; i++) {
			int position = (int) (Math.round(nombreCartes)*Math.random());
			Cartes carte = deckCartes.pop();
			deckCartes.add(position, carte);
		}
	}


	/**
	 * Recuperer carte restante.
	 *
	 * @param carteRestante the carte restante
	 */
	public void recupererCarteRestante(Cartes carteRestante) {
		stackIntermediaire.add(carteRestante);
	}
	
	/**
	 * Gets le deck de cartes.
	 *
	 * @return le deck de cartes
	 */
	public LinkedList<Cartes> getDeckCartes() {
		return deckCartes;

	}
	
	/**
	 * Checks if is empty.
	 *
	 * @return the boolean
	 */
	public Boolean isEmpty() {
		return deckCartes.isEmpty();
	}
	
	/**
	 * Gets le nombre de cartes.
	 *
	 * @return le nombre de cartes
	 */
	public int getNombreCartes() {
		return nombreCartes;
	}

	/**
	 * Sets le nombre de cartes.
	 *
	 * @param nombreCartes le nouveau nombre de cartes 
	 */
	public void setNombreCartes(int nombreCartes) {
		this.nombreCartes = nombreCartes;
	}
	
	/**
	 * Gets le stack intermediaire.
	 *
	 * @return le stack intermediaire
	 */
	public LinkedList<Cartes> getStackIntermediaire() {
		return stackIntermediaire;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return deckCartes.toString();
	}
}
