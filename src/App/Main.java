package App;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Partie partie = Partie.getInstance(); 
		partie.creerPartie();

		
		while (!partie.getDeck().isEmpty()) {
			partie.tour();
		}
		partie.distribuer();


	}

}
