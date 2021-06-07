package model;

import java.util.Date;
import java.util.ArrayList;

public class AcademicSchedule {

	private Course firstCourse;
	private ArrayList<StudyPlan> studyPlans;
	private ArrayList<Notify> notifies;
	
	public AcademicSchedule() {
		studyPlans = new ArrayList<StudyPlan>();
		notifies = new ArrayList<Notify>();
	}//End AcademicSchedule constructor

	/**
	 * 
	 * @param name
	 * @param credits
	 */
	public void addCourse(String name, int credits, ArrayList<Day> days) {
		Course toAdd = new Course(name,credits,days);
		if(firstCourse == null) {
			firstCourse = toAdd;
			firstCourse.setNext(toAdd);
			firstCourse.setPrev(toAdd);
		}else
			addCourse(firstCourse,toAdd);
	}//End addCourse
	
	private void addCourse(Course current,Course toAdd){
		if(current.getNext() == firstCourse){
			current.setNext(toAdd);
			toAdd.setPrev(current);
			toAdd.setNext(firstCourse);
			firstCourse.setPrev(toAdd);
		}else
			addCourse(current.getNext(),toAdd);
	}//End addCourse
	
	public Course getCourses() {
		return firstCourse;
	}//End getCourses

	/**
	 * 
	 * @param courseName
	 */
	public boolean deleteCourse(String courseName) {
		Course toDelete = searchCourse(courseName);
		boolean status = false;
		if(toDelete != null){
			toDelete.getNext().setPrev(toDelete.getPrev());
			toDelete.getPrev().setNext(toDelete.getNext());
		}//End if
		return status;
	}//End deleteCourse

	/**
	 * 
	 */
	public void deleteCourse(Course toDelete) {
		toDelete.getNext().setPrev(toDelete.getPrev());
		toDelete.getPrev().setNext(toDelete.getNext());
	}//End deleteCourse

	public Course searchCourse(String name) {
		Course found;
		if(firstCourse.getName().equalsIgnoreCase(name)){
			found = firstCourse;
		}else
			found = searchCourse(firstCourse,name);
		return found;
	}//End searchCourse
	
	private Course searchCourse(Course current, String name){
		if(current.getName().equalsIgnoreCase(name)){
			return current;
		}else if(current.getNext() != firstCourse)
			return searchCourse(current.getNext(),name);
		else
			return null;
	}//searchCourse
	
	/**
	 * 
	 * @param toSendAtHour
	 * @param date
	 * @param description
	 * @param title
	 * @param day
	 */
	public void addNotify(Date toSendAtHour, Date date, String description, String title, Date initHour, Date finHour, String day,Course course) {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,course);
		notifies.add(n);
	}//End addNotify
	
	public void addNotify(Date toSendAtHour, Date date, String description, String title, Date initHour, Date finHour, String day,String course) {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,searchCourse(course));
		int i = 0;
		while(notifies.get(i).compareTo(n) > 0) {i++;}
		notifies.add(i,n);
	}//End addNotify
	
	public ArrayList<Notify> getNotifies() {
		return notifies;
	}//End getNotifies

	/**
	 * 
	 * @param title
	 */
	public Notify getNotify(String title) {
		return searchNotify(title);
	}//End getNotify

	/**
	 * 
	 * @param title
	 */
	public void deleteNotify(String title) {
		notifies.remove(searchNotify(title));
	}//End deleteNotify

	/**
	 * 
	 * @param notify
	 */
	public void deleteNotify(Notify notify) {
		notifies.remove(notify);
	}//End deleteNotify

	/**
	 * 
	 * @param title
	 */  
	private Notify searchNotify(String title) {
		boolean found = false;
		Notify n = null;
		for(int i = 0; i < notifies.size() && !found;i++) {
			if(notifies.get(i).getEvent().getTitle().equalsIgnoreCase(title)){
				n = notifies.get(i);
				found = true;
			}//End if
		}//End for
		return n;
	}//End searchNotify

	/**
	 * 
	 * @param title
	 * @param description
	 * @param goals
	 * @param day
	 */
	public void addStudyPlan(String title, String description, ArrayList<String> goals, String day, Date initHour, Date finHour,Course course) {
		Day d = new Day(Days.valueOf(day),initHour,finHour);
		studyPlans.add(new StudyPlan(title,description,getGoals(goals),d,course));
	}//End addStudyPlan
	
	private ArrayList<Goal> getGoals(ArrayList<String> g){
		ArrayList<Goal> goals = new ArrayList<Goal>();
		for(int i = 0; i < g.size(); i++){
			goals.add(new Goal(g.get(i)));
		}//End for
		return goals;
	}//End getGoals
	/**
	 * 
	 * @param title
	 */
	public StudyPlan searchStudyPlan(String title) {
		boolean found = false;
		StudyPlan s = null;
		for(int i = 0; i < studyPlans.size() && !found;i++) {
			if(studyPlans.get(i).getTitle().equalsIgnoreCase(title)){
				s = studyPlans.get(i);
				found = true;
			}//End if
		}//End for
		return s;
	}//End getStudyPlan

	/**
	 * 
	 * @param title
	 */
	public void deleteStudyPlan(String title) {
		studyPlans.remove(searchStudyPlan(title));
	}//End deleteStudyPlan

	/**
	 * 
	 * @param plan
	 */
	public void deleteStudyPlan(StudyPlan plan) {
		studyPlans.remove(plan);
	}//End deleteStudyPlan

	public Course getFirstCourse() {
		return firstCourse;
	}

	public void setFirstCourse(Course firstCourse) {
		this.firstCourse = firstCourse;
	}

	public void setStudyPlans(ArrayList<StudyPlan> studyPlans) {
		this.studyPlans = studyPlans;
	}
	
	public ArrayList<StudyPlan> getStudyPlans(){
		return studyPlans;
	}
	
	public void setNotifies(ArrayList<Notify> notifies) {
		this.notifies = notifies;
	}
	
}