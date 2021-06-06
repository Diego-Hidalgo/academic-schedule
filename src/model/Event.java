package model;

import java.util.Date;

public class Event extends Plan {

	private Date date;

	public Event() {
		super();
		date = new Date();
	}//End Event constructor

	/**
	 * 
	 * @param date
	 * @param description
	 * @param title
	 * @param day
	 */
	public Event(Date date, String description, String title, Day day,Course course) {
		super(title,description,day,course);
		this.date = date;
	}//End Event constructor

	public Date getDate() {
		return this.date;
	}//End getDate constructor

	/**
	 * 
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}//End setDate

}