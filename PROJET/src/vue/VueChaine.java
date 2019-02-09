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
import java.util.HashMap;

import javax.swing.AbstractButton;
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

import controleur.ControleurChaineDeProduction;
import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.MatierePremiere;
import modele.Produit;

/**
 * @author tovarich
 *
 */
public class VueChaine extends JFrame {
	private ControleurChaineDeProduction cC;
	private VueListeElements listeE, listeS;

	/**
	 * 
	 */
	public VueChaine(ChaineDeProduction c,ControleurUsine u) {
		super();
		JPanel fenetre = new JPanel();
		this.add(fenetre);
		this.cC = new ControleurChaineDeProduction(c);
		BorderLayout bL = new BorderLayout();
		fenetre.setLayout(bL);
		JPanel pTete = new JPanel();
		GridLayout gLt = new GridLayout(2, 1);
		pTete.setLayout(gLt);
		pTete.setBackground(new Color(204, 229, 255));
		pTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JLabel lTitre;
		if(c!=null)
			lTitre = new JLabel("Chaine de Production");
		else
			lTitre = new JLabel("AJouter une Chaine de Production");
		lTitre.setFont(new Font(getName(), Font.BOLD, 20));
		pTete.add(lTitre);
		
		JPanel pSTete = new JPanel();
		pSTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLst = new GridLayout(2, 3);
		pSTete.setLayout(gLst);
		pSTete.setBackground(new Color(204, 229, 255));

		JLabel lCode = new JLabel("Code");
		lCode.setFont(new Font(getName(), Font.BOLD, 12));
		JLabel lNom = new JLabel("Nom");
		lNom.setFont(new Font(getName(), Font.BOLD, 12));
		JLabel lNiveau = new JLabel("Niveau");
		lNiveau.setFont(new Font(getName(), Font.BOLD, 12));
		pSTete.add(lCode);
		pSTete.add(lNom);
		pSTete.add(lNiveau);
		
		Component cCode;
		JTextField tNom = new JTextField(12);
		SpinnerNumberModel sNM = new SpinnerNumberModel(0, 0, 999, 1);
		if(c!=null) {
			cCode = new JLabel(this.cC.getCode());
			tNom.setText(this.cC.getNom());
			sNM.setValue(this.cC.getNiveau());
		}else
			cCode = new JTextField(6);
		JSpinner sNiveau = new JSpinner(sNM);
		pSTete.add(cCode);
		pSTete.add(tNom);
		pSTete.add(sNiveau);
		
		pTete.add(pSTete);
		fenetre.add(pTete, BorderLayout.NORTH);
		
		JPanel pContenu = new JPanel();
		pContenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLc = new GridLayout(3,1);
		pContenu.setLayout(gLc);
		
		if(c!=null) {
			listeE = new VueListeElementsChaine(this.cC.getEntrants(),cC,1);
			listeS = new VueListeElementsChaine(this.cC.getSortants(),cC,2);
		}else {
			listeE = new VueListeElementsChaine(new HashMap<>(),cC,1);
			listeS = new VueListeElementsChaine(new HashMap<>(),cC,2);
		}
		
		JPanel pEntrants = new JPanel();
		BorderLayout bLe = new BorderLayout();
		pEntrants.setLayout(bLe);
		JPanel pTeteEntr = new JPanel();
		GridLayout gLte = new GridLayout(1, 3);
		pTeteEntr.setLayout(gLte);
		pTeteEntr.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JLabel lEntrants = new JLabel("Eléments entrants");
		lEntrants.setBackground(new Color(224, 224, 224));
		lEntrants.setOpaque(true);
		lEntrants.setFont(new Font(getName(), Font.BOLD, 18));
		pTeteEntr.add(lEntrants);
		JPanel pFillE = new JPanel();
		pFillE.setBackground(new Color(224, 224, 224));
		pTeteEntr.add(pFillE);
		JButton bAddEntrant = new JButton("Ajouter");
		bAddEntrant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VueElement vAddE = new VueElementChaine(null,cC,1);
				vAddE.show();	
			}
		});
		pTeteEntr.add(bAddEntrant);
		pEntrants.add(pTeteEntr,BorderLayout.NORTH);
		pEntrants.add(listeE,BorderLayout.CENTER);
		
		JPanel pSortants = new JPanel();
		BorderLayout bLs = new BorderLayout();
		pSortants.setLayout(bLs);
		JPanel pTeteSort = new JPanel();
		GridLayout gLts = new GridLayout(1, 3);
		pTeteSort.setLayout(gLts);
		pTeteSort.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JLabel lSortants = new JLabel("Eléments sortants");
		lSortants.setBackground(new Color(224, 224, 224));
		lSortants.setOpaque(true);
		lSortants.setFont(new Font(getName(), Font.BOLD, 18));
		pTeteSort.add(lSortants);
		JPanel pFillS = new JPanel();
		pFillS.setBackground(new Color(224, 224, 224));
		pTeteSort.add(pFillS);
		JButton bAddSortant = new JButton("Ajouter");
		bAddSortant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VueElement vAddE = new VueElementChaine(null,cC,2);
				vAddE.show();	
			}
		});
		pTeteSort.add(bAddSortant);
		pSortants.add(pTeteSort,BorderLayout.NORTH);
		pSortants.add(listeS,BorderLayout.CENTER);
		
		pContenu.add(pEntrants);
		pContenu.add(pSortants);
		
		fenetre.add(pContenu, BorderLayout.CENTER);
		
		JPanel pBoutons = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBoutons.setLayout(fLb);
		if(c!=null) {
			JButton bModif = new JButton("Modifier");
			bModif.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier cette chaine?","Modifier chaine",JOptionPane.YES_NO_OPTION);
					if(choix==JOptionPane.YES_OPTION) {
						String nom = tNom.getText();
						int niveau = (Integer)sNiveau.getValue();
						if(nom!="" && niveau>=0) {
							cC.setNom(nom);
							cC.changeNiveau(niveau);
							JOptionPane.showMessageDialog(null, "Chaine modifié!", "Chaine modifié", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur modification", JOptionPane.WARNING_MESSAGE);
						}
					}
					setVisible(false);
					dispose();
				}
			});
			JButton bSuppr = new JButton("Supprimer");
			bSuppr.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer cette chaine?","Supprimer chaine",JOptionPane.YES_NO_OPTION);
					if(choix==JOptionPane.YES_OPTION) {
						u.rmChaine(cC.getCode());
						JOptionPane.showMessageDialog(null, "Chaine supprimée!","Supprimer chaine",JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}
				}
			});
			pBoutons.add(bModif);
			pBoutons.add(bSuppr);
		}else {
			JButton bAJout = new JButton("Ajouter");
			bAJout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String code = ((JTextField) cCode).getText();
					String nom = tNom.getText();
					int niveau;
					try {
						niveau = (Integer)sNiveau.getValue();
					}catch(NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(code!="" && nom!="" && niveau>=0) {
						ChaineDeProduction c = new ChaineDeProduction(code, nom);
						c.setNiveau(niveau);
						u.addChaine(c);
						JOptionPane.showMessageDialog(null, "Chaine ajouté!", "Chaine ajouté", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			pBoutons.add(bAJout);
		}
		JButton bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
			}
		});
		
		pBoutons.add(bAnnuler);
		
		JButton bActualiser = new JButton("Actualiser");
		bActualiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pEntrants.remove(listeE);
				pSortants.remove(listeS);
				if(c!=null) {
					listeE = new VueListeElementsChaine(cC.getEntrants(),cC,1);
					listeS = new VueListeElementsChaine(cC.getSortants(),cC,2);
				}else {
					listeE = new VueListeElementsChaine(new HashMap<>(),cC,1);
					listeS = new VueListeElementsChaine(new HashMap<>(),cC,2);
				}
				listeE.revalidate();
				listeE.repaint();
				listeS.revalidate();
				listeS.repaint();
				pEntrants.add(listeE);
				pSortants.add(listeS);
				pack();
				
				
			}
		});
		
		pBoutons.add(bActualiser);
		
		fenetre.add(pBoutons, BorderLayout.SOUTH);
		this.setResizable(false);
		this.pack();
	}

}
