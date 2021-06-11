package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Course;

public class CourseBlock {

	private HBox course;
	private Label cn;
	private Course cs;
	private String color;
	
	public CourseBlock(Course c){
		course = new HBox();
		course.setPrefHeight(25);
		HBox.setMargin(cn, new Insets(3, 0, 0, 5));
		cn.setFont(Font.font("Avenir Next LT Pro", FontWeight.BOLD, 14));
		cn.setTextFill(Color.web("FFFFFF"));
		course.getChildren().addAll(cn);
		cs = c;
		cn.setText(cs.getName());
		course.setStyle("-fx-background-color: #"+color+";");
		course.setOnMouseEntered(new EventHandler<MouseEvent>(){
			
		});
	}//End CourseBlock constructor
	
	public HBox getHBox(){
		return course;
	}//End HBox
}//End CourseBlock

