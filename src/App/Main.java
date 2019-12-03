package App;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Partie partie = Partie.getInstance(); 
		//partie ne s arrete pas quand deck empty ! Ni lorsqu il reste plus de 3 cartes, cela depend du nombre de joueur.  
		int nbTour = 1;
		while (partie.getDeck().getDeckCartes().size()>=Joueur.getNbJoueurs()) {
			System.out.println("TOUR # "+ nbTour);
			if (nbTour!=1) {
				partie.remettreEnJeuCarteOffre();
			}
			partie.tour();
			nbTour++;
		}
		partie.dernierTour();
		partie.attribuerTrophees();
		 


	}

}
