package exceptions;

public class UserNameAlreadyInUseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;

    public UserNameAlreadyInUseException(String userName) {
        super("El nombre de usuario ya se encuentra en uso");
        this.userName = userName;
    }//End Constructor1

    public String getUserName() {
        return userName;
    }//End getUserName

    public void setUserName(String userName) {
        this.userName = userName;
    }//End setUserName

}//End UserNameAlreadyInUseException
