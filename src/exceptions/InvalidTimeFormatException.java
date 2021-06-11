package exceptions;

public class InvalidTimeFormatException extends Exception{

	private static final long serialVersionUID = 1L;
	private String wrongFormat;
	
	public InvalidTimeFormatException(){
		super("The time was expected in format HH:MM:SS");
	}//End InvalidTimeFormatException constructor
	
	public InvalidTimeFormatException(String wf){
		super("The time was expected in format HH:MM:SS, instead "+wf+" was received");
		wrongFormat = wf;
	}//End InvalidTimeFormatException constructor
	
	public String getWrongFormat(){
		return wrongFormat;
	}
}//End InvalidTimeFormatException
