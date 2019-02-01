package vue;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurChaineDeProduction;
import modele.ChaineDeProduction;

public class VueListeChaines extends JPanel {
	ArrayList<ChaineDeProduction> listeC;

	public VueListeChaines(ArrayList<ChaineDeProduction> listeC) {
		this.listeC = listeC;
		BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
		this.setLayout(bl);
		JPanel blocLabel = new JPanel();
		GridLayout gLabel = new GridLayout(1, 4);
		blocLabel.setLayout(gLabel);
		JLabel lCode = new JLabel("Code");
		JLabel lNom = new JLabel("Nom");
		JLabel lNiveau = new JLabel("Niveau");
		blocLabel.add(lCode);
		blocLabel.add(lNom);
		blocLabel.add(lNiveau);
		blocLabel.add(new JLabel(" "));
		this.add(blocLabel);
		for (ChaineDeProduction c : listeC) {
			ControleurChaineDeProduction cC = new ControleurChaineDeProduction(c);
			JPanel blocChaine = new JPanel();
			GridLayout gL = new GridLayout(1, 4);
			blocChaine.setLayout(gL);
			JTextField tfCode = new JTextField(cC.getCode());
			tfCode.setEditable(false);
			JTextField tfNom = new JTextField(cC.getNom());
			tfNom.setEditable(false);
			JTextField tfNiveau = new JTextField(cC.getNiveau()+"");
			tfNiveau.setEditable(false);
			blocChaine.add(tfCode);
			blocChaine.add(tfNom);
			blocChaine.add(tfNiveau);
			JButton bDetails = new JButton(">");
			blocChaine.add(bDetails);
			this.add(blocChaine);
		}
	}

}
