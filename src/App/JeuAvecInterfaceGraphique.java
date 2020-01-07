package App;
import Visit.VisitorJest;
import Vue.*;
import controleur.JestControleur;

public class JeuAvecInterfaceGraphique implements Runnable {
	
	public JeuAvecInterfaceGraphique() {
		VisitorJest monVisiteur = new VisitorJest();
		Partie partie = Partie.getInstance();
		partie.accept(monVisiteur);
		partie.init();
		VuePartie vuePartie = new VuePartie(partie);
		JestControleur jestControleur = new JestControleur(partie,vuePartie);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		Thread partie = new Thread(new JeuAvecInterfaceGraphique());
		partie.start();
		javax.swing.SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				new JeuAvecInterfaceGraphique();
			}
		});

	}

	

}
