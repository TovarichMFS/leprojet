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
		return this.getRemplissage();
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
