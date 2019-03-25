/**
 * 
 */
package modele;

/**
 * @author tovarich
 *
 */
public class Stockage implements Cloneable{
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
	
	/**
	 * Additionne à la quantiteDispo
	 * @param modif
	 */
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
	 * Additionne au remplissage
	 * @param modif
	 */
	public void modifRemplissage(int remplissage) {
		this.remplissage += remplissage;
	}
	
	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @param quantiteDispo
	 */
	public void setQuantiteDispo(int quantiteDispo) {
		this.quantiteDispo = quantiteDispo;
	}

	/**
	 * @param capacite
	 */
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	/**
	 * @param remplissage
	 */
	public void setRemplissage(int remplissage) {
		this.remplissage = remplissage;
	}

	@Override
	public String toString() {
		return "Stockage "+this.getCode()+": "+this.getNom()+" Capacité: "+this.getCapacite()+" Quantité dispo: "+this.getQuantiteDispo()+" Remplissage: "+this.getRemplissage();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Stockage clone() throws CloneNotSupportedException {   
		return (Stockage)super.clone();
	}
}
