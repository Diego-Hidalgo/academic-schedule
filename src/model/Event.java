package model;

import java.util.Date;

public class Event extends Plan {

	private Date date;

	public Event() {
		// TODO - implement Event.Event
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param date
	 * @param description
	 * @param title
	 * @param day
	 */
	public Event(Date date, String description, String title, Day day) {
		// TODO - implement Event.Event
		throw new UnsupportedOperationException();
	}

	public Date getDate() {
		return this.date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}