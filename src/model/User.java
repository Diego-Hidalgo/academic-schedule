package model;

import java.io.*;

public class User extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String profilePhoto;
	private AcademicSchedule academicSchedule;

	/**
	 * Constructor of the User class. Creates an User object<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new User object has been created.
	 */
	public User() {
		super();
		userName = new String();
		password = new String();
		profilePhoto = new String();
		academicSchedule = new AcademicSchedule();
	}//End Constructor1

	/**
	 * Constructor of the User class. Creates a new User object.<br>
	 *     <b>pre:</b>
	 *     <b>post:</b> a new User object has been created.
	 * @param name the name of the user
	 * @param lastName the last name of the user
	 * @param userName the user name of the user
	 * @param password the password of the user
	 * @param profilePhoto the path to the profile photo of the user
	 */
	public User(String name, String lastName, String userName, String password, String profilePhoto, AcademicScheduleUsersManager asum) {
		super(name, lastName);
		this.userName = userName;
		this.password = password;
		this.profilePhoto = profilePhoto;
		academicSchedule = new AcademicSchedule(asum);
	}//End Constructor2

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePhoto() {
		return this.profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public AcademicSchedule getAcademicSchedule() {
		return academicSchedule;
	}

	public void setAcademicSchedule(AcademicSchedule academicSchedule) {
		this.academicSchedule = academicSchedule;
	}

}//End User