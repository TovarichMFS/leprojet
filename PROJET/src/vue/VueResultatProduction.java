/**
 * 
 */
package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import controleur.ControleurUsine;
import others.CalculException;

/**
 * @author tovarich
 *
 */
public class VueResultatProduction extends JFrame{

	/**
	 * 
	 */
	public VueResultatProduction(ControleurUsine u) {
		super();
		JPanel fenetre = new JPanel();
		BorderLayout bLf = new BorderLayout();
		fenetre.setLayout(bLf);
		JLabel lTete = new JLabel("Estimation de la Production");
		lTete.setBackground(new Color(204, 229, 255));
		lTete.setOpaque(true);
		lTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lTete.setFont(new Font(getName(), Font.BOLD, 30));
		fenetre.add(lTete,BorderLayout.NORTH);
		double cout = 0;
		HashMap<String, Double> liste = u.calculResultatDemande(u);
		JPanel pContenu = new JPanel();
		pContenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		try {
			cout = u.calculerProduction(u);
			pContenu = new JPanel();
			BoxLayout bl = new BoxLayout(pContenu, BoxLayout.PAGE_AXIS);
			pContenu.setLayout(bl);
			for(String key : liste.keySet()) {
				if(key!=null) {
					JPanel blocElement = new JPanel();
					GridLayout gLe = new GridLayout(1, 3);
					blocElement.setLayout(gLe);
					JTextField tCode = new JTextField(key);
					tCode.setEnabled(false);
					JTextField tDemande = new JTextField(liste.get(key)+"%");
					tDemande.setEnabled(false);
					JPanel pSatisfait = new JPanel();
					if(liste.get(key)<100)
						pSatisfait.setBackground(new Color(255, 0, 0));
					else
						pSatisfait.setBackground(new Color(0, 255, 0));
					blocElement.add(tCode);
					blocElement.add(tDemande);
					blocElement.add(pSatisfait);
					pContenu.add(blocElement);
				}
			}
		} catch (CalculException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Production impossible", JOptionPane.ERROR_MESSAGE);
			pContenu.add(new JLabel("Production impossible!"));
		} catch (CloneNotSupportedException e1) {
			JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
			pContenu.add(new JLabel("Erreur!"));
		}
		fenetre.add(pContenu, BorderLayout.CENTER);
		JPanel pBas = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBas.setLayout(fLb);
		JLabel lCout = new JLabel("Profit: ");
		JTextField tCout = new JTextField(cout+" €");
		JLabel lPercent = new JLabel("Pourcentage assuré:");
		JTextField tPercent = new JTextField();
		if(liste.get(null)==Double.POSITIVE_INFINITY)
			tPercent.setText(0+" %");
		else
			tPercent.setText(liste.get(null)+" %");
		pBas.add(lCout);
		pBas.add(tCout);
		pBas.add(lPercent);
		pBas.add(tPercent);
		pBas.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		fenetre.add(pBas,BorderLayout.SOUTH);
		this.add(fenetre);
		this.pack();
	}

}
