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
	 * Construit une MatierePremiere
	 * @param code
	 * @param nom
	 * @param prixAchat
	 * @param quantite
	 * @param uniteQuantite
	 */
	public MatierePremiere(String code, String nom, double prixAchat, double quantite, String unite) {
		super(code, nom, prixAchat, quantite, unite);
	}
	
	/* (non-Javadoc)
	 * @see modele.Element#getPrixVente()
	 */
	@Override
	public double getPrixVente() {
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see modele.Element#setPrixVente(double)
	 */
	@Override
	public void setPrixVente(double prixVente) {
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Matière première "+this.getNom()+" ("+this.getCode()+"): "+this.getQuantite()+" "+this.getUniteQuantite()+" Prix achat: "+this.getPrixAchat()+"€";
	}

}
