package App;

import java.util.Iterator;
import java.util.Scanner;

import Joueurs.Joueur;

public class Variante2 extends Partie{

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
		while (itj.hasNext()) {
			Joueur j =  itj.next();
			if (j.getScoreDevine()>j.getScoreFinal()) {
				j.setScoreFinal(j.getScoreFinal()-(j.getScoreDevine()-j.getScoreFinal()));
			}
			
			else if (j.getScoreDevine()<j.getScoreFinal()) {
				j.setScoreFinal(j.getScoreFinal()-(j.getScoreFinal()-j.getScoreDevine()));
			}
			else {
				System.out.println("bravo tu as devine ton score!!!\n");
			}
			
		}
		
	}
		
}
