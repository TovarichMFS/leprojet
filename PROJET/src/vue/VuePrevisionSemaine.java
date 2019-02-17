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
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import modele.Usine;
import others.CalculException;

/**
 * @author tovarich
 *
 */
public class VuePrevisionSemaine extends JFrame {
	private ControleurUsine u;
	private VueListeElements lE;
	private VueListeElements lA;
	private VueResultatProduction vRP;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public VuePrevisionSemaine(ControleurUsine u) {
		ControleurUsine nU = new ControleurUsine(new Usine());
		nU.setListeAchats((HashMap<String, Element>) u.getListeAchats().clone());
		nU.setChaines((ArrayList<ChaineDeProduction>) u.getChaines().clone());
		nU.setStocks((HashMap<String, Element>) u.getStocks().clone());
		
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
		GridLayout gL = new GridLayout(2, 1);
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
		pContenu.add(pAchats);
		
		fenetre.add(pContenu,BorderLayout.CENTER);
		
		//Prévision de la prod
		JPanel pProd = new JPanel();
		BorderLayout bLprod = new BorderLayout();
		pProd.setLayout(bLprod);
		vRP = new VueResultatProduction(nU, (Integer) sSemaine.getValue());
		pProd.add(vRP,BorderLayout.CENTER);
		JScrollPane spProd = new JScrollPane(pProd,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
					ControleurUsine nU = new ControleurUsine(new Usine());
					nU.setListeAchats((HashMap<String, Element>) u.getListeAchats().clone());
					nU.setChaines((ArrayList<ChaineDeProduction>) u.getChaines().clone());
					nU.setStocks((HashMap<String, Element>) u.getStocks().clone());
					nU.calculerProductionSemaines(nU, (Integer) sSemaine.getValue());
					pStocks.remove(lE);
					lE = new VueListeElementsUsine(nU.getStocks(),nU,0);
					lE.revalidate();
					lE.repaint();
					pStocks.add(lE,BorderLayout.CENTER);
					
					pAchats.remove(lA);
					lA = new VueListeElementsUsine(nU.getListeAchats(),nU,3);
					lA.revalidate();
					lA.repaint();
					pAchats.add(lA,BorderLayout.CENTER);
					
					pProd.remove(vRP);
					vRP = new VueResultatProduction(nU,(Integer) sSemaine.getValue());
					pProd.add(vRP,BorderLayout.EAST);
					
					pack();
				} catch (CalculException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Production impossible", JOptionPane.ERROR_MESSAGE);
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		pBas.add(bActu);
		fenetre.add(pBas, BorderLayout.SOUTH);
		
		add(fenetre, "Center");
	    this.pack();
	    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
