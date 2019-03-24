package modele;

public abstract class Element implements Cloneable{
	private String code, nom;
	private String unite;
	private double quantite,prixAchat,prixVente;
	private int demande;
	private String stockage;
	
	/**
	 * Construit un Element sans prix
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 */
	public Element(String code, String nom, double quantite, String unite,String stockage, int demande) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.stockage = stockage;
		this.setDemande(demande);
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
	public Element(String code, String nom, double prixAchat, double quantite, String unite, double prixVente,String stockage,int demande) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixAchat(prixAchat);
		this.setPrixVente(prixVente);
		this.stockage = stockage;
		this.setDemande(demande);
	}
	
	/**
	 * Construit un ELement avec un prix d'achat
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param unite
	 */
	public Element(String code, String nom, double prixAchat, double quantite, String unite,String stockage,int demande) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixAchat(prixAchat);
		this.stockage = stockage;
		this.setDemande(demande);
	}
	
	/**
	 * Construit un Element avec un prix de vente
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 * @param prixVente
	 */
	public Element(String code, String nom, double quantite, String unite, double prixVente,String stockage,int demande) {
		this.code = code;
		this.nom = nom;
		this.setQuantite(quantite);
		this.unite = unite;
		this.setPrixVente(prixVente);
		this.stockage = stockage;
		this.setDemande(demande);
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
	
	/**
	 * Modifie le nom de l'Element
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	 /**
	  * Retourne la demande de l'Element
	 * @return demande
	 */
	public int getDemande() {
		return demande;
	}


	/**
	 * Modifie la demande de l'Element
	 * @param demande
	 */
	public void setDemande(int demande) {
		this.demande = demande;
	}


	/**
	 * @return the stockage
	 */
	public String getStockage() {
		return stockage;
	}


	/**
	 * @param stockage the stockage to set
	 */
	public void setStockage(String stockage) {
		this.stockage = stockage;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Element clone() throws CloneNotSupportedException {   
		return (Element)super.clone();
	}

}
