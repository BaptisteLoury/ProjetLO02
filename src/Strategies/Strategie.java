package Strategies;

import java.util.LinkedList;

import Joueurs.Joueur;

public interface Strategie {
	
	public void effectuerStrategie(LinkedList<Joueur> joueurs);
}
