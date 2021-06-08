package ui;

import java.io.File;
import java.io.IOException;

import exceptions.UserNameAlreadyInUseException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

	//************User data ***************\\
	@FXML private ImageView ProfileImg;
	@FXML private TextField nameTxt;
	@FXML private TextField lastNameTxt;
	@FXML private TextField userNameTxt;
	@FXML private TextField passwordTxt;
	@FXML private TextField confirmationTxt;
	private String imgPath;
	
	
	//************ Academic schedule *******
	private AcademyScheduleUsersManager as;
	
	public MainWindowsController(AcademyScheduleUsersManager as){
		this.as = as;
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
		window.setTitle("Bienvenido");
		window.show();
	}//End switchToSecondaryPane

	@FXML
	public void showLoginScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"LoginWindows.fxml"));
		fxmlLoader.setController(this);
		Parent loginScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(loginScene);
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setTitle("Iniciar sesion");
		stage.setWidth(500);
		stage.setHeight(480);
		stage.setResizable(false);
	}//End showLoginScene
	
	@FXML
	public void showRegisterUser() throws IOException {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindow.fxml"));
		fxml.setController(this);
		Parent registerUser = fxml.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerUser);
		Stage st = (Stage) mainPane.getScene().getWindow();
		st.setTitle("Registrar usuario");
		st.setHeight(530);
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
	
	public void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert
	
	//************************ Objects and data managment *******************
	
	@FXML
	public void registerUser(ActionEvent event) {
		String name = nameTxt.getText();
		String lastName = lastNameTxt.getText();
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		String confirmation = confirmationTxt.getText();
		if(as.verifyBlankChars(new String[]{name, lastName, userName, password, confirmation})) {
			if(password.equals(confirmation)) {
				if(password.length() >= 7) {
					try {
						as.addUser(name, lastName, userName, password, imgPath);
						showInformationAlert("Registro completado", "Se ha registrado al nuevo usuario exitosamente", null);
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
	
	//********************** Events listeners *******************************************

	@FXML
	public void listenEnteredProfileImgEvent(MouseEvent event ){
		ProfileImg.setOpacity(0.5);
	}//End listenProfileImgEvent
	
	public void chooseImgFilePath() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona una imagen");
		fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG, PNG, JPEG", "*.jpg", "*.png", "*.jpeg")
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
	}//End listenProfileImgEvent

}
