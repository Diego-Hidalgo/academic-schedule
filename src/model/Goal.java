package model;

import java.io.Serializable;

public class Goal implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;
	private boolean status;
	
	public Goal() {
		description = new String();
		status = false;
	}//End Goal constructor
	
	public Goal(String description){
		this.description = description;
		status = false;
	}//End Goal constructor
	
	public Goal(String description, boolean status) {
		this.description = description;
		this.status = status;
	}//End Goal constructor
	
	public void setDescription(String d) {
		description = d;
	}//End setDescription
	public String getDescription() {
		return description;
	}//End getDescription
	public void setStatus(boolean s) {
		status = s;
	}//End setStatus
	public boolean getStatus() {
		return status;
	}//End getStatus
	
	public String toString(){
		return description;
	}//End toString
	
}
