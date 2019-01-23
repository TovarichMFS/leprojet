package modele;

public abstract class Element implements Cloneable{
	private String code, nom;
	private String unite;
	private double quantite,prixAchat,prixVente;
	
	/**
	 * Construit un Element sans prix
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 */
	public Element(String code, String nom, double quantite, String unite) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
	}
	

	/**
	 * Construit un Element avec prix d'achat et de vente
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param unite
	 * @param prixVente
	 */
	public Element(String code, String nom, double prixAchat, double quantite, String unite, double prixVente) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixAchat(prixAchat);
		this.setPrixVente(prixVente);
	}
	
	/**
	 * Construit un ELement avec un prix d'achat
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param unite
	 */
	public Element(String code, String nom, double prixAchat, double quantite, String unite) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixAchat(prixAchat);
	}
	
	/**
	 * Construit un Element avec un prix de vente
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 * @param prixVente
	 */
	public Element(String code, String nom, double quantite, String unite, double prixVente) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixVente(prixVente);
	}

	/**
	 * Retourne le code de l'Element
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Retourne la quantite de l'Element
	 * @return quantite
	 */
	public double getQuantite() {
		return quantite;
	}

	/**
	 * Modifie la quantite de l'Element
	 * @param quantite
	 */
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	/**
	 * Retourne l'unité de quantité de l'Element
	 * @return uniteQuantite
	 */
	public String getUniteQuantite() {
		return this.unite;
	}

	/**
	 * Retourne le prix d'achat de l'Element
	 * @return prixAchat
	 */
	public double getPrixAchat() {
		return prixAchat;
	}

	/**
	 * Modifie le prix d'achat de l'Element
	 * @param prixAchat
	 */
	public void setPrixAchat(double prixAchat) {
		this.prixAchat = prixAchat;
	}

	/**
	 * Retourne le prix de vente de l'Element
	 * @return prixVente
	 */
	public double getPrixVente() {
		return prixVente;
	}

	/**
	 * Modifie le prix de vente de l'Element
	 * @param prixVente
	 */
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * Retourne le nom de l'Element
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}
	
	 /* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Element clone() throws CloneNotSupportedException {   
		return (Element)super.clone();
	}

}
