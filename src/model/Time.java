package model;

import exceptions.InvalidTimeFormatException;
import exceptions.OutOfTimeRangeException;
import java.io.Serializable;

public class Time implements Comparable<Time>, Serializable {

	private static final long serialVersionUID = 1L;
	
	final private int MAXHOUR = 23;
	final private int MAXMINUTE = 59;
	final private int MAXSECOND = 59;
	
	private int hour;
	private int minute;
	private int second;

	/**
	 * Constructor of the Time object. Creates a new Time object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Time object has been created.
	 */
	public Time(){
		hour = 0;
		minute = 0;
		second = 0;
	}//End Time constructor

	/**
	 * Constructor of the Time object. Creates a new Time object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Time object has been created
	 * @param h the hour of the Time
	 * @param m the minutes of the Time
	 */
	public Time(int h, int m) throws OutOfTimeRangeException{
		setTime(h,m,0);
	}//End Time constructor

	/**
	 * Constructor of the Time object. Creates a new Time object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Time object has been created
	 * @param h
	 * @param m
	 * @param s
	 */
	public Time(int h, int m, int s) throws OutOfTimeRangeException{
		setTime(h,m,s);
	}//End Time constructor

	/**
	 * Constructor of the Time object. Creates a new Time object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Time object has been created
	 * @param time
	 */
	public Time(String time) throws OutOfTimeRangeException, InvalidTimeFormatException{
		setTimeFromString(time);
	}//End Time constructor

	/**
	 * sets the attributes of this class Time <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the time attribute have been set
	 * @param h the hours to set the time
	 * @param m the minutes to set the time
	 * @param s the seconds to set the time
	 */
	public void setTime(int h, int m, int s) throws OutOfTimeRangeException{
		if(h < 0 || h > MAXHOUR)
			throw new OutOfTimeRangeException(h,"hours");
		if(m < 0 || m > MAXMINUTE)
			throw new OutOfTimeRangeException(m,"minutes");
		if(s < 0 || s > MAXSECOND)
			throw new OutOfTimeRangeException(s,"seconds");
		hour = h;
		minute = m;
		second = s;
	}//End setTime

	/**
	 * sets the time attributes from a String<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the time attributes have been set from the String
	 * @param time the String used to set the times attributes
	 */
	public void setTimeFromString(String time) throws InvalidTimeFormatException, OutOfTimeRangeException{
		String[] splitTime = time.split(":");
		if(splitTime.length < 2 || splitTime.length > 3)
			throw new InvalidTimeFormatException(time);
		try{
			int h = Integer.parseInt(splitTime[0]);
			int m = Integer.parseInt(splitTime[1]);
			int s = (splitTime.length == 2)?0:Integer.parseInt(splitTime[2]);
			setTime(h,m,s);
		}catch(NumberFormatException e){
			throw new InvalidTimeFormatException(time);
		}
	}//End setTimeFromString
	
	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	/**
	 * Returns a String that contains the information formatted of the Time.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the String that contains the information of the Time
	 */
	@Override
	public String toString(){
		String time = String.format("%02d:%02d",hour,minute);
		time += (second != 0)?String.format(":%02d",second):"";
		return time;
	}

	/**
	 * Compares the numeric comparison of this object hour with the given Time<br>
	 *     <b>pre:</b> time is not null
	 *     <b>post:</b> the numeric comparison
	 * @param time the time to compare to
	 */
	private int compareHour(Time time){
		int compareResult = -1;
		if(hour == time.hour){
			compareResult = 0;
		}else if(hour > time.hour){
			compareResult = 1;
		}//End if..else
		return compareResult;
	}//End compareHours

	/**
	 * returns the comparison between this object and other using the minutes<br>
	 *     <b>pre:</b> time is not null
	 *     <b>post:</b> the numeric comparison
	 * @param time the time to compare to
	 */
	private int compareMinutes(Time time){
		int compareResult = -1;
		if(minute == time.minute){
			compareResult = 0;
		}else if(minute > time.minute){
			compareResult = 1;
		}//End if..else
		return compareResult;
	}//End compareMinutes

	/**
	 * returns the comparison between this object and other using the seconds<br>
	 *     <b>pre:</b> time is not null
	 *     <b>post:</b> the numeric comparison
	 * @param time the time to compare to
	 */
	private int compareSeconds(Time time){
		int compareResult = -1;
		if(second == time.second){
			compareResult = 0;
		}else if(second > time.second){
			compareResult = 1;
		}//End if..else
		return compareResult;
	}//End compareSeconds

	/**
	 * compares this Time with a given time using 3 comparison params<br>
	 *     <b>pre:</b> time is not null
	 *     <b>post:</b> the numeric comparison between the times
	 * @param time the time to compare to
	 */
	@Override
	public int compareTo(Time time) {
		int compareResult = compareHour(time);
		if(compareResult == 0){
			compareResult = compareMinutes(time);
		}
		if(compareResult == 0){
			compareResult = compareSeconds(time);
		}//End else
		return compareResult;
	}//End compareTo

}//End Time
