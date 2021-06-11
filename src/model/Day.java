package model;

import java.io.Serializable;

public class Day implements Serializable {

	private static final long serialVersionUID = 1L;

	private Time initialHour;
	private Time finishHour;
	private Days day;
	
	public Day() {
		initialHour = new Time();
		finishHour = new Time();
		day = Days.LUNES;
	}//End Day constructor

	/**
	 * 
	 * @param initialHour
	 * @param finishHour
	 */
	public Day(Days day,Time initialHour, Time finishHour) {
		this.finishHour = finishHour;
		this.initialHour = initialHour;
		this.day = day;
	}//End Day constructor

	public Time getInitialHour() {
		return this.initialHour;
	}//End getInitialHour

	/**
	 * 
	 * @param initialHour
	 */
	public void setInitialHour(Time initialHour) {
		this.initialHour = initialHour;
	}//End setInitialHour

	public Time getFinishHour() {
		return this.finishHour;
	}//End getFinishHour

	/**
	 * 
	 * @param finishHour
	 */
	public void setFinishHour(Time finishHour) {
		this.finishHour = finishHour;
	}//End setFinishHour

	public Days getDay() {
		return day;
	}//End getDay

	public void setDay(Days day) {
		this.day = day;
	}//End setDay

}//End Day