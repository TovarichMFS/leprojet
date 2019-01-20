package tests;

import modele.ChaineDeProduction;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;
import modele.Usine;

public class TestModele {

	public static void main(String[] args) {
		Element a = new MatierePremiere("1324536", "lol", 3, 5, "kg");
		Element aa = new MatierePremiere("1324536", "lol", 3, 10, "kg");
		Element b = new Produit("13871983", "lel", 10, "l", 1);
		ChaineDeProduction c = new ChaineDeProduction("111", "AH");
		c.addEntrant(a);
		c.addSortants(b);
		System.out.println(c.toString());
		Usine u = new Usine("UIO");
		u.addChaine(c);
		u.addStock(aa);
		System.out.println(u.toString());

	}

}
