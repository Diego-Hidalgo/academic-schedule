package model;

import java.io.*;
import java.util.Date;

import java.util.ArrayList;

public class AcademicSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	private AcademicScheduleUsersManager asum;

	private Course firstCourse;
	private ArrayList<StudyPlan> studyPlans;
	private ArrayList<Notify> notifies;

	/**
	 * Constructor of the class. Creates a new AcademicSchedule object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new object of this class has been created.
	 */
	public AcademicSchedule() {
		studyPlans = new ArrayList<StudyPlan>();
		notifies = new ArrayList<Notify>();
		this.asum = new AcademicScheduleUsersManager();
	}//End AcademicSchedule constructor

	/**
	 * Constructor of the class. Creates a new AcademicSchedule object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new object of this class has been created.
	 * @param asum the AcademicScheduleUsersManager to use
	 */
	public AcademicSchedule(AcademicScheduleUsersManager asum) {
		studyPlans = new ArrayList<StudyPlan>();
		notifies = new ArrayList<Notify>();
		this.asum = asum;
	}//End AcademicSchedule constructor

	/**
	 * Registers a new course in the system.<br>
	 *     <b>pre:</b> the object that calls this method is not null
	 *     <b>post:</b> a new course has been added.
	 * @param name the name of the course
	 * @param credits the amount of credits of the course
	 * @param days the days the course is taken
	 * @param initHours the start hours
	 * @param finHours the finish hours
	 */
	public void addCourse(String name, int credits, ArrayList<String> days, ArrayList<Time> initHours,ArrayList<Time> finHours) throws IOException {
		Course toAdd = new Course(name,credits,convertToDay(days,initHours,finHours));
		if(firstCourse == null) {
			firstCourse = toAdd;
			firstCourse.setNext(toAdd);
			firstCourse.setPrev(toAdd);
		}else
			addCourse(firstCourse,toAdd);
		asum.saveAllData();
	}//End addCourse

	/**
	 * Registers a new course in the system.<br>
	 *     <b>pre:</b> parameters are initialized and not null
	 *     <b>post:</b> a new course has been added
	 * @param current the current course being used to loop through the linked list.
	 * @param toAdd the new course to add
	 */
	private void addCourse(Course current,Course toAdd) {
		if(current.getNext() == firstCourse){
			current.setNext(toAdd);
			toAdd.setPrev(current);
			toAdd.setNext(firstCourse);
			firstCourse.setPrev(toAdd);
		}else
			addCourse(current.getNext(),toAdd);
	}//End addCourse

	/**
	 * converts the lists of days, initHours and finHours to a list of Day<br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param days the days to convert
	 * @param initHours the initial hours
	 * @param finHours the finish hours
	 */
	private ArrayList<Day> convertToDay(ArrayList<String> days, ArrayList<Time> initHours,ArrayList<Time> finHours){
		ArrayList<Day> da = new ArrayList<Day>();
		for(int i = 0; i < days.size();i++){
			da.add(new Day(Days.valueOf(days.get(i).toUpperCase()),initHours.get(i),finHours.get(i)));
		}//End for
		return da;
	}//End convertToDay

	/**
	 * returns the linked list of courses<br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 */
	public Course getCourses() {
		return firstCourse;
	}//End getCourses

	/**
	 * deletes a course given its name<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the course has been deleted
	 * @param courseName the name of the course to delete
	 */
	public boolean deleteCourse(String courseName) throws IOException {
		Course toDelete = searchCourse(courseName);
		boolean status = false;
		if(toDelete != null){
			toDelete.getNext().setPrev(toDelete.getPrev());
			toDelete.getPrev().setNext(toDelete.getNext());
			status = true;
		}//End if
		asum.saveAllData();
		return status;
	}//End deleteCourse

	/**
	 * deletes a course given its object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the course has been deleted.
	 * @param toDelete the object of the course to delete
	 */
	public void deleteCourse(Course toDelete) throws IOException {
		toDelete.getNext().setPrev(toDelete.getPrev());
		toDelete.getPrev().setNext(toDelete.getNext());
		asum.saveAllData();
	}//End deleteCourse

	/**
	 * searches a course given its name<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> returns a course object given its name
	 * @param name the name of the course to search
	 */
	public Course searchCourse(String name) {
		Course found;
		if(firstCourse.getName().equalsIgnoreCase(name)){
			found = firstCourse;
		}else
			found = searchCourse(firstCourse,name);
		return found;
	}//End searchCourse

	/**
	 * searches a course in the linked list <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> returns a course given its name
	 * @param current the current course being used as an aux to loop the linked list
	 * @param name
	 */
	private Course searchCourse(Course current, String name){
		if(current.getName().equalsIgnoreCase(name)){
			return current;
		}else if(current.getNext() != firstCourse)
			return searchCourse(current.getNext(),name);
		else
			return null;
	}//searchCourse

	/**
	 * Registers a new Notify object in the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Notify has been added with the given params
	 * @param toSendAtHour the hour to send the notification
	 * @param date the date of the notification
	 * @param description the description of the notification
	 * @param title the title of the notification
	 * @param initHour the start hour of the event
	 * @param finHour the finish hour of the event
	 * @param day the day of the event
	 * @param course the related course for the notification
	 */
	public void addNotify(Time toSendAtHour, Date date, String description, String title, Time initHour,Time finHour, String day,Course course) throws IOException {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,course);
		notifies.add(n);
		asum.saveAllData();
	}//End addNotify

	/**
	 * Registers a new Notify object in the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new Notify has been added with the given params
	 * @param toSendAtHour the time when the notification will be sent
	 * @param date the date of the event
	 * @param description the description of the notification
	 * @param title the title of the notification
	 * @param initHour the start hour of the event
	 * @param finHour the finish hour of the event
	 * @param day the day of the event
	 * @param course the course related to the notification
	 */
	public void addNotify(Time toSendAtHour, Date date, String description, String title, Time initHour, Time finHour, String day,String course) throws IOException {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,searchCourse(course));
		int i = 0;
		while(notifies.get(i).compareTo(n) > 0) {i++;}
		notifies.add(i,n);
		asum.saveAllData();
	}//End addNotify

	/**
	 * returns the list of Notify<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of Notify
	 */
	public ArrayList<Notify> getNotifies() {
		return notifies;
	}//End getNotifies

	/**
	 * returns an specific notify.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the specific notify given its title
	 * @param title the title of the notify to return
	 */
	public Notify getNotify(String title) {
		return searchNotify(title);
	}//End getNotify

	/**
	 * deletes a notify given its title<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the notify has been deleted
	 * @param title the title of the notify to delete
	 */
	public void deleteNotify(String title) throws IOException {
		notifies.remove(searchNotify(title));
		asum.saveAllData();
	}//End deleteNotify

	/**
	 * deletes a notify from the system given its object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the notify has been deleted from the system.
	 * @param notify the notify to delete
	 */
	public void deleteNotify(Notify notify) throws IOException {
		notifies.remove(notify);
		asum.saveAllData();
	}//End deleteNotify

	/**
	 * searches a notify in the system given its title<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the object of the notify with the given title
	 * @param title the title of the notify to search
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
	 * Registers a new StudyPlan object in the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new StudyPlan has been added
	 * @param title the title of the study plan to register
	 * @param description the description of the study plan
	 * @param goals the goals to accomplish with the study plan
	 * @param day the day of the study plan
	 * @param initHour the start hour of the study plan
	 * @param finHour the finish hour of the study plan
	 * @param course the course related to the study plan
	 */
	public void addStudyPlan(String title, String description, ArrayList<String> goals, String day, Time initHour, Time finHour,Course course) throws IOException {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		studyPlans.add(new StudyPlan(title,description,getGoals(goals),d,course));
		asum.saveAllData();
	}//End addStudyPlan

	/**
	 * returns a list of Goal given a list of String<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of Goals
	 * @param g the list of Goals
	 */
	private ArrayList<Goal> getGoals(ArrayList<String> g){
		ArrayList<Goal> goals = new ArrayList<Goal>();
		for(int i = 0; i < g.size(); i++){
			goals.add(new Goal(g.get(i)));
		}//End for
		return goals;
	}//End getGoals

	/**
	 * Searches and returns a StudyPlan given its title<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the StudyPlan with the given title
	 * @param title the title of the StudyPlan to search and return
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
	 * Deletes a StudyPlan given its title<br>
	 *     <b>pre:</b> the study plan to delete exists
	 *     <b>post:</b> the StudyPlan with the given title has been deleted
	 * @param title the title of the study plan to delete
	 */
	public void deleteStudyPlan(String title) throws IOException {
		studyPlans.remove(searchStudyPlan(title));
		asum.saveAllData();
	}//End deleteStudyPlan

	/**
	 * Deletes a StudyPlan given its Object<br>
	 *     <b>pre:</b> the study plan to delete exists
	 *     <b>post:</b> the study plan has been deleted
	 * @param plan the object of the study plan to delete
	 */
	public void deleteStudyPlan(StudyPlan plan) throws IOException {
		studyPlans.remove(plan);
		asum.saveAllData();
	}//End deleteStudyPlan

	/**
	 * returns the first course<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the linked list of courses
	 */
	public Course getFirstCourse() {
		return firstCourse;
	}

	/**
	 * changes the linked list of courses<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the courses have been changed
	 * @param firstCourse the new firstCourse
	 */
	public void setFirstCourse(Course firstCourse) throws IOException {
		this.firstCourse = firstCourse;
		asum.saveAllData();
	}//End setFirstCourse

	/**
	 * changes the list of study plans<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of study plans has been changed has been changed
	 * @param studyPlans the new list of study plans
	 */
	public void setStudyPlans(ArrayList<StudyPlan> studyPlans) throws IOException {
		this.studyPlans = studyPlans;
		asum.saveAllData();
	}//End setStudyPlans

	/**
	 * returns the list of study plans<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of study plans
	 */
	public ArrayList<StudyPlan> getStudyPlans(){
		return studyPlans;
	}

	/**
	 * changes the list of notifies<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of notifies has been changed.
	 * @param notifies the new list of notifies
	 */
	public void setNotifies(ArrayList<Notify> notifies) throws IOException {
		this.notifies = notifies;
		asum.saveAllData();
	}//End setNotifies
	
}//End AcademicSchedule