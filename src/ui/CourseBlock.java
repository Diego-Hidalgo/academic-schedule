package ui;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Course;

public class CourseBlock {

	private HBox course;
	private Label cn;
	private Course cs;
	private String colour;
	private EmergentWindowController ewc;
	
	public CourseBlock(Course c, String col,EmergentWindowController emergent){
		course = new HBox();
		course.setPrefHeight(25);
		cn = new Label();
		ewc = emergent;
		HBox.setMargin(cn, new Insets(3, 0, 0, 5));
		cn.setFont(Font.font("Avenir Next LT Pro", FontWeight.BOLD, 14));
		cn.setTextFill(Color.web("FFFFFF"));
		course.getChildren().addAll(cn);
		cs = c;
		colour = col;
		if(cs != null)
		cn.setText(cs.getName());
		else
			cn.setText("Tal parece que aun no hay cursos");
		course.setStyle("-fx-background-color: #"+colour+";");
		course.setOnMouseEntered(new EventHandler<MouseEvent>() {
			  public void handle(MouseEvent e){
				  course.setOpacity(0.7);
			  }//End handle  
		});
		course.setOnMouseExited(new EventHandler<MouseEvent>() {
			  public void handle(MouseEvent e){
				  course.setOpacity(1);
			  }//End handle
		});
		course.setOnMouseClicked(new EventHandler<MouseEvent>() {
			  public void handle(MouseEvent e){
				  showCourseInformation();
			  }//End handle  
		});
	}//End CourseBlock constructor
	
	public HBox getHBox(){
		return course;
	}//End HBox
	
	private void showCourseInformation(){
		if(cs != null){
			try{
				ewc.showCourseData(cs);
			}catch(IOException e) {
				showInformationAlert("Error","Ha ocurrido un error inesperado",null);
			}	
		}else
			showInformationAlert("Upps","Tal parece que este curso no tiene informacion",null);
	}
	
	private void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert
}//End CourseBlock

