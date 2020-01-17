/*
 *  Cette fenetre permet de rentrer les noms pour chaque joueur reel.
 */

package Vue;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FenetreCreerJoueur extends JDialog {

	/** Le Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** Le nom. */
	private JTextArea nom;
	
	/** Le fenetre. */
	private JPanel fenetre;
	
	/** Le ok. */
	private JButton ok;
	
	/**
	 * Instantiates a new fenetre creer joueur.
	 *
	 * @param parent le parent
	 * @param title le title
	 * @param modal le modal
	 */
	public FenetreCreerJoueur(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		
	}
	
	/**
	 * Inits le.
	 */
	public void init() {

		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
	
	    
	    JLabel n = new JLabel("Nom du joueur");
	    nom = new JTextArea();
		nom.setEditable(true);

	    
	    ok = new JButton("Ok");
	    ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				
			    setVisible(false);
			}
			
		});
	    
	    fenetre.add(n);
	    fenetre.add(nom);
	    fenetre.add(ok);
	    
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Gets le nom.
	 *
	 * @return le nom
	 */
	public JTextArea getNom() {
		return nom;
	}
	
	/**
	 * Sets le nom.
	 *
	 * @param nom le new nom
	 */
	public void setNom(JTextArea nom) {
		this.nom = nom;
	}
	
}
