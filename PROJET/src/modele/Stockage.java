/**
 * 
 */
package modele;

/**
 * @author tovarich
 *
 */
public class Stockage {
	private String code, nom;
	private int capacite, quantiteDispo, remplissage;

	/**
	 * 
	 */
	public Stockage(String code, String nom, int capacite, int quantiteDispo) {
		this.capacite = capacite;
		this.code = code;
		this.nom = nom;
		this.quantiteDispo = quantiteDispo;
		this.remplissage = 0;
	}
	
	public Stockage(String code, String nom, int capacite, int quantiteDispo, int remplissage) {
		this.capacite = capacite;
		this.code = code;
		this.nom = nom;
		this.quantiteDispo = quantiteDispo;
		this.remplissage = remplissage;
	}

	/**
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @return capacite
	 */
	public int getCapacite() {
		return capacite;
	}

	/**
	 * @return quantiteDispo
	 */
	public int getQuantiteDispo() {
		return quantiteDispo;
	}
	
	public void modifQuantiteDispo(int modif) {
		this.quantiteDispo+=modif;
	}
	
	/**
	 * @return remplissage
	 */
	public int getRemplissage() {
		return remplissage;
	}

	/**
	 * @param remplissage remplissage to set
	 */
	public void modifRemplissage(int remplissage) {
		this.remplissage += remplissage;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param quantiteDispo the quantiteDispo to set
	 */
	public void setQuantiteDispo(int quantiteDispo) {
		this.quantiteDispo = quantiteDispo;
	}

	/**
	 * @param capacite the capacite to set
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	/**
	 * @param remplissage the remplissage to set
	 */
	public void setRemplissage(int remplissage) {
		this.remplissage = remplissage;
	}

	@Override
	public String toString() {
		return "Stockage "+this.getCode()+": "+this.getNom()+" Capacité: "+this.getCapacite()+" Quantité dispo: "+this.getQuantiteDispo()+" Remplissage: "+this.getRemplissage();
	}
}
