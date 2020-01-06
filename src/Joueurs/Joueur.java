package Joueurs;
import java.util.LinkedList;
import java.util.Scanner;

import App.Cartes;
import App.Offre;
import Strategies.*;
import java.util.Iterator;


public class Joueur {

	private String pseudo;

	private static int nbJoueurs = 0;
	protected LinkedList<Cartes> main;
	protected LinkedList<Cartes> stack;
	protected Offre offre;
	protected Offre offreChoisie;
	protected int pointsPourRecupererTrophee;
	protected boolean possessionAs = false;
	protected boolean possessionQuatre = false;
	protected boolean possessionDeux = false;
	protected int scoreFinal = 0;
	protected int scoreDevine;
	protected Strategie strategieJoueur;
	
	
	public int getScoreDevine() {
		return scoreDevine;
	}

	public void setScoreDevine(int scoreDevine) {
		this.scoreDevine = scoreDevine;
	}

	public Joueur () {}	
	
	public Joueur(String pseudo) {
		nbJoueurs++;
		this.pseudo = pseudo;
		main = new LinkedList<Cartes>();

		stack = new LinkedList<Cartes>();

	}

	public void faireOffre() {
		this.strategieJoueur.strategieFaireOffre();
	}
	
	public Joueur choisirOffre(LinkedList<Joueur> joueurs) {
		Joueur joueurPioche = this.strategieJoueur.strategieChoisirOffre(joueurs);
		return joueurPioche;
	}
	
	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
	}
	public LinkedList<Cartes> getMain() {
		return main;
	}
	public void setMain(LinkedList<Cartes> main) {
		this.main = main;
	}
	public String getPseudo() {
		return this.pseudo;
	}
	public String afficherIndiceCartes() {
		StringBuffer sb = new StringBuffer();
		sb.append("0");
		sb.append(" : ");
		sb.append(main.getFirst().toString());
		sb.append(" | 1");
		sb.append(" : ");
		sb.append(main.getLast().toString());
		return sb.toString();
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public static int getNbJoueurs() {
		return nbJoueurs;
	}
	
	
	public Offre getOffreChoisie() {
		return offreChoisie;
	}
	public void setOffreChoisie(Offre offreChoisie) {
		this.offreChoisie = offreChoisie;
	}
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(pseudo);
		sb.append(" : ");
		sb.append(stack.toString());
		return sb.toString();
	}
	public LinkedList<Cartes> getStack() {
		return stack;
	}
	public int getPointsPourRecupererTrophee() {
		return pointsPourRecupererTrophee;
	}
	public void setPointsPourRecupererTrophee(int pointsPourRecupererTrophee) {
		this.pointsPourRecupererTrophee = pointsPourRecupererTrophee;
	}
	public boolean isPossessionAs() {
		return possessionAs;
	}
	public void setPossessionAs(boolean possessionAs) {
		this.possessionAs= possessionAs;
	}
	public boolean isPossessionQuatre() {
		return possessionQuatre;
	}
	public void setPossessionQuatre(boolean possessionQuatre) {
		this.possessionQuatre = possessionQuatre;
	}
	public boolean isPossessionDeux() {
		return possessionDeux;
	}
	public void setPossessionDeux(boolean possessionDeux) {
		this.possessionDeux = possessionDeux;
	}
	public int getScoreFinal() {
		return scoreFinal;
	}
	public void setScoreFinal(int scoreFinal) {
		this.scoreFinal = scoreFinal;
	}
	public void effectuerStrategie(Strategie strategieJ) {
		this.strategieJoueur = strategieJ;	
	}
}
