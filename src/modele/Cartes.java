<<<<<<< HEAD:src/App/Cartes.java
/*
 * 
 */
package App;

public class Cartes  {

	/** le tropheeq. */
	private EnumTrophee trophee;
	
	/** le couleurs. */
	private Couleur couleur;
	
	/** le valeurs. */
	private Valeur valeur;
	
	/**
	 * Instantie une nouvelle cartes.
	 */
	public Cartes() {
		
	}
	
	/**
	 * Instantie une nouvelle cartes.
	 *
	 * @param trophee le trophee
	 * @param couleur la couleur
	 * @param valeur la valeur
	 */
	public Cartes(EnumTrophee trophee, Couleur couleur, Valeur valeur) {
		this.couleur = couleur;
		this.trophee = trophee;
		this.valeur = valeur;
	}
	
	

	/**
	 * Gets le trophee.
	 *
	 * @return le trophee
	 */
	public EnumTrophee getTrophee() {
		return trophee;
	}

	/**
	 * Sets le trophee.
	 *
	 * @param trophee le nouveau trophee
	 */
	public void setTrophee(EnumTrophee trophee) {
		this.trophee = trophee;
	}

	/**
	 * Gets la couleur.
	 *
	 * @return la couleur
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * Sets le couleur.
	 *
	 * @param couleur le nouvelle couleur
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	/**
	 * Gets la valeur.
	 *
	 * @return la valeur
	 */
	public Valeur getValeur() {
		return valeur;
	}

	/**
	 * Sets le valeur.
	 *
	 * @param valeur la nouvelle valeur
	 */
	public void setValeur(Valeur valeur) {
		this.valeur = valeur;
	}
	
	/**
	 * To string.
	 *
	 * @return le string
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.valeur.toString());
		sb.append(" de ");
		sb.append(this.couleur.toString());
		return sb.toString();
	}
	
	
	
}
=======
package modele;

public class Cartes  {

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
>>>>>>> master:src/modele/Cartes.java
