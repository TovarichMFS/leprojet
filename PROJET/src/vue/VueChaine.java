/**
 * 
 */
package vue;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import controleur.ControleurChaineDeProduction;
import modele.ChaineDeProduction;

/**
 * @author tovarich
 *
 */
public class VueChaine extends JPanel {
	private ControleurChaineDeProduction cC;

	/**
	 * 
	 */
	public VueChaine(ChaineDeProduction c) {
		this.cC = new ControleurChaineDeProduction(c);
		
	}

}
