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

import controleur.ControleurElement;
import controleur.ControleurUsine;
import modele.Element;

/**
 * @author tovarich
 *
 */
public class VueListeElementsUsine extends VueListeElements {

	/**
	 * @param listeE
	 * @param u
	 * @param option
	 */
	public VueListeElementsUsine(HashMap<String, Element> listeE, ControleurUsine u, int option) {
		super(listeE, option);
		for (String key : listeE.keySet()) {
			ControleurElement cE = new ControleurElement(listeE.get(key));
			JPanel blocElement = new JPanel();
			GridLayout gL = new GridLayout(1, 8);
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
			JTextField tfDemande = new JTextField(cE.getDemande()+"");
			tfDemande.setEditable(false);
			JTextField tfStockage = new JTextField(cE.getStockage());
			tfStockage.setEditable(false);
			blocElement.add(tfCode);
			blocElement.add(tfNom);
			blocElement.add(tfQuantite);
			blocElement.add(tfAchat);
			blocElement.add(tfVente);
			blocElement.add(tfDemande);
			blocElement.add(tfStockage);
			JButton bDetails = new JButton(">");
			bDetails.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					VueElement ve;
					if(option==0)
						ve = new VueElementStock(listeE.get(key),u);
					else
						ve = new VueElementAchat(listeE.get(key));
					ve.show();
				}
			});
			blocElement.add(bDetails);
			this.add(blocElement);
		}
	}

}
