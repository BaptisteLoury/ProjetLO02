<<<<<<< HEAD:src/App/Variante1.java
/*
 *La variante 1 modifie le comptage des scores. Les Coeurs et les Carreaux sont inversés. 
 */
package App;

import java.util.Iterator;
import java.util.LinkedList;

import Joueurs.Joueur;

public class Variante1 extends Partie {
	
	/**
	 * Instantiates a new variante 1.
	 */
	private Variante1() {}
	
	/** L' instance. */
	private static Variante1 INSTANCE= null;
	
	/**
	 * Gets the single instance of Variante1.
	 *
	 * @return single instance of Variante1
	 */
	public static Variante1  getInstance() {
		if (INSTANCE== null) {
			INSTANCE = new Variante1(); 
		}
		else {
			return INSTANCE;
		}
		return INSTANCE ;
	}
	
	/**
	 * Calcul des points.
	 */
	public void calculDesPoints() {
		System.out.println("tu es dans la variante1");
		
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			Iterator<Cartes> itCompter = j.getStack().iterator();
			int scoreJ =0;
			int nbCoeur =0;
			int nbTrefle =0;
			int nbCarreau =0;
			int nbPique =0;
			int nbPaire =0;
			LinkedList<Valeur> paires = new LinkedList<Valeur>();
			boolean possedeJoker =false;
			while (itCompter.hasNext()) {
				Cartes c = itCompter.next();
				switch (c.getCouleur()) {
					case COEUR:
						nbCarreau++;			
					break;
					case PIQUE:
						nbPique++;
					break;
					case TREFLE:
						nbTrefle++;
					break;
					case CARREAU:
						nbCoeur++;
					break;
					case JOKER:
						possedeJoker =true;
					break;
				}
			}
			Iterator<Cartes> itCalcul = j.getStack().iterator();
			while (itCalcul.hasNext()) {
				Cartes cj = itCalcul.next();
				switch(cj.getCouleur()) {
					case COEUR:
						if (cj.getValeur()==Valeur.AS) {
							if (nbCarreau==1) {
								scoreJ = scoreJ-5;
							}
							else {
								scoreJ--;
							}
						}
						else {
							scoreJ = scoreJ-cj.getValeur().ordinal();
						}
					break;
					case PIQUE:
						if (cj.getValeur()==Valeur.AS) {
							if (nbPique==1) {
								scoreJ = scoreJ+5;
							}
							else {
								scoreJ++;
							}
						}
						else {
							scoreJ = scoreJ+cj.getValeur().ordinal();
						}
						if (paires.contains(cj.getValeur())) {
							nbPaire++;
						}
						paires.add(cj.getValeur());
					break;
					case TREFLE:
						if (cj.getValeur()==Valeur.AS) {
							if (nbTrefle==1) {
								scoreJ = scoreJ+5;
							}
							else {
								scoreJ++;
							}
						}
						else {
							scoreJ = scoreJ+cj.getValeur().ordinal();
						}
						if (paires.contains(cj.getValeur())) {
							nbPaire++;
						}
						paires.add(cj.getValeur());
						break;
					case CARREAU:
						if (possedeJoker) {
							if (nbCoeur>=4) {
								scoreJ = scoreJ+cj.getValeur().ordinal();
							}
							else {
								if (cj.getValeur()==Valeur.AS) {
									if (nbCoeur==1) {
										scoreJ = scoreJ-5;
									}
									else {
										scoreJ--;
									}
								}
								else {
									scoreJ = scoreJ-cj.getValeur().ordinal();
								}
							}
						}
						// Sinon une carte coeur ne vaut aucun point
					break;
					// Joker
					case JOKER:
						if (nbCoeur==0) {
							scoreJ = scoreJ+4;
						}
						//Sinon le joker ne vaut aucun point
					break;
				}
			}
			scoreJ = scoreJ+2*nbPaire;
			j.setScoreFinal(scoreJ);
		}
	}
}
=======
package modele;

import java.util.Iterator;
import java.util.LinkedList;

public class Variante1 extends Partie {
	
	private Variante1() {}
	private static Variante1 INSTANCE= null;
	public static Variante1  getInstance() {
		if (INSTANCE== null) {
			INSTANCE = new Variante1(); 
		}
		else {
			return INSTANCE;
		}
		return INSTANCE ;
	}
	
	public void calculDesPoints() {
		System.out.println("tu es dans la variante1");
		
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			Iterator<Cartes> itCompter = j.getStack().iterator();
			int scoreJ =0;
			int nbCoeur =0;
			int nbTrefle =0;
			int nbCarreau =0;
			int nbPique =0;
			int nbPaire =0;
			LinkedList<Valeur> paires = new LinkedList<Valeur>();
			boolean possedeJoker =false;
			while (itCompter.hasNext()) {
				Cartes c = itCompter.next();
				switch (c.getCouleur()) {
					case COEUR:
						nbCarreau++;			
					break;
					case PIQUE:
						nbPique++;
					break;
					case TREFLE:
						nbTrefle++;
					break;
					case CARREAU:
						nbCoeur++;
					break;
					case JOKER:
						possedeJoker =true;
					break;
				}
			}
			Iterator<Cartes> itCalcul = j.getStack().iterator();
			while (itCalcul.hasNext()) {
				Cartes cj = itCalcul.next();
				switch(cj.getCouleur()) {
					case COEUR:
						if (cj.getValeur()==Valeur.AS) {
							if (nbCarreau==1) {
								scoreJ = scoreJ-5;
							}
							else {
								scoreJ--;
							}
						}
						else {
							scoreJ = scoreJ-cj.getValeur().ordinal();
						}
					break;
					case PIQUE:
						if (cj.getValeur()==Valeur.AS) {
							if (nbPique==1) {
								scoreJ = scoreJ+5;
							}
							else {
								scoreJ++;
							}
						}
						else {
							scoreJ = scoreJ+cj.getValeur().ordinal();
						}
						if (paires.contains(cj.getValeur())) {
							nbPaire++;
						}
						paires.add(cj.getValeur());
					break;
					case TREFLE:
						if (cj.getValeur()==Valeur.AS) {
							if (nbTrefle==1) {
								scoreJ = scoreJ+5;
							}
							else {
								scoreJ++;
							}
						}
						else {
							scoreJ = scoreJ+cj.getValeur().ordinal();
						}
						if (paires.contains(cj.getValeur())) {
							nbPaire++;
						}
						paires.add(cj.getValeur());
						break;
					case CARREAU:
						if (possedeJoker) {
							if (nbCoeur>=4) {
								scoreJ = scoreJ+cj.getValeur().ordinal();
							}
							else {
								if (cj.getValeur()==Valeur.AS) {
									if (nbCoeur==1) {
										scoreJ = scoreJ-5;
									}
									else {
										scoreJ--;
									}
								}
								else {
									scoreJ = scoreJ-cj.getValeur().ordinal();
								}
							}
						}
						// Sinon une carte coeur ne vaut aucun point
					break;
					// Joker
					case JOKER:
						if (nbCoeur==0) {
							scoreJ = scoreJ+4;
						}
						//Sinon le joker ne vaut aucun point
					break;
				}
			}
			scoreJ = scoreJ+2*nbPaire;
			j.setScoreFinal(scoreJ);
		}
	}
}
>>>>>>> master:src/modele/Variante1.java
