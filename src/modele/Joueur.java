
/*
 * 
 */
package modele;
import java.util.LinkedList;



public class Joueur {

	/** Le pseudo. */
	private String pseudo;

	/** Le nombre de joueurs. */
	private static int nbJoueurs = 0;
	
	/** La main. */
	protected LinkedList<Cartes> main;
	
	/** Le stack. */
	protected LinkedList<Cartes> stack;
	
	/** L'offre. */
	protected Offre offre;
	
	/** L'offre choisie. */
	protected Offre offreChoisie;
	
	/** Le points pour recuperer trophee.
	 * Il s'agit d'un compteur propre à chaque qui est incrémenté quand les trophées concernent le nombre de cartes spécifiques.
	 *  */
	protected int pointsPourRecupererTrophee;
	/*
	 * Les attributs booleans qui suivent sont utiles pour l'attribution des trophées.
	 */
	/**  possession as. */
	protected boolean possessionAs = false;
	
	/** Le possession quatre. */
	protected boolean possessionQuatre = false;
	
	/** Le possession deux. */
	protected boolean possessionDeux = false;
	
	/** Le score final. */
	protected int scoreFinal = 0;
	
	/** Le score devine. 
	 * L'attribut scoreDevine sert pour la variante2 quand il faut devnier son score.
	 * */
	protected int scoreDevine;
	
	/** Le strategie joueur. */
	protected Strategie strategieJoueur;
	
	/** Le humain. */
	protected Boolean humain;
	
	
	/**
	 * Gets le score devine.
	 *
	 * @return le score devine
	 */
	public int getScoreDevine() {
		return scoreDevine;
	}

	/**
	 * Sets le score devine.
	 *
	 * @param scoreDevine le nouveau score devine
	 */
	public void setScoreDevine(int scoreDevine) {
		this.scoreDevine = scoreDevine;
	}

	/**
	 * Instantie a new joueur.
	 */
	public Joueur () {}	
	
	/**
	 * Instantie un nouveau joueur.
	 *
	 * @param pseudo le pseudo
	 */
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
		main = new LinkedList<Cartes>();

		stack = new LinkedList<Cartes>();

	}

	/**
	 * Faire offre.
	 */
	public void faireOffre() {
		this.strategieJoueur.strategieFaireOffre();
	}
	
	/**
	 * Choisir offre.
	 *
	 * @param joueurs le joueurs
	 * @return le joueur
	 */
	public Joueur choisirOffre(LinkedList<Joueur> joueurs) {
		Joueur joueurPioche = this.strategieJoueur.strategieChoisirOffre(joueurs);
		return joueurPioche;
	}
	
	/**
	 * Gets le offre.
	 *
	 * @return le offre
	 */
	public Offre getOffre() {
		return offre;
	}
	
	/**
	 * Sets le offre.
	 *
	 * @param offre la nouvelle offre
	 */
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	
	/**
	 * Gets la main.
	 *
	 * @return la main
	 */
	public LinkedList<Cartes> getMain() {
		return main;
	}
	
	/**
	 * Sets la main.
	 *
	 * @param main la nouvelle main
	 */
	public void setMain(LinkedList<Cartes> main) {
		this.main = main;
	}
	
	/**
	 * Gets le pseudo.
	 *
	 * @return le pseudo
	 */
	public String getPseudo() {
		return this.pseudo;
	}
	
	/**
	 * Afficher indice cartes.
	 *
	 * @return le string
	 */
	public String afficherIndiceCartes() {
		StringBuffer sb = new StringBuffer();
		sb.append("0");
		sb.append(" : ");
		sb.append(main.getFirst().toString());
		sb.append(" | 1");
		sb.append(" : ");
		sb.append(main.getLast().toString());
		return sb.toString();
	}
	
	/**
	 * Sets le pseudo.
	 *
	 * @param pseudo le nouveau pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Gets le nombre de joueurs.
	 *
	 * @return le nombre de joueurs
	 */
	public static int getNbJoueurs() {
		return nbJoueurs;
	}
	
	
	/**
	 * Gets l'offre choisie.
	 *
	 * @return l'offre choisie
	 */
	public Offre getOffreChoisie() {
		return offreChoisie;
	}
	
	/**
	 * Sets loffre choisie.
	 *
	 * @param offreChoisie la nouvelle offre choisie
	 */
	public void setOffreChoisie(Offre offreChoisie) {
		this.offreChoisie = offreChoisie;
	}
	
	/**
	 * To string.
	 *
	 * @return le string
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pseudo);
		sb.append(" : ");
		sb.append(stack.toString());
		return sb.toString();
	}
	
	/**
	 * Gets le stack.
	 *
	 * @return le stack
	 */
	public LinkedList<Cartes> getStack() {
		return stack;
	}
	
	/**
	 * Gets le points pour recuperer trophee.
	 *
	 * @return le points pour recuperer trophee
	 */
	public int getPointsPourRecupererTrophee() {
		return pointsPourRecupererTrophee;
	}
	
	/**
	 * Sets le points pour recuperer trophee.
	 *
	 * @param pointsPourRecupererTrophee le new points pour recuperer trophee
	 */
	public void setPointsPourRecupererTrophee(int pointsPourRecupererTrophee) {
		this.pointsPourRecupererTrophee = pointsPourRecupererTrophee;
	}
	
	/**
	 * Checks if is possession as.
	 *
	 * @return true, if is possession as
	 */
	public boolean isPossessionAs() {
		return possessionAs;
	}
	
	/**
	 * Sets le possession as.
	 *
	 * @param possessionAs le new possession as
	 */
	public void setPossessionAs(boolean possessionAs) {
		this.possessionAs= possessionAs;
	}
	
	/**
	 * Checks if is possession quatre.
	 *
	 * @return true, if is possession quatre
	 */
	public boolean isPossessionQuatre() {
		return possessionQuatre;
	}
	
	/**
	 * Sets le possession quatre.
	 *
	 * @param possessionQuatre le new possession quatre
	 */
	public void setPossessionQuatre(boolean possessionQuatre) {
		this.possessionQuatre = possessionQuatre;
	}
	
	/**
	 * Checks if is possession deux.
	 *
	 * @return true, if is possession deux
	 */
	public boolean isPossessionDeux() {
		return possessionDeux;
	}
	
	/**
	 * Sets le possession deux.
	 *
	 * @param possessionDeux le new possession deux
	 */
	public void setPossessionDeux(boolean possessionDeux) {
		this.possessionDeux = possessionDeux;
	}
	
	/**
	 * Gets le score final.
	 *
	 * @return le score final
	 */
	public int getScoreFinal() {
		return scoreFinal;
	}
	
	/**
	 * Sets le score final.
	 *
	 * @param scoreFinal le new score final
	 */
	public void setScoreFinal(int scoreFinal) {
		this.scoreFinal = scoreFinal;
	}
	
	/**
	 * Effectuer strategie.
	 *
	 * @param strategieJ le strategie J
	 */
	public void effectuerStrategie(Strategie strategieJ) {
		this.strategieJoueur = strategieJ;	
	}

	/**
	 * Gets la strategie joueur.
	 *
	 * @return la strategie joueur
	 */
	public Strategie getStrategieJoueur() {
		return strategieJoueur;
	}

	/**
	 * Checks if is humain.
	 *
	 * @return le boolean
	 */
	public Boolean isHumain() {
		return humain;
	}

	/**
	 * Sets l'humain.
	 *
	 * @param humain le nouvel humain
	 */
	public void setHumain(Boolean humain) {
		this.humain = humain;
	}
	
	
}

