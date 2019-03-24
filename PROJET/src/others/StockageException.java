/**
 * 
 */
package others;

/**
 * @author tovarich
 *
 */
public class StockageException extends Exception {
	private String code;

	/**
	 * 
	 */
	public StockageException(String code) {
		super("La production est impossible (Stockage "+code+" plein!)");
		this.setCode(code);
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}


}
