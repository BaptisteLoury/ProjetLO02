/*
 * @author Baptiste Loury et Pierre Groshens
 */
package App;

import Joueurs.Joueur;
import Visit.*;
import java.io.*;
import java.util.Scanner;

public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VisitorJest monVisiteur = new VisitorJest();
		
		//Choix de la variante
		
		Scanner scVariante = new Scanner(System.in);
		System.out.println("Voulez vous jouer ра une variante ? (O/N)");
		char variante = scVariante.nextLine().charAt(0);
		switch (variante) {
		case 'O':
			Scanner scQuellevariante = new Scanner(System.in);
			System.out.println("A quelle variante veux tu jouer ? (1 ou 2)");
			int quelleVariante = scQuellevariante.nextInt();
			
			switch (quelleVariante) {
			case 1: 
				
				System.out.println("okay on va jouer a la variante 1");
				Variante1 variante1 = Variante1.getInstance();
				variante1.accept(monVisiteur);
				variante1.creerPartie();
				
				int nbTour = 1;
				while (variante1.getDeck().getDeckCartes().size()>=Joueur.getNbJoueurs()) {
					System.out.println("TOUR # "+ nbTour);
					if (nbTour!=1) {
						variante1.remettreEnJeuCarteOffre();
					}
					variante1.tour();
					nbTour++;
				}
				variante1.dernierTour();
				variante1.attribuerTrophees();
				variante1.calculDesPoints();
				
				
				break;
				 
			case 2:	
				
				System.out.println("okay on va jouer a la variante 2");
				Variante2 variante2 = Variante2.getInstance();
				variante2.accept(monVisiteur);
				variante2.creerPartie();
				
				nbTour = 1;
				while (variante2.getDeck().getDeckCartes().size()>=Joueur.getNbJoueurs()) {
					System.out.println("TOUR # "+ nbTour);
					if (nbTour!=1) {
						variante2.remettreEnJeuCarteOffre();
					}
					variante2.tour();
					nbTour++;
				}
				variante2.dernierTour();
				variante2.attribuerTrophees();
				variante2.calculDesPoints();
				variante2.donnerLesResultats();
				
				variante2.ajouterVariante2();
				
				
			}
		break;
		case 'N':
			System.out.println("Okay, tu ne veux pas joueur р une variante. J'en prends note");
			Partie partie = Partie.getInstance();
			partie.accept(monVisiteur);
			partie.creerPartie();
			
			int nbTour = 1;
			while (partie.getDeck().getDeckCartes().size()>=Joueur.getNbJoueurs()) {
				System.out.println("TOUR # "+ nbTour);
				if (nbTour!=1) {
					partie.remettreEnJeuCarteOffre();
				}
				partie.tour();
				nbTour++;
			}
			partie.dernierTour();
			partie.attribuerTrophees();
			partie.calculDesPoints();
			partie.donnerLesResultats();
			
			
			
		break;
		default:
			System.out.println("La syntaxe de la reponse n'est pas correcte. Je pars du principe que tu ne veux pas jouer р une variante!") ;
			Partie partieDefault = Partie.getInstance();
			partieDefault.accept(monVisiteur);
			partieDefault.creerPartie();
			
			nbTour = 1;
			while (partieDefault.getDeck().getDeckCartes().size()>=Joueur.getNbJoueurs()) {
				System.out.println("TOUR # "+ nbTour);
				if (nbTour!=1) {
					partieDefault.remettreEnJeuCarteOffre();
				}
				partieDefault.tour();
				nbTour++;
			}
			partieDefault.dernierTour();
			partieDefault.attribuerTrophees();
			partieDefault.calculDesPoints();
			partieDefault.donnerLesResultats();
		}
		
		
		

	}

}
