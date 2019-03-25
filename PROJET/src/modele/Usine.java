/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.HashMap;

import others.CSV;

/**
 * @author tovarich
 *
 */
public class Usine {
	private ArrayList<ChaineDeProduction> chaines;
	private HashMap<String,Element> stocks, listeAchats;
	private HashMap<String, Stockage>stockages;

	/**
	 * Construit une Usine
	 * @param nom
	 */
	public Usine() {
		this.chaines = new ArrayList<ChaineDeProduction>();
		this.stocks = new HashMap<String,Element>();
		this.listeAchats = new HashMap<String,Element>();
		this.stockages = new HashMap<String, Stockage>();
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
	
	/**
	 * Retourne la liste d'achats
	 * @return listeAchats
	 */
	public HashMap<String,Element> getListeAchats() {
		return listeAchats;
	}
	
	/**
	 * Remplace la liste de stocks par celle passée en paramètre
	 * @param stock
	 */
	public void setStocks(HashMap<String, Element> stocks) {
		this.stocks = stocks;
	}
	
	/**
	 * Remplace la liste de chaines par celle passée en paramètre
	 * @param chaines
	 */
	public void setChaines(ArrayList<ChaineDeProduction> chaines) {
		this.chaines = chaines;
	}
	
	/**
	 * Remplace la liste d'achats par celle passée en paramètre
	 * @param listeAchats
	 */
	public void setListeAchats(HashMap<String, Element> listeAchats) {
		this.listeAchats = listeAchats;
	}

	/**
	 * @return stockages
	 */
	public HashMap<String, Stockage> getStockages() {
		return this.stockages;
	}

	/**
	 * @param stockages
	 */
	public void setStockages(HashMap<String, Stockage> stockages) {
		this.stockages = stockages;
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
		String stockages = "";
		for (String key : this.getStockages().keySet()) {
			stockages += this.getStockages().get(key).toString()+"\n";
		}
		String achats = "";
		for (String key : this.getListeAchats().keySet()) {
			achats += this.getListeAchats().get(key).toString()+"\n";
		}
		return "Usine :\nStocks:\n"+stocks+"Chaines de Production:\n"+chaines+"Stockages:\n"+stockages+"Liste d'achats:\n"+achats;
	}

}
