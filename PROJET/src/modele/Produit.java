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
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param uniteQuantite
	 * @param prixVente
	 */
	public Produit(String code, String nom, double prixAchat, int quantite, UniteQuantite unite, double prixVente) {
		super(code, nom, prixAchat, quantite, unite, prixVente);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param uniteQuantite
	 */
	public Produit(String code, String nom, double prixAchat, int quantite, UniteQuantite unite) {
		super(code, nom, prixAchat, quantite, unite);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param nom
	 * @param quantite
	 * @param uniteQuantite
	 * @param prixVente
	 */
	public Produit(String code, String nom, int quantite, UniteQuantite unite, double prixVente) {
		super(code, nom, quantite, unite, prixVente);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		String prix = ""; 
		if(this.getPrixAchat()!=0.0)
			prix += " Prix Achat: "+this.getPrixAchat()+" €";
		if(this.getPrixVente()!=0.0)
			prix += " Prix Vente: "+this.getPrixVente()+" €";
			
		return "Produit "+this.getNom()+" ("+this.getCode()+"): "+this.getQuantite()+" "+this.getUniteQuantite()+prix;
	}

}
