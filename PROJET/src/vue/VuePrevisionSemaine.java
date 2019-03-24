/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.Element;
import modele.Stockage;
import modele.Usine;
import others.CalculException;
import others.StockageException;

/**
 * @author tovarich
 *
 */
public class VuePrevisionSemaine extends JFrame {
	private ControleurUsine u;
	private VueListeElements lE;
	private VueListeElements lA;
	private VueResultatProduction vRP;
	private VueListeChaines lC;
	private VueListeStockages lS;
	private HashMap<String,String[]> bestPrix;
	private VueBestPrix vBest;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public VuePrevisionSemaine(ControleurUsine u) {
		ControleurUsine nU = new ControleurUsine(new Usine());
		this.bestPrix = new HashMap<String,String[]>();
		nU.setListeAchats((HashMap<String, Element>) u.getListeAchats().clone());
		nU.setChaines((ArrayList<ChaineDeProduction>) u.getChaines().clone());
		nU.setStocks((HashMap<String, Element>) u.getStocks().clone());
		nU.setStockages((HashMap<String, Stockage>) u.getStockages().clone());
		
		JPanel fenetre = new JPanel();
		BorderLayout bLf = new BorderLayout();
		fenetre.setLayout(bLf);
		
		//TETE
		JPanel pTete = new JPanel();
		FlowLayout fLt = new FlowLayout();
		pTete.setLayout(fLt);
		JLabel lTete = new JLabel("Prévision semaine: ");
		pTete.setBackground(new Color(204, 229, 255));
		pTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lTete.setFont(new Font(getName(), Font.BOLD, 30));
		
		SpinnerNumberModel  snm = new SpinnerNumberModel(0,0,99,1);
		JSpinner sSemaine = new JSpinner(snm);
		pTete.add(lTete);
		pTete.add(sSemaine);
		fenetre.add(pTete,BorderLayout.NORTH);
		
		//CONTENU
		JPanel pContenu = new JPanel();
		GridLayout gL = new GridLayout(5, 1);
		pContenu.setLayout(gL);
		
		//STOCKS
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
		pStocks.add(pTeteStocks,BorderLayout.NORTH);
		
		lE = new VueListeElementsUsine(nU.getStocks(),nU,0);
		pStocks.add(lE,BorderLayout.CENTER);
		JScrollPane spStock = new JScrollPane(pStocks,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
		
		//ACHATS
		JPanel pAchats = new JPanel();
		lA = new VueListeElementsUsine(nU.getListeAchats(),nU,3);
		BorderLayout bLa = new BorderLayout();
		pAchats.setLayout(bLa);
		JLabel lAchats = new JLabel("Liste d'Achats");
		lAchats.setBackground(new Color(224, 224, 224));
		lAchats.setOpaque(true);
		lAchats.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lAchats.setFont(new Font(getName(), Font.BOLD, 18));
		pAchats.add(lAchats,BorderLayout.NORTH);
		pAchats.add(lA,BorderLayout.CENTER);
		JScrollPane spAchats = new JScrollPane(pAchats,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pContenu.add(spAchats);
		
		//BEST PRIX
		JPanel pBestPrix = new JPanel();
		vBest = new VueBestPrix(bestPrix);
		BorderLayout bLbp = new BorderLayout();
		pBestPrix.setLayout(bLbp);
		JLabel lBestprix = new JLabel("Meilleurs prix:");
		lBestprix.setBackground(new Color(224, 224, 224));
		lBestprix.setOpaque(true);
		lBestprix.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lBestprix.setFont(new Font(getName(), Font.BOLD, 18));
		pBestPrix.add(lBestprix,BorderLayout.NORTH);
		pBestPrix.add(vBest,BorderLayout.CENTER);
		JScrollPane spBestPrix = new JScrollPane(pBestPrix,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pContenu.add(spBestPrix);
		
		fenetre.add(pContenu,BorderLayout.CENTER);
		
		//Prévision de la prod
		JPanel pProd = new JPanel();
		BorderLayout bLprod = new BorderLayout();
		pProd.setLayout(bLprod);
		vRP = new VueResultatProduction(nU, (Integer) sSemaine.getValue());
		pProd.add(vRP,BorderLayout.CENTER);
		JScrollPane spProd = new JScrollPane(pProd,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		fenetre.add(spProd,BorderLayout.EAST);
		
		//PIED
		JPanel pBas = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBas.setLayout(fLb);
		pBas.setBackground(new Color(224, 224, 224));
		pBas.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		/*JButton bProd = new JButton("Calculer Production");
		bProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VueResultatProduction vRP = new VueResultatProduction(nU,(Integer) sSemaine.getValue());
				vRP.show();			
			}
		});
		pBas.add(bProd);*/
		JButton bActu = new JButton("Changer semaine");
		bActu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					nU.setListeAchats((HashMap<String, Element>) u.getListeAchats().clone());
					nU.setChaines((ArrayList<ChaineDeProduction>) u.getChaines().clone());
					nU.setStocks((HashMap<String, Element>) u.getStocks().clone());
					nU.setStockages((HashMap<String, Stockage>) u.getStockages().clone());
					nU.calculerProductionSemaines(nU, (Integer) sSemaine.getValue(),bestPrix);
					nU.saveCSV(nU,(int) sSemaine.getValue());
					
				} catch (CalculException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Production impossible", JOptionPane.ERROR_MESSAGE);
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (StockageException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Production impossible", JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException e1) {
					System.err.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Stockage indisponible", "Erreur", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				pStocks.remove(lE);
				lE = new VueListeElementsUsine(nU.getStocks(),nU,0);
				lE.revalidate();
				lE.repaint();
				pStocks.add(lE,BorderLayout.CENTER);
				
				pStockages.remove(lS);
				lS = new VueListeStockages(nU.getStockages(), u);
				lS.revalidate();
				lS.repaint();
				pStockages.add(lS,BorderLayout.CENTER);
				
				pChaines.remove(lC);
				lC = new VueListeChaines(nU.getChaines(), u);
				lC.revalidate();
				lC.repaint();
				pChaines.add(lC,BorderLayout.CENTER);
				
				pAchats.remove(lA);
				lA = new VueListeElementsUsine(nU.getListeAchats(),nU,3);
				lA.revalidate();
				lA.repaint();
				pAchats.add(lA,BorderLayout.CENTER);
				
				pProd.remove(vRP);
				vRP = new VueResultatProduction(nU,(Integer) sSemaine.getValue());
				pProd.add(vRP,BorderLayout.EAST);
				
				pBestPrix.remove(vBest);
				vBest = new VueBestPrix(bestPrix);
				vBest.revalidate();
				vBest.repaint();
				pBestPrix.add(vBest, BorderLayout.CENTER);
				
				pack();
				
			}
		});
		pBas.add(bActu);
		fenetre.add(pBas, BorderLayout.SOUTH);
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem jmi = new JMenuItem("Sauvegarder CSV");
		jmi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(null, "Sauvegarder le CSV va écraser le fichier précédent.\nVoulez-vous continuer?","Sauvegarder CSV",JOptionPane.YES_NO_OPTION);
				if(choix==JOptionPane.YES_OPTION) {
					u.saveCSV(u,(Integer) sSemaine.getValue());
					JOptionPane.showMessageDialog(null, "Sauvegarde effectuée", "Sauvegarde CSV", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		menu.add(jmi);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
		
		add(fenetre, "Center");
	    this.pack();
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
