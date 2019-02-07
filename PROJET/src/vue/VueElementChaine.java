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
				int demande;
				try {
					quantite = Double.parseDouble(tQuantite.getText());
					achat = Double.parseDouble(tAchat.getText());
					vente = Double.parseDouble(tVente.getText());
					demande = (Integer)sDemande.getValue();
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(code!="" && nom!="" && quantite>=0 && unite!="" && achat>=0 && vente>=0 && demande>=0) {
					Element nE;
					if(achat !=0 && vente==0) {
						nE = new MatierePremiere(code, nom, achat, quantite, unite, demande);
					}else {
						nE = new Produit(code, nom,achat, quantite, unite, vente, demande);
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
this.bModif.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier cet élément?","Modifier élément",JOptionPane.YES_NO_OPTION);
				if(choix==JOptionPane.YES_OPTION) {
					String nom = tNom.getText();
					double quantite,achat,vente;
					int demande;
					try {
						quantite = Double.parseDouble(tQuantite.getText());
						achat = Double.parseDouble(tAchat.getText());
						vente = Double.parseDouble(tVente.getText());
						demande = (Integer)sDemande.getValue();
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur modification", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(nom!="" && quantite>=0 && achat>=0 && vente>=0 && demande>=0) {
						Element nE;
						if(achat !=0 && vente==0) {
							nE = new MatierePremiere(cE.getCode(), nom, achat, quantite, cE.getUnite(), demande);
						}else {
							nE = new Produit(cE.getCode(), nom,achat, quantite, cE.getUnite(), vente, demande);
						}
						if(option==1)
							c.addEntrant(nE);
						else
							c.addSortant(nE);
						JOptionPane.showMessageDialog(null, "Elément modifié!", "Elément modifié", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}

}
