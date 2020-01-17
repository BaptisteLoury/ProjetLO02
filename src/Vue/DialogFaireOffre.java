/*
 *  Cette fenetre permet de choisir quelle carte mettre en recto au debut de chaque tour.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Cartes;
import modele.Joueur;

public class DialogFaireOffre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Le joueur. */
	private Joueur joueur;
	
	/** Le carte A jouer. */
	private LinkedList<Cartes> carteAJouer;
	
	/** Le recto. */
	private Cartes recto;
	
	/** Le verso. */
	private Cartes verso;
	
	/**
	 * Instantiates a new dialog faire offre.
	 *
	 * @param parent le parent
	 * @param title le title
	 * @param modal le modal
	 * @param joueur le joueur
	 */
	public DialogFaireOffre(JFrame parent, String title, boolean modal, Joueur joueur) {
		super(parent, title, modal);
		this.joueur = joueur;
		this.carteAJouer = new LinkedList<>();
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}
	
	/**
	 * Inits le.
	 */
	public void init() {
		JPanel fenetre = new JPanel();
		fenetre.setLayout(new BorderLayout(0, 1));
		
	
		JLabel pseudo = new JLabel("Offre de " +joueur.getPseudo());
		JLabel question = new JLabel("Quelle carte mettez-vous en recto ?");
		
		
		JPanel carteDroite = new JPanel();
		carteDroite.setLayout(new BorderLayout());
		VueCartes vcd = new VueCartes(joueur.getMain().getFirst());
		carteDroite.add(vcd.getImage(), BorderLayout.NORTH);
		
		JPanel carteGauche = new JPanel();
		carteGauche.setLayout(new BorderLayout());
		VueCartes vcg = new VueCartes(joueur.getMain().getLast());
		carteGauche.add(vcg.getImage(), BorderLayout.NORTH);
		
	    final JButton okDroite = new JButton("Carte de droite");
	    carteDroite.add(okDroite, BorderLayout.SOUTH);
	    okDroite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				recto = joueur.getMain().getFirst();
				verso = joueur.getMain().getLast();
				setVisible(false);
			}
				
		});
	    
	    final JButton okGauche = new JButton("Carte de gauche");
	    carteGauche.add(okGauche, BorderLayout.SOUTH);
	    okGauche.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				recto = joueur.getMain().getLast();
				verso = joueur.getMain().getFirst();
				setVisible(false);
			}
				
		});
		
	    //fenetre.add(main, BorderLayout.NORTH);
	    fenetre.add(pseudo, BorderLayout.NORTH);
	    fenetre.add(question, BorderLayout.SOUTH);
		fenetre.add(carteDroite, BorderLayout.EAST);
		fenetre.add(carteGauche, BorderLayout.WEST);
		
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Gets le recto.
	 *
	 * @return le recto
	 */
	public Cartes getRecto() {
		return recto;
	}
	
	/**
	 * Sets le recto.
	 *
	 * @param recto le new recto
	 */
	public void setRecto(Cartes recto) {
		this.recto = recto;
	}
	
	/**
	 * Gets le verso.
	 *
	 * @return le verso
	 */
	public Cartes getVerso() {
		return verso;
	}
	
	/**
	 * Sets le verso.
	 *
	 * @param verso le new verso
	 */
	public void setVerso(Cartes verso) {
		this.verso = verso;
	}
	
}
