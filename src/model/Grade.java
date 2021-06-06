package model;

public class Grade implements Comparable<Grade> {

	private double grade;
	private double percentage;
	private String description;
	private Grade parent;
	private Grade rigth;
	private Grade left;
	
	public Grade() {
		grade = 0;
		percentage = 0;
		description = new String();
	}//End Grade constructor

	/**
	 * 
	 * @param grade
	 * @param percentage
	 * @param description
	 */
	public Grade(double grade, double percentage, String description) {
		this.grade = grade;
		this.percentage = percentage;
		this.description = description;
	}//End Grade constructor

	/**
	 * 
	 * @param grade
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}

	/**
	 * 
	 * @param percentage
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	/**
	 * 
	 * @param description
	 */
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