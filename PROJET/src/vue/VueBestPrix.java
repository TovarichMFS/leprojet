/**
 * 
 */
package vue;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author tovarich
 *
 */
public class VueBestPrix extends JPanel {

	/**
	 * 
	 */
	public VueBestPrix(HashMap<String,String[]> bestprix) {
		if(bestprix.isEmpty()) {
			this.add(new JLabel("La liste est vide!"));
		}else {
			BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
			this.setLayout(bl);
			JPanel blocLabel = new JPanel();
			GridLayout gLabel = new GridLayout(1, 8);
			blocLabel.setLayout(gLabel);
			JLabel lElement = new JLabel("Elément:");
			JLabel lPrix = new JLabel("Prix d'achat:");
			JLabel lSemaine = new JLabel("N° semaine:");
			blocLabel.add(lElement);
			blocLabel.add(lPrix);
			blocLabel.add(lSemaine);
			this.add(blocLabel);
			JPanel pContenu = new JPanel();
			pContenu.setLayout(new GridLayout(bestprix.size(),1));
			for (String key : bestprix.keySet()) {
				JPanel pLigne = new JPanel();
				pLigne.setLayout(new GridLayout(1, 3));
				JTextField tElement = new JTextField(key);
				tElement.setEditable(false);
				JTextField tPrix = new JTextField(bestprix.get(key)[0]+"€");
				tPrix.setEditable(false);
				JTextField tSemaine = new JTextField(bestprix.get(key)[1]);
				tSemaine.setEditable(false);
				pLigne.add(tElement);
				pLigne.add(tPrix);
				pLigne.add(tSemaine);
				
				pContenu.add(pLigne);
			}
			this.add(pContenu);
		}
		
	}

}
