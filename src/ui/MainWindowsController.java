package ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.ArrayList;
import exceptions.InvalidCredentialsException;
import exceptions.InvalidTimeFormatException;
import exceptions.OutOfTimeRangeException;
import exceptions.UserNameAlreadyInUseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AcademicScheduleUsersManager;
import model.Course;
import model.Goal;
import model.Time;

public class MainWindowsController{
	
	final private String[] colours = {"00B42C","00B468","00B4AC","0086B4","005AB4","0800B4","7D00B4","B400AF",
									  "B40088","B4005D","B4003C","B4000E"};
	final private String DEFAULTPROFILEIMG = "file:/fxml/profile.png";
	final private String FOLDER = "fxml/";
	@FXML private BorderPane mainPane;
	@FXML private BorderPane secondaryPane;
	@FXML private MenuBar menuBar;
	//************User data ***************\\
	private EmergentWindowController ewc;
	//************User data ***************
	@FXML private ImageView ProfileImg;
	@FXML private TextField nameTxt;
	@FXML private TextField lastNameTxt;
	@FXML private TextField userNameTxt;
	@FXML private TextField passwordTxt;
	@FXML private TextField confirmationTxt;
	private String imgPath;
	//************Course data**************
	@FXML private TextField courseNameTxt;
	@FXML private TextField creditsTxt;
	@FXML private Pane monday;
	@FXML private Pane tuesday;
	@FXML private Pane wednesday;
	@FXML private Pane thursday;
	@FXML private Pane friday;
	@FXML private Pane saturday;
	private ArrayList<String> days;
	private ArrayList<Time> initHour;
	private ArrayList<Time> finHour;
	//************* Courses ****************
	private VBox center;
	private ArrayList<CourseBlock> coursesBlock;
	//*************Study plan***************
	@FXML private TextField titleTxt;
	@FXML private TextField descriptionTxt;
	@FXML private TextField initTimeTxt;
	@FXML private TextField finTimeTxt;
	@FXML private ListView<String> goalsLV;
	@FXML private  ChoiceBox<String> dayCB;
	@FXML private ChoiceBox<Course> courseCB;
	private ArrayList<String> goalsSt;
	//************ Event ********************
	@FXML private TextField eventName;
	@FXML private TextField eventDescription;
	@FXML private TextField eventInitHour;
	@FXML private TextField eventFinHour;
	@FXML private TextField eventToSendHour;
	@FXML private ChoiceBox<String> eventDayCB;
	@FXML private ChoiceBox<Course> eventCourseCB;
	@FXML private DatePicker eventDate;
	@FXML private CheckBox eventSendNotify;
	//************ Academic schedule *******
	private AcademicScheduleUsersManager academicSchedule;
	
	public MainWindowsController(AcademicScheduleUsersManager as){
		this.academicSchedule = as;
		ewc = new EmergentWindowController(as);
		days = new ArrayList<String>();
		initHour = new ArrayList<Time>();
		finHour = new ArrayList<Time>();
		coursesBlock = new ArrayList<CourseBlock>();
	}//End MainWindowsController constructor

	//**************************** SCENES *************************************

	@FXML
	public void switchToMainPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"MainWindows.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Stage window = (Stage) menuBar.getScene().getWindow();
		Scene scene = new Scene(root, null);
		window.setScene(scene);
		window.show();
	}//End switchToMainPane

	@FXML
	public void switchToSecondaryPane(Event e) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"SecondaryWindows.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, null);
		window.setScene(scene);
		window.setTitle("Bienvenido " + academicSchedule.getCurrentUser().getUserName());
		window.show();
	}//End switchToSecondaryPane

	@FXML
	public void logInUser(Event e) throws IOException {
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		if(academicSchedule.verifyBlankChars(new String[]{userName, password})) {
			try {
				academicSchedule.logIn(userName, password);
				switchToSecondaryPane(e);
			} catch (InvalidCredentialsException exception) {
				showErrorAlert("Credenciales incorrectas", exception.getMessage() + ". Vuelva a intentarlo.", null);
			}//End try/catch
		} else {
			showInformationAlert("Campos vacíos","Deben llenarse todos los campos",null);
		}//End if/else
	}//End logInUser

	@FXML
	public void showLoginScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"LoginWindows.fxml"));
		fxmlLoader.setController(this);
		Parent loginScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(loginScene);
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setTitle("Iniciar sesion");
		stage.setWidth(485);
		stage.setHeight(480);
		stage.setResizable(false);
	}//End showLoginScene
	
	@FXML
	public void showRegisterUser() throws IOException {
		imgPath = DEFAULTPROFILEIMG;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindow.fxml"));
		fxml.setController(this);
		Parent registerUser = fxml.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerUser);
		Stage st = (Stage) mainPane.getScene().getWindow();
		st.setTitle("Registrar usuario");
		st.setHeight(485);
		st.setWidth(520);
		st.setResizable(false);
	}//End switchToMainPane

	@FXML
	public void showChangePasswordScene() throws IOException {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangePasswordWindow.fxml"));
		fxml.setController(this);
		Parent changePassword = fxml.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(changePassword);
		Stage st = (Stage) mainPane.getScene().getWindow();
		st.setTitle("Restaurar contraseña");
		st.setHeight(400);
		st.setWidth(500);
		st.setResizable(false);
	}//End showChangePasswordScene

	public void showUserProfileScene() throws IOException, URISyntaxException {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER + "UserProfileWindow.fxml"));
		fxml.setController(this);
		Parent userProfile = fxml.load();
		secondaryPane.setCenter(userProfile);
		setUserProfileFields();
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Perfil del usuario " + academicSchedule.getCurrentUser().getUserName());
		st.setHeight(485);
		st.setWidth(525);
		st.setResizable(false);
	}//End showUserProfileScene

	public void setUserProfileFields() throws URISyntaxException {
		try {
			ProfileImg.setImage(new Image(academicSchedule.getCurrentUser().getProfilePhoto()));
		} catch (NullPointerException e) {
			ProfileImg.setImage(new Image(getClass().getResource("fxml/img/profile.png").toURI().toString()));
		}//End try/catch
		nameTxt.setText(academicSchedule.getCurrentUser().getName());
		lastNameTxt.setText(academicSchedule.getCurrentUser().getLastName());
		userNameTxt.setText(academicSchedule.getCurrentUser().getUserName());
	}//End setUserProfileFields
	
	@FXML
	public void showRegisterCourse() throws IOException{
		days = new ArrayList<String>();
		initHour = new ArrayList<Time>();
		finHour = new ArrayList<Time>();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"AddCourseWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Registrar curso");
		stage.setHeight(610);
		stage.setWidth(550);
		stage.setResizable(false);
	}//End showRegisterCourse
	
	@FXML
	public void showRegisterStudyPlan() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"CreateStudyPlanWindow.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		goalsSt = new ArrayList<String>();
		initializeDayChoiceBox();
		initializeCoursesChoiceBox();
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Crear plan de estudio");
		stage.setHeight(670);
		stage.setWidth(550);
		stage.setResizable(false);
	}//End showRegisterCourse
	
	@FXML
	public void showAllCourses() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ShowCoursesWindow.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		BorderPane cen = (BorderPane) registerScene;
		center = new VBox();
		center.setPrefHeight(400);
		cen.setCenter(center);
		secondaryPane.setCenter(cen);
		addCourseToScreen();
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Cursos");
		stage.setHeight(580);
		stage.setWidth(500);
		stage.setResizable(false);
	}//End showAllCourses
	
	@FXML
	public void showRegisterEvent() throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"AddEventWithNotifyWindow.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Crear evento");
		stage.setHeight(630);
		stage.setWidth(550);
		stage.setResizable(false);
	}//End showRegisterCourse
	
	public void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert
	//************************ Objects and data managment *******************
	
	@FXML
	public void addGoals() throws IOException{
		ewc.showAddGoal();
		boolean show = true;
		String msg = "No se ha agregado la meta";
		if(ewc.getGoal() != null && !ewc.getGoal().isEmpty()){
			goalsSt.add(ewc.getGoal());
			ObservableList<String> gls = FXCollections.observableArrayList(goalsSt);
			goalsLV.setItems(gls);
			show = false;
		}//End if
		if(show)
			showInformationAlert("Error",msg,null);
	}//End addGoals
	
	@FXML
	public boolean showConfirmationAlert(String title, String msg, String header) {
		Alert feedBack = new Alert(AlertType.CONFIRMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		ButtonType acceptBtn = new ButtonType("Aceptar");
		ButtonType cancelBtn = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
		feedBack.getButtonTypes().setAll(acceptBtn, cancelBtn);
		Optional<ButtonType> result = feedBack.showAndWait();
		return result.get() == acceptBtn;
	}//End showConfirmationAlert

	public void showErrorAlert(String title, String msg, String header) {
		Alert feedBack = new Alert(AlertType.ERROR);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showErrorAlert

	//************************ Objects and data managment *******************
	public void addCourseToScreen(){
		coursesBlock = new ArrayList<CourseBlock>();
		Course current = academicSchedule.getCurrentUser().
				getAcademicSchedule().getFirstCourse();
		center.getChildren().setAll((new CourseBlock(current,colours[0],ewc)).getHBox());
		if(current != null) {
			coursesBlock.add(new CourseBlock(current,colours[0],ewc));
			addCourse(academicSchedule.getCurrentUser().getAcademicSchedule().
					getFirstCourse().getNext(),1);
			for(int i = 1; i < coursesBlock.size();i++){
				center.getChildren().addAll(coursesBlock.get(i).getHBox());
				center.setSpacing(10);
			}//End for
		}
		
	}//End addCourseToScreen
	
	private void addCourse(Course current, int index){
		if(current != academicSchedule.getCurrentUser().getAcademicSchedule().getFirstCourse()){
			coursesBlock.add(new CourseBlock(current,colours[index],ewc));
			addCourse(current.getNext(),(++index));
		}//End if
	}//End addCourse
	
	@FXML
	public void registerCourse(){
		String msg = "Llena todos los campos";
		if(days.size() != 0 && !courseNameTxt.getText().isEmpty() && 
			!creditsTxt.getText().isEmpty()){
			try{
				int credits = Integer.parseInt(creditsTxt.getText());
				if(academicSchedule.getCurrentUser() == null){
					System.out.println("Usuario nulo");
				}else if(academicSchedule.getCurrentUser().getAcademicSchedule() == null) {
					System.out.println("Academic schedule nulo");
				}
				academicSchedule.getCurrentUser().getAcademicSchedule().
				addCourse(courseNameTxt.getText(), credits, days, initHour, finHour);
				msg = "Curso agregado con exito";
				days = new ArrayList<String>();
				initHour = new ArrayList<Time>();
				finHour = new ArrayList<Time>();
				courseNameTxt.setText("");
				creditsTxt.setText("");
				DesactivateAllDays();
			}catch(NumberFormatException e){
				msg = "Los creditos deben ser un valor numerico entero";
			}//End try..catch
		}//End if
		showInformationAlert("Agregar curso estado",msg,null);
	}//End registerCourse
	
	@FXML
	public void addDay() throws IOException{
		ewc.showAddDay();
		String d = ewc.getAndClearDay();
		days.add(d);
		initHour.add(ewc.getAndClearInitHour());
		finHour.add(ewc.getAndClearFinHour());
		activateDay(d);
	}//End addDay
	
	@FXML
	public void createStudyPlan(){
		String msg = new String();
		if(!titleTxt.getText().isEmpty() && !descriptionTxt.getText().isEmpty() && 
			dayCB.getValue() != null && courseCB.getValue() != null && !initTimeTxt.getText().isEmpty()
			&& !finTimeTxt.getText().isEmpty()){
			try{
				Time init = new Time(initTimeTxt.getText());
				Time fin = new Time(finTimeTxt.getText());
				ArrayList<String> goals = getGoalsAsArrayList(goalsLV.getItems());
				academicSchedule.getCurrentUser().getAcademicSchedule().addStudyPlan(titleTxt.getText(),
						descriptionTxt.getText(),goals,dayCB.getValue(),init,fin,courseCB.getValue());
				titleTxt.setText("");
				descriptionTxt.setText("");
				dayCB.setValue(null);
				courseCB.setValue(null);
				initTimeTxt.setText("");
				finTimeTxt.setText("");
				msg = "Se ha agregado el plan de estudio correctamenta";
			}catch(OutOfTimeRangeException e){
				msg = "La hora asignada esta fuera de rango";
			}catch(InvalidTimeFormatException e){
				msg = "El formato ingresado en la hora es incorrecto";
			}//End try..catch
		}else
			msg = "Llena todos los campos";
		showInformationAlert("Info",msg,null);
	}//End createStudyPlan
	
	private ArrayList<String> getGoalsAsArrayList(ObservableList<String> goal){
		ArrayList<String> goals = new ArrayList<String>();
		for(int i = 0; i < goal.size();i++){
			goals.add(goal.get(i));
		}//End for
		return goals;
	}//End getGoalsAsArrayList
	
	public void activateDay(String day){
		switch(day){
		case "Lunes":
			monday.setDisable(false);
			monday.setOpacity(1);
			break;
		case "Martes": 
			tuesday.setDisable(false);
			tuesday.setOpacity(1);
			break;
		case "Miercoles": 
			wednesday.setDisable(false);
			wednesday.setOpacity(1);
			break;
		case "Jueves": 
			thursday.setDisable(false);
			thursday.setOpacity(1);
			break;
		case "Viernes": 
			friday.setDisable(false);
			friday.setOpacity(1);
			break;
		case "Sabado": 
			saturday.setDisable(false);
			saturday.setOpacity(1);
			break;
		}//End switch
	}//End activateDay
	
	public void DesactivateAllDays(){
		monday.setDisable(true);
		monday.setOpacity(0.15); 
		tuesday.setDisable(true);
		tuesday.setOpacity(0.15);
		wednesday.setDisable(true);
		wednesday.setOpacity(0.15);
		thursday.setDisable(true);
		thursday.setOpacity(0.15);
		friday.setDisable(true);
		friday.setOpacity(0.15); 
		saturday.setDisable(true);
		saturday.setOpacity(0.15);
	}//End activateDay

	public void registerUser(ActionEvent event) throws URISyntaxException, IOException {
		String name = nameTxt.getText();
		String lastName = lastNameTxt.getText();
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		String confirmation = confirmationTxt.getText();
		if(academicSchedule.verifyBlankChars(new String[]{name, lastName, userName, password, confirmation})) {
			if(password.equals(confirmation)) {
				if(password.length() >= 7) {
					try {
						academicSchedule.addUser(name, lastName, userName, password, imgPath);
						showInformationAlert("Registro completado", "Se ha registrado al nuevo usuario exitosamente", null);
						nameTxt.clear();
						lastNameTxt.clear();
						userNameTxt.clear();
						passwordTxt.clear();
						confirmationTxt.clear();
						ProfileImg.setImage(new Image(getClass().getResource("fxml/img/profile.png").toURI().toString()));
					} catch (UserNameAlreadyInUseException e) {
						showInformationAlert("No se pudo completar el registro", e.getMessage(), null);
					} catch (IOException e) {
						showInformationAlert("No se pudo completar el registro", "Ocurrió un eeror inesperado :(", null);
					}//End try/catch
				} else {
					showInformationAlert("Contraseña demasiado corta", "La contraseña es demasiado corta, debe contener por lo menos siete (7) caracteres. Vuelva a intentarlo", null);
				}//End if/else
			} else {
				showInformationAlert("Contraseñas distintas","Las contraseñas no coinciden, vuelva a intentarlo",null);
			}//End if/else
		} else {
			showInformationAlert("Campos vacíos","Deben llenarse todos los campos",null);
		}//End if/else
	}//End registerUser

	@FXML
	public void changeUserPassword() throws IOException {
		String userName = userNameTxt.getText();
		String newPassword = passwordTxt.getText();
		String confirmation = confirmationTxt.getText();
		if(academicSchedule.verifyBlankChars(new String[]{userName, newPassword, confirmation})) {
			if(academicSchedule.searchUser(userName) != -1) {
				if(newPassword.equals(confirmation)) {
					if(newPassword.length() >= 7) {
						academicSchedule.changeUserPassword(userName, newPassword);
						showInformationAlert("Contraseña cambiada", "La contraseña se ha cambiado exitosamente", null);
						userNameTxt.clear();
						passwordTxt.clear();
						confirmationTxt.clear();
					} else {
						showInformationAlert("Contraseña demasiado corta", "La contraseña es demasiado corta, debe contener por lo menos siete (7) caracteres. Vuelva a intentarlo", null);
					}//Enf if/else
				} else {
					showInformationAlert("Contraseñas distintas","Las contraseñas no coinciden, vuelva a intentarlo",null);
				}//End if/else
			} else {
				showInformationAlert("Usuario inexistente", "El nombre de usuario ingresado no existe", null);
			}//End if/else
		} else {
			showInformationAlert("Campos vacíos","Deben llenarse todos los campos",null);
		}//End if/else
	}//End changeUserPassword

	@FXML
	public void changeUserData() throws URISyntaxException, IOException {
		String name = nameTxt.getText();
		String lastName = lastNameTxt.getText();
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		String confirmation = confirmationTxt.getText();
		if(showConfirmationAlert("Cambiar datos de la cuente", "¿Está seguro que desea cambiar los datos de la cuenta?", null)) {
			if(academicSchedule.verifyBlankChars(new String[]{name, lastName, userName, password, confirmation})) {
				if(password.equals(confirmation)) {
					if(password.length() >= 7) {
						try {
							academicSchedule.changeUser(name, lastName, userName, password, imgPath);
							showInformationAlert("Cambios regitrados", "Se han cambiado los datos exitosamente", null);
							setUserProfileFields();
							passwordTxt.clear();
							confirmationTxt.clear();
						} catch (UserNameAlreadyInUseException e) {
							showInformationAlert("No se pudo completar el cambio", e.getMessage(), null);
						} catch (IOException e) {
							showInformationAlert("No se pudo completar el cambio", "Ocurrió un eeror inesperado :(", null);
						}//End try/catch
					} else {
						showInformationAlert("Contraseña demasiado corta", "La contraseña es demasiado corta, debe contener por lo menos siete (7) caracteres. Vuelva a intentarlo", null);
					}//End if/else
				} else {
					showInformationAlert("Contraseñas distintas","Las contraseñas no coinciden, vuelva a intentarlo",null);
				}//End if/else
			} else {
				showInformationAlert("Campos vacíos","Deben llenarse todos los campos",null);
			}//End if/else
		}//End if
	}//End changeUserData

	@FXML
	public void deleteUserAccount(Event e) throws IOException {
		if(showConfirmationAlert("Eliminar cuenta de usuario", "¿Está seguro que desea eliminar su cuenta de usuario definitivamente?", null)) {
			switchToMainPane();
			showLoginScene();
			academicSchedule.deleteUser();
			showInformationAlert("Cuenta eliminada", "Se ha eliminado su cuenta exitosamente", null);
		}//End if
	}//End deleteUserAccount
	
	private void initializeDayChoiceBox(){
		ObservableList<String> status = FXCollections.observableArrayList();
		status.add("Lunes");
		status.add("Martes");
		status.add("Miercoles");
		status.add("Jueves");
		status.add("Viernes");
		status.add("Sabado");
		dayCB.setItems(status);
	}//initializeCourseStatus
	
	private void initializeCoursesChoiceBox(){
		if(academicSchedule.getCurrentUser().getAcademicSchedule().getFirstCourse() != null){
			ObservableList<Course> coursesList = FXCollections.observableArrayList();
			coursesList.add(academicSchedule.getCurrentUser().getAcademicSchedule().getFirstCourse());
			addCourseToChoiceBox(academicSchedule.getCurrentUser().getAcademicSchedule().
					getFirstCourse().getNext(),coursesList);
			courseCB.setItems(coursesList);
		}//End if
	}//initializeCourseStatus
	
	private void addCourseToChoiceBox(Course current, ObservableList<Course> coursesList){
		if(current != academicSchedule.getCurrentUser().getAcademicSchedule().getFirstCourse()){
			coursesList.add(current);
			addCourseToChoiceBox(current.getNext(),coursesList);
		}//End if
	}//End addCourseToChoiceBox
	
	//********************** Events listeners *******************************************

	@FXML
	public void ShowCoursesError(){
		showInformationAlert("Que gonorrea","Ocurrio tremendo error, nunca deberias ver esto",null);
	}//End showCoursesError
	
	@FXML
	public void listenEnteredDay(MouseEvent event){
		Pane day = (Pane) event.getSource();
		day.setOpacity(0.7);
	}//End listenEnteredDay
	
	@FXML
	public void listenExitedDay(MouseEvent event){
		Pane day = (Pane) event.getSource();
		day.setOpacity(1);
	}//End listenEnteredDay
	
	@FXML
	public void listenEnteredProfileImgEvent(MouseEvent event){
		ProfileImg.setOpacity(0.5);
	}//End listenProfileImgEvent
	
	public void chooseImgFilePath() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona una imagen");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Custom extensions", "*.jpg", "*.png", "*.jpeg")
            );
		File f = fileChooser.showOpenDialog(null);
		if(f != null) {
			imgPath = "file:/" + f.getAbsolutePath();
		}//End if
	}//End chooseImgFilePath
	
	@FXML
	public void listenChooseImage(MouseEvent event){
		chooseImgFilePath();
		if(imgPath != null){
			ProfileImg.setImage(new Image(imgPath));
			ProfileImg.setFitWidth(99);
		}//End if
	}//file:/D:/brianR/ElpolloOriginal.jpg

	@FXML
	public void listenExitedProfileImgEvent(MouseEvent event ){
		ProfileImg.setOpacity(1);
	}//End listenProfileImgEvent

}//End MainWindowsController