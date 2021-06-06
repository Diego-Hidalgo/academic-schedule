package model;

public class AcademyScheduleUsersManager {

	private User currentUser;

	public AcademyScheduleUsersManager() {
		// TODO - implement AcademyScheduleUsersManager.AcademyScheduleUsersManager
		throw new UnsupportedOperationException();
	}

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
	public void login(String userName, String password) {
		// TODO - implement AcademyScheduleUsersManager.login
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO - implement AcademyScheduleUsersManager.logout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	private User searchUser(String userName, String password) {
		// TODO - implement AcademyScheduleUsersManager.searchUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	public User getUser(String userName, String password) {
		// TODO - implement AcademyScheduleUsersManager.getUser
		throw new UnsupportedOperationException();
	}

	public User[] getUsers() {
		// TODO - implement AcademyScheduleUsersManager.getUsers
		throw new UnsupportedOperationException();
	}

}