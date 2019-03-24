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

	/**
	 * 
	 */
	public ControleurStockage(Stockage s) {
		this.stockage = s;
	}
	
	public String getCode() {
		return this.stockage.getCode();
	}
	
	public String getNom() {
		return this.stockage.getNom();
	}
	
	public int getCapacite() {
		return this.stockage.getCapacite();
	}
	
	public int getQuantiteDispo() {
		return this.stockage.getQuantiteDispo();
	}
	
	public int getRemplissage() {
		return this.stockage.getRemplissage();
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.stockage.setCode(code);;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.stockage.setNom(nom);
	}

	/**
	 * @param quantiteDispo the quantiteDispo to set
	 */
	public void setQuantiteDispo(int quantiteDispo) {
		this.stockage.setQuantiteDispo(quantiteDispo);
	}

	/**
	 * @param remplissage the remplissage to set
	 */
	public void setRemplissage(int remplissage) {
		this.stockage.setRemplissage(remplissage);
	}
	
	/**
	 * @param capacite the capacite to set
	 */
	public void setCapacite(int capacite) {
		this.stockage.setCapacite(capacite);
	}
	
	public void modifRemplissage(int modif) throws Exception {
		if(this.getRemplissage()+modif>(this.getCapacite()*this.getQuantiteDispo()))
			throw new Exception("Stockage insuffisant!");
		else if(this.getRemplissage()+modif<0)
			throw new Exception("Stocks insuffisant!");
		else
			this.stockage.modifRemplissage(modif);
	}
	
	public void modifQuantiteDispo(int modif) throws Exception {
		if(this.getQuantiteDispo()+modif<0)
			throw new Exception("Nombre de stockages négatif!");
		else
			this.stockage.modifQuantiteDispo(modif);
	}

}
