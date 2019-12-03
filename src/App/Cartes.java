package App;
import java.util.LinkedList;
import java.util.Iterator;

public class Cartes {

	private EnumTrophee trophee;
	private Couleur couleur;
	private Valeur valeur;
	private static LinkedList<Cartes> CARTES;
	
	public Cartes() {
		
	}
	public Cartes(EnumTrophee trophee, Couleur couleur, Valeur valeur) {
		this.couleur = couleur;
		this.trophee = trophee;
		this.valeur = valeur;
		CARTES.add(this);
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
	public Cartes getPaire() {
		Iterator<Cartes> itc = CARTES.iterator();
		Cartes paire = new Cartes();
		while (itc.hasNext()) {
			Cartes c = itc.next();
			if (c.getValeur()==this.getValeur()) {
				if (c.getCouleur()==Couleur.TREFLE && this.getCouleur()==Couleur.PIQUE) {
					paire = c;
				}
				else if (c.getCouleur()==Couleur.PIQUE && this.getCouleur()==Couleur.TREFLE) {
					paire = c;

				}
				else {
					paire = null;
				}
			}
		}
		return paire;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.valeur.toString());
		sb.append(" de ");
		sb.append(this.couleur.toString());
		return sb.toString();
	}

}
