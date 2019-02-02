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
import modele.Element;

/**
 * @author tovarich
 *
 */
public class VueListeElements extends JPanel{
	private HashMap<String, Element> listeE;

	/**
	 * 
	 */
	public VueListeElements(HashMap<String, Element> listeE) {
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
			for (String key : this.listeE.keySet()) {
				ControleurElement cE = new ControleurElement(this.listeE.get(key));
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
						VueElement ve = new VueElement(listeE.get(key));
						ve.show();
					}
				});
				blocElement.add(bDetails);
				this.add(blocElement);
			}
		}
	}

}
