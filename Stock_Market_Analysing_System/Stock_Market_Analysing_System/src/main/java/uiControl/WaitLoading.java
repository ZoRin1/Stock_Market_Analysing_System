/**
 * 
 */
package uiControl;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author wang
 *
 */
public class WaitLoading extends BorderPane{
	/**
	 * 
	 */
	public WaitLoading() {
		// TODO Auto-generated constructor stub
		this.setLayoutX(50);  
		this.setLayoutY(176);
		this.setMinSize(1834, 739);
		this.setMaxSize(1834, 739);
		ProgressIndicator progressIndicator=new ProgressIndicator(-1);
		progressIndicator.setMaxSize(300, 300);
		this.setCenter(progressIndicator);

	}
	
}
