package model;

import java.util.Date;

public class Course {

	private String name;
	private int credits;

	public Course() {
		// TODO - implement Course.Course
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param credit
	 * @param day
	 */
	public Course(String name, int credit, Day day) {
		// TODO - implement Course.Course
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param credit
	 */
	public void setCredit(int credit) {
		// TODO - implement Course.setCredit
		throw new UnsupportedOperationException();
	}

	public int getCredits() {
		return this.credits;
	}

	/**
	 * 
	 * @param day
	 * @param initialHour
	 * @param finishHour
	 */
	public void addDays(Day day, Date initialHour, Date finishHour) {
		// TODO - implement Course.addDays
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dayToEdit
	 * @param day
	 * @param initialHour
	 * @param finishHour
	 */
	public void editDay(Day dayToEdit, Day day, Date initialHour, Date finishHour) {
		// TODO - implement Course.editDay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dayToDelete
	 */
	public void deleteDay(Day dayToDelete) {
		// TODO - implement Course.deleteDay
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param grade
	 * @param percentage
	 * @param description
	 */
	public void addGrade(double grade, double percentage, String description) {
		// TODO - implement Course.addGrade
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gradeToEdit
	 * @param newGrade
	 * @param newPercentage
	 * @param newDescription
	 */
	public void editGrade(Grade gradeToEdit, double newGrade, double newPercentage, String newDescription) {
		// TODO - implement Course.editGrade
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gradeToDelete
	 */
	public void deleteGrade(Grade gradeToDelete) {
		// TODO - implement Course.deleteGrade
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param day
	 */
	public Day getDay(Day day) {
		// TODO - implement Course.getDay
		throw new UnsupportedOperationException();
	}

	public Grade[] getGrades() {
		// TODO - implement Course.getGrades
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param oldGrade
	 * @param newGrade
	 */
	public void editGrade(Grade oldGrade, Grade newGrade) {
		// TODO - implement Course.editGrade
		throw new UnsupportedOperationException();
	}

}