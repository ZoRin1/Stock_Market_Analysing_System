/**
 * 
 */
package presentation;

import uiControl.cuowuLabel;
import BL.IndustryAnalyse;
import blService.AnalyseBlSer;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * @author wang
 *
 */
public class Group51 implements Group5Father{
	private Label yiju;
	private Group group;
	private String shichang;
	private Label jielun;
	private Label shuju;
	private String shujuString="";
	private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
	private BorderPane pane;
	private PieChart pieChart;
	private Label label;

	public Group51(String shichang) {
		// TODO Auto-generated constructor stub
		this.shichang=shichang;
		init();
	}
	public void init() {
		
		group=new Group();

		AnalyseBlSer analyseBlSer=new IndustryAnalyse();
		String[] hangyechengjiaoliang = null;
		try {
			hangyechengjiaoliang = analyseBlSer.getAllIn(shichang);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			cuowuLabel cuowuLabel=new cuowuLabel(group);
			new Thread(new Runnable() {
			 	@Override public void run() {	
			 		try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 		System.exit(0);	
			 	}
		}).start();	
		}
		String aString[]=hangyechengjiaoliang[hangyechengjiaoliang.length-1].split(",");
        shujuString=shujuString+"最近一周市场总成交量"+"    "+aString[1]+"\n\n";
		for (int i = 0; i < hangyechengjiaoliang.length-1; i++) {
			String [] strings=hangyechengjiaoliang[i].split(",");
			details.add(new PieChart.Data(strings[0], Double.parseDouble(strings[1])));
			shujuString=shujuString+strings[0]+"    "+strings[1]+"\n";
		}
        
        pane = new BorderPane();
        pieChart = new PieChart();
        pieChart.setData(details);
        pieChart.setLegendSide(Side.RIGHT);
        pieChart.setLabelLineLength(10);
        pieChart.getStylesheets().add(getClass().getResource("Piestyle.css").toExternalForm());
        pane.setCenter(pieChart);
        yiju=new Label();
        yiju.setWrapText(true);
        yiju.setText("判断依据："+"\n"+"   股谚云：“量为价先”，股市是投资者资金与资金之间的博弈过程，而股票成交量是最直接传达给我们的信息。透过成交量信息，可以很容易了解到行业市场的人气变化。所以我们将各行业最近一周的成交量来作为选取最热门行业的依据，同样的方式来选出行业中最热门的股票。");
        yiju.setMaxWidth(300);
        yiju.setMaxHeight(740);
        yiju.setLayoutX(70);
        yiju.setLayoutY(230);
        yiju.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
        yiju.setTextFill(Color.WHITE);
		shuju=new Label(shujuString);       
        shuju.setWrapText(true);
        shuju.setMaxHeight(700);
        shuju.setMaxWidth(600);
        shuju.setLayoutX(1355);
        shuju.setLayoutY(290);
        shuju.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
        shuju.setTextFill(Color.WHITE);
        jielun=new Label(); 
        jielun.setText("结论："+"\n"+"   从各行业成交量来看，"+aString[0]+"成交量最大，该行业最热门。");
        jielun.setWrapText(true);
        jielun.setMaxHeight(200);
        jielun.setMaxWidth(500);
        jielun.setLayoutX(1355);
        jielun.setLayoutY(790);
        jielun.setFont(Font.font("SanSerif", FontWeight.BOLD, 30));
        jielun.setTextFill(Color.WHITE);
        label = new Label("  "+"\n"+"\n  ");
        label.setFont(Font.font("SanSerif", FontWeight.BOLD, 24));
        label.setTextFill(Color.web("#8B0000"));
        pieChart.getData().stream().forEach(data ->{
                    data.getNode().addEventHandler(MouseEvent.ANY, e->{
                        label.setText(data.getName()+"："+"\n   一周内"+data.getName() + "行业成交量为：" + (int)data.getPieValue() +
                                "\n" + "该行业占市场总成交量比例为：" + new java.text.DecimalFormat("#.00").format((double)data.getPieValue()*100/Double.parseDouble(aString[1])) +
                                "%");
                    });
                    data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                   	 Bounds b1 = data.getNode().getBoundsInLocal();
                   	
                        double newX = (b1.getWidth()) / 2 + b1.getMinX();
                        double newY = (b1.getHeight()) / 2 + b1.getMinY();
                        // Make sure pie wedge location is reset
                        data.getNode().setTranslateX(0);
                        data.getNode().setTranslateY(0);
                        TranslateTransition tt = new TranslateTransition(
                                Duration.millis(1500), data.getNode());
                        tt.setByX(newX);
                        tt.setByY(newY);
                        tt.setAutoReverse(true);
                        tt.setCycleCount(2);
                        tt.play();
                   });
                    data.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {

                    	@Override
                    	public void handle(MouseEvent arg0) {
                    		// TODO Auto-generated method stub
                        	details.clear();
                        	shujuString="";
                        	String[] gupiaochengjiaoliang = null;
							try {
								gupiaochengjiaoliang = analyseBlSer.getOneIn(shichang, data.getName());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								cuowuLabel cuowuLabel=new cuowuLabel(group);
								new Thread(new Runnable() {
								 	@Override public void run() {	
								 		try {
											Thread.sleep(3000);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
								 		System.exit(0);	
								 	}
							}).start();	
							}
                        	String bString[]=gupiaochengjiaoliang[gupiaochengjiaoliang.length-1].split(",");
                        	shujuString=shujuString+data.getName()+"总成交量    "+bString[1]+"\n\n";
                        	for (int i = 0; i < gupiaochengjiaoliang.length-1; i++) {
                    			String [] strings=gupiaochengjiaoliang[i].split(",");
                    			details.add(new PieChart.Data(strings[0], Double.parseDouble(strings[1])));
                            	shujuString=shujuString+strings[0]+"    "+strings[1]+"\n";
                    		}
                        	shuju.setText(shujuString);
                        	jielun.setText("结论："+"\n   "+"在"+data.getName()+"行业成交量中，"+bString[0]+"成交量最大，该只股票最热门。");
                        	pieChart.setData(details);
                        	 pieChart.getData().stream().forEach(newdata ->{
                        		 newdata.getNode().addEventHandler(MouseEvent.ANY, e->{
                                     label.setText(newdata.getName()+"：\n   一周内在"+data.getName()+"行业中，"+newdata.getName() + "的成交量为：" + (int)newdata.getPieValue() +
                                             "\n" + "该只股票占行业成交量的比例为：" + new java.text.DecimalFormat("#.00").format((double)newdata.getPieValue()*100/Double.parseDouble(bString[1])) +
                                             "%");
                                 });
                        		 pieChart.getStylesheets().add(getClass().getResource("Piestyle.css").toExternalForm());
                        		 newdata.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                                	 Bounds b1 = newdata.getNode().getBoundsInLocal();                   	
                                     double newX = (b1.getWidth()) / 2 + b1.getMinX();
                                     double newY = (b1.getHeight()) / 2 + b1.getMinY();
                                     // Make sure pie wedge location is reset
                                     newdata.getNode().setTranslateX(0);
                                     newdata.getNode().setTranslateY(0);
                                     TranslateTransition tt = new TranslateTransition(
                                             Duration.millis(1500), newdata.getNode());
                                     tt.setByX(newX);
                                     tt.setByY(newY);
                                     tt.setAutoReverse(true);
                                     tt.setCycleCount(2);
                                     tt.play();
                                });
                        	 });
                    	}
                    	
                    });
//                    data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
//
//                    });
                });
       pane.setBottom(label);
        BorderPane.setMargin(label, new Insets(0, -50, 5, 600));
        pane.setLayoutX(50);  
        pane.setLayoutY(176);
        pane.setMinSize(1834, 739);
        pane.setMaxSize(1834, 739);
		ImageView imageView = new ImageView();
		imageView.setImage(new Image(getClass().getResourceAsStream("面板.png")));
		imageView.setLayoutX(50);
		imageView.setLayoutY(176);

		group.getChildren().add(imageView);
		group.getChildren().add(shuju);
		group.getChildren().add(jielun);
		group.getChildren().add(yiju);
        group.getChildren().add(pane);
        
	}
	/* (non-Javadoc)
	 * @see presentation.Group5Father#getGroup()
	 */
	public Group getGroup() {
		// TODO Auto-generated method stub
		return group;
	}
}
