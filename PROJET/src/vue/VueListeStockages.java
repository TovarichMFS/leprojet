package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ControleurStockage;
import controleur.ControleurUsine;
import modele.Stockage;

public class VueListeStockages extends JPanel{

	public VueListeStockages(HashMap<String,Stockage> listeS, ControleurUsine u) {
		super();
		if(listeS.isEmpty()) {
			this.add(new JLabel("La liste est vide!"));
		}else {
			BoxLayout bl = new BoxLayout(this, BoxLayout.PAGE_AXIS);
			this.setLayout(bl);
			JPanel blocLabel = new JPanel();
			GridLayout gLabel = new GridLayout(1, 4);
			blocLabel.setLayout(gLabel);
			JLabel lCode = new JLabel("Code");
			JLabel lNom = new JLabel("Nom");
			JLabel lCapacite = new JLabel("Capacite");
			JLabel lQuantiteDispo = new JLabel("Quantité disponible");
			JLabel lRemplissage = new JLabel("Remplissage");
			blocLabel.add(lCode);
			blocLabel.add(lNom);
			blocLabel.add(lCapacite);
			blocLabel.add(lQuantiteDispo);
			blocLabel.add(lRemplissage);
			blocLabel.add(new JLabel(" "));
			this.add(blocLabel);
			
			for (String sKey : listeS.keySet()) {
				Stockage s = listeS.get(sKey);
				ControleurStockage cS = new ControleurStockage(s);
				JPanel blocStockage = new JPanel();
				GridLayout gL = new GridLayout(1, 4);
				blocStockage.setLayout(gL);
				JTextField tfCode = new JTextField(cS.getCode());
				tfCode.setEditable(false);
				JTextField tfNom = new JTextField(cS.getNom());
				tfNom.setEditable(false);
				JTextField tfCapacite = new JTextField(cS.getCapacite()+"");
				tfCapacite.setEditable(false);
				JTextField tfQuantiteDispo = new JTextField(cS.getQuantiteDispo()+"");
				tfQuantiteDispo.setEditable(false);
				JTextField tfRemplissage = new JTextField(cS.getRemplissage()+"");
				tfRemplissage.setEditable(false);
				blocStockage.add(tfCode);
				blocStockage.add(tfNom);
				blocStockage.add(tfCapacite);
				blocStockage.add(tfQuantiteDispo);
				blocStockage.add(tfRemplissage);
				JButton bDetails = new JButton(">");
				bDetails.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						VueStockage vs = new VueStockage(s,u);
						vs.setResizable(false);
						vs.pack();
						vs.show();
						
					}
				});
				blocStockage.add(bDetails);
				this.add(blocStockage);
			}
		}
	}

}
