package ui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.InvalidCredentialsException;
import exceptions.UserNameAlreadyInUseException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AcademyScheduleUsersManager;
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
	private EmergentWindowController ewc;
	//************User data ***************
	@FXML private ImageView ProfileImg;
	@FXML private Label imgText;
	@FXML private TextField nameTxt;
	@FXML private TextField lastNameTxt;
	@FXML private TextField userNameTxt;
	@FXML private TextField passwordTxt;
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
	@FXML private ListView<Goal> goalsLV;
	@FXML private  ChoiceBox<String> dayCB;
	@FXML private ChoiceBox<Course> courseCB;
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
	private AcademyScheduleUsersManager academicSchedule;
	
	public MainWindowsController(AcademyScheduleUsersManager as){
		this.academicSchedule = as;
		ewc = new EmergentWindowController(as);
		days = new ArrayList<String>();
		initHour = new ArrayList<Time>();
		finHour = new ArrayList<Time>();
		coursesBlock = new ArrayList<CourseBlock>();
	}//End MainWindowsController constructor
	
	
	//**************************** SCENES *************************************
	@FXML
	public void showLoginScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"LoginWindows.fxml"));
		fxmlLoader.setController(this);
		Parent loginScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(loginScene);
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setTitle("Iniciar sesion");
		stage.setWidth(600);
		stage.setHeight(440);
		stage.setResizable(false);
	}//End showLoginScene
	
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
	public void showRegisterUser() throws IOException {
		imgPath = DEFAULTPROFILEIMG;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindow.fxml"));
		fxml.setController(this);
		Parent registerUser = fxml.load();
		mainPane.setCenter(registerUser);
		Stage st = (Stage) mainPane.getScene().getWindow();
		st.setTitle("Registrar usuario");
		st.setHeight(530);
		st.setWidth(520);
		st.setResizable(false);
	}//End switchToMainPane
	
	@FXML
	public void switchToSecondaryPane(Event e) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"SecondaryWindows.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, null);
		window.setScene(scene);
		window.setTitle("Bienvenido");
		window.show();
	}//End switchToSecondaryPane
	
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
		//addCourseToScreen();
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
	public void addCourseToScreen(){
		coursesBlock.add(new CourseBlock(academicSchedule.getCurrentUser().getAcademicSchedule().getFirstCourse(),colours[0],ewc));
		center.getChildren().addAll(coursesBlock.get(0).getHBox());
		center.setSpacing(10);
	}//End addCourseToScreen
	
	@FXML
	public void registerUser(){
		if(!nameTxt.getText().isEmpty() && !lastNameTxt.getText().isEmpty() &&
		   !userNameTxt.getText().isEmpty() && !passwordTxt.getText().isEmpty() && !imgPath.equals(DEFAULTPROFILEIMG)){
			try {
				academicSchedule.addUser(nameTxt.getText(),lastNameTxt.getText(),
						userNameTxt.getText(),passwordTxt.getText(),imgPath);
				showInformationAlert("registro","El usuario se ha registrado con exito",null);
				nameTxt.setText("");
				lastNameTxt.setText("");
				userNameTxt.setText("");
				passwordTxt.setText("");
				imgPath = DEFAULTPROFILEIMG;
				ProfileImg.setImage(new Image(imgPath));
			}catch(UserNameAlreadyInUseException e){
				showInformationAlert("registro","El nombre de usuario " + userNameTxt.getText() + " ya est en uso",null);
			} catch (IOException e) {
				e.printStackTrace();
			}//End try/catch
		}else
			showInformationAlert("registro","Llena por completo los campos",null);
	}//End registUser
	
	@FXML
	public void loginUser(Event e) throws IOException{
		if(!userNameTxt.getText().isEmpty() && !passwordTxt.getText().isEmpty()){
			try{
				academicSchedule.login(userNameTxt.getText(),passwordTxt.getText());
				showInformationAlert("registro","Haz ingresado con exito",null);
				switchToSecondaryPane(e);
			}catch(InvalidCredentialsException ice){
				showInformationAlert("registro","Credenciales incorrectas",null);
			}//End try/catch
		}else
			showInformationAlert("registro","Llena por completo los campos",null);
	}//End loginUser
	
	@FXML
	public void registerCourse(){
		String msg = "Llena todos los campos";
		if(days.size() != 0 && !courseNameTxt.getText().isEmpty() && 
			!creditsTxt.getText().isEmpty()){
			try{
				int credits = Integer.parseInt(creditsTxt.getText());
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
	
	//********************** Events listeners *******************************************
	
	@FXML
	public void ShowCoursesError(){
		showInformationAlert("Que gonorrea","Ocurrio tremendo error, nunca deberias ver esto",null);
	}
	
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
		imgText.setOpacity(1);
	}//End listenProfileImgEvent
	
	public void chooseImgFilePath() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona una imagen");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
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
		}
	}//file:/D:/brianR/ElpolloOriginal.jpg
	
	@FXML
	public void listenExitedProfileImgEvent(MouseEvent event ){
		ProfileImg.setOpacity(1);
		imgText.setOpacity(0);
	}//End listenProfileImgEvent
	
}//End MainWindowsController
