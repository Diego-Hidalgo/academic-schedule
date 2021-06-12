package ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import exceptions.*;

public class EmergentWindowController {
	
	@SuppressWarnings("unused")
	private AcademyScheduleUsersManager academicSchedule;
	final private String FOLDER = "fxml/EmergentsWindows/";
	
	//******************** Day data **************************
	@FXML private ChoiceBox<String> dayCB;
	@FXML private TextField initHourTxt;
	@FXML private TextField finHourTxt;
	private String day;
	private Time initHour;
	private Time finHour;
	//******************** Course data ***********************
	private Course currentCourse;
	@FXML private Label courseNameTxt;
	@FXML private Label creditsTxt;
	@FXML private Label estatusLb;
	@FXML private Label monday;
	@FXML private Label tuesday;
	@FXML private Label wednesday;
	@FXML private Label thursday;
	@FXML private Label friday;
	@FXML private Label saturday;
	@FXML private TableView<Grade> grades;
	@FXML private TableColumn<Grade,String> gradeDescription;
	@FXML private TableColumn<Grade,String> gradeValue;
	//******************** Goals *****************************
	@FXML private TextArea goalDescriptionTA;
	private String goalST;
	//******************** Study plan **************************
	@FXML private Label titleLB;
	@FXML private Label descriptionLB;
	@FXML private Label relationateCourseLB;
	@FXML private ListView<Goal> goalsLV;
	private StudyPlan currenStudyPlan;
	public EmergentWindowController(AcademyScheduleUsersManager as) {
		academicSchedule = as;
	}//End EmergentWindowController constructor
	
	public void showAddDay() throws IOException {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"AddDayToCourseEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage form = new Stage();
		initializeDayChoiceBox();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Agregar dia");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showAddDay
	
	public void showCourseData(Course courseInfo) throws IOException{
		currentCourse = courseInfo;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ShowCourseInfoEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage form = new Stage();
		initializeCurrentCourseData();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Información del curso");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showCourseData
	
	public void showStudyPlanData(StudyPlan planInfo) throws IOException{
		currenStudyPlan = planInfo;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ShowStudyPlansInfoEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage form = new Stage();
		initializeCurrentStudyPlanData();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Información del plan");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showCourseData
	
	public void showAddGoal() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"AddGoalEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage form = new Stage();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Agregar metas");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showCourseData
	

	//********************* Data management ********************
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
	
	private void initializeCurrentCourseData(){
		courseNameTxt.setText(currentCourse.getName());
		creditsTxt.setText(String.valueOf(currentCourse.getCredits()));
		estatusLb.setText(currentCourse.getStatus() +"");
		activateDays();
	}//End initializeCurrentCourseData
	
	private void initializeCurrentStudyPlanData(){
		titleLB.setText(currenStudyPlan.getTitle());
		descriptionLB.setText(currenStudyPlan.getDescription());
		relationateCourseLB.setText(currenStudyPlan.getCourse().toString());
		ObservableList<Goal> goals = FXCollections.observableArrayList(currenStudyPlan.getGoals());
		goalsLV.setItems(goals);
	}//End initializeCurrentCourseData
	
	private void activateDays(){
		int size = currentCourse.getDays().size();
		for(int i = 0; i < size; i++){
			String day = String.valueOf( currentCourse.getDays().get(i).getDay());
			switch(day){
				case "LUNES": monday.setOpacity(1); break;
				case "MARTES": tuesday.setOpacity(1); break;
				case "MIERCOLES": wednesday.setOpacity(1); break;
				case "JUEVES": thursday.setOpacity(1); break;
				case "VIERNES": friday.setOpacity(1); break;
				case "SABADO": saturday.setOpacity(1); break;
				default:
					System.out.println("Holy shit wth is happening, the day was " + day); break;
			}//End switch
		}//End for
	}//End activateDays
	
	@FXML
	public void addDay(ActionEvent event) {
		boolean exit = false;
		String msg = "Debes llenar todos los campos";
		if(dayCB.getValue() != null && !initHourTxt.getText().isEmpty() && 
			!finHourTxt.getText().isEmpty()){
			try{
				initHour = new Time(initHourTxt.getText());
				finHour = new Time(finHourTxt.getText());
				day = dayCB.getValue();
				if(initHour.compareTo(finHour) == 0){
					msg = "Las horas no pueden ser las mismas";
				}else if(initHour.compareTo(finHour) == 1) {
					msg = "La hora final no puede ser inferior a la hora de inicio";
				}else{
					msg = "Agregado con exito";
					exit = true;
				}
			}catch(InvalidTimeFormatException e){
				msg = "No se reconoce " + e.getWrongFormat() + " como un formato valido";
			} catch (OutOfTimeRangeException e) {
				msg = "Hora incorrecta";
			}//End try/catch
		}//End if
		showInformationAlert("Agregar dia estado",msg,null);
		if(exit)
			closeEmergentWindows(event);
	}//End addDay
	
	public void addGoal(ActionEvent event){
		String msg = "Llena todos los campos";
		boolean exit = false;
		if(!goalDescriptionTA.getText().isEmpty()){
			goalST = goalDescriptionTA.getText();
			msg = "Meta agregada con exito";
			exit = true;
		}//End if
		showInformationAlert("Agregar meta",msg,null);
		if(exit)
			closeEmergentWindows(event);
	}//End addGoal
	
	//************************* Management *********************
	public void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert
	
	public Time getAndClearInitHour(){
		Time t = initHour;
		initHour = null;
		return t;
	}//End getAndClearInitHour
	
	public String getGoal(){
		return goalST;
	}
	
	public Time getAndClearFinHour(){
		Time t = finHour;
		finHour = null;
		return t;
	}//End getAndClearInitHour
	
	public String getAndClearDay(){
		String d = day;
		day = null;
		return d;
	}//End getAndClearDay
	
	private void closeEmergentWindows(ActionEvent event) {
	    Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}//End closeEmergentWindowss
}//End EmergentWindowController

/*
 *  #C80000
 *  #00C87F
 *  #00C8B9
 *  #0037C8
 *  #7300C8
 *  #A700C8
 * */
