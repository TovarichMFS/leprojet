/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

import controleur.ControleurUsine;
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

	/**
	 * 
	 */
	public MainWindow() {
		//Initialisation Controleur
		super("");
		this.u = new ControleurUsine(new Usine(""));
		//u.chargerCSV();
		
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
		GridLayout gL = new GridLayout(3, 1);
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
				VueElement vAddE = new VueElement(null);
				vAddE.show();
			}
		});
		pTeteStocks.add(bAddStock);
		pStocks.add(pTeteStocks,BorderLayout.NORTH);
		
		lE = new VueListeElements(u.getStocks());
		pStocks.add(lE,BorderLayout.CENTER);
		pContenu.add(pStocks);
		
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
				VueChaine vAddC = new VueChaine(null);
				vAddC.show();	
			}
		});
		pTeteChaines.add(bAddChaine);
		pChaines.add(pTeteChaines,BorderLayout.NORTH);
		
		lC = new VueListeChaines(u.getChaines());
		pChaines.add(lC,BorderLayout.CENTER);
		pContenu.add(pChaines);
		
		fenetre.add(pContenu,BorderLayout.CENTER);
		
		//Liste d'achats
		JPanel pAchats = new JPanel();
		VueListeElements lA = new VueListeElements(u.getListeAchats());
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
		pBas.setBackground(new Color(224, 224, 224));
		pBas.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		JButton bProd = new JButton("Calculer Production");
		bProd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double res = u.calculerProduction();
					JOptionPane.showMessageDialog(null, "La production aura un coût estimé à "+res+" €", "Production", JOptionPane.INFORMATION_MESSAGE);
				} catch (CalculException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Production impossible", JOptionPane.ERROR_MESSAGE);
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		pBas.add(bProd);
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
					u.chargerCSV();
					pChaines.remove(lC);
					lC = new VueListeChaines(u.getChaines());
					lC.revalidate();
					lC.repaint();
					pChaines.add(lC,BorderLayout.CENTER);
					
					pStocks.remove(lE);
					lE = new VueListeElements(u.getStocks());
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
					u.saveCSV();
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
	    setDefaultCloseOperation(3);
	    setResizable(false);
	    this.pack();
	}
	

}
