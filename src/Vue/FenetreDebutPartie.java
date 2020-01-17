
	/**
	 * Cette fenêtre permet d'initialiser la partie avec :
	 * <ul>
	 *<li>le nombre de joueurs </li>
	 *<li>le nombre de joueurs humains</li>
	 *<li>Le choix d'une extension ou d'une variante</li> 

	 * </ul>
	 */
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


public class FenetreDebutPartie extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The nom. */
	private JTextArea nom;
	
	
	
	/** The fenetre. */
	private JPanel fenetre;
	//private JPanel fenetreJoueur;
	
	/** The ok. */
	private JButton ok;
	
	/** The ok 2. */
	private JButton ok2;
	
	
	/** The nb total. */
	private int nbTotal;
	
	/** The nb reel. */
	private int nbReel;
	
	/** The choix nb total. */
	private JComboBox<Integer> choixNbTotal = new JComboBox<Integer>();
	
	/** The choix nb reel. */
	private JComboBox<Integer> choixNbReel = new JComboBox<Integer>();
	

	
	/**
	 * Instantiates a new fenetre debut partie.
	 *
	 * @param parent the parent
	 * @param title the title
	 * @param modal the modal
	 */
	public FenetreDebutPartie(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		//this.creationJoueur();
	}
	
	/**
	 * Inits the.
	 */
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
	
	/**
	 * Creation joueur.
	 */
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
	
	/**
	 * Gets the nb total.
	 *
	 * @return the nb total
	 */
	public int getNbTotal() {
		return nbTotal;
	}
	
	/**
	 * Sets the nb total.
	 *
	 * @param nbTotal the new nb total
	 */
	public void setNbTotal(int nbTotal) {
		this.nbTotal = nbTotal;
	}
	
	/**
	 * Gets the nb reel.
	 *
	 * @return the nb reel
	 */
	public int getNbReel() {
		return nbReel;
	}
	
	/**
	 * Sets the nb reel.
	 *
	 * @param nbReel the new nb reel
	 */
	public void setNbReel(int nbReel) {
		this.nbReel = nbReel;
	}
	
}
