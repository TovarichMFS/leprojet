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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

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
		BorderLayout bL = new BorderLayout();
		this.setLayout(bL);
		JPanel pTete = new JPanel();
		GridLayout gLt = new GridLayout(2, 1);
		pTete.setLayout(gLt);
		pTete.setBackground(new Color(204, 229, 255));
		pTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JPanel pSTete = new JPanel();
		pSTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLst = new GridLayout(1, 3);
		pSTete.setLayout(gLst);
		pSTete.setBackground(new Color(204, 229, 255));
		JLabel lTitre = new JLabel("Chaine de Production\n");
		lTitre.setFont(new Font(getName(), Font.BOLD, 20));
		JLabel lCode = new JLabel(this.cC.getCode());
		JLabel lNom = new JLabel(this.cC.getNom());
		
		JPanel pNiveau = new JPanel();
		GridLayout gLn = new GridLayout(1, 2);
		pNiveau.setLayout(gLn);
		JLabel lNiveau = new JLabel("Niveau: ");
		SpinnerNumberModel sNM = new SpinnerNumberModel(this.cC.getNiveau(), 0, 99, 1);
		JSpinner sNiveau = new JSpinner(sNM);
		pNiveau.add(lNiveau);
		pNiveau.add(sNiveau);
		pSTete.add(lCode);
		pSTete.add(lNom);
		pSTete.add(pNiveau);
		pTete.add(lTitre);
		pTete.add(pSTete);
		this.add(pTete, BorderLayout.NORTH);
		
		JPanel pContenu = new JPanel();
		pContenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLc = new GridLayout(3,1);
		pContenu.setLayout(gLc);
		
		JPanel pEntrants = new JPanel();
		BorderLayout bLe = new BorderLayout();
		pEntrants.setLayout(bLe);
		JLabel lEntrants = new JLabel("Eléments entrants");
		lEntrants.setBackground(new Color(224, 224, 224));
		lEntrants.setOpaque(true);
		lEntrants.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lEntrants.setFont(new Font(getName(), Font.BOLD, 18));
		pEntrants.add(lEntrants,BorderLayout.NORTH);
		VueListeElements listeE = new VueListeElements(this.cC.getEntrants());
		pEntrants.add(listeE,BorderLayout.CENTER);
		
		JPanel pSortants = new JPanel();
		BorderLayout bLs = new BorderLayout();
		pSortants.setLayout(bLs);
		JLabel lSortants = new JLabel("Eléments sortants");
		lSortants.setBackground(new Color(224, 224, 224));
		lSortants.setOpaque(true);
		lSortants.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lSortants.setFont(new Font(getName(), Font.BOLD, 18));
		pSortants.add(lSortants,BorderLayout.NORTH);
		VueListeElements listeS = new VueListeElements(this.cC.getSortants());
		pSortants.add(listeS,BorderLayout.CENTER);
		
		pContenu.add(pEntrants);
		pContenu.add(pSortants);
		
		this.add(pContenu, BorderLayout.CENTER);
		
		JPanel pBoutons = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBoutons.setLayout(fLb);
		JButton bAnnuler = new JButton("Annuler");
		JButton bModif = new JButton("Modifier");
		JButton bSuppr = new JButton("Supprimer");
		pBoutons.add(bAnnuler);
		pBoutons.add(bModif);
		pBoutons.add(bSuppr);
		
		this.add(pBoutons, BorderLayout.SOUTH);
	}

}
