package App;

import java.util.Iterator;

public class JoueurVirtuel extends Joueur {

	private Offre offre;
	
	JoueurVirtuel(String pseudo) {
		super(pseudo);
	}
	public void faireOffre() {
		//faire iterator sur les cartes de la main 
		//selectionner carte qui a plus haute valeur 
			//si egalite, prendre meilleur couleur (faire en sorte de choisir offre en premier
		
		
		Iterator <Cartes> itc =  getMain().iterator();
		Cartes carteLaPlusHaute = new Cartes();
		carteLaPlusHaute = getMain().getFirst();
		Cartes carteLaPlusBasse = new Cartes();
		carteLaPlusBasse = getMain().getLast();
		
		while(itc.hasNext()) {
			Cartes carteSuivante = (Cartes) itc.next();
			if(carteSuivante.getValeur().ordinal() >carteLaPlusHaute.getValeur().ordinal()) {
				carteLaPlusBasse = carteLaPlusHaute;
				carteLaPlusHaute = carteSuivante ;
			}
		}
		Offre offre = new Offre(carteLaPlusHaute,carteLaPlusBasse,this);
		getMain().clear();
		System.out.println(offre.toString());

		
	}
	public void choisirOffre() {
		//choisir carte qui fait gagner le plus de points  
		//sinon carte aleatoire sur les joueurs 
	}
	public Offre getOffre() {
		return offre;
	}
}
