package model;

import java.io.Serializable;
import java.util.Date;

public class Notify implements Comparable<Notify>, Serializable {

	private static final long serialVersionUID = 1L;

	private Time toSendAtHour;
	private Event event;

	/**
	 * Constructor of the class Notify. Creates a new Notify object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Notify object has been created.
	 */
	public Notify(){
		toSendAtHour = new Time();
		event = new Event();
	}//End Notify constructor

	/**
	 * Constructor of the class Notify. Creates a new Notify object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Notify object has been created
	 * @param tSAH the hour when the notification will be sent
	 * @param date the date of the notification
	 * @param description the description of the notification
	 * @param title the title of the notification
	 * @param day the day of the notification
	 * @param course the course related with the notification
	 */
	public Notify(Time tSAH,Date date, String description, String title, Day day,Course course){
		toSendAtHour = tSAH;
		setEvent(date,description,title,day,course);
	}//End Notify constructor

	/**
	 * returns the time when the notification will be sent.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the time when the notification will be sent
	 */
	public Time getToSendAtHour() {
		return this.toSendAtHour;
	}

	/**
	 * changes the time when the notification will be sent.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the time has been changed
	 * @param toSendAtHour the new time when the notification will be sent
	 */
	public void setToSendAtHour(Time toSendAtHour) {
		this.toSendAtHour = toSendAtHour;
	}


	/**
	 * returns the event of the notification<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Changes all the attributes of the event object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the data of the event has been changed
	 * @param date the new date
	 * @param description the new description
	 * @param title the new title
	 * @param day the new day
	 * @param course the new related course
	 */
	public void setEvent(Date date, String description, String title, Day day,Course course) {
		this.event = new Event(date,description,title,day,course);
	}

	/**
	 * returns a numeric value that represents the comparison between two notify objects<br>
	 *     <b>pre:</b> the param is not null
	 *     <b>post:</b> the comparison made by Time.compareTo
	 * @param toCompare the other object Notify used to make the comparison
	 */
	@Override
	public int compareTo(Notify toCompare) {
		return toCompare.toSendAtHour.compareTo(toSendAtHour);
	}//End compareTo

}//End Notify