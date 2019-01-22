/**
 * 
 */
package modele;

/**
 * @author tovarich
 *
 */
public class MatierePremiere extends Element {

	/**
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param uniteQuantite
	 */
	public MatierePremiere(String code, String nom, double prixAchat, double quantite, UniteQuantite unite) {
		super(code, nom, prixAchat, quantite, unite);
	}
	
	@Override
	public double getPrixVente() {
		return -1;
	}
	
	@Override
	public void setPrixVente(double prixVente) {
	}
	
	@Override
	public String toString() {
		return "Matière première "+this.getNom()+" ("+this.getCode()+"): "+this.getQuantite()+" "+this.getUniteQuantite()+" Prix achat: "+this.getPrixAchat()+"€";
	}

}
