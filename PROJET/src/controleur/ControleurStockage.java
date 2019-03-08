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
	
	public void modifQuantiteDispo(int modif) throws Exception {
		if(this.getQuantiteDispo()+modif>this.getCapacite())
			throw new Exception("Stockage insuffisant!");
		else if(this.getQuantiteDispo()+modif<0)
			throw new Exception("Stocks insuffisant!");
		else
			this.stockage.modifQuantiteDispo(modif);
	}

}
