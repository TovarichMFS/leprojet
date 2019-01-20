package modele;

public abstract class Element {
	private String code, nom, uniteQuantite;
	private int quantite;
	private double prixAchat,prixVente;

	public Element(String code, String nom, double prixAchat, int quantite, String uniteQuantite, double prixVente) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.uniteQuantite = uniteQuantite;
		this.setPrixAchat(prixAchat);
		this.setPrixVente(prixVente);
	}
	
	public Element(String code, String nom, double prixAchat, int quantite, String uniteQuantite) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.uniteQuantite = uniteQuantite;
		this.setPrixAchat(prixAchat);
	}
	
	public Element(String code, String nom, int quantite, String uniteQuantite, double prixVente) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.uniteQuantite = uniteQuantite;
		this.setPrixVente(prixVente);
	}

	public String getCode() {
		return code;
	}

	/**
	 * @return the quantite
	 */
	public int getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the uniteQuantite
	 */
	public String getUniteQuantite() {
		return uniteQuantite;
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

}
