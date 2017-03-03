/**
 * 
 */
package presentation;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import uiControl.cuowuLabel;
import uiControl.timeInControl;
import BL.ClickOne;
import PO.NowStockPo;
import PO.StockPO;
import brokenChart.MultipleAxesLineChart;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.util.Callback;

/**
 * @author RABOOK
 *
 */
public class Group32 implements Group3Father{

	private String codeString;
	private Group group;
    public static  int X_DATA_COUNT = 0; //只是初步  要改变的
	private Button yesButton;
	private timeInControl time1;
	private timeInControl time2;
	
	private LineChart baseChart;
	private MultipleAxesLineChart  chart;
	private BorderPane borderPane;
	
    private ClickOne click;
    private List<StockPO> ceshi;
    
    private Date start;
    private Date end;
    
    private BorderPane list;
    private GridPane grid;
	
    
	public Group32(String codeString){
		this.codeString = codeString;
		group = new Group();
		addtime();
		initChart();
		init();
	}
	
	//两个纵坐标 换手数  换手金额
	
	public void init(){
        getNowData();
        borderPane = new BorderPane();
        
        list = new BorderPane();
        
        borderPane.setCenter(chart);
        borderPane.setBottom(chart.getLegend());
        borderPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        borderPane.setStyle("-fx-background-color: #333333;");
        
        list.setTop(grid);
        list.setLayoutX(1662);  
        list.setLayoutY(176);
        list.setMaxSize(223, 789);
        list.setMinSize(223, 789);
        list.setStyle("-fx-background-color: #333333;");
        
		Line line1 = new Line(1652,176,1652,887);  
		Line line2 = new Line(1652,887,1885,887);  
		line1.setStroke(Color.RED);
		line2.setStroke(Color.RED);
        
        borderPane.setLayoutX(50);
        borderPane.setLayoutY(176);
        borderPane.setMinSize(1612, 789);  
        borderPane.setMaxSize(1612, 789);  
        group.getChildren().add(borderPane);
        group.getChildren().add(list);
        
        group.getChildren().addAll(line1,line2);
//        group.getChildren().add(grid);
//        group.getStyleClass().add(getClass().getResource("style.css").toExternalForm());
	}
    
	private void initChart(){
        NumberAxis xAxis = new NumberAxis(0, X_DATA_COUNT, 1);
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("成交手数");
        
        baseChart = new LineChart(xAxis, yAxis);  //这里要改成String     #0099FF
        baseChart.getData().add(prepareSeries("成交手数"));

        chart = new MultipleAxesLineChart(baseChart, Color.rgb(220,20,60));
        chart.setList(ceshi);
        chart.addSeries(prepareSeries("成交金额"), Color.web("#0099FF"));
        
        chart.addSeries(prepareSeries("换手率"), Color.web("#009933"));
        
        chart.addSeries(prepareSeries("市净率"), Color.rgb(255, 255, 0));
	}
	
    private void addtime(){
		click = new ClickOne();
		//时间选择控件
		time1= new timeInControl("Start");
		time1.setX(1400);
		time1.setY(55);
		time2= new timeInControl("End");
		time2.setX(1650);
		time2.setY(55);
		//第二个时间选择不能在第一个时间选择之前
		time2.setValue(LocalDate.now().minusDays(1));// 把time2设为当前时间		
		time1.setValue(time2.getValue().minusMonths(1)); // 把time1设为一个月前		
		final Callback<DatePicker, DateCell> dayCaleeFactory = new Callback<DatePicker, DateCell>() {
			
			@Override
			public DateCell call(final DatePicker datePicker) {
				// TODO Auto-generated method stub
				return new DateCell(){
					public void updateItem(LocalDate item,boolean empty){
						super.updateItem(item, empty);
						
						if(item.isBefore(time1.getValue().plusDays(1))){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		time2.setDayCellFactory(dayCaleeFactory);
		//第二个时间选择不能在第一个时间选择之前
		
		//第一个时间选择不能在第二个时间选择之后
		final Callback<DatePicker, DateCell> dayCalFactory = new Callback<DatePicker, DateCell>() {
			
			@Override
			public DateCell call(DatePicker arg0) {
				// TODO Auto-generated method stub
				return new DateCell(){
					public void updateItem(LocalDate item,boolean empty){
						super.updateItem(item, empty);
						if(item.isAfter(time2.getValue().plusDays(-1))){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		time1.setDayCellFactory(dayCalFactory);
		//第一个时间选择不能在第二个时间选择之后
		
		//限制不能选择的未来的时间
		final Callback<DatePicker, DateCell> dayFactory = new Callback<DatePicker, DateCell>() {
			
			@Override
			public DateCell call(DatePicker arg0) {
				// TODO Auto-generated method stub
				return new DateCell(){
					public void updateItem(LocalDate item,boolean empty){
						super.updateItem(item, empty);
						if(item.isAfter(LocalDate.now())){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}
					}
				};
			}
		};
		time2.setDayCellFactory(dayFactory);
		//限制不能选择的未来的时间
		yesButton=new Button("OK");
		yesButton.setMinSize(80, 30);
		yesButton.setLayoutX(1770);
		yesButton.setLayoutY(130);
		Label aLabel=new Label("——>");
		aLabel.setLayoutX(1606);
		aLabel.setLayoutY(105);
		aLabel.setTextFill(Color.WHITE);
		//时间选择控件
		group.getChildren().add(aLabel);
		group.getChildren().add(yesButton);
		group.getChildren().add(time1.getTime());
		group.getChildren().add(time2.getTime());
		
		//初始化start和end
		start = time1.getDate();
		end = time2.getDate();
		try {
			ceshi = click.clickOne(start, end, codeString);
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
		X_DATA_COUNT = ceshi.size()-1;
		//初始化start和end
		yesButton.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				start = time1.getDate();
				end = time2.getDate();
				try {
					ceshi = click.clickOne(start, end, codeString);
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
				X_DATA_COUNT = ceshi.size()-1;
				group.getChildren().remove(borderPane);
				initChart();
				init();
			}
		});
	}
	
	
    private XYChart.Series<Number,Number> prepareSeries(String name){
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        try {
			ceshi = click.clickOne(start, end, codeString);
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
        series.setName(name);
        if(name.equals("成交手数")){
            for(int i = 0 ; i < ceshi.size();i++){
            	series.getData().add(new XYChart.Data<>(i,Double.parseDouble(ceshi.get(i).getQuantity()),ceshi.get(i)));
            }
        }else if(name.equals("成交金额")){
            for(int i = 0 ; i < ceshi.size();i++){
            	double open = Double.parseDouble(ceshi.get(i).getStartPrice());
            	double end = Double.parseDouble(ceshi.get(i).getEndPrice());
            	double min = Double.parseDouble(ceshi.get(i).getMinPrice());
            	double max = Double.parseDouble(ceshi.get(i).getMaxPrice());
            	double account = (open+end+min+max)/4;
            	double result = account*Double.parseDouble(ceshi.get(i).getQuantity());
            	series.getData().add(new XYChart.Data<>(i,result,ceshi.get(i)));
            }
        }else if(name.equals("换手率")){
            for(int i = 0 ; i < ceshi.size();i++){
            	series.getData().add(new XYChart.Data<>(i,Double.parseDouble(ceshi.get(i).getChangeRate()),ceshi.get(i)));
            }
        }else if(name.equals("市净率")){
            for(int i = 0 ; i < ceshi.size();i++){
            	series.getData().add(new XYChart.Data<>(i,Double.parseDouble(ceshi.get(i).getAllRate()),ceshi.get(i)));
            }
        }
//      for(int i = 0 ; i < ceshi.size();i++){
//    	series.getData().add(new XYChart.Data<>(i,Double.parseDouble(ceshi.get(i).getQuantity()),ceshi.get(i)));
//    }
        return series;
    }
	
	/* (non-Javadoc)
	 * @see presentation.Group3Father#getGroup()
	 */
	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return this.group;
	}
	
	public void getNowData(){
		grid = new GridPane();
		grid.setMaxSize(204, 680);
		grid.setMinSize(204, 680);
		grid.setVgap(5);
		grid.getColumnConstraints().add(new ColumnConstraints(105));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		
		NowStockPo po = null;
		try {
			po = click.showNowStock(codeString);
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
		Label date = new Label("日期：");
		Label date1 = new Label(po.getDate());
		date.setTextFill(Color.WHITE);
		date.setMinSize(50, 34);
		date1.setTextFill(Color.WHITE);
		date1.setMinSize(150, 34);
		date.setFont(new Font("宋体", 15));
		date1.setFont(new Font("宋体", 15));
		grid.add(date, 0, 0);
		grid.add(date1, 1, 0);
		
		Label time = new Label("时间：");
		Label time1 = new Label(po.getTime());
		time.setTextFill(Color.WHITE);
		time.setMinSize(50, 34);
		time1.setTextFill(Color.WHITE);
		time1.setMinSize(150, 34);
		time.setFont(new Font("宋体", 15));
		time1.setFont(new Font("宋体", 15));
		grid.add(time, 0, 1);
		grid.add(time1, 1, 1);
		
		Label gid = new Label("股票编号：");
		Label gid1 = new Label(po.getGid());
		gid.setTextFill(Color.WHITE);
		gid.setMinSize(50, 34);
		gid1.setTextFill(Color.WHITE);
		gid1.setMinSize(150, 34);
		gid.setFont(new Font("宋体", 15));
		gid1.setFont(new Font("宋体", 15));
		grid.add(gid, 0, 2);
		grid.add(gid1, 1, 2);
		
		
		Label increPer = new Label("涨跌百分比：");
		Label increPer1 = new Label(po.getIncrePer());
		increPer.setTextFill(Color.WHITE);
		increPer.setMinSize(50, 34);
		increPer1.setTextFill(judgeUp(po.getIncrePer()));
		increPer1.setMinSize(150, 34);
		increPer.setFont(new Font("宋体", 15));
		increPer1.setFont(new Font("宋体", 15));
		grid.add(increPer, 0, 3);
		grid.add(increPer1, 1, 3);
		
		Label increase = new Label("涨跌额：");
		Label increase1 = new Label(po.getIncrease());
		increase.setTextFill(Color.WHITE);
		increase.setMinSize(50, 34);
		increase1.setTextFill(judgeUp(po.getIncrease()));
		increase1.setMinSize(150, 34);
		increase.setFont(new Font("宋体", 15));
		increase1.setFont(new Font("宋体", 15));
		grid.add(increase, 0, 4);
		grid.add(increase1, 1, 4);
		
		Label name = new Label("股票名称：");
		Label name1 = new Label(po.getName());
		name.setTextFill(Color.WHITE);
		name.setMinSize(50, 34);
		name1.setTextFill(Color.WHITE);
		name1.setMinSize(150, 34);
		name.setFont(new Font("宋体", 15));
		name1.setFont(new Font("宋体", 15));
		grid.add(name, 0, 5);
		grid.add(name1, 1, 5);
		
		Label todayStartPri = new Label("今日开盘价：");
		Label todayStartPri1 = new Label(po.getTodayStartPri());
		todayStartPri.setTextFill(Color.WHITE);
		todayStartPri.setMinSize(50, 34);
		todayStartPri1.setTextFill(judgeUp(po.getYestodEndPri(), po.getTodayStartPri()));
		todayStartPri1.setMinSize(150, 34);
		todayStartPri.setFont(new Font("宋体", 15));
		todayStartPri1.setFont(new Font("宋体", 15));
		grid.add(todayStartPri, 0, 6);
		grid.add(todayStartPri1, 1, 6);
		
		Label yestodEndPri = new Label("昨日收盘价：");
		Label yestodEndPri1 = new Label(po.getYestodEndPri());
		yestodEndPri.setTextFill(Color.WHITE);
		yestodEndPri.setMinSize(50, 34);
		yestodEndPri1.setTextFill(Color.web("#EEEE00"));
		yestodEndPri1.setMinSize(150, 34);
		yestodEndPri.setFont(new Font("宋体", 15));
		yestodEndPri1.setFont(new Font("宋体", 15));
		grid.add(yestodEndPri, 0, 7);
		grid.add(yestodEndPri1, 1, 7);
		
		Label nowPri = new Label("当前价格：");
		Label nowPri1 = new Label(po.getNowPri());
		nowPri.setTextFill(Color.WHITE);
		nowPri.setMinSize(50, 34);
		nowPri1.setTextFill(judgeUp(po.getYestodEndPri(), po.getNowPri()));
		nowPri1.setMinSize(150, 34);
		nowPri.setFont(new Font("宋体", 15));
		nowPri1.setFont(new Font("宋体", 15));
		grid.add(nowPri, 0, 8);
		grid.add(nowPri1, 1, 8);
		
		Label todayMax = new Label("今日最高价：");
		Label todayMax1 = new Label(po.getTodayMax());
		todayMax.setTextFill(Color.WHITE);
		todayMax.setMinSize(50, 34);
		todayMax1.setTextFill(judgeUp(po.getYestodEndPri(), po.getTodayMax()));
		todayMax1.setMinSize(150, 34);
		todayMax.setFont(new Font("宋体", 15));
		todayMax1.setFont(new Font("宋体", 15));
		grid.add(todayMax, 0, 9);
		grid.add(todayMax1, 1, 9);
		
		Label todayMin = new Label("今日最低价：");
		Label todayMin1 = new Label(po.getTodayMin());
		todayMin.setTextFill(Color.WHITE);
		todayMin.setMinSize(50, 34);
		todayMin1.setTextFill(judgeUp(po.getYestodEndPri(), po.getTodayMin()));
		todayMin1.setMinSize(150, 34);
		todayMin.setFont(new Font("宋体", 15));
		todayMin1.setFont(new Font("宋体", 15));
		grid.add(todayMin, 0, 10);
		grid.add(todayMin1, 1, 10);
		
		Label competitivePri = new Label("竞买价：");
		Label competitivePri1 = new Label(po.getCompetitivePri());
		competitivePri.setTextFill(Color.WHITE);
		competitivePri.setMinSize(50, 34);
		competitivePri1.setTextFill(Color.web("#97FFFF"));
		competitivePri1.setMinSize(150, 34);
		competitivePri.setFont(new Font("宋体", 15));
		competitivePri1.setFont(new Font("宋体", 15));
		grid.add(competitivePri, 0, 11);
		grid.add(competitivePri1, 1, 11);
		
		Label reservePri = new Label("竞卖价：");
		Label reservePri1 = new Label(po.getReservePri());
		reservePri.setTextFill(Color.WHITE);
		reservePri.setMinSize(50, 34);
		reservePri1.setTextFill(Color.web("#97FFFF"));
		reservePri1.setMinSize(150, 34);
		reservePri.setFont(new Font("宋体", 15));
		reservePri1.setFont(new Font("宋体", 15));
		grid.add(reservePri, 0, 12);
		grid.add(reservePri1, 1, 12);
		
		Label traNumber = new Label("成交量：");
		Label traNumber1 = new Label(po.getTraNumber());
		traNumber.setTextFill(Color.WHITE);
		traNumber.setMinSize(50, 34);
		traNumber1.setTextFill(Color.web("#97FFFF"));
		traNumber1.setMinSize(150, 34);
		traNumber.setFont(new Font("宋体", 15));
		traNumber1.setFont(new Font("宋体", 15));
		grid.add(traNumber, 0, 13);
		grid.add(traNumber1, 1, 13);
		
		Label traAmount = new Label("成交金额：");
		Label traAmount1 = new Label(po.getTraAmount());
		traAmount.setTextFill(Color.WHITE);
		traAmount.setMinSize(50, 34);
		traAmount1.setTextFill(Color.web("#97FFFF"));
		traAmount1.setMinSize(150, 34);
		traAmount.setFont(new Font("宋体", 15));
		traAmount1.setFont(new Font("宋体", 15));
		grid.add(traAmount, 0, 14);
		grid.add(traAmount1, 1, 14);
		
		
		Label rate = new Label("大盘涨跌率：");
		Label rate1 = new Label(po.getRate());
		rate.setTextFill(Color.WHITE);
		rate.setMinSize(50, 34);
		rate1.setTextFill(judgeUp(po.getRate()));
		rate1.setMinSize(150, 34);
		rate.setFont(new Font("宋体", 15));
		rate1.setFont(new Font("宋体", 15));
		grid.add(rate, 0, 15);
		grid.add(rate1, 1, 15);
		
		Label dot = new Label("大盘当前点数：");
		Label dot1 = new Label(po.getDot());
		dot.setTextFill(Color.WHITE);
		dot.setMinSize(50, 34);
		dot1.setTextFill(judgeUp(po.getYestodEndPri(), po.getDot()));
		dot1.setMinSize(150, 34);
		dot.setFont(new Font("宋体", 15));
		dot1.setFont(new Font("宋体", 15));
		grid.add(dot, 0, 16);
		grid.add(dot1, 1, 16);
		
		Label nowPic = new Label("大盘当前价格：");	
		Label nowPic1 = new Label(po.getNowPic());
		nowPic.setTextFill(Color.WHITE);
		nowPic.setMinSize(50, 34);
		nowPic1.setTextFill(judgeUp(po.getNowPic()));
		nowPic1.setMinSize(150, 34);
		nowPic.setFont(new Font("宋体", 15));
		nowPic1.setFont(new Font("宋体", 15));
		grid.add(nowPic, 0, 17);
		grid.add(nowPic1, 1, 17);
		
	}
	
	public Color judgeUp(String data){
		double da = Double.parseDouble(data);
		if(da>0){
			return Color.RED;
		}else if(da==0){
			return Color.WHITE;
		}else{
			return Color.web("#7CFC00");
		}
	}
	
	public Color judgeUp(String yesPri,String operator){
		double pri = Double.parseDouble(yesPri);  //昨收
		double ope = Double.parseDouble(operator);  //比较
		if(ope>pri){
			return Color.RED;
		}else if(ope==pri){
			return Color.WHITE;
		}else{
			return Color.web("#7CFC00");
		}
	}

}
