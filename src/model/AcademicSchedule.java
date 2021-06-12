package model;

import java.io.*;
import java.util.Date;
import exceptions.InvalidTimeFormatException;
import exceptions.OutOfTimeRangeException;

import java.util.ArrayList;

public class AcademicSchedule implements Serializable {

	private static final long serialVersionUID = 1L;

	private Course firstCourse;
	private ArrayList<StudyPlan> studyPlans;
	private ArrayList<Notify> notifies;
	
	public AcademicSchedule() {
		studyPlans = new ArrayList<StudyPlan>();
		notifies = new ArrayList<Notify>();
	}//End AcademicSchedule constructor

	public void addCourse(String name, int credits, ArrayList<String> days, ArrayList<Time> initHours,ArrayList<Time> finHours) {
		Course toAdd = new Course(name,credits,convertToDay(days,initHours,finHours));
		if(firstCourse == null) {
			firstCourse = toAdd;
			firstCourse.setNext(toAdd);
			firstCourse.setPrev(toAdd);
		}else
			addCourse(firstCourse,toAdd);
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

	public boolean deleteCourse(String courseName) {
		Course toDelete = searchCourse(courseName);
		boolean status = false;
		if(toDelete != null){
			toDelete.getNext().setPrev(toDelete.getPrev());
			toDelete.getPrev().setNext(toDelete.getNext());
			status = true;
		}//End if
		return status;
	}//End deleteCourse

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

	public void addNotify(Time toSendAtHour, Date date, String description, String title, Time initHour,Time finHour, String day,Course course) {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,course);
		notifies.add(n);
	}//End addNotify
	
	public void addNotify(Time toSendAtHour, Date date, String description, String title, Time initHour, Time finHour, String day,String course) {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		Notify n = new Notify(toSendAtHour,date,description,title,d,searchCourse(course));
		int i = 0;
		while(notifies.get(i).compareTo(n) > 0) {i++;}
		notifies.add(i,n);
	}//End addNotify
	
	public ArrayList<Notify> getNotifies() {
		return notifies;
	}//End getNotifies

	public Notify getNotify(String title) {
		return searchNotify(title);
	}//End getNotify

	public void deleteNotify(String title) {
		notifies.remove(searchNotify(title));
	}//End deleteNotify

	public void deleteNotify(Notify notify) {
		notifies.remove(notify);
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

	public void addStudyPlan(String title, String description, ArrayList<String> goals, String day, Time initHour, Time finHour,Course course) {
		Day d = new Day(Days.valueOf(day.toUpperCase()),initHour,finHour);
		studyPlans.add(new StudyPlan(title,description,getGoals(goals),d,course));
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

	public void deleteStudyPlan(String title) {
		studyPlans.remove(searchStudyPlan(title));
	}//End deleteStudyPlan

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
	
}//End AcademicSchedule