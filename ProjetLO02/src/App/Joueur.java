package App;

public class Joueur {

	private String pseudo;
	public static int nbJoueurs = 0;
	
	public Joueur() {
		
	}
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
	}

	public void faireOffre()  {
		
	}
	public void choisirCarte()  {
		
	}	
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public static int getNbJoueurs() {
		return nbJoueurs;
	}
	public static void setNbJoueurs(int nbJoueurs) {
		Joueur.nbJoueurs = nbJoueurs;
	}
}
