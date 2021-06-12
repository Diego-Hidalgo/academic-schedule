package model;

import java.io.Serializable;
import java.util.ArrayList;

public class StudyPlan extends Plan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Goal> goals;

	/**
	 * Constructor of the StudyPlan class. Creates a new StudyPlan object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new StudyPlan object has been created.
	 */
	public StudyPlan() {
		super();
		goals = new ArrayList<Goal>();
	}//End StudyPlan constructor

	/**
	 * Constructor of the StudyPlan class. Creates a new StudyPlan object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new StudyPlan object has been created with the given data.
	 * @param title the title of the StudyPlan
	 * @param description the description of the StudyPlan
	 * @param goals the goals of the StudyPlan
	 * @param day the day of the StudyPlan
	 * @param course the course related to the StudyPlan
	 */
	public StudyPlan(String title, String description,ArrayList<Goal> goals, Day day,Course course) {
		super(title,description,day,course);
		this.goals = goals;
	}//End StudyPlan constructor

	/**
	 * Adds a new goal to the StudyPlan<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new goal has been added.
	 * @param goal the new goal to add.
	 */
	public void addGoals(String goal,boolean status) {
		goals.add(new Goal(goal,status));
	}//End addGoals

	/**
	 * Deletes an specific goal given the object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the given goal has been deleted.
	 * @param goal the goal to delete
	 */
	public void deleteGoal(Goal goal) {
		goals.remove(goal);
	}//End deleteGoal

	/**
	 * Edits the attribute of an specific goal.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the information of the given goal has been changed.
	 * @param oldGoal the goal to change
	 * @param goal the new attribute for the goal
	 * @param status the new status of the goal
	 */
	public void editGoal(Goal oldGoal, String goal, boolean status) {
		if(goal != null)
			oldGoal.setDescription(goal);
		oldGoal.setStatus(status);
	}//End editGoal

	/**
	 * returns the list of goals<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of goals
	 */
	public ArrayList<Goal> getGoals() {
		return goals;
	}//End getGoals

	/**
	 * changes the list of goals<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of goals
	 * @param goals the new list of goals
	 */
	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}//End setGoals

}//End StudyPlan