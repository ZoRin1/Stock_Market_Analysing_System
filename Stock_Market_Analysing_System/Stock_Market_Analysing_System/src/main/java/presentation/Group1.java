package presentation;


import BL.SearchOne;
import blService.SearchOneBlSer;
import uiControl.TopButton;
import uiControl.WaitLoading;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Group1 {
//	private ComboBox comboBox;
	private Label logo;
	private GroupFather groupFather;
	private Group root;
	private Group group;
	private GridPane grid;
	private TextField search;
	private Button searchButton;
	private TopButton b1;
	private TopButton b2;
	private TopButton b3;
	private TopButton b4;
	private Label dapan;
	public Group1(Group root, GroupFather groupFather){
		this.root=root;
		this.groupFather=groupFather;
		init();
		jianting(this);
	}
	private  void init() {
		logo=new Label();
		logo.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("LOGO.png"))));
		logo.setLayoutX(700);
		logo.setLayoutY(15);
		
		dapan=new Label("沪深300");
		dapan.setTextAlignment(TextAlignment.CENTER);
		dapan.setTextFill(Color.WHITE);
		dapan.setFont(Font.font("SimHei", 40));
		dapan.setLayoutX(150);
		dapan.setLayoutY(965);
		//大盘标签缩放效果
		dapan.setOnMouseEntered(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				dapan.setScaleX(1.2);
		    	dapan.setScaleY(1.2);
			}
		});
		dapan.setOnMouseExited(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				dapan.setScaleX(1);
		    	dapan.setScaleY(1);
			}
		});
		//大盘标签缩放效果
	
		b1=new TopButton("上证市场");
		b2=new TopButton("深证市场");
		b3=new TopButton("自选股");
		b4=new TopButton("行业分析");
		
		b1.setX(0);
		b1.setY(125);
		b2.setX(200);
		b2.setY(125);
		b3.setX(400);
		b3.setY(125);
		b4.setX(600);
		b4.setY(125);
		group=new Group();
		//股票搜索框
		grid = new GridPane();//Creating a GridPane container 
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5); 
		grid.setHgap(5);
		//自动补全
//		ObservableList<String> options =   
//			    FXCollections.observableArrayList(  
//			        "Option 1",  
//			        "Option 2",  
//			        "Option 3"  
//			    ); 
//		comboBox=new ComboBox<>(options);
//		comboBox.setVisibleRowCount(5);
//		GridPane.setConstraints(comboBox, 2, 0);
//		grid.getChildren().add(comboBox);
		search = new TextField();
		search.setPromptText("Please input the Inf to search");
		search.setPrefColumnCount(30);
		search.setMinHeight(40);
		GridPane.setConstraints(search, 0, 0);
		grid.getChildren().add(search);
//		search.setOnKeyPressed(new EventHandler<Event>() {
//
//			@Override
//			public void handle(Event arg0) {
//				// TODO Auto-generated method stub
//			}
//		});
		searchButton= new Button("Search");
		searchButton.setMinHeight(40);
		GridPane.setConstraints(searchButton, 1, 0);
		grid.getChildren().add(searchButton);
		
		grid.setLayoutX(40);
		grid.setLayoutY(40);
		
		//股票搜索框

		group.getChildren().add(logo);
		group.getChildren().add(dapan);
		group.getChildren().add(b1.getButton());
		group.getChildren().add(b2.getButton());
		group.getChildren().add(b3.getButton());
		group.getChildren().add(b4.getButton());
		group.getChildren().add(grid);
		
	}
	public void jianting(final Group1 group1){
		searchButton.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				SearchOneBlSer searchOneBl=new SearchOne();
				String searchString=searchOneBl.searchOne(search.getText());
				if (searchString.equals("")) {
					final Label cwLabel = new Label();
//					cwLabel.setGraphic(new ImageView(new Image("输入信息错误.png")));
					cwLabel.setEffect(new ImageInput(new Image(getClass().getResourceAsStream("输入错误.png"))));
					//显示错误Label 在10秒后消失
					cwLabel.setLayoutX(660);
					cwLabel.setLayoutY(430);
					
					group1.getGroupFather().addNode(cwLabel);
					final Timeline timeline = new Timeline();
					timeline.setAutoReverse(true);
					timeline.getKeyFrames().addAll(new KeyFrame(Duration.ZERO,new KeyValue(cwLabel.opacityProperty(), 2)),
							new KeyFrame(new Duration(3000),new KeyValue(cwLabel.opacityProperty(), 0)));
					timeline.play();
					timeline.setOnFinished(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							// TODO Auto-generated method stub
							group1.getGroupFather().removeNode(cwLabel); //这个地方有待商榷
						}
					});
					//显示错误Label 在10秒后消失
					
				}
				else {
					WaitLoading waitLoading=new WaitLoading();
					root.getChildren().add(waitLoading);
					
					new Thread(new Runnable() {
						 	@Override public void run() {
						 		String string[]=searchString.split(",");
						 		GroupFather group3=new Group3(string[1],string[0]);
							 Platform.runLater(new Runnable() {
								 @Override public void run() {
									 root.getChildren().remove(groupFather.getGroup());
									 groupFather=group3;
									 root.getChildren().add(group3.getGroup());
									 root.getChildren().remove(waitLoading);
									 search.setText("");
								 }
							 });
						 	}
					}).start();

				}
				
			}
		});
		dapan.setOnMouseClicked(new EventHandler<Event>() {


			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				root.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {						
					 	GroupFather group4=new Group4();
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 root.getChildren().remove(groupFather.getGroup());
								 groupFather=group4;
								 root.getChildren().add(group4.getGroup());
								 root.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();
				
			}
		});
		//大盘点击
		b1.getButton().setOnMouseClicked(new EventHandler<Event>() {


			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				root.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {						
						 GroupFather group2=new Group2("上证市场");
						 group2.setGroup1(group1);							
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 root.getChildren().remove(groupFather.getGroup());
								 groupFather=group2;
								 root.getChildren().add(group2.getGroup());
								 root.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();
				
			}
		});
		b2.getButton().setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				root.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {						
						 GroupFather group2=new Group2("深证市场");
						 group2.setGroup1(group1);							
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 root.getChildren().remove(groupFather.getGroup());
								 groupFather=group2;
								 root.getChildren().add(group2.getGroup());
								 root.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();
				
			}
		});
		b3.getButton().setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				root.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {						
						 GroupFather group2=new Group2("自选股");
						 group2.setGroup1(group1);							
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 root.getChildren().remove(groupFather.getGroup());
								 groupFather=group2;
								 root.getChildren().add(group2.getGroup());
								 root.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();
				
			}
		});
		b4.getButton().setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				root.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {						
							GroupFather group5=new Group5();
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 root.getChildren().remove(groupFather.getGroup());
								 groupFather=group5;
								 root.getChildren().add(group5.getGroup());
								 root.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();	
			}
		});
	}
	public Group getroot(){
		return root;
	}
	public GroupFather getGroupFather(){
		return groupFather;
	}
	public void setGroupFather(GroupFather groupFather){
		this.groupFather=groupFather;
	}
	public Group getGroup(){
		return group;
	}

}

