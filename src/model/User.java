package model;

public class User {

	private String name;
	private String lastName;
	private String userName;
	private String password;
	private String profilePhoto;

	public User() {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param profilePhoto
	 */
	public User(String name, String lastName, String userName, String password, String profilePhoto) {
		// TODO - implement User.User
		throw new UnsupportedOperationException();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	/**
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return this.userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePhoto() {
		return this.profilePhoto;
	}

	/**
	 * 
	 * @param profilePhoto
	 */
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public AcademicSchedule getAcademicSchedule() {
		// TODO - implement User.getAcademicSchedule
		throw new UnsupportedOperationException();
	}

}