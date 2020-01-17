/*
 * Cette fenêtre permet de choisir une carte chez un joueur.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import modele.Cartes;
import modele.Joueur;
import modele.Partie;

public class DialogChoisirOffre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The joueur. */
	private Joueur joueur;
	
	/** The joueur pioche. */
	private Joueur joueurPioche;
	
	/** The carte A jouer. */
	private LinkedList<Cartes> carteAJouer;
	
	/** The carte choisie. */
	private Cartes carteChoisie;
	
	/**
	 * Instantiates a new dialog choisir offre.
	 *
	 * @param parent the parent
	 * @param title the title
	 * @param modal the modal
	 * @param joueur the joueur
	 */
	public DialogChoisirOffre(JFrame parent, String title, boolean modal, Joueur joueur) {
		super(parent, title, modal);
		this.joueur = joueur;
		this.carteAJouer = new LinkedList<>();
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}
	
	/**
	 * Inits the.
	 */
	public void init() {
		JPanel fenetre = new JPanel();
		fenetre.setLayout(new BorderLayout(0, 1));
		
		JLabel pseudo = new JLabel("Choix de " +joueur.getPseudo());
		JLabel question = new JLabel("Quelle carte mettez-vous en recto ?");
		
		JPanel offreDisponible = new JPanel();
		offreDisponible.setLayout(new BorderLayout());
		JPanel offreJ = new JPanel();
		Iterator<Joueur> itj = Partie.getJoueurs().iterator();
		int nombreOffreSuffisante = 0;
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != joueur) {
				JLabel pseudoJ = new JLabel("Offre du joueur "+j.getPseudo());
				VueCartes vueRectoj = new VueCartes(j.getOffre().getRecto());
				
				final JButton okRecto = new JButton("Prendre carte recto");
			    okRecto.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						joueur.getStack().add(j.getOffre().getRecto());
						j.getOffre().setRecto(null);
						j.getOffre().setOffreSuffisante(false);
						joueurPioche = j;
						setVisible(false);
					}
						
				});
			    final JButton okVerso = new JButton("Prendre carte verso");
			    
			    okVerso.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						joueur.getStack().add(j.getOffre().getVerso());
						j.getOffre().setVerso(null);
						j.getOffre().setOffreSuffisante(false);
						joueurPioche = j;
						setVisible(false);
					}
						
				});
				nombreOffreSuffisante++;
				offreJ.add(pseudoJ);
				offreJ.add(vueRectoj.getImage());
				offreJ.add(okRecto);
				offreJ.add(okVerso);
			}
		}
		offreDisponible.add(offreJ);
		if (nombreOffreSuffisante == 0) {
			JPanel offrePropre = new JPanel();
			JLabel pseudoJ1 = new JLabel("Vous devez choisir parmis les cartes de votre offre.");
			VueCartes vueRectoPropre = new VueCartes(joueur.getOffre().getRecto());
			
			final JButton okRecto2 = new JButton("Prendre carte recto");
			
		    okRecto2.addActionListener(new ActionListener() {		
				@Override
				public void actionPerformed(ActionEvent e) {
					joueur.getStack().add(joueur.getOffre().getRecto());
					joueur.getOffre().setRecto(null);
					joueur.getOffre().setOffreSuffisante(false);
					joueurPioche = joueur;
					setVisible(false);
				}
					
			});
		    final JButton okVerso2 = new JButton("Prendre carte verso");
		    
		    okVerso2.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					joueur.getStack().add(joueur.getOffre().getVerso());
					joueur.getOffre().setVerso(null);
					joueur.getOffre().setOffreSuffisante(false);
					joueurPioche = joueur;
					setVisible(false);
				}
					
			});
			offrePropre.add(pseudoJ1);
			offrePropre.add(vueRectoPropre.getImage());
			offrePropre.add(okRecto2);
			offrePropre.add(okVerso2);
			offreDisponible.add(offrePropre);
		}
		
		
	    //fenetre.add(main, BorderLayout.NORTH);
	    fenetre.add(pseudo, BorderLayout.NORTH);
	    fenetre.add(question, BorderLayout.SOUTH);
		fenetre.add(offreDisponible);
		
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Gets the joueur pioche.
	 *
	 * @return the joueur pioche
	 */
	public Joueur getJoueurPioche() {
		return joueurPioche;
	}
	
	/**
	 * Sets the joueur pioche.
	 *
	 * @param joueurPioche the new joueur pioche
	 */
	public void setJoueurPioche(Joueur joueurPioche) {
		this.joueurPioche = joueurPioche;
	}
	
}
