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
		u.chargerCSV();
		
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
				JFrame frame = new JFrame();
				VueElement vAddE = new VueElement(null);
				frame.add(vAddE);
				frame.setResizable(false);
				frame.pack();
				frame.show();
				System.out.println("lol");
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
		
		//Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Options");
		JMenuItem jmi1 = new JMenuItem("Charger CSV");
		jmi1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = JOptionPane.showConfirmDialog(null, "Charger un CSV va écraser les données actuelles.\nVoulez-vous continuer?","Charger CSV", JOptionPane.YES_NO_OPTION);
				if(choix==JOptionPane.YES_OPTION) {
					System.out.println(u.toString());
					u.chargerCSV();
					System.out.println(u.toString());
					lC = new VueListeChaines(u.getChaines());
					lC.revalidate();
					lC.repaint();
					
					lE = new VueListeElements(u.getStocks());
					lE.revalidate();
					lE.repaint();
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
