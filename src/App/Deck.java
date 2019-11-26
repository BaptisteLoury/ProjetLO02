package App;
import java.util.LinkedList;

public class Deck {
	
	protected LinkedList<Cartes> deckCartes;
	protected LinkedList<Cartes> stackIntermediaire;


	private int nombreCartes;
	
	public Deck() {
		this.creerJeuDeBase();
		this.melanger();
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
				System.out.println("test");
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
