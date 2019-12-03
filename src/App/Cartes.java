package App;
import java.util.LinkedList;
import java.util.Iterator;

public class Cartes {

	private EnumTrophee trophee;
	private Couleur couleur;
	private Valeur valeur;
	
	public Cartes() {
		
	}
	public Cartes(EnumTrophee trophee, Couleur couleur, Valeur valeur) {
		this.couleur = couleur;
		this.trophee = trophee;
		this.valeur = valeur;
	}

	public EnumTrophee getTrophee() {
		return trophee;
	}

	public void setTrophee(EnumTrophee trophee) {
		this.trophee = trophee;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public Valeur getValeur() {
		return valeur;
	}

	public void setValeur(Valeur valeur) {
		this.valeur = valeur;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.valeur.toString());
		sb.append(" de ");
		sb.append(this.couleur.toString());
		return sb.toString();
	}

}
