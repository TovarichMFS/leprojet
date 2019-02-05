package vue;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurChaineDeProduction;
import controleur.ControleurUsine;
import modele.ChaineDeProduction;

public class VueListeChaines extends JPanel {
	ArrayList<ChaineDeProduction> listeC;

	public VueListeChaines(ArrayList<ChaineDeProduction> listeC,ControleurUsine u) {
		this.listeC = listeC;
		if(this.listeC.isEmpty()) {
			this.add(new JLabel("La liste est vide!"));
		}else {
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
				bDetails.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						VueChaine vc = new VueChaine(c,u);
						vc.setResizable(false);
						vc.pack();
						vc.show();
						
					}
				});
				blocChaine.add(bDetails);
				this.add(blocChaine);
			}
		}
	}

}
