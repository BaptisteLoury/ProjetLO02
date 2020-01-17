/*
 * 
 */
package App;

import Joueurs.Joueur;
import Joueurs.JoueurVirtuel;

public class Offre {

	/** Le recto. */
	private Cartes recto;
	
	/** Le verso. */
	private Cartes verso;
	
	/** L'offrant. */
	private Joueur offrant;
	
	/** L'offrant virtuel. */
	private JoueurVirtuel offrantVirtuel ; 
	
	/** L'offre suffisante.
	 * L'offres suffisante sert à déterminer si un joueur a assez de cartes pour piocher chez lui.
	 * Si son offre n'est pas suffisante, personne ne peut piocher chez lui.
	 *  */
	//private Joueur preneur;
	private boolean offreSuffisante;
	
	/**
	 * Instantie a new offre.
	 *
	 * @param recto the recto
	 * @param verso the verso
	 * @param offrant the offrant
	 */
	public Offre(Cartes recto, Cartes verso , Joueur offrant) {
		this.recto = recto;
		this.verso = verso;
		this.offrant = offrant;
		offreSuffisante = true;
	}
	
	/**
	 * Gets the recto.
	 *
	 * @return the recto
	 */
	public Cartes getRecto() {
		return recto;
	}
	
	/**
	 * Sets the recto.
	 *
	 * @param recto the new recto
	 */
	public void setRecto(Cartes recto) {
		this.recto = recto;
	}
	
	/**
	 * Gets the verso.
	 *
	 * @return the verso
	 */
	public Cartes getVerso() {
		return verso;
	}
	
	/**
	 * Sets the verso.
	 *
	 * @param verso le nouveau verso
	 */
	public void setVerso(Cartes verso) {
		this.verso = verso;
	}
	
	/**
	 * Gets l'offrant.
	 *
	 * @return l'offrant
	 */
	public Joueur getOffrant() {
		return offrant;
	}
	
	/**
	 * Sets l'offrant.
	 *
	 * @param offrant l'offrant
	 */
	public void setOffrant(Joueur offrant) {
		this.offrant = offrant;
	}
	
	/**
	 * Est offre suffisante.
	 *
	 * @return true, if successful
	 */
	public boolean estOffreSuffisante() {
		return offreSuffisante;
	}
	
	/**
	 * Sets the offre suffisante.
	 *
	 * @param offreSuffisante the new offre suffisante
	 */
	public void setOffreSuffisante(Boolean offreSuffisante) {
		this.offreSuffisante = offreSuffisante;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*public Joueur getPreneur() {
		return preneur;
	}
	public void setPreneur(Joueur preneur) {
		this.preneur = preneur;
	}*/
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(offrant.getPseudo());
		sb.append(" - Carte recto : ");
		sb.append(recto.toString());
		return sb.toString();
	}
	
}
