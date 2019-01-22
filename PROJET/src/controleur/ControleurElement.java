/**
 * 
 */
package controleur;

import modele.Element;

/**
 * @author tovarich
 *
 */
public class ControleurElement {

	/**
	 * 
	 */
	private Element e;
	
	public ControleurElement(Element e) {
		this.e = e;
	}
	
	public String getCode() {
		return this.e.getCode();
	}
	
	public double getQuantite() {
		return this.e.getQuantite();
	}
	
	public void changeQuantite(double nQuantite) {
		this.e.setQuantite(nQuantite);
	}
	
	public boolean enStock(double quantite) {
		if(this.getQuantite()-quantite<0)
			return false;
		else
			return true;
	}
	
	public String getUnite() {
		return this.getUnite();
	}
	
	public double getPrixAchat() {
		return this.e.getPrixAchat();
	}
	
	public double getPrixVente() {
		return this.e.getPrixVente();
	}
	
	public void setPrixAchat(double prix) {
		this.e.setPrixAchat(prix);
	}
	
	public void setPrixVente(double prix) {
		this.e.setPrixVente(prix);
	}

}
