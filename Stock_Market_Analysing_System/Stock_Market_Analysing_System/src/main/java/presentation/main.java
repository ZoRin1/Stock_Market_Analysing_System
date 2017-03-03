package presentation;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class main extends Application{
	private Label tuichuLabel;
	private Label zuixiaohuaLabel;
	static Point orginPoint=new Point();
	
	private Group root;
	
	private Image backGround,miniSize,exit;
	private GroupFather group2;
	private Group1 group1;
//	private ProgressBar p1;
//	private Text text;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args); 
	}

	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		root = new Group();	
		
//		initGui();
//		System.out.println(Calendar.getInstance());
		initPicture();
//		text.setText("加载图片中.....");
//		p1.setProgress(0.3);
//		text.setText("图片加载完毕.....");
//		
//		text.setText("加载组件 1 ........");
//		p1.setProgress(0.5);
		//窗口最大化显示
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(primaryScreenBounds.getMinX()+10);
		primaryStage.setY(primaryScreenBounds.getMinY()+5);
		primaryStage.setWidth(1895);
		primaryStage.setHeight(1020);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		//窗口最大化显示		
		//背景图
		ImageView imageView = new ImageView();
		imageView.setImage(backGround);
//		imageView.setFitHeight(primaryScreenBounds.getHeight());//使图片fit满Scene
//		imageView.setFitWidth(primaryScreenBounds.getWidth());//使图片fit满Scene
		//背景图


		//最小化		
		zuixiaohuaLabel=new Label();
		zuixiaohuaLabel.setEffect(new ImageInput(miniSize));
		zuixiaohuaLabel.setLayoutX(1730);
		zuixiaohuaLabel.setLayoutY(0);
		zuixiaohuaLabel.setOnMouseEntered(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				zuixiaohuaLabel.setScaleX(1.2);
				zuixiaohuaLabel.setScaleY(1.2);
			}
		});
		zuixiaohuaLabel.setOnMouseExited(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				zuixiaohuaLabel.setScaleX(1);
				zuixiaohuaLabel.setScaleY(1);
			}
		});
		zuixiaohuaLabel.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				primaryStage.setIconified(true);
			}
		});
		//最小化
		
		//退出
		tuichuLabel=new Label();
		tuichuLabel.setEffect(new ImageInput(exit));
		tuichuLabel.setLayoutX(1810);
		tuichuLabel.setLayoutY(0);
		tuichuLabel.setOnMouseEntered(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				tuichuLabel.setScaleX(1.2);
				tuichuLabel.setScaleY(1.2);
			}
		});
		tuichuLabel.setOnMouseExited(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				tuichuLabel.setScaleX(1);
				tuichuLabel.setScaleY(1);
			}
		});
		tuichuLabel.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
			}
		});		
		//退出
		
	
//		text.setText("加载组件 2 ........");
//		p1.setProgress(0.8);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("ButtonStyle.css").toExternalForm());
		primaryStage.setScene(scene);
		group2=new Group2("上证市场");
		group1=new Group1(root,group2);
		group2.setGroup1(group1);

		//拖动窗体
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
			 @Override public void handle(MouseEvent mouseEvent) {
				 // record a delta distance for the drag and drop operation.
				 orginPoint.setX(primaryStage.getX()-mouseEvent.getScreenX());
				 orginPoint.setY(primaryStage.getY()-mouseEvent.getScreenY());
//			
				 }
				});
		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			 @Override public void handle(MouseEvent mouseEvent) {
				 primaryStage.setX(mouseEvent.getScreenX()+orginPoint.getX());
				 primaryStage.setY(mouseEvent.getScreenY()+orginPoint.getY());
				 }
				});
		//拖动窗体
		
		root.getChildren().add(imageView);
		root.getChildren().add(zuixiaohuaLabel);
		root.getChildren().add(tuichuLabel);
		root.getChildren().add(group1.getGroup());
		root.getChildren().add(group2.getGroup());
//		System.out.println(Calendar.getInstance());
//		text.setText("加载完毕....");
//		p1.setProgress(1);
		primaryStage.show();
	}
	
//	private void initGui(){
//		final Stage stage = new Stage();
//		Group rootGroup = new Group();
//		Scene scene = new Scene(rootGroup, 620, 453, Color.WHITESMOKE);
//		stage.setScene(scene);
//        stage.centerOnScreen();
//        stage.show();
//        
//        
//        text = new Text(20, 110, "正在初始化.......");
//        text.setFill(Color.DODGERBLUE);
//        text.setEffect(new Lighting());
//        text.setFont(Font.font(Font.getDefault().getFamily(), 50));
//        
//        p1 = new ProgressBar();
//        p1.setPrefSize(620, 25);
//        p1.setLayoutX(0);
//        p1.setLayoutY(428);
//        p1.setProgress(0);
//
//        //add text to the main root group
//
//        rootGroup.getChildren().add(text);
//        rootGroup.getChildren().add(p1);
//	}
	
	public void initPicture(){
		backGround = new Image(getClass().getResourceAsStream("背景.jpg"));
		miniSize = new Image(getClass().getResourceAsStream("最小化.png"));
		exit = new Image(getClass().getResourceAsStream("退出.png"));
	}

}

