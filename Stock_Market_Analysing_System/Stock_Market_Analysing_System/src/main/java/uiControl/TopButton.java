package uiControl;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class TopButton {
	private Button button;
	public TopButton (String text){
		button=new Button(text);
		button.setMinHeight(50);
		button.setMinWidth(200);
	}
	public Button getButton(){
		return button;
	}
	public void setX(double x){
		button.setLayoutX(x);
	}
	public void setY(double y){
		button.setLayoutY(y);
	}
}
