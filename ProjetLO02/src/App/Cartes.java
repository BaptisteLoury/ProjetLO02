package App;

public class Cartes {

	private EnumTrophee trophee;
	private Couleur couleur;
	private Valeur valeur;
	private String bonjour;
	
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


}
