package model;

import java.io.Serializable;
import java.util.Date;

public class Event extends Plan implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date date;

	/**
	 * Constructor of the Event class. Creates a new Event object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> A new Event object has been created
	 */
	public Event() {
		super();
		date = new Date();
	}//End Event constructor

	/**
	 * Constructor of the Event class. Creates a new Event object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> A new Event object has been created
	 * @param date the date of the event
	 * @param description the description of the event
	 * @param title the title of the event
	 * @param day the day of the event
	 * @param course the course related to the event
	 */
	public Event(Date date, String description, String title, Day day,Course course) {
		super(title,description,day,course);
		this.date = date;
	}//End Event constructor

	/**
	 * returns the date attribute<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the date attribute
	 */
	public Date getDate() {
		return this.date;
	}//End getDate constructor

	/**
	 * changes the date of the event<br>
	 *     <b>pre:</b>
	 *     <b>post;</b> the date has been changed.
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}//End setDate

}//End Event