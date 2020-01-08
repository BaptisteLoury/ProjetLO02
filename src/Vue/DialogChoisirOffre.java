package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import App.*;
import Joueurs.Joueur;

public class DialogChoisirOffre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Joueur joueur;
	private Joueur joueurPioche;
	private LinkedList<Cartes> carteAJouer;
	private Cartes carteChoisie;
	
	public DialogChoisirOffre(JFrame parent, String title, boolean modal, Joueur joueur) {
		super(parent, title, modal);
		this.joueur = joueur;
		this.carteAJouer = new LinkedList<>();
		//this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}
	public void init() {
		JPanel fenetre = new JPanel();
		fenetre.setLayout(new BorderLayout(0, 1));
		
		JLabel pseudo = new JLabel("Choix de " +joueur.getPseudo());
		JLabel question = new JLabel("Quelle carte mettez-vous en recto ?");
		
		JPanel offreDisponible = new JPanel();
		offreDisponible.setLayout(new BorderLayout());
		Iterator<Joueur> itj = Partie.getJoueurs().iterator();
		int nombreOffreSuffisante = 0;
		while (itj.hasNext()) {
			Joueur j = (Joueur) itj.next();
			if (j.getOffre().estOffreSuffisante() == true && j != joueur) {
				JPanel offreJ = new JPanel();
				JLabel pseudoJ = new JLabel("Offre du joueur "+j);
				VueCartes vueRectoj = new VueCartes(j.getOffre().getRecto());
				
				final JButton okRecto = new JButton("Prendre carte recto");
			    okRecto.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						joueur.getStack().add(j.getOffre().getRecto());
						j.getOffre().setRecto(null);
						j.getOffre().setOffreSuffisante(false);
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
						setVisible(false);
					}
						
				});
				nombreOffreSuffisante++;
				offreJ.add(pseudoJ);
				offreJ.add(vueRectoj.getImage());
				offreJ.add(okRecto);
				offreJ.add(okVerso);
				offreDisponible.add(offreJ);
			}
		}
		if (nombreOffreSuffisante == 0) {
			
		}
		
	    //fenetre.add(main, BorderLayout.NORTH);
	    fenetre.add(pseudo, BorderLayout.NORTH);
	    fenetre.add(question, BorderLayout.SOUTH);
		fenetre.add(offreDisponible);
		
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
	}
	public Joueur getJoueurPioche() {
		return joueurPioche;
	}
	public void setJoueurPioche(Joueur joueurPioche) {
		this.joueurPioche = joueurPioche;
	}
	
}
