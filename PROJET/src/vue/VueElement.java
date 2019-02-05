/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import controleur.ControleurElement;
import controleur.ControleurUsine;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;

/**
 * @author tovarich
 *
 */
public abstract class VueElement extends JFrame {

	protected ControleurElement cE;
	protected JButton bAjout, bSuppr;
	protected JTextField tCode,tNom,tQuantite,tUnite,tAchat,tVente;
	
	/**
	 * 
	 */
	public VueElement(Element e) {
		super();
		this.bSuppr = new JButton("Supprimer");

		this.bAjout = new JButton("Ajouter");
		JPanel fenetre = new JPanel();
		this.add(fenetre);
		this.cE = new ControleurElement(e);
		BorderLayout bL = new BorderLayout();
		fenetre.setLayout(bL);
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
		fenetre.add(lTete, BorderLayout.NORTH);
		
		JPanel pContenu = new JPanel();
		pContenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLc = new GridLayout(3, 1);
		pContenu.setLayout(gLc);
		
		JPanel pNom = new JPanel();
		pNom.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		FlowLayout fLn = new FlowLayout();
		pNom.setLayout(fLn);
		JLabel lNom = new JLabel("Nom:");
		this.tNom = new JTextField(10);
		this.tCode = new JTextField(5);
		if(e!=null)
			tNom.setText(this.cE.getNom());
		else {
			JLabel lCode = new JLabel("Code:");
			pNom.add(lCode);
			pNom.add(tCode);
		}
		pNom.add(lNom);
		pNom.add(tNom);
		pContenu.add(pNom);
		
		JPanel pQuantite = new JPanel();
		pQuantite.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		FlowLayout fLq = new FlowLayout();
		pNom.setLayout(fLq);
		JLabel lQuantite = new JLabel("Quantité:");
		this.tQuantite = new JTextField(5);
		Component lUnite;
		this.tUnite = new JTextField(10);
		if(e!=null) {
			tQuantite.setText(this.cE.getQuantite()+"");
			lUnite = new JLabel(this.cE.getUnite());
		}else {
			lUnite = tUnite;
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
		this.tAchat = new JTextField("0",4);
		JLabel lEuro = new JLabel("€");
		JLabel lVente = new JLabel("Prix de vente:");
		this.tVente = new JTextField("0",4);
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
		
		fenetre.add(pContenu, BorderLayout.CENTER);
		
		JPanel pBoutons = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBoutons.setLayout(fLb);
		JButton bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
			}
		});
		if(e!=null) {
			JButton bModif = new JButton("Modifier");
			pBoutons.add(bModif);
			pBoutons.add(this.bSuppr);
		}else {
			pBoutons.add(this.bAjout);
		}
		pBoutons.add(bAnnuler);
		
		fenetre.add(pBoutons, BorderLayout.SOUTH);
		this.setResizable(false);
		this.pack();
		
	}

}
