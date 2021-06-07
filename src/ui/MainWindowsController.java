package ui;

import java.io.File;
import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.AcademyScheduleUsersManager;

public class MainWindowsController {
	
	final private String FOLDER = "fxml/";
	@FXML private BorderPane mainPane;
	@FXML private MenuBar menuBar;
	//************User data ***************
	@FXML private ImageView ProfileImg;
	@FXML private Label imgText;
	@FXML private TextField nameTxt;
	@FXML private TextField lastNameTxt;
	@FXML private TextField userNameTxt;
	@FXML private TextField passwordTxt;
	private String imgPath;
	
	
	//************ Academic schedule *******
	private AcademyScheduleUsersManager as;
	
	public MainWindowsController(AcademyScheduleUsersManager as){
		this.as = as;
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
	
	public void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert
	
	//************************ Objects and data managment *******************
	
	@FXML
	public void registerUser(){
		//if(){
			
		//}
	}//End registUser
	
	
	
	//********************** Events listeners *******************************************
	
	@FXML
	public void listenEnteredProfileImgEvent(MouseEvent event ){
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
}
