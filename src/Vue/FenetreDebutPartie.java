package Vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import App.*;

public class FenetreDebutPartie extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextArea nom;
	
	
	
	private JPanel fenetre;
	//private JPanel fenetreJoueur;
	
	private JButton ok;
	private JButton ok2;
	
	
	private int nbTotal;
	private int nbReel;
	
	private JComboBox<Integer> choixNbTotal = new JComboBox<Integer>();
	
	private JComboBox<Integer> choixNbReel = new JComboBox<Integer>();
	

	
	public FenetreDebutPartie(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		//this.creationJoueur();
	}
	public void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
	   // JPanel top = new JPanel();
	    

	    
	    JLabel nb1 = new JLabel("Nombre de joueur :");
	    choixNbTotal.setPreferredSize(new Dimension(100, 20));
	    choixNbTotal.addItem(3);
	    choixNbTotal.addItem(4);
	    
	    JLabel nb2 = new JLabel("Nombre de joueur humain :");
	    choixNbReel.setPreferredSize(new Dimension(100, 20));
	    choixNbReel.addItem(0);
	    choixNbReel.addItem(1);
	    choixNbReel.addItem(2);
	    choixNbReel.addItem(3);
	    choixNbReel.addItem(4);
	    
	    
	    ok = new JButton("Ok");
	    ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (nbReel<=nbTotal) {
				    nbTotal = (int)choixNbTotal.getSelectedItem();
				    nbReel = (int)choixNbReel.getSelectedItem();
				    setVisible(false);
				}
				else {
					System.out.println("Erreur dans la saisie");
				}
			}
			
		});
	    
	    fenetre.add(nb1);
	    fenetre.add(choixNbTotal);
	    fenetre.add(nb2);
	    fenetre.add(choixNbReel);
	    fenetre.add(ok);
	    
	    //top.add(fenetre);
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
		
	}
	public void creationJoueur() {
		
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
	
	    
	    JLabel n = new JLabel("Nom du joueur");
	    nom = new JTextArea();
		nom.setEditable(true);

	    
	    ok2 = new JButton("Ok");
	    ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
	    
	    fenetre.add(n);
	    fenetre.add(nom);
	    fenetre.add(ok2);
	    
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
		
	}
	public int getNbTotal() {
		return nbTotal;
	}
	public void setNbTotal(int nbTotal) {
		this.nbTotal = nbTotal;
	}
	public int getNbReel() {
		return nbReel;
	}
	public void setNbReel(int nbReel) {
		this.nbReel = nbReel;
	}
	
}
