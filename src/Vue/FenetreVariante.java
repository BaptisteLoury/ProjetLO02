package Vue;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreVariante extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Checkbox jeuBase;
	private Checkbox variante1;
	private Checkbox variante2;
	private JButton ok;
	private JPanel fenetre;
	private boolean extension;
	private JComboBox<String> choixExtension = new JComboBox<String>();

	public FenetreVariante(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.init();
		//this.creationJoueur();
	}
	
	public void init() {
		fenetre = new JPanel();
		fenetre.setLayout(new GridLayout(0, 2, 10, 10));
		
	    JLabel variante = new JLabel("Quelle version jouer :");
	    CheckboxGroup cbg = new CheckboxGroup();
	    add(jeuBase = new Checkbox("Jeu de base",cbg,true));
	    add(variante1 = new Checkbox("Variante 1",cbg,false));
	    add(variante2 = new Checkbox("Variante 2",cbg,false));
	    
	    JLabel nExtension = new JLabel("Voulez vous utiliser l'extension ?");
	    choixExtension.setPreferredSize(new Dimension(100, 20));
	    choixExtension.addItem("Oui");
	    choixExtension.addItem("Non");
	    
	    ok = new JButton("Ok");
	    ok.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				String reponse = (String)choixExtension.getSelectedItem();
				extension = false;
				switch (reponse) {
				case "Oui":
					extension = true;
				break;
				case "Non":
					extension = false;
				break;
				}
			    setVisible(false);
			}
			
		});
	    fenetre.add(variante);
	    fenetre.add(jeuBase);
	    fenetre.add(variante1);
	    fenetre.add(variante2);
	    fenetre.add(nExtension);
	    fenetre.add(choixExtension);
	    fenetre.add(ok);
	    
	    this.getContentPane().add(fenetre);
		this.pack();
		this.setVisible(true);
	}

	public Checkbox getJeuBase() {
		return jeuBase;
	}

	public void setJeuBase(Checkbox jeuBase) {
		this.jeuBase = jeuBase;
	}

	public Checkbox getVariante1() {
		return variante1;
	}

	public void setVariante1(Checkbox variante1) {
		this.variante1 = variante1;
	}

	public Checkbox getVariante2() {
		return variante2;
	}

	public void setVariante2(Checkbox variante2) {
		this.variante2 = variante2;
	}

	public boolean isExtension() {
		return extension;
	}

	public void setExtension(boolean extension) {
		this.extension = extension;
	}
	
	
}
