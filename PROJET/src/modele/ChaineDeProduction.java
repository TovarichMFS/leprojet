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
public class ChaineDeProduction {
	private String code, nom;
	private int niveau;
	private HashMap<String,Element> entrants, sortants;


	/**
	 * Construit une ChaineDeProduction
	 * @param code
	 * @param nom
	 */
	public ChaineDeProduction(String code, String nom) {
		this.code = code;
		this.nom = nom;
		this.setNiveau(1);
		this.entrants = new HashMap<String,Element>();
		this.sortants = new HashMap<String,Element>();
	}

	/**
	 * Retourne le code de la ChaineDeProduction
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Retourne le nom de la ChaineDeProduction
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Retourne le niveau de la ChaineDeProduction
	 * @return niveau
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * Modifie le niveau de la ChaineDeProduction
	 * @param niveau
	 */
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	/**
	 * Retourne la liste des {@link Element} entrants
	 * @return entrants
	 */
	public HashMap<String,Element> getEntrants() {
		return entrants;
	}

	/**
	 * Retourne la liste des {@link Element} sortants
	 * @return sortants
	 */
	public HashMap<String,Element> getSortants() {
		return sortants;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ent = "";
		for (String key : this.entrants.keySet())
			ent += this.entrants.get(key).toString()+"\n";
		String sort = "";
		for (String key : this.sortants.keySet())
			sort += this.sortants.get(key).toString()+"\n";
		return "Chaine "+this.getNom()+" ("+this.getCode()+"): Niveau "+this.getNiveau()+"\nEntrants:\n"+ent+"Sortants:\n"+sort;
	}

}
