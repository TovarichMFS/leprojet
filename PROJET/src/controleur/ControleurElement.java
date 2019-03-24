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

	private Element e;
	
	/**
	 * Construit un ControleurElement sur l'Element e
	 * @param e
	 */
	public ControleurElement(Element e) {
		this.e = e;
	}
	
	/**
	 * Retourne le code d'un élément
	 * @return String code
	 */
	public String getCode() {
		return this.e.getCode();
	}
	
	/**
	 * Retourne le nom d'un élément
	 * @return String nom
	 */
	public String getNom() {
		return this.e.getNom();
	}
	
	/**
	 * Modifie le nom d'un élément
	 * @param nom
	 */
	public void setNom(String nom) {
		this.e.setNom(nom);
	}
	
	/**
	 * Retourne la quantite d'un élément
	 * @return double quantite
	 */
	public double getQuantite() {
		return this.e.getQuantite();
	}
	
	/**
	 * Modifie la quantite d'un élément
	 * @param nQuantite
	 */
	public void changeQuantite(double nQuantite) {
		this.e.setQuantite(nQuantite);
	}
	
	/**
	 * True si la quantite demandée est en stock, False sinon
	 * @param quantite
	 * @return boolean
	 */
	public boolean enStock(double quantite) {
		if(this.getQuantite()-quantite<0)
			return false;
		else
			return true;
	}
	
	/**
	 * Retourne l'unité de quantité de l'élément
	 * @return String unite
	 */
	public String getUnite() {
		return this.e.getUniteQuantite();
	}
	
	/**
	 * Retourne le prix d'achat d'un élément
	 * @return double prixAchat
	 */
	public double getPrixAchat() {
		return this.e.getPrixAchat();
	}
	
	/**
	 * Retourne le prix de vente d'un élément
	 * @return double prixVente
	 */
	public double getPrixVente() {
		return this.e.getPrixVente();
	}
	
	/**
	 * Modifie le prix d'achat d'un élément
	 * @param prix
	 */
	public void setPrixAchat(double prix) {
		this.e.setPrixAchat(prix);
	}
	
	/**
	 * Modifie le prix de vente d'un élément
	 * @param prix
	 */
	public void setPrixVente(double prix) {
		this.e.setPrixVente(prix);
	}
	
	/**
	 * Retourne la demande d'un élément
	 * @return int demande
	 */
	public int getDemande() {
		return this.e.getDemande();
	}
	
	/**
	 * Modifie la demande d'un élément
	 * @param demande
	 */
	public void setDemande(int demande) {
		this.e.setDemande(demande);
	}
	
	/**
	 * Retourne l'élément
	 * @return double prixVente
	 */
	public Element getElement() {
		return this.e;
	}
	
	public String getStockage() {
		return this.e.getStockage();
	}
	
	public void setStockage(String s) {
		this.e.setStockage(s);
	}

}
