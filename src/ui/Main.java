package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private MainWindowsController mwc;
	
	public Main() {
		mwc = new MainWindowsController();
	}
	
	public static void main(String[] args){
		launch(args);
	}//End main

	@Override
	public void start(Stage window) throws Exception {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("fxml/MainWindows.fxml"));
		fxml.setController(mwc);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		window.setScene(scene);
		window.setTitle("Ventana principal");
		window.show();
		mwc.showLoginScene();
	}//End start

}//End Main
