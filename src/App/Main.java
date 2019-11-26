package App;

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
		
		
		

		//Tour
		while (!partie.getDeck().isEmpty()) {
			partie.tour();
		}
		partie.distribuer();
		/*PotCommun pot = new PotCommun();
		pot.remplirPot();*/

		
	}

}
