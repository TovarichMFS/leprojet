/**
 * 
 */
package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurElement;
import controleur.ControleurUsine;
import modele.Element;

/**
 * @author tovarich
 *
 */
public abstract class VueListeElements extends JPanel{
	private HashMap<String, Element> listeE;

	/**
	 * 
	 */
	public VueListeElements(HashMap<String, Element> listeE,int option) {
		super();
		this.listeE = listeE;
		if(this.listeE.isEmpty()) {
			this.add(new JLabel("La liste est vide!"));
		}else {
			BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
			this.setLayout(bl);
			JPanel blocLabel = new JPanel();
			GridLayout gLabel = new GridLayout(1, 5);
			blocLabel.setLayout(gLabel);
			JLabel lCode = new JLabel("Code");
			JLabel lNom = new JLabel("Nom");
			JLabel lQuantite = new JLabel("Quantité");
			JLabel lAchat = new JLabel("Prix d'achat");
			JLabel lVente = new JLabel("Prix de vente");
			blocLabel.add(lCode);
			blocLabel.add(lNom);
			blocLabel.add(lQuantite);
			blocLabel.add(lAchat);
			blocLabel.add(lVente);
			blocLabel.add(new JLabel(" "));
			this.add(blocLabel);
		}
	}

}
