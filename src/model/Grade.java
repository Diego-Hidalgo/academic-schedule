package model;

public class Grade {

	private double grade;
	private double percentage;
	private String description;

	public Grade() {
		// TODO - implement Grade.Grade
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param grade
	 * @param percentage
	 * @param description
	 */
	public Grade(double grade, double percentage, String description) {
		// TODO - implement Grade.Grade
		throw new UnsupportedOperationException();
	}

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

	public double getGrade() {
		return this.grade;
	}

	public double getPercentage() {
		return this.percentage;
	}

	public double getDescription() {
		// TODO - implement Grade.getDescription
		throw new UnsupportedOperationException();
	}

	public double getAverageGrade() {
		// TODO - implement Grade.getAverageGrade
		throw new UnsupportedOperationException();
	}

}