package model;

import java.util.ArrayList;

public class AcademyScheduleUsersManager {

	private User currentUser;
	private ArrayList<User> users;

	public AcademyScheduleUsersManager() {
		users = new ArrayList<User>();
	}//End AcademyScheduleUsersManager constructor

	public User getCurrentUser() {
		return this.currentUser;
	}

	/**
	 * 
	 * @param currentUser
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	public boolean login(String userName, String password) {
		boolean log = false;
		int userIndex = searchUser(userName);
		if(userIndex >= 0){
			if(users.get(userIndex).getPassword().equals(password)){
				setCurrentUser(users.get(userIndex));
				log = true;
			}//End if
		}//End if
		return log;
	}//End login

	public void logout() {
		setCurrentUser(null);
	}//End logout
	
	/**
	 * 
	 * @param userName
	 * @param password
	 */
	public int searchUser(String userName) {
		int start = 0;
		int end = users.size() - 1;
		int mid = (end-start)/2;
		boolean found = false;
		int userIndex = -1;
		User current;
		while(!found && start <= end){
			current = users.get(mid);
			if(current.getUserName().equals(userName)){
				found = true;
				userIndex = mid;
			}else if(current.getUserName().compareTo(userName) < 0){
				start = mid + 1;
			}else{
				end = mid - 1;
			}//End else
			mid = (end-start)/2;
		}//End while
		return userIndex;
	}//End searchUser

	public ArrayList<User> getUsers() {
		return users;
	}//End getUsers

}