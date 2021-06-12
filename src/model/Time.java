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
	
	public Time(){
		hour = 0;
		minute = 0;
		second = 0;
	}//End Time constructor
	
	public Time(int h, int m) throws OutOfTimeRangeException{
		setTime(h,m,0);
	}//End Time constructor
	
	public Time(int h, int m, int s) throws OutOfTimeRangeException{
		setTime(h,m,s);
	}//End Time constructor
	
	public Time(String time) throws OutOfTimeRangeException, InvalidTimeFormatException{
		setTimeFromString(time);
	}//End Time constructor
	
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
	
	@Override
	public String toString(){
		String time = String.format("%02d:%02d",hour,minute);
		time += (second != 0)?String.format(":%02d",second):"";
		return time;
	}
	
	private int compareHour(Time time){
		int compareResult = -1;
		if(hour == time.hour){
			compareResult = 0;
		}else if(hour > time.hour){
			compareResult = 1;
		}//End if..else
		return compareResult;
	}//End compareHours
	
	private int compareMinutes(Time time){
		int compareResult = -1;
		if(minute == time.minute){
			compareResult = 0;
		}else if(minute > time.minute){
			compareResult = 1;
		}//End if..else
		return compareResult;
	}//End compareMinutes
	
	private int compareSeconds(Time time){
		int compareResult = -1;
		if(second == time.second){
			compareResult = 0;
		}else if(second > time.second){
			compareResult = 1;
		}//End if..else
		return compareResult;
	}//End compareSeconds
	
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
