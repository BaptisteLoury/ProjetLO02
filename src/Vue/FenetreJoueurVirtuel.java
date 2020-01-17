/*
 * 
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

public class FenetreJoueurVirtuel extends JDialog {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The strategie. */
	private JComboBox<String> strategie = new JComboBox<String>();
	
	/** The string strategie. */
	private String stringStrategie;
	
	/** The fenetre. */
	private JPanel fenetre;
	
	/** The ok. */
	private JButton ok;
	
	/**
	 * Instantiates a new fenetre joueur virtuel.
	 *
	 * @param parent the parent
	 * @param title the title
	 * @param modal the modal
	 */
	public FenetreJoueurVirtuel(JFrame parent, String title, boolean modal) {
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
	
	    
		 JLabel nomStrategie = new JLabel("Avec quelle strategie ce joueur virtuel jouera ?");
		 strategie.setPreferredSize(new Dimension(100, 20));
		 strategie.addItem("Avancee");
		 strategie.addItem("Basique");
	    
	    ok = new JButton("Ok");
	    ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				stringStrategie = (String)strategie.getSelectedItem();
			    setVisible(false);
			}
			
		});
	    
	    fenetre.add(nomStrategie);
	    fenetre.add(strategie);
	    fenetre.add(ok);
	    
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Gets the string strategie.
	 *
	 * @return the string strategie
	 */
	public String getStringStrategie() {
		return stringStrategie;
	}
	
	/**
	 * Gets the strategie.
	 *
	 * @return the strategie
	 */
	public JComboBox<String> getStrategie() {
		return strategie;
	}
	
	/**
	 * Sets the strategie.
	 *
	 * @param strategie the new strategie
	 */
	public void setStrategie(JComboBox<String> strategie) {
		this.strategie = strategie;
	}
	
	/**
	 * Sets the string strategie.
	 *
	 * @param stringStrategie the new string strategie
	 */
	public void setStringStrategie(String stringStrategie) {
		this.stringStrategie = stringStrategie;
	}
	
	
}