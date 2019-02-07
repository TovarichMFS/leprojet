/**
 * 
 */
package controleur;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import static java.nio.file.StandardOpenOption.APPEND;

import modele.ChaineDeProduction;
import modele.Element;
import modele.MatierePremiere;
import modele.Produit;
import modele.Usine;
import others.CSV;
import others.CalculException;
import others.CalculsProduction;

/**
 * @author tovarich
 *
 */
public class ControleurUsine implements CSV, CalculsProduction{

	private Usine u;
	/**
	 * Construit un ControleurUsine sur l'Usine u
	 * @param u
	 */
	public ControleurUsine(Usine u) {
		this.u = u;
	}
	
	/**
	 * Ajoute un Element e dans le stock de l'Usine u
	 * @param e
	 */
	public void addStock(Element e) {
		ControleurElement cE = new ControleurElement(e);
		this.u.getStocks().put(cE.getCode(), e);
	}
	
	/**
	 * Retourne l'Element du stock dont le code est passé en paramètre
	 * @param code
	 * @return Element
	 */
	public Element getStock(String code) {
		return this.getStocks().get(code);
	}
	
	/**
	 * Retourne le stock de l'Usine u
	 * @return HashMap<String,Element>
	 */
	public HashMap<String,Element> getStocks(){
		return this.u.getStocks();
	}
	
	/**
	 * Retire l'Element e du stock de l'Usine u
	 * @param code
	 */
	public void rmStock(String code) {
		this.getStocks().remove(code);
	}
	
	/**
	 * Ajoute la ChaineDeProduction c à la liste des chaines de l'Usine u
	 * @param c
	 */
	public void addChaine(ChaineDeProduction c) {
		this.u.getChaines().add(c);
	}
	
	/**
	 * Retourne la ChaineDeProduction correspondante au code passé en paramètre
	 * @param code
	 * @return ChaineDeProduction
	 */
	public ChaineDeProduction getChaine(String code) {
		for (ChaineDeProduction c : this.getChaines()) {
			ControleurChaineDeProduction cCDP = new ControleurChaineDeProduction(c);
			if(code.equals(cCDP.getCode()))
				return c;
		}
		return null;
	}
	
	/**
	 * Retourne la liste des ChaineDeProduction de l'Usine u
	 * @return ArrayList<ChaineDeProduction>
	 */
	public ArrayList<ChaineDeProduction> getChaines() {
		return this.u.getChaines();
	}
	
	/**
	 * Retire la ChaineDeProduction c de la liste des chaines de l'Usine u
	 * @param c
	 */
	public void rmChaine(String code) {
		this.getChaines().remove(this.getChaine(code));
	}
	
	/**
	 * Retourne la liste des achats de l'Usine u
	 * @return listeAchats
	 */
	public HashMap<String, Element> getListeAchats(){
		return this.u.getListeAchats();
	}
	
	/**
	 * Retourne l'Elément de la liste d'achats selon son code
	 * @param code
	 * @return Element
	 */
	public Element getAchat(String code) {
		return this.getListeAchats().get(code);
	}
	
	/**
	 * Ajoute l'Element e à la liste d'achats
	 * @param e
	 */
	public void addAchat(Element e) {
		this.getListeAchats().put(e.getCode(),e);
	}
	
	/**
	 * Retire un Element de la liste d'achats selon son code
	 * @param code
	 */
	public void rmAchat(String code) {
		this.getListeAchats().remove(code);
	}
	
	/**
	 * @param listeAchats the listeAchats to set
	 */
	public void setListeAchats(HashMap<String, Element> listeAchats) {
		this.u.setListeAchats(listeAchats);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.u.toString();
	}

}
