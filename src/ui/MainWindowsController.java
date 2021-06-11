package ui;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import exceptions.InvalidCredentialsException;
import exceptions.UserNameAlreadyInUseException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
		window.setTitle("Bienvenido " + as.getCurrentUser().getUserName());
		window.show();
	}//End switchToSecondaryPane

	@FXML
	public void logInUser(Event e) throws IOException {
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		if(as.verifyBlankChars(new String[]{userName, password})) {
			try {
				as.login(userName, password);
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
	
	public void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert

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
	
	@FXML
	public void registerUser(ActionEvent event) throws URISyntaxException {
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
		if(as.verifyBlankChars(new String[]{userName, newPassword, confirmation})) {
			if(as.searchUser(userName) != -1) {
				if(newPassword.equals(confirmation)) {
					if(newPassword.length() >= 7) {
						as.changeUserPassword(userName, newPassword);
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
	public void deleteUserAccount() throws IOException {
		if(showConfirmationAlert("Eliminar cuenta de usuario", "¿Está seguro que desea eliminar su cuenta de usuario definitivamente?", null)) {
			as.deleteUser();
			showLoginScene();
			showInformationAlert("Cuenta eliminada", "Se ha eliminado su cuenta exitosamente", null);
		}//End if
	}//End deleteUserAccount
	
	//********************** Events listeners *******************************************

	@FXML
	public void listenEnteredProfileImgEvent(MouseEvent event ){
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
