package App;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedList;

//Jai mis en extends pour l instant pour pouvoir me servir de lattribut deckCartes sinon je bloque 
public class PotCommun extends Deck {
	
	private ArrayDeque<Cartes> potCommun;
	
	//protected Joueur[] joueurs = new Joueur[5];
	
	public PotCommun() {
		
	}
	public void classerOffres() {
		
	}	
	public void remplirPot() {

		  potCommun = new ArrayDeque<Cartes>();
		//Transfert des cartes du deck vers le pot
		  
		  while (deckCartes.isEmpty() == false) {
			Cartes carteATransferer = deckCartes.pop(); 
			potCommun.push(carteATransferer);
		}
		//Verification que les cartes sont bien dans le pot
		/*Iterator<Cartes> it  = potCommun.iterator();
		while (it.hasNext()) {
			Cartes carte = (Cartes) it.next();
			System.out.println(carte.toString());
			}
		 */
	}	
	public void distribuerCartes(Partie partie, LinkedList<Cartes> main) {
		//boucle qui distribue deux cartes a chaque joueur 
		
		partie.getInstance();
		
		for (int i=1;i<=partie.getNombreJoueurs();i++) {
			
			main.add(potCommun.pop());
			main.add(potCommun.pop());
			//ON A UN PROBLEME AVEC JOUEUR. main est dans Joueur mais comment les joueurs passent de Parte à Jouer 
		}
	}
	public ArrayDeque<Cartes> getPotCommun() {
		return potCommun;
	}
	public void setPotCommun(ArrayDeque<Cartes> potCommun) {
		this.potCommun = potCommun;
	}	
}
