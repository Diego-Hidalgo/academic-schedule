package model;

import java.io.Serializable;

public class Grade implements Comparable<Grade>, Serializable {

	private static final long serialVersionUID = 1L;

	private double grade;
	private double percentage;
	private String description;
	private Grade parent;
	private Grade rigth;
	private Grade left;

	/**
	 * Constructor of the Grade class. Creates a new Grade object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Grade object has been created.
	 */
	public Grade() {
		grade = 0;
		percentage = 0;
		description = new String();
	}//End Grade constructor

	/**
	 * Constructor of the Grade class. Creates a new Grade object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Grade object has been created.
	 * @param grade
	 * @param percentage
	 * @param description
	 */
	public Grade(double grade, double percentage, String description) {
		this.grade = grade;
		this.percentage = percentage;
		this.description = description;
	}//End Grade constructor

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getGrade() {
		return this.grade;
	}

	public double getPercentage() {
		return this.percentage;
	}

	public double getAverageGrade() {
		return (grade*percentage)/100;
	}//End 

	public Grade getRigth() {
		return rigth;
	}

	public void setRigth(Grade rigth) {
		this.rigth = rigth;
	}

	public Grade getLeft() {
		return left;
	}

	public void setLeft(Grade left) {
		this.left = left;
	}

	public Grade getParent() {
		return parent;
	}

	public void setParent(Grade parent) {
		this.parent = parent;
	}

	/**
	 * returns the information of the description and the value of the grade in a String<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the String that contains the indicated information
	 */
	@Override
	public String toString(){
		return description + " " + grade;
	}

	/**
	 * Compares this Grade object with a given Grade object<br>
	 *     <b>pre:</b> the param is not null
	 *     <b>post:</b> a numeric value that indicates the comparison
	 * @param o the other object to compare
	 */
	@Override
	public int compareTo(Grade o) {
		int result = 0;
		if(o.grade > grade)
			result = 1;
		else if(o.grade < grade)
			result = -1;
		return result;
	}//End compareTo

}//End Grade