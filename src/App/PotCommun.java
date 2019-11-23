package App;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;


public class PotCommun {
	
	private ArrayDeque<Cartes> potCommun;
	private Deck deckCartes;
	protected Joueur[] joueurs = new Joueur[5];
	
	public PotCommun() {
		
	}
	public void classerOffres() {
		
	}	
	public void remplirPot() {
		//NE FONCTIONNE PAS
		/*
		 * potCommun = new ArrayDeque<Cartes>();
		//Transfert des cartes du deck vers le pot
		while (deckCartes.isEmpty() == false) {
			private Cartes carteATransferer = deckCartes.pop(); 
			potCommun.push(carteATransferer);
		}
		//Verification que les cartes sont bien dans le pot
		Iterator<Cartes> it  = potCommun.iterator();
		while (it.hasNext()) {
			Cartes carte = (Cartes) it.next();
			System.out.println(carte.toString());
			}
		*/ 
	}	
	public void distribuerCartes() {
		
		
	}
	public ArrayDeque<Cartes> getPotCommun() {
		return potCommun;
	}
	public void setPotCommun(ArrayDeque<Cartes> potCommun) {
		this.potCommun = potCommun;
	}	
}
