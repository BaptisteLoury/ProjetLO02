package modele;


public interface Visitor {

	void visit (Partie partie);
	void visit (Cartes partie);
	void visit (Joueur joueurs);
	void visit (Strategie strategie);
}
