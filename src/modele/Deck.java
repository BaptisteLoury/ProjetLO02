<<<<<<< HEAD:src/App/Deck.java
/*
 * 
 */
package App;
import java.util.LinkedList;


public class Deck {
	
	/** Le deck cartes. */
	protected LinkedList<Cartes> deckCartes;
	
	/** Le stack intermediaire.
	 * Le stack intermediaire est un ensemble de cartes compos� des cartes non-pioch�es pendant le tour pr�c�dent et des cartes ajout�es depuis le deck
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
					//Comme un terme ne peut etre pr�sent qu'une seule fois dans une �num�ration et que les Cartes coeurs ont toutes le m�me troph�e,
					// � savoir Joker, lorsque la boucle arrive � la cr�ation des cartes coeurs il ne fait plus avancer le troph�e et toutes les cartes coeurs
					// ont pour troph�e joker
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
=======
package modele;
import java.util.LinkedList;


public class Deck {
	
	protected LinkedList<Cartes> deckCartes;
	protected LinkedList<Cartes> stackIntermediaire;

	private int nombreCartes;
	
	public Deck() {
		this.creerJeuDeBase();
		this.melanger();
		stackIntermediaire = new LinkedList<Cartes>();
	}
	public void creerJeuDeBase() {
		int trophee = 0;
		deckCartes = new LinkedList<Cartes>();
		for (Couleur c : Couleur.values()) {
			for (Valeur v : Valeur.values()) {
				if (c != Couleur.JOKER && v != Valeur.JOKER && v !=Valeur.CINQ) {
					Cartes carte = new Cartes(EnumTrophee.values()[trophee],c,v);
					deckCartes.add(carte);
					//Comme un terme ne peut etre pr�sent qu'une seule fois dans une �num�ration et que les Cartes coeurs ont toutes le m�me troph�e,
					// � savoir Joker, lorsque la boucle arrive � la cr�ation des cartes coeurs il ne fait plus avancer le troph�e et toutes les cartes coeurs
					// ont pour troph�e joker
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
 * 
 */
	// Pour le moment, ajoute les cinqs de chaque couleur, ils ont tous un trophee JOKER
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
	
	public void melanger() {
		for (int i = 0;i < nombreCartes; i++) {
			int position = (int) (Math.round(nombreCartes)*Math.random());
			Cartes carte = deckCartes.pop();
			deckCartes.add(position, carte);
		}
	}

	public void distribuer() {
		/*joueurs = new HashSet<Joueur>();
		
		for (int i=1; i<= joueurs.size() ;i++) {
			main  =deckCartes.pop();
		*/}

	public void recupererCarteRestante(Cartes carteRestante) {
		stackIntermediaire.add(carteRestante);
	}
	public LinkedList<Cartes> getDeckCartes() {
		return deckCartes;

	}
	public Boolean isEmpty() {
		return deckCartes.isEmpty();
	}
	
	public int getNombreCartes() {
		return nombreCartes;
	}

	public void setNombreCartes(int nombreCartes) {
		this.nombreCartes = nombreCartes;
	}
	public LinkedList<Cartes> getStackIntermediaire() {
		return stackIntermediaire;
	}

	public String toString() {
		return deckCartes.toString();
	}
}
>>>>>>> master:src/modele/Deck.java
