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
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}


}
