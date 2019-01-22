package modele;

public abstract class Element implements Cloneable{
	private String code, nom;
	private String unite;
	private double quantite,prixAchat,prixVente;
	
	public Element(String code, String nom, double quantite, String unite) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
	}
	

	public Element(String code, String nom, double prixAchat, double quantite, String unite, double prixVente) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixAchat(prixAchat);
		this.setPrixVente(prixVente);
	}
	
	public Element(String code, String nom, double prixAchat, double quantite, String unite) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixAchat(prixAchat);
	}
	
	public Element(String code, String nom, double quantite, String unite, double prixVente) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixVente(prixVente);
	}

	public String getCode() {
		return code;
	}

	/**
	 * @return the quantite
	 */
	public double getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the uniteQuantite
	 */
	public String getUniteQuantite() {
		return this.unite;
	}

	/**
	 * @return the prixAchat
	 */
	public double getPrixAchat() {
		return prixAchat;
	}

	/**
	 * @param prixAchat the prixAchat to set
	 */
	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	/**
	 * @return the prixVente
	 */
	public double getPrixVente() {
		return prixVente;
	}

	/**
	 * @param prixVente the prixVente to set
	 */
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	
	 @Override
	public Element clone() throws CloneNotSupportedException {   
		return (Element)super.clone();
	}

}
