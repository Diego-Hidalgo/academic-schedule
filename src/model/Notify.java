package model;

import java.io.Serializable;
import java.util.Date;

public class Notify implements Comparable<Notify>, Serializable {

	private static final long serialVersionUID = 1L;

	private Time toSendAtHour;
	private Event event;
	
	public Notify(){
		toSendAtHour = new Time();
		event = new Event();
	}//End Notify constructor
	
	public Notify(Time tSAH,Date date, String description, String title, Day day,Course course){
		toSendAtHour = tSAH;
		setEvent(date,description,title,day,course);
	}//End Notify constructor
	
	public Time getToSendAtHour() {
		return this.toSendAtHour;
	}

	/**
	 * 
	 * @param toSendAtHour
	 */
	public void setToSendAtHour(Time toSendAtHour) {
		this.toSendAtHour = toSendAtHour;
	}


	public Event getEvent() {
		return event;
	}

	public void setEvent(Date date, String description, String title, Day day,Course course) {
		this.event = new Event(date,description,title,day,course);
	}

	@Override
	public int compareTo(Notify toCompare) {
		return toCompare.toSendAtHour.compareTo(toSendAtHour);
	}//End compareTo

}