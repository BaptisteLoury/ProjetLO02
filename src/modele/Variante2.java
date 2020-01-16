package modele;

import java.util.Iterator;
import java.util.Scanner;

public class Variante2 extends Partie{

	private Variante2() {}
	private static Variante2 INSTANCE= null;
	public static Variante2  getInstance() {
		if (INSTANCE== null) {
			INSTANCE = new Variante2(); 
		}
		else {
			return INSTANCE;
		}
		return INSTANCE ;
	}
	
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
	
	public void donnerLesResultats() {
		System.out.println("Résultats de la partie :");
		Iterator<Joueur> itj = joueurs.iterator();
		while (itj.hasNext()) {
			Joueur j = itj.next();
			System.out.println(j.getPseudo()+" : "+j.getScoreFinal());
		}
	}
}
