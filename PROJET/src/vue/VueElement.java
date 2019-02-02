/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import controleur.ControleurElement;
import modele.Element;

/**
 * @author tovarich
 *
 */
public class VueElement extends JPanel {

	private ControleurElement cE;
	
	/**
	 * 
	 */
	public VueElement(Element e) {
		this.cE = new ControleurElement(e);
		BorderLayout bL = new BorderLayout();
		this.setLayout(bL);
		JLabel lTete = new JLabel();
		if(e!=null) {
			lTete.setText("Elément "+this.cE.getCode());
		}else {
			lTete.setText("Ajouter un élément");
		}
		lTete.setFont(new Font(getName(), Font.BOLD, 20));
		lTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lTete.setBackground(new Color(204, 229, 255));
		lTete.setOpaque(true);
		this.add(lTete, BorderLayout.NORTH);
		
		JPanel pContenu = new JPanel();
		pContenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLc = new GridLayout(3, 1);
		pContenu.setLayout(gLc);
		
		JPanel pNom = new JPanel();
		pNom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		FlowLayout fLn = new FlowLayout();
		pNom.setLayout(fLn);
		JLabel lNom = new JLabel("Nom:");
		JTextField tNom = new JTextField("");
		if(e!=null)
			tNom.setText(this.cE.getNom());
		pNom.add(lNom);
		pNom.add(tNom);
		pContenu.add(pNom);
		
		JPanel pQuantite = new JPanel();
		pQuantite.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		FlowLayout fLq = new FlowLayout();
		pNom.setLayout(fLq);
		JLabel lQuantite = new JLabel("Quantité:");
		JTextField tQuantite = new JTextField(0);
		JLabel lUnite = new JLabel("");
		if(e!=null) {
			tQuantite.setText(this.cE.getQuantite()+"");
			lUnite.setText(this.cE.getUnite());
		}
		pQuantite.add(lQuantite);
		pQuantite.add(tQuantite);
		pQuantite.add(lUnite);
		pContenu.add(pQuantite);
		
		JPanel pPrix = new JPanel();
		pPrix.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		FlowLayout fLp = new FlowLayout();
		pNom.setLayout(fLp);
		JLabel lAchat = new JLabel("Prix d'achat:");
		JTextField tAchat = new JTextField(0);
		JLabel lEuro = new JLabel("€");
		JLabel lVente = new JLabel("Prix de vente:");
		JTextField tVente = new JTextField(0);
		if(e!=null) {
			tAchat.setText(this.cE.getPrixAchat()+"");
			tVente.setText(this.cE.getPrixVente()+"");
		}
		pPrix.add(lAchat);
		pPrix.add(tAchat);
		pPrix.add(lEuro);
		pPrix.add(lVente);
		pPrix.add(tVente);
		pPrix.add(lEuro);
		pContenu.add(pPrix);
		
		this.add(pContenu, BorderLayout.CENTER);
		
		JPanel pBoutons = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBoutons.setLayout(fLb);
		JButton bAnnuler = new JButton("Annuler");
		if(e!=null) {
			JButton bModif = new JButton("Modifier");
			JButton bSuppr = new JButton("Supprimer");
			pBoutons.add(bModif);
			pBoutons.add(bSuppr);
		}
		pBoutons.add(bAnnuler);
		
		this.add(pBoutons, BorderLayout.SOUTH);
		System.out.println("oui");
		
	}

}
