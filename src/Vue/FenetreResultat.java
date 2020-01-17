/*
 *
 */
package Vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import modele.Joueur;
import modele.Partie;

public class FenetreResultat extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** La fenetre. */
	private JPanel fenetre;
	//private JPanel fenetreJoueur;
	
	/** Le ok. */
	private JButton ok;

	
	/**
	 * Instantiates a new fenetre resultat.
	 *
	 * @param parent le parent
	 * @param title le title
	 * @param modal le modal
	 */
	public FenetreResultat(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		//this.creationJoueur();
	}
	
	/**
	 * Inits le.
	 */
	public void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new BorderLayout());
	
	  JLabel message = new JLabel("Résultats de la partie");
	  JPanel result = new JPanel();
	  fenetre.add(message, BorderLayout.NORTH);
	   Iterator<Joueur> itj = Partie.getJoueurs().iterator();
	   while (itj.hasNext()) {
		   Joueur j = itj.next();
		   JLabel scoreJ = new JLabel(j.getPseudo() +" : "+j.getScoreFinal()+"pts");
		   result.add(scoreJ);
	   }
	   
	   fenetre.add(result);
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