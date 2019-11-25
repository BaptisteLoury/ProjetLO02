package App;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Partie partie = Partie.getInstance(); 
		partie.creerPartie();
		Deck deck = new Deck();
		deck.creerJeuDeBase();
		deck.ajouterExtension();
		deck.melanger();
		System.out.println(deck.toString());
		System.out.println(deck.getNombreCartes());
		
		
		PotCommun pot = new PotCommun();
		pot.remplirPot();
		
		
	}

}
