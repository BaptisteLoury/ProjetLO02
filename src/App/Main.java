package App;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Partie partie = Partie.getInstance(); 
		partie.creerPartie();
		Deck deck = new Deck();
		deck.creerJeuDeBase();
		deck.melanger();
		
		
		PotCommun pot = new PotCommun();
		pot.remplirPot();
		
	}

}
