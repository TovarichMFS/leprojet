/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.sound.midi.ControllerEventListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
		JLabel lStocks = new JLabel("Stocks");
		lStocks.setBackground(new Color(224, 224, 224));
		lStocks.setOpaque(true);
		lStocks.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lStocks.setFont(new Font(getName(), Font.BOLD, 18));
		pStocks.add(lStocks,BorderLayout.NORTH);
		VueListeElements lE = new VueListeElements(u.getStocks());
		pStocks.add(lE,BorderLayout.CENTER);
		pContenu.add(pStocks);
		
		//Liste des Chaines de production
		JPanel pChaines = new JPanel();
		VueListeChaines lC = new VueListeChaines(u.getChaines());
		BorderLayout bLc = new BorderLayout();
		pChaines.setLayout(bLc);
		JLabel lChaines = new JLabel("Chaines de production");
		lChaines.setBackground(new Color(224, 224, 224));
		lChaines.setOpaque(true);
		lChaines.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lChaines.setFont(new Font(getName(), Font.BOLD, 18));
		pChaines.add(lChaines,BorderLayout.NORTH);
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
		JMenuItem jmi2 = new JMenuItem("Sauvegarder CSV");
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
