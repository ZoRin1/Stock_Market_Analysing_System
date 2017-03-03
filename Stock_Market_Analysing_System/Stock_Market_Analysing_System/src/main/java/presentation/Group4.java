package presentation;

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
import uiControl.SideButton;
import uiControl.WaitLoading;
/**
 * 
 * @author wang
 *大盘行情的group
 */
public class Group4 implements GroupFather{
	private Group group;
	private Label label;
	private SideButton b1;
	private SideButton b2;
	private SideButton b3;
	private Group4Father group4Father;
	public Group4(){	
		init();
	}
	public Group getGroup(){
		return group;
	}
	public  void init(){
		group4Father=new Group41();
		b1=new SideButton("历史行情");
		b1.setX(0);
		b1.setY(175);
		b2=new SideButton("折线图");
		b2.setX(0);
		b2.setY(325);
		b3=new SideButton("K线图");
		b3.setX(0);
		b3.setY(475);
		group=new Group();
		BorderPane borderPane=new BorderPane();
		borderPane.setLayoutX(750);
		borderPane.setLayoutY(125);
		borderPane.setMinHeight(50);
		borderPane.setMinWidth(400);
		label=new Label("沪深300");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("SimHei", 40));
		borderPane.setCenter(label);
	
				
		group.getChildren().add(group4Father.getGroup());
		group.getChildren().add(b1.getButton());
		group.getChildren().add(b2.getButton());
		group.getChildren().add(b3.getButton());
		group.getChildren().add(borderPane);
		
		b1.getButton().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
							
				new Thread(new Runnable() {
					 	@Override public void run() {	
					 		Group4Father newGroup4Father=new Group41();
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								group.getChildren().remove(group4Father.getGroup());
								group4Father=newGroup4Father;
								 group.getChildren().add(group4Father.getGroup());
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
							
				new Thread(new Runnable() {
					 	@Override public void run() {	
					 		Group4Father newGroup4Father=new Group42();
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								group.getChildren().remove(group4Father.getGroup());
								group4Father=newGroup4Father;
								 group.getChildren().add(group4Father.getGroup());
								 group.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();	
			}
		});
		
		b3.getButton().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				group.getChildren().remove(group4Father.getGroup());
				group4Father=new Group43();
				group.getChildren().add(group4Father.getGroup());
			}
		});

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
