package model;

import java.io.Serializable;

public abstract class Plan implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;
	private String title;
	private Day	day;
	private Course course;

	/**
	 * The constructor of the Plan class. Creates a new Plan object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Plan object has been created.
	 */
	public Plan() {
		this.description = new String();
		this.title = new String();
		day = new Day();
		course = new Course();
	}//End Plan constructor1
	
	/**
	 * Constructor of the Plan class<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Plan object has been created
	 * @param title the title of the plan
	 * @param description the description of the plan
	 * @param day the day of the plan
	 * @param course the course related to the plan
	 */
	public Plan(String title,String description, Day day,Course course) {
		this.description = description;
		this.title = title;
		this.day = day;
		this.course = course;
	}//End Plan constructor2

	public String getDescription() {
		return this.description;
	}//End getDescription

	public void setDescription(String description) {
		this.description = description;
	}//End setDescription

	public String getTitle() {
		return this.title;
	}//End getTitle

	public void setTitle(String title) {
		this.title = title;
	}//End setTitle
	
	public Day getDay() {
		return day;
	}//End getday

	public void setDay(Day day) {
		this.day = day;
	}//End setday

	public Course getCourse() {
		return course;
	}//End getCourse

	public void setCourse(Course course) {
		this.course = course;
	}//End setCourse

}//End Plan