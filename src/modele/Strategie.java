
package modele;


import java.util.LinkedList;

public interface Strategie {
	
	/**
	 * Strategie faire offre.
	 * Choisis quelle carte mettre en recto et quelle carte mettre en verso.
	 */
	public void strategieFaireOffre();
	
	/**
	 * Strategie choisir offre.
	 * <ul>
	 * <li>Choisis chez qui piocher. </li>
	 *<li>Choisis quelle carte piocher chez ce joueur</li>
	 *</ul>
	 * @param joueurs la liste de joueurs
	 * @return un joueur
	 */
	public Joueur strategieChoisirOffre(LinkedList<Joueur> joueurs);
}
