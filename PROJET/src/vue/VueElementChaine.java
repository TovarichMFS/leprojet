/**
 * 
 */
package vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controleur.ControleurChaineDeProduction;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;

/**
 * @author tovarich
 *
 */
public class VueElementChaine extends VueElement {

	/**
	 * @param e
	 */
	public VueElementChaine(Element e,ControleurChaineDeProduction c,int option) {
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
					Element nE;
					if(achat !=0 && vente==0) {
						nE = new MatierePremiere(code, nom, achat, quantite, unite);
					}else {
						nE = new Produit(code, nom,achat, quantite, unite, vente);
					}
					if(option==1)
						c.addEntrant(nE);
					else
						c.addSortant(nE);
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
					if(option==1)
						c.rmEntrant(cE.getCode());
					else
						c.rmSortant(cE.getCode());
					JOptionPane.showMessageDialog(null, "Elément supprimé!","Supprimer élément",JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				}
			}
		});
	}

}
