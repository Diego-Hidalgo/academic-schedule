package exceptions;

public class InvalidCredentialsException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException() {
        super("Las credenciales que ha ingresado son incorrectas");
    }//End Constructor1

}//End InvalidCredentialsException
