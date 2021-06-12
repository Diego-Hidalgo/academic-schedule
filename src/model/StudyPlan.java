package model;

import java.util.ArrayList;

public class StudyPlan extends Plan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Goal> goals;

	public StudyPlan() {
		super();
		goals = new ArrayList<Goal>();
	}//End StudyPlan constructor

	/**
	 * 
	 * @param title
	 * @param description
	 * @param goals
	 * @param day
	 */
	public StudyPlan(String title, String description,ArrayList<Goal> goals, Day day,Course course) {
		super(title,description,day,course);
		this.goals = goals;
	}//End StudyPlan constructor

	/**	
	 * 
	 * @param goal
	 */
	public void addGoals(String goal,boolean status) {
		goals.add(new Goal(goal,status));
	}//End addGoals

	/**
	 * 
	 * @param goal
	 */
	public void deleteGoal(Goal goal) {
		goals.remove(goal);
	}//End deleteGoal

	public void editGoal(Goal oldGoal, String goal, boolean status) {
		if(goal != null)
			oldGoal.setDescription(goal);
		oldGoal.setStatus(status);
	}

	public ArrayList<Goal> getGoals() {
		return goals;
	}

	public void setGoals(ArrayList<Goal> goals) {
		this.goals = goals;
	}
}