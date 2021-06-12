package ui;

import model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application{

	private static final String SAVE_PATH = "save-file.dm";
	
	private MainWindowsController mwc;
	private AcademicScheduleUsersManager as;
	
	public Main() {
		try {
			loadAllData();
		} catch(IOException | ClassNotFoundException e) {
			as = new AcademicScheduleUsersManager();
		}
		mwc = new MainWindowsController(as);
	}
	
	public static void main(String[] args){
		launch(args);
	}//End main

	public void loadAllData() throws IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH);
		if(!f.exists()) {
			f.createNewFile();
		}
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		as = (AcademicScheduleUsersManager) ois.readObject();
		ois.close();
	}//End loadAllData

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
