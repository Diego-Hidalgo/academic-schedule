package model;

import java.util.Date;

public class Day {

	private Date initialHour;
	private Date finishHour;

	public Day() {
		// TODO - implement Day.Day
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param initialHour
	 * @param finishHour
	 */
	public Day(Date initialHour, Date finishHour) {
		// TODO - implement Day.Day
		throw new UnsupportedOperationException();
	}

	public Date getInitialHour() {
		return this.initialHour;
	}

	/**
	 * 
	 * @param initialHour
	 */
	public void setInitialHour(Date initialHour) {
		this.initialHour = initialHour;
	}

	public Date getFinishHour() {
		return this.finishHour;
	}

	/**
	 * 
	 * @param finishHour
	 */
	public void setFinishHour(Date finishHour) {
		this.finishHour = finishHour;
	}

}