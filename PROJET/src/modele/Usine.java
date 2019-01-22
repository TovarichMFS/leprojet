/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author tovarich
 *
 */
public class Usine {
	private String nom;
	private ArrayList<ChaineDeProduction> chaines;
	private HashMap<String,Element> stocks;

	/**
	 * 
	 */
	public Usine(String nom) {
		this.nom = nom;
		this.chaines = new ArrayList<ChaineDeProduction>();
		this.stocks = new HashMap<String,Element>();
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the chaines
	 */
	public ArrayList<ChaineDeProduction> getChaines() {
		return chaines;
	}
	
	public void addChaine(ChaineDeProduction c) {
		this.chaines.add(c);
	}

	/**
	 * @return the stocks
	 */
	public HashMap<String,Element> getStocks() {
		return stocks;
	}
	
	@Override
	public String toString() {
		String chaines = "";
		for (ChaineDeProduction c : this.chaines) {
			chaines += c.toString()+"\n";
		}
		String stocks = "";
		for (Element e : this.stocks.values()) {
			stocks += e.toString()+"\n";
		}
		return "Usine "+this.getNom()+":\nStocks:\n"+stocks+"Chaines de Production:\n"+chaines;
	}

}
