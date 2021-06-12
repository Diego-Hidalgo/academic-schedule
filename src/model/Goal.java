package model;

import java.io.Serializable;

public class Goal implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;
	private boolean status;

	/**
	 * Constructor of the Goal class. Creates a new Goal object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Goal object has been created.
	 */
	public Goal() {
		description = new String();
		status = false;
	}//End Goal constructor

	/**
	 * Constructor of the Goal class. Creates a new Goal object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Goal object has been created
	 * @param description the description of the goal
	 */
	public Goal(String description){
		this.description = description;
		status = false;
	}//End Goal constructor

	/**
	 * Constructor of the Goal class. Creates a new Goal object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Goal object has been created
	 * @param description the description of the goal
	 * @param status the current status of the goal.
	 */
	public Goal(String description, boolean status) {
		this.description = description;
		this.status = status;
	}//End Goal constructor

	/**
	 * changes the description of the goal.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the description has been changed
	 * @param d the new description
	 */
	public void setDescription(String d) {
		description = d;
	}//End setDescription

	/**
	 * returns the description of the goal<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the description
	 */
	public String getDescription() {
		return description;
	}//End getDescription

	/**
	 * changes the status of the goal<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the status has been changed
	 * @param s the new status
	 */
	public void setStatus(boolean s) {
		status = s;
	}//End setStatus

	/**
	 * returns the status of the goal<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the status
	 */
	public boolean getStatus() {
		return status;
	}//End getStatus

}//End Goal
