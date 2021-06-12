package model;

import java.io.Serializable;

public class Day implements Serializable {

	private static final long serialVersionUID = 1L;

	private Time initialHour;
	private Time finishHour;
	private Days day;

	/**
	 * Constructor of the Day class. Creates a new Day object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> A new Day object has been created
	 */
	public Day() {
		initialHour = new Time();
		finishHour = new Time();
		day = Days.LUNES;
	}//End Day constructor

	/**
	 * Constructor of the Day class. Creates a new Day object <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> A new Day object has been created
	 * @param day the day
	 * @param initialHour the initial hour of the day
	 * @param finishHour the finish hour of the day
	 */
	public Day(Days day,Time initialHour, Time finishHour) {
		this.finishHour = finishHour;
		this.initialHour = initialHour;
		this.day = day;
	}//End Day constructor

	/**
	 * returns the initial hour<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the initial hour of the day
	 */
	public Time getInitialHour() {
		return this.initialHour;
	}//End getInitialHour

	/**
	 * changes the initial hour of the day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the initial hour has been changed
	 * @param initialHour the new initial hour
	 */
	public void setInitialHour(Time initialHour) {
		this.initialHour = initialHour;
	}//End setInitialHour

	/**
	 * returns the finish hour of the day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the finish hour of the day
	 */
	public Time getFinishHour() {
		return this.finishHour;
	}//End getFinishHour

	/**
	 * changes the finish hour of the day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the finish hour has been changed
	 * @param finishHour the new finish hour
	 */
	public void setFinishHour(Time finishHour) {
		this.finishHour = finishHour;
	}//End setFinishHour

	/**
	 * returns the day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the day
	 */
	public Days getDay() {
		return day;
	}//End getDay

	/**
	 * changes the day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the day has been changed
	 * @param day the new day
	 */
	public void setDay(Days day) {
		this.day = day;
	}//End setDay

}//End Day