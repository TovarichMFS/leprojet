/**
 * 
 */
package modele;

import java.util.ArrayList;

/**
 * @author tovarich
 *
 */
public class ChaineDeProduction {
	private String code, nom;
	private int niveau;
	private ArrayList<Element> entrants, sortants;

	/**
	 * 
	 */
	public ChaineDeProduction(String code, String nom) {
		this.code = code;
		this.nom = nom;
		this.setNiveau(0);
		this.entrants = new ArrayList<Element>();
		this.sortants = new ArrayList<Element>();
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return the niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @param niveau the niveau to set
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * @return the entrants
	 */
	public ArrayList<Element> getEntrants() {
		return entrants;
	}
	
	public void addEntrant(Element e) {
		this.entrants.add(e);
	}

	/**
	 * @return the sortants
	 */
	public ArrayList<Element> getSortants() {
		return sortants;
	}
	
	public void addSortants(Element e) {
		this.sortants.add(e);
	}
	
	@Override
	public String toString() {
		String ent = "";
		for (Element element : this.entrants)
			ent += element.toString()+"\n";
		String sort = "";
		for (Element element : this.sortants)
			sort += element.toString()+"\n";
		return "Chaine "+this.getNom()+" ("+this.getCode()+"): Niveau "+this.getNiveau()+"\nEntrants:\n"+ent+"Sortants:\n"+sort;
	}

}
