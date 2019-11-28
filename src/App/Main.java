package App;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Partie partie = Partie.getInstance(); 
		partie.creerPartie();

		//partie ne s arrete pas quand deck empty !  
		
		while (partie.getDeck().getNombreCartes()>3) {
			partie.tour();
		}
		partie.dernierTour();
		partie.attribuerTrophees();
		//Ne sert a rien car est appele par partie.tour();
		//partie.distribuer();


	}

}
