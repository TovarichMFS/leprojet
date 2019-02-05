/**
 * 
 */
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controleur.ControleurUsine;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;

/**
 * @author tovarich
 *
 */
public class VueElementStock extends VueElement {

	/**
	 * @param e
	 */
	public VueElementStock(Element e, ControleurUsine u) {
		super(e);
		this.bAjout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String code = tCode.getText();
				String nom = tNom.getText();
				String unite = tUnite.getText();
				double quantite,achat,vente;
				try {
					quantite = Double.parseDouble(tQuantite.getText());
					achat = Double.parseDouble(tAchat.getText());
					vente = Double.parseDouble(tVente.getText());
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(code!="" && nom!="" && quantite>=0 && unite!="" && achat>=0 && vente>=0) {
					if(achat !=0 && vente==0) {
						u.addStock(new MatierePremiere(code, nom, achat, quantite, unite));
					}else {
						u.addStock(new Produit(code, nom,achat, quantite, unite, vente));
					}
					JOptionPane.showMessageDialog(null, "Elément ajouté!", "Elément ajouté", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		this.bSuppr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cet élément?","Supprimer élément",JOptionPane.YES_NO_OPTION);
				if(choix==JOptionPane.YES_OPTION) {
					u.rmStock(cE.getCode());
					JOptionPane.showMessageDialog(null, "Elément supprimé!","Supprimer élément",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				}
			}
		});
	}

}
