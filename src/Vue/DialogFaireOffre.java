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

public class DialogFaireOffre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Joueur joueur;
	private LinkedList<Cartes> carteAJouer;
	private Cartes recto;
	private Cartes verso;
	
	public DialogFaireOffre(JFrame parent, String title, boolean modal, Joueur joueur) {
		super(parent, title, modal);
		this.joueur = joueur;
		this.carteAJouer = new LinkedList<>();
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}
	public void init() {
		JPanel fenetre = new JPanel();
		fenetre.setLayout(new BorderLayout(0, 1));
		
		/*JPanel main = new JPanel();
		main.setLayout(new GridLayout(0, 3));
		Iterator<Cartes> itc1 = joueur.getMain().iterator();
		while (itc1.hasNext()) {
			VueCartes vc = new VueCartes(itc1.next());
			main.add(vc.getImage());
		}*/
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
	public Cartes getRecto() {
		return recto;
	}
	public void setRecto(Cartes recto) {
		this.recto = recto;
	}
	public Cartes getVerso() {
		return verso;
	}
	public void setVerso(Cartes verso) {
		this.verso = verso;
	}
	
}
