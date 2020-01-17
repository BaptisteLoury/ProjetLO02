
/*
 * Les regles de la variante2 sont les suivantes 

 *  A la fin de la partie, avant d'annoncer les scores, chaque joueur doit deviner son propre score.
	 * <ul>
	 * <li>S'il a juste, il n'est pas p�nalis�.</li>
	 * <li>S'il majore ou minore son score, il verra ses points diminuer proportionnellement � la r�ponse fausse equ'il a donner.</li>
	 * </ul>
 */
package modele;

import java.util.Iterator;
import java.util.Scanner;



public class Variante2 extends Partie{

	/**
	 * Instantie une nouvelle variante 2.
	 
	 */
	private Variante2() {}
	
	/** l'instance. */
	private static Variante2 INSTANCE= null;
	
	/**
	 * Gets the single instance of Variante2.
	 *
	 * @return single instance of Variante2
	 */
	public static Variante2  getInstance() {
		if (INSTANCE== null) {
			INSTANCE = new Variante2(); 
		}
		else {
			return INSTANCE;
		}
		return INSTANCE ;
	}
	
	/**
	 * Ajouter variante 2.
	 */
	public void ajouterVariante2(){
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j =  itj.next();
			System.out.println(j.getPseudo()+j.getStack()+"\n");
			
			Scanner scScoreDevine = new Scanner(System.in);
			System.out.println("Devine ton score");
			j.setScoreDevine(scScoreDevine.nextInt());
		}
		Iterator<Joueur> itj2 = joueurs.iterator();
		//Change les scores en fonction des r�ponses donn�es par chacun.
		while (itj2.hasNext()) {
			Joueur j =  itj2.next();
			if (j.getScoreDevine()>j.getScoreFinal()) {
				j.setScoreFinal(j.getScoreFinal()-(j.getScoreDevine()-j.getScoreFinal()));
			}
			
			else if (j.getScoreDevine()<j.getScoreFinal()) {
				j.setScoreFinal(j.getScoreFinal()-(j.getScoreFinal()-j.getScoreDevine()));
			}
			else if(j.getScoreDevine()==j.getScoreFinal()){
				System.out.println(j.getPseudo()+"bravo tu as devine ton score!!!\n");
			}
			else {
				System.out.println("petite erreur. je reviens");
			}
		}
		Variante2.getInstance().donnerLesResultats();
	}
	
	/**
	 * Donner les resultats.
	 */
	public void donnerLesResultats() {
		System.out.println("R�sultats de la partie :");
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			System.out.println(j.getPseudo()+" : "+j.getScoreFinal());
		}
	}
}

