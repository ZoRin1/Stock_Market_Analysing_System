/**
 * 
 */
package uiControl;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * @author wang
 *
 */
public class SideButton {
	private Button button;
	public SideButton (String text){
		button=new Button(text);
		button.setWrapText(true);
		button.setMinHeight(150);
		button.setMaxWidth(50);
		
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