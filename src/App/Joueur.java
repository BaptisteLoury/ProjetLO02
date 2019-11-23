package App;
import java.util.LinkedList;

public class Joueur {

	private String pseudo;
	private static int nbJoueurs = 0;
	private LinkedList<Cartes> main;
	
	public Joueur() {
		
	}
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
		main = new LinkedList<Cartes>();
	}

	public void faireOffre()  {
		
	}
	public void choisirCarte()  {
		//cartesProposees a definir dans faireOffre
		main.add(cartesProposees);
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
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pseudo);
		sb.append(" : ");
		sb.append(main);
		return sb.toString();
	}
}
