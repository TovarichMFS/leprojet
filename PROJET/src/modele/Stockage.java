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
	private int capacite, quantiteDispo;

	/**
	 * 
	 */
	public Stockage(String code, String nom, int capacite, int quantiteDispo) {
		this.capacite = capacite;
		this.code = code;
		this.nom = nom;
		this.quantiteDispo = quantiteDispo;
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
	
	@Override
	public String toString() {
		return "Stockage "+this.getCode()+": "+this.getNom()+" Capacité: "+this.getCapacite()+" Quantité dispo: "+this.getQuantiteDispo();
	}
}
