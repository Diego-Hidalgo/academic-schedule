package model;

import exceptions.InvalidCredentialsException;
import exceptions.UserNameAlreadyInUseException;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AcademicScheduleUsersManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SAVE_PATH = "save-file.dm";

	private User currentUser;
	private ArrayList<User> users;

	/**
	 * Constructor of the AcademicScheduleUsersManager class. Creates a new object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new object has been created.
	 */
	public AcademicScheduleUsersManager() {
		users = new ArrayList<User>();
	}//End AcademicScheduleUsersManager constructor

	/**
	 * returns the current user logged in the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the user in the system.
	 */
	public User getCurrentUser() {
		return this.currentUser;
	}
	
	/**
	 * changes the current user in the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the current user has been changed.
	 * @param currentUser the new current user in the system.
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	/**
	 * returns the list of users.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}//End getUsers

	/**
	 * changes the list of users.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of users have been changed.
	 * @param users the new list of users.
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}//End setUsers

	/**
	 * Serializes the information of the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the information has been saved in the specified path
	 */
	public synchronized void saveAllData() throws IOException {
		File f = new File(SAVE_PATH);
		if(!f.exists()) {
			f.createNewFile();
		}//End if
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(this);
		oos.close();
	}//End saveAllData

	/**
	 * allows an user to log in in the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the user has logged in
	 * @param userName the name of the user that tries to log in
	 * @param password the password of the user that tries to log in
	 */
	public void logIn(final String userName, final String password) throws InvalidCredentialsException {
		int userIndex = searchUser(userName);
		if(userIndex == -1) {
			throw new InvalidCredentialsException();
		}//End if
		if(users.get(userIndex).getPassword().equals(password)){
				setCurrentUser(users.get(userIndex));
		} else {
			throw new InvalidCredentialsException();
		}//End if/else
	}//End login

	/**
	 * logs out the current user from the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the user has logged out from the system.
	 */
	public void logout() {
		setCurrentUser(null);
	}//End logout
	
	/**
	 * searches an user using binary search given its name<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the index of the user in the list of users.
	 * @param userName the name used as the search parameter
	 */
	public int searchUser(final String userName) {
		int start = 0;
		int end = users.size() - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			if (users.get(mid).getUserName().compareTo(userName) == 0) {
				return mid;
			} else if (users.get(mid).getUserName().compareTo(userName) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}//End if/else
		}//End while
		return -1;
	}//End searchUser

	/**
	 * verifies if a set of strings contains only blank spaces<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> returns false if contains only blank spaeces, false if not
	 * @param toVerify the set of strings to verify
	 */
	public boolean verifyBlankChars(String[] toVerify) {
		boolean stop = false;
		int count = 0;
		for(int i = 0; i < toVerify.length; i ++) {
			String aux = toVerify[i];
			stop = false;
			for(int j = 0; j < aux.length() && !stop; j ++) {
				if(aux.charAt(j) != ' ') {
					stop = true;
					count ++;
				}//End if
			}//End for
		}//End for
		return count == toVerify.length;
	}//End verifyBlankChars

	/**
	 * adds a new user to the system.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new user has been added
	 * @param name the name of the new user
	 * @param lastName the last name of the new user
	 * @param userName the user name of the new user
	 * @param passWord the password of the new user
	 * @param profilePhotoPath the path to the profile photo of the new user
	 */
	public void addUser(String name, String lastName, String userName, String passWord, String profilePhotoPath) throws IOException, UserNameAlreadyInUseException {
		if(searchUser(userName) != -1) {
			throw new UserNameAlreadyInUseException(userName);
		}//End if
		User newUser = new User(name, lastName, userName, passWord, profilePhotoPath, this);
		if(users.isEmpty()) {
			users.add(newUser);
		} else {
			Comparator<User> userNameComparator = new UserNameComparator();
			int i = 0;
			while(i < users.size() && userNameComparator.compare(newUser, users.get(i)) > 0) {
				i ++;
			}//End while
			users.add(i, newUser);
		}//End if/else
		saveAllData();
	}//End addUser

	/**
	 * Changes the information of the current user<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the information of the user has been changed.
	 * @param name the new name of the user
	 * @param lastName the new last name of the user
	 * @param userName the new user name of the user
	 * @param passWord the new password of the user
	 * @param profilePhotoPath the new profile photo of the user
	 */
	public void changeUser(String name, String lastName, String userName, String passWord, String profilePhotoPath) throws IOException, UserNameAlreadyInUseException {
		User userToChange = getCurrentUser();
		String prevUserName = userToChange.getUserName();
		if(searchUser(userName) != -1 && !prevUserName.equals(userName)) {
			throw new UserNameAlreadyInUseException(userName);
		}//End if
		userToChange.setName(name);
		userToChange.setLastName(lastName);
		userToChange.setUserName(userName);
		userToChange.setPassword(passWord);
		userToChange.setProfilePhoto(profilePhotoPath);
		if(!prevUserName.equals(userName)) {
			sortUsersList();
		}//End if
		saveAllData();
	}//End changeUser

	/**
	 * changes the password of a given user<br>
	 *     <b>pre:</b> the user exists in the list.
	 *     <b>post:</b> the password of the user has been changed
	 * @param userName the user name of the user to change the password
	 * @param newPassword the new password of the user
	 */
	public void changeUserPassword(String userName, String newPassword) throws IOException {
		User userToChange = users.get(searchUser(userName));
		userToChange.setPassword(newPassword);
		saveAllData();
	}//End changeUserPassword

	/**
	 * deletes the current user in the system<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the user has been deleted
	 */
	public void deleteUser() throws IOException {
		User toDelete = users.get(searchUser(currentUser.getUserName()));
		users.remove(toDelete);
		logout();
		saveAllData();
	}//End deleteUser

	/**
	 * sorts the list of user using bubble sort<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the list of users has been sorted
	 */
	public void sortUsersList() {
		Comparator<User> userNameComparator = new UserNameComparator();
		for(int i = 0; i < users.size(); i ++) {
			for(int j = 0; j < users.size() - 1; j ++) {
				if(userNameComparator.compare(users.get(j), users.get(j + 1)) > 0) {
					User temp = users.get(j);
					users.set(j, users.get(j + 1));
					users.set(j + 1, temp);
				}//End if
			}//End for
		}//End for
	}//End sortUsersList


}//End AcademyScheduleManager