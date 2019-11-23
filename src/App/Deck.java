package App;
import java.util.LinkedList;
import java.util.Iterator;

public class Deck {
	
	private LinkedList<Cartes> deckCartes;
	private int nombreCartes;
	
	public Deck() {
		
	}
	public void creerJeuDeBase() {
		int trophee = 0;
		deckCartes = new LinkedList<Cartes>();
		for (Couleur c : Couleur.values()) {
			for (Valeur v : Valeur.values()) {
				if (c != Couleur.JOKER && v != Valeur.JOKER) {
					Cartes carte = new Cartes(EnumTrophee.values()[trophee],c,v);
					deckCartes.add(carte);
					//Je ne comprends pas ça
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

	public void ajouterExtension() {
		
		
	}
	
	public void melanger() {
		for (int i = 0;i < nombreCartes; i++) {
			int position = (int) (Math.round(nombreCartes)*Math.random());
			Cartes carte = deckCartes.pop();
			deckCartes.add(position, carte);
		}
		//Pourquoi avoir mis ça sous commentaire ? 
		//Verification creation cartes et melange
		Iterator<Cartes> it  = deckCartes.iterator();
		while (it.hasNext()) {
			Cartes carte = (Cartes) it.next();
			System.out.println(carte.toString());
			}
			
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

	public String toString() {
		return deckCartes.toString();
	}
}
