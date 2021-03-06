/**
 * 
 */
package modele;

/**
 * @author tovarich
 *
 */
public class Produit extends Element {
	
	/**
	 * Construit un Produit sans prix
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param unite
	 */
	public Produit(String code, String nom, double quantite, String unite,String stockage,int demande) {
		super(code,nom,quantite,unite,stockage,demande);
	}

	/**
	 * Construit un Produit avec prix d'achat et de vente
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param uniteQuantite
	 * @param prixVente
	 */
	public Produit(String code, String nom, double prixAchat, double quantite, String unite, double prixVente,String stockage,int demande) {
		super(code, nom, prixAchat, quantite, unite, prixVente,stockage,demande);
	}

	/**
	 * Construit un Produit avec un prix de vente
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param uniteQuantite
	 * @param prixVente
	 */
	public Produit(String code, String nom, double quantite, String unite, double prixVente,String stockage,int demande) {
		super(code, nom, quantite, unite, prixVente,stockage,demande);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String prix = ""; 
		if(this.getPrixAchat()!=0.0)
			prix += " Prix Achat: "+this.getPrixAchat()+" €";
		if(this.getPrixVente()!=0.0)
			prix += " Prix Vente: "+this.getPrixVente()+" €";
			
		return "Produit "+this.getNom()+" ("+this.getCode()+"): "+this.getQuantite()+" "+this.getUniteQuantite()+prix+" Demande: "+this.getDemande()+" "+this.getStockage();
	}

}
