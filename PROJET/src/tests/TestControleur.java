package tests;

import controleur.ControleurUsine;
import modele.Usine;

public class TestControleur {

	public static void main(String[] args) {
		ControleurUsine u = new ControleurUsine(new Usine("lol"));
		u.chargerCSV();
		System.out.println(u);
		u.saveCSV();

	}

}
