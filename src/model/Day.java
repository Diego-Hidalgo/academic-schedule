package model;

import java.util.Date;

public class Day {

	private Date initialHour;
	private Date finishHour;
	private Days day;
	
	public Day() {
		initialHour = new Date();
		finishHour = new Date();
		day = Days.LUNES;
	}//End Day constructor

	/**
	 * 
	 * @param initialHour
	 * @param finishHour
	 */
	public Day(Days day,Date initialHour, Date finishHour) {
		this.finishHour = finishHour;
		this.initialHour = initialHour;
		this.day = day;
	}//End Day constructor

	public Date getInitialHour() {
		return this.initialHour;
	}//End getInitialHour

	/**
	 * 
	 * @param initialHour
	 */
	public void setInitialHour(Date initialHour) {
		this.initialHour = initialHour;
	}//End setInitialHour

	public Date getFinishHour() {
		return this.finishHour;
	}//End getFinishHour

	/**
	 * 
	 * @param finishHour
	 */
	public void setFinishHour(Date finishHour) {
		this.finishHour = finishHour;
	}//End setFinishHour

	public Days getDay() {
		return day;
	}//End getDay

	public void setDay(Days day) {
		this.day = day;
	}//End setDay

}//End Day