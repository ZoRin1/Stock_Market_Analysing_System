package uiControl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.geometry.HPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class timeInControl {
	private DatePicker checkInDatePicker;
	private GridPane gridPane;
	public timeInControl(String string){
		checkInDatePicker = new DatePicker();
		checkInDatePicker.setMaxSize(200, 30);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        Label checkInlabel = new Label("Check "+string+" Date:");
        checkInlabel.setFont(Font.font(24));
        checkInlabel.setTextFill(Color.WHITE);
        gridPane.add(checkInlabel, 0, 0);

        GridPane.setHalignment(checkInlabel, HPos.LEFT);
        gridPane.add(checkInDatePicker, 0, 1);
	}
	
	public GridPane getTime(){
		return gridPane;
	}
	
	public void setX(double x){
		gridPane.setLayoutX(x);
	}
	public void setY(double y){
		gridPane.setLayoutY(y);
	}
	
	public void setValue(LocalDate a){
		checkInDatePicker.setValue(a);
	}
	public LocalDate getValue(){
		return checkInDatePicker.getValue();
	}
	public Date getDate(){
		LocalDate localDate = checkInDatePicker.getValue();
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		java.util.Date riqi = Date.from(instant);
		return riqi;
	}
	public void setDayCellFactory(Callback a){
		checkInDatePicker.setDayCellFactory(a);
	}
}
