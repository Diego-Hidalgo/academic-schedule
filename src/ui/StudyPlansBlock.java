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
import model.StudyPlan;

public class StudyPlansBlock {
	private HBox course;
	private Label cn;
	private StudyPlan sp;
	private String colour;
	private EmergentWindowController ewc;
	
	public StudyPlansBlock(StudyPlan c, String col,EmergentWindowController emergent){
		course = new HBox();
		course.setPrefHeight(25);
		cn = new Label();
		ewc = emergent;
		HBox.setMargin(cn, new Insets(3, 0, 0, 5));
		cn.setFont(Font.font("Avenir Next LT Pro", FontWeight.BOLD, 14));
		cn.setTextFill(Color.web("FFFFFF"));
		course.getChildren().addAll(cn);
		sp = c;
		colour = col;
		if(sp != null)
		cn.setText(sp.getTitle());
		else
			cn.setText("Tal parece que aun no hay planes de estudio");
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
		if(sp != null){
			try{
				ewc.showStudyPlanData(sp);
			}catch(IOException e) {
				showInformationAlert("Error","Ha ocurrido un error inesperado",null);
			}	
		}else
			showInformationAlert("Upps","Tal parece que aun no tienes planes de estudio",null);
	}
	
	private void showInformationAlert(String title,String msg,String header){
		Alert feedBack = new Alert(AlertType.INFORMATION);
		feedBack.setTitle(title);
		feedBack.setHeaderText(header);
		feedBack.setContentText(msg);
		feedBack.showAndWait();
	}//End showInformationAlert
}
