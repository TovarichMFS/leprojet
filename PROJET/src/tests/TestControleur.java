package tests;

import controleur.ControleurStockage;
import controleur.ControleurUsine;
import modele.ChaineDeProduction;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;
import modele.Stockage;
import modele.Usine;
import others.CalculException;
import others.StockageException;

public class TestControleur {

	public static void main(String[] args) {
		Usine us = new Usine();
		ControleurUsine u = new ControleurUsine(us);
		u.addStock(new MatierePremiere("AA", "AH", 12, 1, "kg","cc",0));
		u.addStock(new Produit("AB", "AH", 0, "kg","cc",10));
		ChaineDeProduction c = new ChaineDeProduction("CC", "CHAINE");
		c.getEntrants().put("AA",new MatierePremiere("AA", "AH", 12, 1, "kg","cc",0));
		c.getSortants().put("AB",new Produit("AB", "AH", 1, "kg","cc",10));
		c.setNiveau(2);
		u.addChaine(c);
//		u.addAchat(new MatierePremiere("AA", "AH", 12, 1, "kg"));
//		ControleurUsine u = new ControleurUsine(new Usine("lol"));
//		u.chargerCSV();
//		u.rmChaine(u.getChaine("C003"));
		System.out.println(u);
//		u.saveCSV();
		try {
			System.out.println("Résultat production: "+u.calculerProduction(u));
		} catch (CalculException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} catch (StockageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(u);
//
	}

}
