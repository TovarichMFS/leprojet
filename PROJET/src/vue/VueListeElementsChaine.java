/**
 * 
 */
package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurChaineDeProduction;
import controleur.ControleurElement;
import modele.Element;

/**
 * @author tovarich
 *
 */
public class VueListeElementsChaine extends VueListeElements {

	/**
	 * @param listeE
	 * @param u
	 * @param option
	 */
	public VueListeElementsChaine(HashMap<String, Element> listeE, ControleurChaineDeProduction c, int option) {
		super(listeE, option);
		for (String key : listeE.keySet()) {
			ControleurElement cE = new ControleurElement(listeE.get(key));
			JPanel blocElement = new JPanel();
			GridLayout gL = new GridLayout(1, 5);
			blocElement.setLayout(gL);
			JTextField tfCode = new JTextField(cE.getCode());
			tfCode.setEditable(false);
			JTextField tfNom = new JTextField(cE.getNom());
			tfNom.setEditable(false);
			JTextField tfQuantite = new JTextField(cE.getQuantite()+ " " + cE.getUnite());
			tfQuantite.setEditable(false);
			JTextField tfAchat;
			if(cE.getPrixAchat()==0) {
				tfAchat = new JTextField("");
				tfAchat.setEnabled(false);
			}else {
				tfAchat = new JTextField(cE.getPrixAchat()+" €");
			}
			tfAchat.setEditable(false);
			JTextField tfVente;
			if(cE.getPrixVente()==0) {
				tfVente = new JTextField("");
				tfVente.setEnabled(false);
			}else {
				tfVente = new JTextField(cE.getPrixVente()+" €");
			}
			tfVente.setEditable(false);
			blocElement.add(tfCode);
			blocElement.add(tfNom);
			blocElement.add(tfQuantite);
			blocElement.add(tfAchat);
			blocElement.add(tfVente);
			JButton bDetails = new JButton(">");
			bDetails.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VueElement ve;
					ve = new VueElementChaine(listeE.get(key),c,option);
					ve.show();
				}
			});
			blocElement.add(bDetails);
			this.add(blocElement);
		}
	}

}
