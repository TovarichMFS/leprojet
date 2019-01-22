/**
 * 
 */
package modele;

/**
 * @author tovarich
 *
 */
public enum UniteQuantite {
	KILO ("kg"),
	LITRE ("L"),
	MCUBE ("m3"),
	UNIT ("unités");
	
	private String name;
	private UniteQuantite(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String toString() {
		return name;
	}

}
