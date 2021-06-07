package model;

public class User {

	private String name;
	private String lastName;
	private String userName;
	private String password;
	private String profilePhoto;
	private AcademicSchedule academicSchedule;
	
	public User() {
		name = new String();
		lastName = new String();
		userName = new String();
		password = new String();
		profilePhoto = new String();
		academicSchedule = new AcademicSchedule();
				
	}//End User constructor

	/**
	 * 
	 * @param name
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param profilePhoto
	 */
	public User(String name, String lastName, String userName, String password, String profilePhoto) {
		this.name = name;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.profilePhoto = profilePhoto;
		academicSchedule = new AcademicSchedule();
	}//End User constructor

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
		return academicSchedule;
	}

	public void setAcademicSchedule(AcademicSchedule academicSchedule) {
		this.academicSchedule = academicSchedule;
	}
	
}