/**
 * 
 */
package controleur;

import java.util.ArrayList;

import modele.ChaineDeProduction;
import modele.Element;

/**
 * @author tovarich
 *
 */
public class ControleurChaineDeProduction {

	private ChaineDeProduction c;
	/**
	 * Construit un Controleur sur la ChaineDeProduction c
	 * @param c
	 */
	public ControleurChaineDeProduction(ChaineDeProduction c) {
		this.c = c;
	}
	
	/**
	 * Retourne le code de la ChaineDeProduction c
	 * @return String
	 */
	public String getCode() {
		return this.c.getCode();
	}
	
	/**
	 * Retourne le nom de la ChaineDeProduction c
	 * @return String
	 */
	public String getNom() {
		return this.c.getNom();
	}
	
	/**
	 * Retourne le niveau de la ChaineDeProduction c
	 * @return int
	 */
	public int getNiveau() {
		return this.c.getNiveau();
	}
	
	/**
	 * Change le niveau de la ChaineDeProduction c par le paramètre i
	 * @param i
	 */
	public void changeNiveau(int i) {
		this.c.setNiveau(i);
	}
	
	/**
	 * Retourne un ELement entrant selon le code en paramètre
	 * @param code
	 * @return Element
	 */
	public Element getEntrant(String code) {
		for (Element e : this.getEntrants()) {
			if(code.equals(e.getCode())) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Ajoute l'Element e dans la liste des entrants
	 * @param e
	 */
	public void addEntrant(Element e) {
		this.c.getEntrants().add(e);
	}
	
	/**
	 * Retourne la liste des entrants de la ChaineDeProduction c
	 * @return ArrayList<Element>
	 */
	public ArrayList<Element> getEntrants(){
		return this.c.getEntrants();
	}
	
	/**
	 * Retire l'Element e de la liste des entrants
	 * @param e
	 */
	public void rmEntrant(Element e) {
		this.c.getEntrants().remove(e);
	}
	
	/**
	 * Retourne un Element de la liste des sortants selon le code en paramètre
	 * @param code
	 * @return Element
	 */
	public Element getSortant(String code) {
		for (Element e : this.getSortants()) {
			if(code.equals(e.getCode())) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Ajoute l'Element e dans la liste des sortants
	 * @param e
	 */
	public void addSortant(Element e) {
		this.c.getSortants().add(e);
	}
	
	/**
	 * Retourne la liste des sortants de la ChaineDeProduction c
	 * @return ArrayList<Element>
	 */
	public ArrayList<Element> getSortants(){
		return this.c.getSortants();
	}
	
	/**
	 * Retire l'Element e de la liste des sortants
	 * @param e
	 */
	public void rmSortant(Element e) {
		this.c.getSortants().remove(e);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.toString();
	}

}
