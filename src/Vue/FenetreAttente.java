package Vue;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;


import modele.Joueur;

public class FenetreAttente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fenetre;
	//private JPanel fenetreJoueur;
	
	private JButton ok;
	private Joueur joueur;

	
	public FenetreAttente(JFrame parent, String title, boolean modal,Joueur joueur) {
		super(parent, title, modal);
		this.joueur = joueur;
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		//this.creationJoueur();
	}
	public void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new BorderLayout());
		
	  JLabel message = new JLabel("Au tour du joueur "+joueur.getPseudo());
	    
	    
	    ok = new JButton("Ok");
	    ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				setVisible(false);
			}
			
		});
	    fenetre.add(message, BorderLayout.NORTH);
	    fenetre.add(ok, BorderLayout.SOUTH);
	    
	    //top.add(fenetre);
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
		
	}

}