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
	 * Construit une Usine
	 * @param nom
	 */
	public Usine(String nom) {
		this.nom = nom;
		this.chaines = new ArrayList<ChaineDeProduction>();
		this.stocks = new HashMap<String,Element>();
	}

	/**
	 * Retourne le nom de l'Usine
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne la liste des {@link ChaineDeProduction} de l'Usine
	 * @return chaines
	 */
	public ArrayList<ChaineDeProduction> getChaines() {
		return chaines;
	}

	/**
	 * Retourne la liste des {@link Element} en stock de l'Usine
	 * @return stocks
	 */
	public HashMap<String,Element> getStocks() {
		return stocks;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
