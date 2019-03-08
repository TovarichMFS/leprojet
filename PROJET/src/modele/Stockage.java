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

	@Override
	public String toString() {
		return "Stockage "+this.getCode()+": "+this.getNom()+" Capacité: "+this.getCapacite()+" Quantité dispo: "+this.getQuantiteDispo();
	}
}
