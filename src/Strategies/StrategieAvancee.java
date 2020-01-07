package Strategies;

import java.util.Iterator;
import java.util.LinkedList;

import App.Cartes;
import App.Couleur;
import App.EnumTrophee;
import App.Offre;
import App.Valeur;
import Joueurs.Joueur;

public class StrategieAvancee implements Strategie{
	
	private Joueur joueur;
	/*@Override
	public void effectuerStrategie(LinkedList<Joueur> joueurs) {
		// TODO Auto-generated method stub
		System.out.println("je suis dans la strategie avanc�e");
	}*/
	//Equivalent a strategie basique pour le moment
	public StrategieAvancee(Joueur joueur) {
		this.joueur = joueur;
	}
	@Override
	public void strategieFaireOffre() {
		Iterator <Cartes> itc =  joueur.getMain().iterator();
		Cartes carteLaPlusHaute = new Cartes();
		carteLaPlusHaute = joueur.getMain().getFirst();
		Cartes carteLaPlusBasse = new Cartes();
		carteLaPlusBasse = joueur.getMain().getLast();
		
		while(itc.hasNext()) {
			Cartes carteSuivante = (Cartes) itc.next();
			if(carteSuivante.getValeur().ordinal() >carteLaPlusHaute.getValeur().ordinal()) {
				carteLaPlusBasse = carteLaPlusHaute;
				carteLaPlusHaute = carteSuivante ;
			}
		}
		joueur.setOffre(new Offre(carteLaPlusHaute,carteLaPlusBasse,joueur));
		joueur.getMain().clear();
	}
	@Override
	public Joueur strategieChoisirOffre(LinkedList<Joueur> joueurs) {
		System.out.println("Au tour de "+joueur.getPseudo()+" de prendre une carte");
		
		
		Iterator<Joueur> itj = joueurs.iterator();
		int nombreOffreSuffisante = 0;
		Offre meilleureOffre = joueurs.getFirst().getOffre();
		Cartes plusForteRecto = new Cartes(EnumTrophee.Joker,Couleur.JOKER,Valeur.JOKER);
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != joueur) {
				if (j.getOffre().getRecto().getValeur().ordinal()>plusForteRecto.getValeur().ordinal() && j.getOffre().getRecto().getCouleur()!=Couleur.CARREAU && j.getOffre().getRecto().getCouleur()!=Couleur.COEUR) {
					meilleureOffre = j.getOffre();
					plusForteRecto = j.getOffre().getRecto();
				}
				//deuxieme if qui voit les cartes verso de tout le monde. Strategie Avanc�e
				if (j.getOffre().getVerso().getValeur().ordinal()>plusForteRecto.getValeur().ordinal() && j.getOffre().getVerso().getCouleur()!=Couleur.CARREAU && j.getOffre().getRecto().getCouleur()!=Couleur.COEUR) {
					meilleureOffre = j.getOffre();
					plusForteRecto = j.getOffre().getRecto();
				}
				nombreOffreSuffisante++;
			}
		}
		if (nombreOffreSuffisante==0) {
			joueur.getStack().add(joueur.getOffre().getVerso());
			joueur.getOffre().setVerso(null);
			System.out.println(joueur.getPseudo()+" a pris la carte verso de sa propre offre.");
			joueur.getOffre().setOffreSuffisante(false);
			return joueur;
		}
		else {
			if (plusForteRecto.getCouleur()==Couleur.JOKER) {
				Iterator<Joueur> itj2 = joueurs.iterator();
				boolean continuerBoucle = true;
				while (itj2.hasNext() && continuerBoucle) {
					Joueur j2 = (Joueur) itj2.next();
					if (j2.getOffre().estOffreSuffisante()) {
						continuerBoucle = false;
						joueur.getStack().add(j2.getOffre().getVerso());
						System.out.println(joueur.getPseudo()+" a pris la carte verso du joueur "+meilleureOffre.getOffrant().getPseudo());
						j2.getOffre().setVerso(null);
						meilleureOffre = j2.getOffre();
					}
				}
				meilleureOffre.getOffrant().getOffre().setOffreSuffisante(false);
				return meilleureOffre.getOffrant();
			}
			else {
				joueur.getStack().add(meilleureOffre.getRecto());

				System.out.println(joueur.getPseudo()+" a pris la carte recto "+meilleureOffre.getRecto()+" du joueur "+meilleureOffre.getOffrant().getPseudo());
				meilleureOffre.getOffrant().getOffre().setRecto(null);
				meilleureOffre.getOffrant().getOffre().setOffreSuffisante(false);

				return meilleureOffre.getOffrant();
				
			}
			
		}
	}
}
