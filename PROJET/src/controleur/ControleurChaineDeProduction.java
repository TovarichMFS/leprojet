/**
 * 
 */
package controleur;

import java.util.HashMap;

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
	 * Change le nom de la ChaineDeProduction c
	 * @param nom
	 */
	public void setNom(String nom) {
		this.c.setNom(nom);
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
		for (String key : this.getEntrants().keySet()) {
			if(code.equals(this.getEntrants().get(key).getCode())) {
				return this.getEntrant(key);
			}
		}
		return null;
	}
	
	/**
	 * Ajoute l'Element e dans la liste des entrants
	 * @param e
	 */
	public void addEntrant(Element e) {
		this.c.getEntrants().put(e.getCode(),e);
	}
	
	/**
	 * Retourne la liste des entrants de la ChaineDeProduction c
	 * @return ArrayList<Element>
	 */
	public HashMap<String, Element> getEntrants(){
		return this.c.getEntrants();
	}
	
	/**
	 * Retire l'Element de la liste des entrants
	 * @param codee
	 */
	public void rmEntrant(String code) {
		this.c.getEntrants().remove(code);
	}
	
	/**
	 * Retourne un Element de la liste des sortants selon le code en paramètre
	 * @param code
	 * @return Element
	 */
	public Element getSortant(String code) {
		for (String key : this.getSortants().keySet()) {
			if(code.equals(this.getSortants().get(key).getCode())) {
				return this.getSortant(key);
			}
		}
		return null;
	}
	
	/**
	 * Ajoute l'Element e dans la liste des sortants
	 * @param e
	 */
	public void addSortant(Element e) {
		this.c.getSortants().put(e.getCode(),e);
	}
	
	/**
	 * Retourne la liste des sortants de la ChaineDeProduction c
	 * @return ArrayList<Element>
	 */
	public HashMap<String, Element> getSortants(){
		return this.c.getSortants();
	}
	
	/**
	 * Retire l'Element de la liste des sortants
	 * @param e
	 */
	public void rmSortant(String code) {
		this.c.getSortants().remove(code);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.toString();
	}

}
