package model;

import java.util.Date;

public class Notify implements Comparable<Notify>{

	private Date toSendAtHour;
	private Event event;
	
	public Notify(){
		toSendAtHour = new Date();
		event = new Event();
	}//End Notify constructor
	
	public Notify(Date tSAH,Date date, String description, String title, Day day,Course course){
		toSendAtHour = tSAH;
		setEvent(date,description,title,day,course);
	}//End Notify constructor
	
	public Date getToSendAtHour() {
		return this.toSendAtHour;
	}

	/**
	 * 
	 * @param toSendAtHour
	 */
	public void setToSendAtHour(Date toSendAtHour) {
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