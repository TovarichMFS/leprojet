/**
 * 
 */
package controleur;

import modele.Stockage;

/**
 * @author tovarich
 *
 */
public class ControleurStockage {
	private Stockage stockage;

	public ControleurStockage(Stockage s) {
		this.stockage = s;
	}
	
	/**
	 * @return code
	 */
	public String getCode() {
		return this.stockage.getCode();
	}
	
	/**
	 * @return nom
	 */
	public String getNom() {
		return this.stockage.getNom();
	}
	
	/**
	 * @return capacite
	 */
	public int getCapacite() {
		return this.stockage.getCapacite();
	}
	
	/**
	 * @return quantiteDispo
	 */
	public int getQuantiteDispo() {
		return this.stockage.getQuantiteDispo();
	}
	
	/**
	 * @return remplissage
	 */
	public int getRemplissage() {
		return this.stockage.getRemplissage();
	}
	
	/**
	 * Modifie le code
	 * @param code
	 */
	public void setCode(String code) {
		this.stockage.setCode(code);;
	}

	/**
	 * Modifie le nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.stockage.setNom(nom);
	}

	/**
	 * Modifie la quantiteDispo
	 * @param quantiteDispo
	 */
	public void setQuantiteDispo(int quantiteDispo) {
		this.stockage.setQuantiteDispo(quantiteDispo);
	}

	/**
	 * Modifie le remplissage
	 * @param remplissage
	 */
	public void setRemplissage(int remplissage) {
		this.stockage.setRemplissage(remplissage);
	}
	
	/**
	 * Modifie la capacite
	 * @param capacite
	 */
	public void setCapacite(int capacite) {
		this.stockage.setCapacite(capacite);
	}
	
	/**
	 * Additionne au remplissage
	 * @param modif
	 */
	public void modifRemplissage(int modif) throws Exception {
		if(this.getRemplissage()+modif>(this.getCapacite()*this.getQuantiteDispo()))
			throw new Exception("Stockage insuffisant!");
		else if(this.getRemplissage()+modif<0)
			throw new Exception("Stocks insuffisant!");
		else
			this.stockage.modifRemplissage(modif);
	}
	
	/**
	 * Additionne à la quantiteDispo
	 * @param modif
	 */
	public void modifQuantiteDispo(int modif) throws Exception {
		if(this.getQuantiteDispo()+modif<0)
			throw new Exception("Nombre de stockages négatif!");
		else
			this.stockage.modifQuantiteDispo(modif);
	}

}
