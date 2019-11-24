package App;
import java.util.LinkedList;
import java.util.Iterator;

public class Deck {
	
	private LinkedList<Cartes> deckCartes;
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
					//Comme un terme ne peut etre présent qu'une seule fois dans une énumération et que les Cartes coeurs ont toutes le même trophée,
					// à savoir Joker, lorsque la boucle arrive à la création des cartes coeurs il ne fait plus avancer le trophée et toutes les cartes coeurs
					// ont pour trophée joker
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
				deckCartes.add(carte);
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
		//Pourquoi avoir mis Ã§a sous commentaire ? 
		//Verification creation cartes et melange
		// Cette boucle équivaut à un this.toString() - Elle ne sera pas dans l'algo final
		/*Iterator<Cartes> it  = deckCartes.iterator();
		while (it.hasNext()) {
			Cartes carte = (Cartes) it.next();
			System.out.println(carte.toString());
			}*/
			
	}
	public void distribuer() {
		
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
