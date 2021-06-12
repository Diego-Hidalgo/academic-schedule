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

	public AcademicSchedule() {
		studyPlans = new ArrayList<StudyPlan>();
		notifies = new ArrayList<Notify>();
		this.asum = new AcademicScheduleUsersManager();
	}//End AcademicSchedule constructor
	
	public AcademicSchedule(AcademicScheduleUsersManager asum) {
		studyPlans = new ArrayList<StudyPlan>();
		notifies = new ArrayList<Notify>();
		this.asum = asum;
	}//End AcademicSchedule constructor

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
	
	private void addCourse(Course current,Course toAdd) {
		if(current.getNext() == firstCourse){
			current.setNext(toAdd);
			toAdd.setPrev(current);
			toAdd.setNext(firstCourse);
			firstCourse.setPrev(toAdd);
		}else
			addCourse(current.getNext(),toAdd);
	}//End addCourse
	
	private ArrayList<Day> convertToDay(ArrayList<String> days, ArrayList<Time> initHours,ArrayList<Time> finHours){
		ArrayList<Day> da = new ArrayList<Day>();
		for(int i = 0; i < days.size();i++){
			da.add(new Day(Days.valueOf(days.get(i).toUpperCase()),initHours.get(i),finHours.get(i)));
		}//End for
		return da;
	}//End convertToDay

	public Course getCourses() {
		return firstCourse;
	}//End getCourses

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

	public void deleteCourse(Course toDelete) throws IOException {
		toDelete.getNext().setPrev(toDelete.getPrev());
		toDelete.getPrev().setNext(toDelete.getNext());
		asum.saveAllData();
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

	public void addNotify(Time toSendAtHour, Date date, String description, String title, Time initHour,Time finHour, String day,Course course) throws IOException {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,course);
		notifies.add(n);
		asum.saveAllData();
	}//End addNotify
	
	public void addNotify(Time toSendAtHour, Date date, String description, String title, Time initHour, Time finHour, String day,String course) throws IOException {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,searchCourse(course));
		int i = 0;
		while(notifies.get(i).compareTo(n) > 0) {i++;}
		notifies.add(i,n);
		asum.saveAllData();
	}//End addNotify
	
	public ArrayList<Notify> getNotifies() {
		return notifies;
	}//End getNotifies

	public Notify getNotify(String title) {
		return searchNotify(title);
	}//End getNotify

	public void deleteNotify(String title) throws IOException {
		notifies.remove(searchNotify(title));
		asum.saveAllData();
	}//End deleteNotify

	public void deleteNotify(Notify notify) throws IOException {
		notifies.remove(notify);
		asum.saveAllData();
	}//End deleteNotify

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

	public void addStudyPlan(String title, String description, ArrayList<String> goals, String day, Time initHour, Time finHour,Course course) throws IOException {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		studyPlans.add(new StudyPlan(title,description,getGoals(goals),d,course));
		asum.saveAllData();
	}//End addStudyPlan
	
	private ArrayList<Goal> getGoals(ArrayList<String> g){
		ArrayList<Goal> goals = new ArrayList<Goal>();
		for(int i = 0; i < g.size(); i++){
			goals.add(new Goal(g.get(i)));
		}//End for
		return goals;
	}//End getGoals

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

	public void deleteStudyPlan(String title) throws IOException {
		studyPlans.remove(searchStudyPlan(title));
		asum.saveAllData();
	}//End deleteStudyPlan

	public void deleteStudyPlan(StudyPlan plan) throws IOException {
		studyPlans.remove(plan);
		asum.saveAllData();
	}//End deleteStudyPlan

	public Course getFirstCourse() {
		return firstCourse;
	}

	public void setFirstCourse(Course firstCourse) throws IOException {
		this.firstCourse = firstCourse;
		asum.saveAllData();
	}//End setFirstCourse

	public void setStudyPlans(ArrayList<StudyPlan> studyPlans) throws IOException {
		this.studyPlans = studyPlans;
		asum.saveAllData();
	}//End setStudyPlans
	
	public ArrayList<StudyPlan> getStudyPlans(){
		return studyPlans;
	}
	
	public void setNotifies(ArrayList<Notify> notifies) throws IOException {
		this.notifies = notifies;
		asum.saveAllData();
	}//End setNotifies
	
}//End AcademicSchedule