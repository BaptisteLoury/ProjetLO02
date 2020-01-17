/*
 * 
 */
package Vue;

import modele.Cartes;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class VueCartes {

	/** La carte. */
	private Cartes carte;
	
	/** L'image. */
	private JLabel image;
	

	/**
	 * Instantiates a new vue cartes.
	 *
	 * @param carte the carte
	 */
	public VueCartes(Cartes carte) {
		this.carte = carte;
		String cheminImage = "img/" + carte.getValeur() + "_" + carte.getCouleur() + ".png";
		this.image = new JLabel(new ImageIcon(cheminImage));
	}
	/**
	 * Getter de carte
	 * @return la carte a representer
	 */
	public Cartes getCarte() {
		return carte;
	}

	/**
	 * Getter de l'image
	 * @return l'image (Jlabel) associee a la carte
	 */
	public JLabel getImage() {
		return image;
	}
}
