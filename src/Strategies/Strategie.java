package Strategies;

import java.util.LinkedList;

import Joueurs.Joueur;

public interface Strategie {
	
	public void strategieFaireOffre();
	public Joueur strategieChoisirOffre(LinkedList<Joueur> joueurs);
}
