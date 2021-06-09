package exceptions;

public class OutOfTimeRangeException extends Exception{

	private static final long serialVersionUID = 1L;
	private int incorrectTime;
	
	public OutOfTimeRangeException(int incorrectTime){
		super("\'"+incorrectTime + "\' is out of time range");
		this.incorrectTime =  incorrectTime;
	}//End incorrectTime
	
	public OutOfTimeRangeException(int incorrectTime, String timeName){
		super("\'"+incorrectTime + "\' is out of "+timeName+" range");
		this.incorrectTime =  incorrectTime;
	}//End incorrectTime
	
	public int getIncorrectTime(){
		return incorrectTime;
	}//End getIncorrectTime
}
