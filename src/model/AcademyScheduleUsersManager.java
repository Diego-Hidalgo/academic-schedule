package model;

import exceptions.InvalidCredentialsException;
import exceptions.UserNameAlreadyInUseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AcademyScheduleUsersManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SAVE_PATH = "save-file.dm";

	private User currentUser;
	private ArrayList<User> users;

	/**
	 *
	 */
	public AcademyScheduleUsersManager() {
		users = new ArrayList<User>();
	}//End AcademyScheduleUsersManager constructor

	/**
	 *
	 */
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
	 */
	public ArrayList<User> getUsers() {
		return users;
	}//End getUsers

	/**
	 *
	 * @param users
	 */
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}//End setUsers

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
	 * 
	 * @param userName
	 * @param password
	 */
	public void login(String userName, String password) throws InvalidCredentialsException {
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
	 *
	 */
	public void logout() {
		setCurrentUser(null);
	}//End logout
	
	/**
	 * 
	 * @param userName
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

	public void addUser(String name, String lastName, String userName, String passWord, String profilePhotoPath) throws IOException, UserNameAlreadyInUseException {
		if(searchUser(userName) != -1) {
			throw new UserNameAlreadyInUseException(userName);
		}
		User newUser = new User(name, lastName, userName, passWord, profilePhotoPath);
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

	public void changeUser(String name, String lastName, String userName, String passWord, String profilePhotoPath) throws IOException {
		User userToChange = users.get(searchUser(userName));
		String prevUserName = userToChange.getUserName();
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

	public void removeUser(String userName) throws IOException {
		User user = users.get(searchUser(userName));
		users.remove(user);
		saveAllData();
	}//End removeUser

}//End AcademyScheduleManager