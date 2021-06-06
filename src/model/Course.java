package model;

import java.util.Date;
import java.util.ArrayList;

public class Course {

	private String name;
	private int credits;
	private Status status;
	private Grade gradesRoot;
	private Course prev;
	private Course next;
	private ArrayList<Day> days;
	
	public Course() {
		name = new String();
		credits = 0;
		status = Status.MATRICULADO;
		gradesRoot = new Grade();
		days = new ArrayList<Day>();
	}//End Course constructor

	/**
	 * 
	 * @param name
	 * @param credit
	 * @param day
	 */
	public Course(String name, int credit, ArrayList<Day> days) {
		this.name = name;
		this.credits = credit;
		status = Status.MATRICULADO;
		this.days = days;
	}//End Course constructor

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}//End setName

	public String getName() {
		return this.name;
	}//End getName
	/**
	 * 
	 * @param credit
	 */
	public void setCredit(int credit) {
		credits = credit;
	}//End setCredit

	public int getCredits() {
		return this.credits;
	}//End getCredit

	/**
	 * 
	 * @param day
	 * @param initialHour
	 * @param finishHour
	 */
	public void addDays(Days day, Date initialHour, Date finishHour) {
		days.add(new Day(day,initialHour,finishHour));
	}//End addDays

	/**
	 * 
	 * @param dayToEdit
	 * @param day
	 * @param initialHour
	 * @param finishHour
	 */
	public void editDay(Day dayToEdit, Days day, Date initialHour, Date finishHour) {
		dayToEdit.setDay(day);
		dayToEdit.setInitialHour(initialHour);
		dayToEdit.setFinishHour(finishHour);
	}//End editDay

	/**
	 * 
	 * @param dayToDelete
	 */
	public void deleteDay(Day dayToDelete) {
		days.remove(dayToDelete);
	}//End deleteDay

	/**
	 * 
	 * @param grade
	 * @param percentage
	 * @param description
	 */
	public void addGrade(double grade, double percentage, String description) {
		Grade toAdd = new Grade(grade,percentage,description);
		if(gradesRoot == null)
			gradesRoot = toAdd;
		else
			addGrade(gradesRoot,toAdd);
	}//End addGrade
	
	private void addGrade(Grade current, Grade toAdd){
		if(current.compareTo(toAdd) >= 0){
			if(current.getRigth() == null){
				current.setRigth(toAdd);
				current.getRigth().setParent(current);
			}else
				addGrade(current.getRigth(),toAdd);
		}else{
			if(current.getLeft() == null){
				current.setLeft(toAdd);
				current.getLeft().setParent(current);
			}else
				addGrade(current.getLeft(),toAdd);
		}//End else
	}//End addGrade
	
	/**
	 * 
	 * @param gradeToEdit
	 * @param newGrade
	 * @param newPercentage
	 * @param newDescription
	 */
	public void editGrade(Grade gradeToEdit, double newGrade, double newPercentage, String newDescription) {
		gradeToEdit.setGrade(newGrade);
		gradeToEdit.setPercentage(newPercentage);
		gradeToEdit.setDescription(newDescription);
	}//End editGrade

	/**
	 * 
	 * @param gradeToDelete
	 */
	public void deleteGrade(Grade gradeToDelete) {
		
	}//End deleteGrade

	public Grade getGradeRoot() {
		return gradesRoot;
	}//End getGrades

	/**
	 * 
	 * @param oldGrade
	 * @param newGrade
	 */

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Grade getGradesRoot() {
		return gradesRoot;
	}

	public void setGradesRoot(Grade gradesRoot) {
		this.gradesRoot = gradesRoot;
	}

	public Course getPrev() {
		return prev;
	}

	public void setPrev(Course prev) {
		this.prev = prev;
	}

	public Course getNext() {
		return next;
	}

	public void setNext(Course next) {
		this.next = next;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public ArrayList<Day> getDays() {
		return days;
	}

	public void setDays(ArrayList<Day> days) {
		this.days = days;
	}
	
}