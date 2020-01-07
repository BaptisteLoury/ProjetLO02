package Vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
	
	private JButton ok;
	
	private int nbTotal;
	private int nbReel;
	
	private JComboBox<Integer> choixNbTotal = new JComboBox<Integer>();
	
	private JComboBox<Integer> choixNbReel = new JComboBox<Integer>();
	

	
	public FenetreDebutPartie(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
	}
	public void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
	    JPanel top = new JPanel();
	    
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
	    ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    nbTotal = (int)choixNbTotal.getSelectedItem();
			    nbReel = (int)choixNbReel.getSelectedItem();

			    System.out.println(nbTotal + " " + nbReel);
			    setVisible(false);
			    setVisible(false);
			}
		});
	    
	    fenetre.add(nb1);
	    fenetre.add(choixNbTotal);
	    fenetre.add(nb2);
	    fenetre.add(choixNbReel);
	    fenetre.add(ok);
	    
	    top.add(fenetre);
	    this.getContentPane().add(top);
		this.pack();
		
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
