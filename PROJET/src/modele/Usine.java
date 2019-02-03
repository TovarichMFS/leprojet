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
	private HashMap<String,Element> stocks, listeAchats;

	/**
	 * Construit une Usine
	 * @param nom
	 */
	public Usine(String nom) {
		this.nom = nom;
		this.chaines = new ArrayList<ChaineDeProduction>();
		this.stocks = new HashMap<String,Element>();
		this.listeAchats = new HashMap<String,Element>();
	}

	/**
	 * Retourne le nom de l'Usine
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Modifie le nom de l'usine
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
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
	 * Remplace la liste d'achats par celle passée en paramètre
	 * @param listeAchats
	 */
	public void setListeAchats(HashMap<String, Element> listeAchats) {
		this.listeAchats = listeAchats;
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
		String achats = "";
		for (String key : this.getListeAchats().keySet()) {
			achats += this.getListeAchats().get(key).toString()+"\n";
		}
		return "Usine "+this.getNom()+":\nStocks:\n"+stocks+"Chaines de Production:\n"+chaines+"Liste d'achats:\n"+achats;
	}

}
