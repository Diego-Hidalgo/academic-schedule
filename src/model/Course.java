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

	/**
	 * Constructor of the Course class. Creates a new Course object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Course object has been created.
	 */
	public Course() {
		name = new String();
		credits = 0;
		status = Status.MATRICULADO;
		gradesRoot = new Grade();
		days = new ArrayList<Day>();
	}//End Course constructor

	/**
	 * Constructor of the Course class. Creates a new Course object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Course object has been created with the given parameters.
	 * @param name the name of the Course
	 * @param credit the amount of credits of the course
	 * @param days days the course is taken
	 */
	public Course(String name, int credit, ArrayList<Day> days) {
		this.name = name;
		this.credits = credit;
		status = Status.MATRICULADO;
		this.days = days;
	}//End Course constructor

	public void setName(String name) {
		this.name = name;
	}//End setName

	public String getName() {
		return this.name;
	}//End getName

	public void setCredit(int credit) {
		credits = credit;
	}//End setCredit

	public int getCredits() {
		return this.credits;
	}//End getCredit

	/**
	 * returns a list that contains all the elements of the Grade tree <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list that contains all the elements.
	 * @param current the current Grade used to traverse the tree
	 */
	private List<Grade> getGradesInList(Grade current) {
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

	/**
	 * returns a list that contains all the elements of the Grade tree<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list that contains all the elements
	 */
	public List<Grade> getGradesInList() {
		if(gradesRoot == null) {
			return null;
		} else {
			return getGradesInList(gradesRoot);
		}//End if/else
	}//End getGradesInList

	/**
	 * Adds a new day to the course<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new day has been added
	 * @param day the day of the class
	 * @param initialHour the initial hour
	 * @param finishHour the finish hour
	 */
	public void addDays(Days day, Time initialHour, Time finishHour) {
		days.add(new Day(day,initialHour,finishHour));
	}//End addDays

	/**
	 * Edits the information of a day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the day has been changed
	 * @param dayToEdit the object of the day that will be edited
	 * @param day the new day
	 * @param initialHour the new initial hour
	 */
	public void editDay(Day dayToEdit, Days day, Time initialHour, Time finishHour) {
		dayToEdit.setDay(day);
		dayToEdit.setInitialHour(initialHour);
		dayToEdit.setFinishHour(finishHour);
	}//End editDay

	/**
	 * deletes a day from the list of days <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the day has been deleted
	 * @param dayToDelete the object of the day to delete
	 */
	public void deleteDay(Day dayToDelete) {
		days.remove(dayToDelete);
	}//End deleteDay

	/**
	 * adds a new grade to the course<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new grade has been added
	 * @param grade the value of the new grade
	 * @param percentage the percentage of the new grade
	 * @param description the description of the new grade
	 */
	public void addGrade(double grade, double percentage, String description) {
		Grade toAdd = new Grade(grade,percentage,description);
		if(gradesRoot == null)
			gradesRoot = toAdd;
		else
			addGrade(gradesRoot,toAdd);
	}//End addGrade

	/**
	 * adds a new grade to the course.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new grade has been added
	 * @param current the current grade used to traverse the tree.
	 * @param toAdd the object of the grade to add.
	 */
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
	 * Edits the information of a specific grade<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the grade has been changed
	 * @param gradeToEdit the object of the grade to edit
	 * @param newGrade the new grade value
	 * @param newPercentage the new percentage value
	 * @param newDescription the new description of the grade
	 */
	public void editGrade(Grade gradeToEdit, double newGrade, double newPercentage, String newDescription) {
		gradeToEdit.setGrade(newGrade);
		gradeToEdit.setPercentage(newPercentage);
		gradeToEdit.setDescription(newDescription);
	}//End editGrade

	/**
	 * Deletes an specific grade<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the grade has been deleted
	 * @param gradeToDelete the grade given to delete
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

	/**
	 * returns a String that contains the information of the course<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the information of the course
	 */
	@Override
	public String toString(){
		return name;
	}//End toString

}//End Course