package App;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Deck deck = new Deck();
		deck.creerJeuDeBase();
		deck.melanger();
		/*Partie partie = new Partie();
		partie.creerPartie();*/
		PotCommun pot = new PotCommun();
		pot.remplirPot();
		
	}

}
