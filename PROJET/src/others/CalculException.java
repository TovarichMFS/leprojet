/**
 * 
 */
package others;

/**
 * @author tovarich
 *
 */
public class CalculException extends Exception {
	private String code;

	/**
	 * 
	 */
	public CalculException(String code) {
		super("La production est impossible (Entrant "+code+" ne peut pas être acheté)");
		this.setCode(code);
	}

	/**
	 * Retourne le code de l'Element entrant manquant
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Remplace le code de l'Element entrant manquant
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
