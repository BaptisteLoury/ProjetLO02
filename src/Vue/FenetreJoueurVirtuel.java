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
public class FenetreJoueurVirtuel extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> strategie = new JComboBox<String>();
	private String stringStrategie;
	private JPanel fenetre;
	private JButton ok;
	
	public FenetreJoueurVirtuel(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		//this.creationJoueur();
	}
	public void init() {

		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
	
	    
		 JLabel nomStrategie = new JLabel("Avec quelle stratégie joueront les joueurs virtuels ?");
		 strategie.setPreferredSize(new Dimension(100, 20));
		 strategie.addItem("Avancee");
		 strategie.addItem("Basique");
	    
	    ok = new JButton("Ok");
	    ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				
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
	public String getStringStrategie() {
		return stringStrategie;
	}
	public void setStringStrategie(String stringStrategie) {
		this.stringStrategie = stringStrategie;
	}
	
	
}