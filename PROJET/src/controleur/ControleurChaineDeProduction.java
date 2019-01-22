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

	/**
	 * 
	 */
	private ChaineDeProduction c;
	public ControleurChaineDeProduction(ChaineDeProduction c) {
		this.c = c;
	}
	
	public String getCode() {
		return this.c.getCode();
	}
	
	public int getNiveau() {
		return this.c.getNiveau();
	}
	
	public void changeNiveau(int i) {
		this.c.setNiveau(i);
	}
	
	public Element getEntrant(String code) {
		for (Element e : this.getEntrants()) {
			if(code.equals(e.getCode())) {
				return e;
			}
		}
		return null;
	}
	
	public void addEntrant(Element e) {
		this.c.getEntrants().add(e);
	}
	
	public ArrayList<Element> getEntrants(){
		return this.c.getEntrants();
	}
	
	public void rmEntrant(Element e) {
		this.c.getEntrants().remove(e);
	}
	
	public Element getSortant(String code) {
		for (Element e : this.getSortants()) {
			if(code.equals(e.getCode())) {
				return e;
			}
		}
		return null;
	}
	
	public void addSortant(Element e) {
		this.c.getSortants().add(e);
	}
	
	public ArrayList<Element> getSortants(){
		return this.c.getSortants();
	}
	
	public void rmSortant(Element e) {
		this.c.getSortants().remove(e);
	}
	
	public String toString() {
		return this.toString();
	}

}
