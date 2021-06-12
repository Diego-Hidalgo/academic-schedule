package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public List<Grade> getGradesInList(Grade current) {
		List<Grade> gradesList = new ArrayList<>();
		gradesList.add(current);
		if(current.getLeft() != null) {
			gradesList.addAll(getGradesInList(current.getLeft()));
		}//End if
		if(current.getRigth() != null) {
			gradesList.addAll(getGradesInList(current.getRigth()));
		}//End if
		return gradesList;
	}//End getGradesInList

	public List<Grade> getGradesInList() {
		if(gradesRoot == null) {
			return null;
		} else {
			return getGradesInList(gradesRoot);
		}//End if/else
	}//End getGradesInList

	/**
	 * 
	 * @param day
	 * @param initialHour
	 * @param finishHour
	 */
	public void addDays(Days day, Time initialHour, Time finishHour) {
		days.add(new Day(day,initialHour,finishHour));
	}//End addDays

	/**
	 * 
	 * @param dayToEdit
	 * @param day
	 * @param initialHour
	 * @param finishHour
	 */
	public void editDay(Day dayToEdit, Days day, Time initialHour, Time finishHour) {
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Grade getGradesRoot(Grade left) {
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
	
	@Override
	public String toString(){
		return name;
	}//End toString
}