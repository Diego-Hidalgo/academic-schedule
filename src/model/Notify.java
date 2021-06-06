package model;

import java.util.Date;

public class Notify {

	private Date toSendAtHour;

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

	/**
	 * 
	 * @param event
	 */
	public void addEvent(Event event) {
		// TODO - implement Notify.addEvent
		throw new UnsupportedOperationException();
	}

}