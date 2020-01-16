package modele;

import java.util.LinkedList;

public interface Strategie {
	
	public void strategieFaireOffre();
	public Joueur strategieChoisirOffre(LinkedList<Joueur> joueurs);
}
