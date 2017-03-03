/**
 * 
 */
package presentation;

import uiControl.SideButton;
import uiControl.WaitLoading;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author wang
 *
 */
public class Group5 implements GroupFather{
	private Group group;
	private Group5Father group5Father;
	private Label label;
	private SideButton b1;
	private SideButton b2;
	public Group5() {
		init();
	}
	/* (non-Javadoc)
	 * @see presentation.GroupFather#init()
	 */
	@Override
	public void init() {
		// TODO Auto-generated method stub
		group5Father=new Group51("sh");
		group=new Group();
		b1=new SideButton("上证市场");
		b1.setX(0);
		b1.setY(175);
		b2=new SideButton("深证市场");
		b2.setX(0);
		b2.setY(325);
		BorderPane borderPane=new BorderPane();
		borderPane.setLayoutX(750);
		borderPane.setLayoutY(125);
		borderPane.setMinHeight(50);
		borderPane.setMinWidth(400);
		label=new Label("上证市场");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("SimHei", 40));
		borderPane.setCenter(label);
		group.getChildren().add(group5Father.getGroup());
		group.getChildren().add(b1.getButton());
		group.getChildren().add(b2.getButton());
		group.getChildren().add(borderPane);
		b1.getButton().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
				
		 		label.setText("上证市场");
				new Thread(new Runnable() {
					 	@Override public void run() {	
					 		Group5Father newgroup5Father=new Group51("sh");
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 group.getChildren().remove(group5Father.getGroup());	
								 group5Father=newgroup5Father;
								 group.getChildren().add(group5Father.getGroup());
								 group.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();					
			}
			
		});
		b2.getButton().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
		 		label.setText("深证市场");
				new Thread(new Runnable() {
					 	@Override public void run() {	
					 		Group5Father newgroup5Father=new Group51("sz");
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 group.getChildren().remove(group5Father.getGroup());	
								 group5Father=newgroup5Father;
								 group.getChildren().add(group5Father.getGroup());
								 group.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();		
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see presentation.GroupFather#getGroup()
	 */
	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return group;
	}

	/* (non-Javadoc)
	 * @see presentation.GroupFather#setGroup1(presentation.Group1)
	 */
	@Override
	public void setGroup1(Group1 group1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see presentation.GroupFather#addNode(javafx.scene.Node)
	 */
	@Override
	public void addNode(Node node) {
		// TODO Auto-generated method stub
		this.group.getChildren().add(node);
	}
	/* (non-Javadoc)
	 * @see presentation.GroupFather#removeNode(javafx.scene.Node)
	 */
	@Override
	public void removeNode(Node node) {
		// TODO Auto-generated method stub
		this.group.getChildren().remove(node);
	}

}
