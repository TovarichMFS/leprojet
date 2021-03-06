/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.midi.ControllerEventListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.MatierePremiere;
import modele.Produit;
import modele.Usine;
import others.CalculException;

/**
 * @author tovarich
 *
 */
public class MainWindow extends JFrame{
	
	private ControleurUsine u;
	private VueListeChaines lC;
	private VueListeElements lE;
	private VueListeElements lA;
	private VueListeStockages lS;

	/**
	 * 
	 */
	public MainWindow() {
		//Initialisation Controleur
		super("");
		this.u = new ControleurUsine(new Usine());
		try {
			this.u = new ControleurUsine(u.chargerCSV(0));
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Fichiers introuvables", "Erreur chargement", JOptionPane.WARNING_MESSAGE);
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erreur encodage fichier", "Erreur chargement", JOptionPane.WARNING_MESSAGE);
		}
//		u.addStock(new MatierePremiere("AA", "AH", 12, 1, "kg",1));
//		u.addStock(new Produit("AB", "AH", 0, "kg",10, 3));
//		ChaineDeProduction c = new ChaineDeProduction("CC", "CHAINE");
//		c.getEntrants().put("AA",new MatierePremiere("AA", "AH", 12, 1, "kg",2));
//		c.getSortants().put("AB",new Produit("AB", "AH", 1, "kg",10,1));
//		c.setNiveau(2);
//		u.addChaine(c);
		
		//Création fenêtre principale
		JPanel fenetre = new JPanel();
		BorderLayout bLf = new BorderLayout();
		fenetre.setLayout(bLf);
		
		//En-tête
		JLabel lTete = new JLabel("Usine");
		lTete.setBackground(new Color(204, 229, 255));
		lTete.setOpaque(true);
		lTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lTete.setFont(new Font(getName(), Font.BOLD, 30));
		fenetre.add(lTete,BorderLayout.NORTH);
		
		//Contenu
		JPanel pContenu = new JPanel();
		GridLayout gL = new GridLayout(4, 1);
		pContenu.setLayout(gL);
		
		//Liste des stocks
		JPanel pStocks = new JPanel();
		BorderLayout bLe = new BorderLayout();
		pStocks.setLayout(bLe);
		
		JPanel pTeteStocks=  new JPanel();
		GridLayout fLTS = new GridLayout(1,3);
		pTeteStocks.setLayout(fLTS);
		JLabel lStocks = new JLabel("Stocks");
		pTeteStocks.setBackground(new Color(224, 224, 224));
		pTeteStocks.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lStocks.setFont(new Font(getName(), Font.BOLD, 18));
		pTeteStocks.add(lStocks);
		JPanel pFillS = new JPanel();
		pFillS.setBackground(new Color(224, 224, 224));
		pTeteStocks.add(pFillS);
		JButton bAddStock = new JButton("Ajouter");
		bAddStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VueElement vAddE = new VueElementStock(null,u);
				vAddE.show();
			}
		});
		pTeteStocks.add(bAddStock);
		pStocks.add(pTeteStocks,BorderLayout.NORTH);
		
		lE = new VueListeElementsUsine(u.getStocks(),u,0);
		pStocks.add(lE,BorderLayout.CENTER);
		JScrollPane spStock = new JScrollPane(pStocks,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pContenu.add(spStock);
		
		//Liste des Chaines de production
		JPanel pChaines = new JPanel();
		BorderLayout bLc = new BorderLayout();
		pChaines.setLayout(bLc);
		
		JPanel pTeteChaines = new JPanel();
		GridLayout fLTC = new GridLayout(1,3);
		pTeteChaines.setLayout(fLTC);
		JLabel lChaines = new JLabel("Chaines de production");
		pTeteChaines.setBackground(new Color(224, 224, 224));
		pTeteChaines.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lChaines.setFont(new Font(getName(), Font.BOLD, 18));
		pTeteChaines.add(lChaines);
		JPanel pFillC = new JPanel();
		pFillC.setBackground(new Color(224, 224, 224));
		pTeteChaines.add(pFillC);
		JButton bAddChaine = new JButton("Ajouter");
		bAddChaine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VueChaine vAddC = new VueChaine(null,u);
				vAddC.show();	
			}
		});
		pTeteChaines.add(bAddChaine);
		pChaines.add(pTeteChaines,BorderLayout.NORTH);
		
		lC = new VueListeChaines(u.getChaines(),u);
		pChaines.add(lC,BorderLayout.CENTER);
		JScrollPane spChaine = new JScrollPane(pChaines,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pContenu.add(spChaine);
		
		fenetre.add(pContenu,BorderLayout.CENTER);
		
		//Liste des stockages
		JPanel pStockages = new JPanel();
		BorderLayout bLs = new BorderLayout();
		pStockages.setLayout(bLs);
		
		JPanel pTeteStockages =  new JPanel();
		GridLayout fLTSt = new GridLayout(1,3);
		pTeteStockages.setLayout(fLTSt);
		JLabel lStockages = new JLabel("Stockages");
		pTeteStockages.setBackground(new Color(224, 224, 224));
		pTeteStockages.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lStockages.setFont(new Font(getName(), Font.BOLD, 18));
		pTeteStockages.add(lStockages);
		JPanel pFillSt = new JPanel();
		pFillSt.setBackground(new Color(224, 224, 224));
		pTeteStockages.add(pFillSt);
		JButton bAddStockage = new JButton("Ajouter");
		bAddStockage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VueStockage vAddS = new VueStockage(null,u);
				vAddS.show();
			}
		});
		pTeteStockages.add(bAddStockage);
		pStockages.add(pTeteStockages,BorderLayout.NORTH);
		
		lS = new VueListeStockages(u.getStockages(),u);
		pStockages.add(lS,BorderLayout.CENTER);
		JScrollPane spStockage = new JScrollPane(pStockages,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pContenu.add(spStockage);
		
		//Liste d'achats
		JPanel pAchats = new JPanel();
		lA = new VueListeElementsUsine(u.getListeAchats(),u,3);
		BorderLayout bLa = new BorderLayout();
		pAchats.setLayout(bLa);
		JLabel lAchats = new JLabel("Liste d'Achats");
		lAchats.setBackground(new Color(224, 224, 224));
		lAchats.setOpaque(true);
		lAchats.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lAchats.setFont(new Font(getName(), Font.BOLD, 18));
		pAchats.add(lAchats,BorderLayout.NORTH);
		pAchats.add(lA,BorderLayout.CENTER);
		pContenu.add(pAchats);
		
		fenetre.add(pContenu,BorderLayout.CENTER);
		
		//Production
		JPanel pBas = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBas.setLayout(fLb);
		pBas.setBackground(new Color(224, 224, 224));
		pBas.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JButton bProd = new JButton("Prévisions");
		bProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VuePrevisionSemaine vPS = new VuePrevisionSemaine(u);
				vPS.show();
				
			}
		});
		pBas.add(bProd);
		JButton bActu = new JButton("Actualiser");
		bActu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pChaines.remove(lC);
				lC = new VueListeChaines(u.getChaines(),u);
				lC.revalidate();
				lC.repaint();
				pChaines.add(lC,BorderLayout.CENTER);
				
				pStocks.remove(lE);
				lE = new VueListeElementsUsine(u.getStocks(),u,0);
				lE.revalidate();
				lE.repaint();
				pStocks.add(lE,BorderLayout.CENTER);
				
				pStockages.remove(lS);
				lS = new VueListeStockages(u.getStockages(), u);
				lS.revalidate();
				lS.repaint();
				pStockages.add(lS, BorderLayout.CENTER);
				
				pAchats.remove(lA);
				lA = new VueListeElementsUsine(u.getListeAchats(),u,3);
				lA.revalidate();
				lA.repaint();
				pAchats.add(lA,BorderLayout.CENTER);
				pack();
				
			}
		});
		pBas.add(bActu);
		fenetre.add(pBas, BorderLayout.SOUTH);
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem jmi1 = new JMenuItem("Charger CSV");
		jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(null, "Charger un CSV va écraser les données actuelles.\nVoulez-vous continuer?","Charger CSV", JOptionPane.YES_NO_OPTION);
				if(choix==JOptionPane.YES_OPTION) {
					try {
						u = new ControleurUsine(u.chargerCSV(0));
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Fichiers introuvables", "Erreur chargement", JOptionPane.WARNING_MESSAGE);
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Erreur encodage fichier", "Erreur chargement", JOptionPane.WARNING_MESSAGE);
					}
					pChaines.remove(lC);
					lC = new VueListeChaines(u.getChaines(),u);
					lC.revalidate();
					lC.repaint();
					pChaines.add(lC,BorderLayout.CENTER);
					
					pStocks.remove(lE);
					lE = new VueListeElementsUsine(u.getStocks(),u,0);
					lE.revalidate();
					lE.repaint();
					pStocks.add(lE,BorderLayout.CENTER);
					
					pack();
				}
			}
		});
		JMenuItem jmi2 = new JMenuItem("Sauvegarder CSV");
		jmi2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(null, "Sauvegarder le CSV va écraser le fichier précédent.\nVoulez-vous continuer?","Sauvegarder CSV",JOptionPane.YES_NO_OPTION);
				if(choix==JOptionPane.YES_OPTION) {
					u.saveCSV(u,0);
					JOptionPane.showMessageDialog(null, "Sauvegarde effectuée", "Sauvegarde CSV", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menu.add(jmi1);
		menu.addSeparator();
		menu.add(jmi2);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
	    add(fenetre, "Center");
	    this.pack();
	    setDefaultCloseOperation(3);
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	

}
