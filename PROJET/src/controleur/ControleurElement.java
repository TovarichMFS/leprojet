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
	
	public void changeQuantite(double nQuantite) {
		this.e.setQuantite(nQuantite);
	}

}
