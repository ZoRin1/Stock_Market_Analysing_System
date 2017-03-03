package presentation;


import BL.Raise;
import blService.RaiseBlSer;
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
import uiControl.TopButton;
import uiControl.WaitLoading;
/**
 * 
 * @author wang
 *单只股票的group
 */
public class Group3 implements GroupFather{
	private Group group;
	private Group3Father group3Father;
	private Label label;
	private SideButton b1;
	private SideButton b2;
	private SideButton b3;
	private SideButton b4;
	private TopButton shoucangButton;
	private String nameString;
	private String codeString;

	public Group3(String nameString,String codeString){	
		this.nameString=nameString;
		this.codeString=codeString;
		init();
	}
	public Group getGroup(){
		return group;
	}
	public void init(){		
		RaiseBlSer raiseBl=new Raise();
		group3Father=new Group31(codeString);
		shoucangButton=new TopButton("收藏为自选股");
		if (raiseBl.checkExise(codeString)) {
			shoucangButton.getButton().setText("取消收藏");
		}
		shoucangButton.setX(1685);
		shoucangButton.setY(965);
		b1=new SideButton("历史行情");
		b1.setX(0);
		b1.setY(175);
		b2=new SideButton("折线图");
		b2.setX(0);
		b2.setY(325);
		b3=new SideButton("K线图");
		b3.setX(0);
		b3.setY(475);
		b4=new SideButton("技术分析");
		b4.setX(0);
		b4.setY(625);
		group=new Group();
		BorderPane borderPane=new BorderPane();
		borderPane.setLayoutX(780);
		borderPane.setLayoutY(125);
		borderPane.setMinHeight(50);
		borderPane.setMinWidth(400);
		label=new Label(nameString+"("+codeString+")");
		label.setTextAlignment(TextAlignment.CENTER);
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("SimHei", 40));
		borderPane.setCenter(label);

		group.getChildren().add(shoucangButton.getButton());
		group.getChildren().add(b1.getButton());
		group.getChildren().add(b2.getButton());
		group.getChildren().add(b3.getButton());
		group.getChildren().add(b4.getButton());
		group.getChildren().add(borderPane);
		group.getChildren().add(group3Father.getGroup());
		shoucangButton.getButton().setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				RaiseBlSer raiseBl=new Raise();
				if (shoucangButton.getButton().getText().equals("取消收藏")) {
					raiseBl.delete(codeString);
					shoucangButton.getButton().setText("收藏为自选股");
				}else {
					raiseBl.raise(codeString);
					shoucangButton.getButton().setText("取消收藏");
				}
			}
		});
		b1.getButton().setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
						
				new Thread(new Runnable() {
					 	@Override public void run() {	
					 		Group3Father newGroup3Father=new Group31(codeString);
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 group.getChildren().remove(group3Father.getGroup());
								 group3Father=newGroup3Father;
								 group.getChildren().add(group3Father.getGroup());
								 group.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();	
				
			}
			
		});
		b2.getButton().setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
						
				new Thread(new Runnable() {
					 	@Override public void run() {	
					 		Group3Father newGroup3Father=new Group32(codeString);
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 group.getChildren().remove(group3Father.getGroup());
								 group3Father=newGroup3Father;
								 group.getChildren().add(group3Father.getGroup());
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
				group.getChildren().remove(group3Father.getGroup());
				group3Father=new Group33(codeString);
				group.getChildren().add(group3Father.getGroup());		        
			}
		});
		b4.getButton().setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub

				group.getChildren().remove(group3Father.getGroup());
				group3Father=new Group34(codeString);
				group.getChildren().add(group3Father.getGroup());		        
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
