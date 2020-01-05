package Joueurs;

import java.util.Iterator;


import java.util.Scanner;

import App.*;

import java.util.LinkedList;
import Strategies.*;
public class JoueurVirtuel extends Joueur {

	
	public JoueurVirtuel(String pseudo) {
		super(pseudo);
	}
	
	
	
	public void faireOffre() {
		//faire iterator sur les cartes de la main 
		//selectionner carte qui a plus haute valeur 
			//si egalite, prendre meilleur couleur (faire en sorte de choisir offre en premier
		
		
		Iterator <Cartes> itc =  getMain().iterator();
		Cartes carteLaPlusHaute = new Cartes();
		carteLaPlusHaute = getMain().getFirst();
		Cartes carteLaPlusBasse = new Cartes();
		carteLaPlusBasse = getMain().getLast();
		
		while(itc.hasNext()) {
			Cartes carteSuivante = (Cartes) itc.next();
			if(carteSuivante.getValeur().ordinal() >carteLaPlusHaute.getValeur().ordinal()) {
				carteLaPlusBasse = carteLaPlusHaute;
				carteLaPlusHaute = carteSuivante ;
			}
		}
		offre = new Offre(carteLaPlusHaute,carteLaPlusBasse,this);
		main.clear();
		
	}
	public Joueur choisirOffre(LinkedList<Joueur> joueurs) {
		//choisir carte qui fait gagner le plus de points  
		//sinon carte aleatoire sur les joueurs 

		System.out.println("Au tour de "+this.getPseudo()+" de prendre une carte");
		
		
		Iterator<Joueur> itj = joueurs.iterator();
		int nombreOffreSuffisante = 0;
		Offre meilleureOffre = joueurs.getFirst().getOffre();
		Cartes plusForteRecto = new Cartes(EnumTrophee.Joker,Couleur.JOKER,Valeur.JOKER);
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != this) {
				if (j.getOffre().getRecto().getValeur().ordinal()>plusForteRecto.getValeur().ordinal() && j.getOffre().getRecto().getCouleur()!=Couleur.CARREAU && j.getOffre().getRecto().getCouleur()!=Couleur.COEUR) {
					meilleureOffre = j.getOffre();
					plusForteRecto = j.getOffre().getRecto();
				}
				nombreOffreSuffisante++;
			}
		}
		if (nombreOffreSuffisante==0) {
			this.getStack().add(offre.getVerso());
			this.getOffre().setVerso(null);
			System.out.println(this.getPseudo()+" a pris la carte verso de sa propre offre.");
			this.offre.setOffreSuffisante(false);
			return this;
		}
		else {
			if (plusForteRecto.getCouleur()==Couleur.JOKER) {
				Iterator<Joueur> itj2 = joueurs.iterator();
				boolean continuerBoucle = true;
				while (itj2.hasNext() && continuerBoucle) {
					Joueur j2 = (Joueur) itj2.next();
					if (j2.getOffre().estOffreSuffisante()) {
						continuerBoucle = false;
						this.getStack().add(j2.getOffre().getVerso());
						System.out.println(this.getPseudo()+" a pris la carte verso du joueur "+meilleureOffre.getOffrant().getPseudo());
						j2.getOffre().setVerso(null);
						meilleureOffre = j2.getOffre();
					}
				}
				meilleureOffre.getOffrant().getOffre().setOffreSuffisante(false);
				return meilleureOffre.getOffrant();
			}
			else {
				this.getStack().add(meilleureOffre.getRecto());

				System.out.println(this.getPseudo()+" a pris la carte recto "+meilleureOffre.getRecto()+" du joueur "+meilleureOffre.getOffrant().getPseudo());
				meilleureOffre.getOffrant().getOffre().setRecto(null);
				meilleureOffre.getOffrant().getOffre().setOffreSuffisante(false);

				return meilleureOffre.getOffrant();
				
			}
			
		}

	}
	public void effectuerStrategie(Strategie strategieJV) {
		LinkedList<Joueur> joueurs = Partie.getJoueurs();
		strategieJV.effectuerStrategie(joueurs);
		
	}
	
	public Offre getOffre() {
		return offre;
	}
}
