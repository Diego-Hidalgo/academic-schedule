package model;

import java.io.Serializable;

public class User extends Person implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private String profilePhoto;
	private AcademicSchedule academicSchedule;

	/**
	 *
	 */
	public User() {
		super();
		userName = new String();
		password = new String();
		profilePhoto = new String();
		academicSchedule = new AcademicSchedule();
	}//End Constructor1

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param profilePhoto
	 */
	public User(String name, String lastName, String userName, String password, String profilePhoto) {
		super(name, lastName);
		this.userName = userName;
		this.password = password;
		this.profilePhoto = profilePhoto;
		academicSchedule = new AcademicSchedule();
	}//End Constructor2

	/**
	 *
	 * @return
	 */
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

	/**
	 *
	 */
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

	/**
	 *
	 */
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

	/**
	 *
	 */
	public AcademicSchedule getAcademicSchedule() {
		return academicSchedule;
	}

	/**
	 *
	 * @param academicSchedule
	 */
	public void setAcademicSchedule(AcademicSchedule academicSchedule) {
		this.academicSchedule = academicSchedule;
	}
	
	public String toString(){
		return userName + " "+password;
	}
}//End User