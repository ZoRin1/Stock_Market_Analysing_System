/**
 * 
 */
package uiControl;

import presentation.Group5Father;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * @author wang
 *
 */
public class cuowuLabel extends Label{
	/**
	 * 
	 */
	public cuowuLabel(Group group) {
		// TODO Auto-generated constructor stub
		this.setText("网络错误，请检查网络后重新打开，程序在三秒后自动关闭");
		this.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
		this.setTextFill(Color.RED);
		this.setLayoutX(660);
		this.setLayoutY(430);
		group.getChildren().add(this);
	}
}
