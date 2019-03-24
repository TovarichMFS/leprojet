package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;

import controleur.ControleurStockage;
import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.Stockage;

public class VueStockage extends JFrame {
	private ControleurStockage cS;

	public VueStockage(Stockage s, ControleurUsine u) {
		super();
		JPanel fenetre = new JPanel();
		this.cS = new ControleurStockage(s);
		BorderLayout bL = new BorderLayout();
		fenetre.setLayout(bL);
		JPanel pTete = new JPanel();
		GridLayout gLt = new GridLayout(2, 1);
		pTete.setLayout(gLt);
		pTete.setBackground(new Color(204, 229, 255));
		pTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		
		JLabel lTitre;
		if(s!=null)
			lTitre = new JLabel("Stockage");
		else
			lTitre = new JLabel("AJouter un Stockage");
		lTitre.setFont(new Font(getName(), Font.BOLD, 20));
		pTete.add(lTitre);
		
		JPanel pSTete = new JPanel();
		pSTete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLst = new GridLayout(2, 3);
		pSTete.setLayout(gLst);
		
		JLabel lCode = new JLabel("Code");
		lCode.setFont(new Font(getName(), Font.BOLD, 12));
		JLabel lNom = new JLabel("Nom");
		lNom.setFont(new Font(getName(), Font.BOLD, 12));
		pSTete.add(lCode);
		pSTete.add(lNom);
		
		JTextField tCode;
		JTextField tNom = new JTextField(12);
		if(s!=null) {
			tCode = new JTextField(this.cS.getCode());
			tCode.setEditable(false);
			tNom.setText(this.cS.getNom());
		}else
			tCode = new JTextField(6);
		pSTete.add(tCode);
		pSTete.add(tNom);
		
		pTete.add(pSTete);
		fenetre.add(pTete, BorderLayout.NORTH);
		
		JPanel pContenu = new JPanel();
		pContenu.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		GridLayout gLc = new GridLayout(3, 1);
		pContenu.setLayout(gLc);
		
		JPanel pCapacite = new JPanel();
		JLabel lCapacite = new JLabel("Capacité:");
		JTextField tCapacite = new JTextField(4);
		JPanel pQuantiteDispo = new JPanel();
		JLabel lQuantiteDispo = new JLabel("Quantité disponible:");
		JTextField tQuantiteDispo = new JTextField(4);
		JPanel pRemplissage = new JPanel();
		JLabel lRemplissage = new JLabel("Remplissage:");
		JTextField tRemplissage = new JTextField(4);
		if(s!=null) {
			tCapacite.setText(s.getCapacite()+"");
			tQuantiteDispo.setText(s.getQuantiteDispo()+"");
			tRemplissage.setText(s.getRemplissage()+"");
		}
		pCapacite.add(lCapacite);
		pCapacite.add(tCapacite);
		pQuantiteDispo.add(lQuantiteDispo);
		pQuantiteDispo.add(tQuantiteDispo);
		pRemplissage.add(lRemplissage);
		pRemplissage.add(tRemplissage);
		
		pCapacite.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		pContenu.add(pCapacite);
		pQuantiteDispo.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		pContenu.add(pQuantiteDispo);
		pRemplissage.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		pContenu.add(pRemplissage);
		
		fenetre.add(pContenu, BorderLayout.CENTER);
		
		JPanel pBoutons = new JPanel();
		FlowLayout fLb = new FlowLayout();
		pBoutons.setLayout(fLb);
		if(s!=null) {
			JButton bModif = new JButton("Modifier");
			bModif.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier ce stockage?","Modifier stockage",JOptionPane.YES_NO_OPTION);
					if(choix==JOptionPane.YES_OPTION) {
						String nom = tNom.getText();
						int capacite = Integer.parseInt(tCapacite.getText());
						int quantiteDispo = Integer.parseInt(tQuantiteDispo.getText());
						int remplissage = Integer.parseInt(tRemplissage.getText());
						if(nom!="" && capacite>=0 && quantiteDispo>=0 && remplissage>=0) {
							cS.setNom(nom);
							cS.setCapacite(capacite);
							cS.setQuantiteDispo(quantiteDispo);
							cS.setRemplissage(remplissage);
							JOptionPane.showMessageDialog(null, "Stockage modifié!", "Stockage modifié", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur modification", JOptionPane.WARNING_MESSAGE);
						}
					}
					setVisible(false);
					dispose();
				}
			});
			JButton bSuppr = new JButton("Supprimer");
			bSuppr.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce stockage?","Supprimer stockage",JOptionPane.YES_NO_OPTION);
					if(choix==JOptionPane.YES_OPTION) {
						u.rmStockage(cS.getCode());
						JOptionPane.showMessageDialog(null, "Stockage supprimé!","Supprimer stockage",JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}
				}
			});
			pBoutons.add(bModif);
			pBoutons.add(bSuppr);
		}else {
			JButton bAJout = new JButton("Ajouter");
			bAJout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String code = tCode.getText();
					String nom = tNom.getText();
					int capacite = Integer.parseInt(tCapacite.getText());
					int quantiteDispo = Integer.parseInt(tQuantiteDispo.getText());
					int remplissage = Integer.parseInt(tRemplissage.getText());
					if(code!="" && nom!="" && capacite>=0 && quantiteDispo>=0 && remplissage>=0) {
						Stockage s = new Stockage(code, nom, capacite, quantiteDispo, remplissage);
						u.addStockage(s);
						JOptionPane.showMessageDialog(null, "Stockage ajouté!", "Stockage ajouté", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Erreur, des paramètres sont manquants ou invalides", "Erreur ajout", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			pBoutons.add(bAJout);
		}
		JButton bAnnuler = new JButton("Annuler");
		bAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				
			}
		});
		
		pBoutons.add(bAnnuler);
		
		fenetre.add(pBoutons, BorderLayout.SOUTH);
		
		this.add(fenetre);
		
		this.setResizable(false);
		this.pack();
		
	}

}
