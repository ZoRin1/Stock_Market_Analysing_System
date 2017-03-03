/**
 * 
 */
package presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import uiControl.cuowuLabel;
import uiControl.timeInControl;
import BL.ClickOne;
import Kchart.BarData;
import Kchart.CandleStickChart;
import Kchart.DecimalAxisFormatter;
import PO.NowDapanPo;
import PO.StockPO;
import blService.ClickOneBlSer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
public class Group43 implements Group4Father{


	private Group group;
	private Button yesButton;
	private timeInControl time1;
	private timeInControl time2;
	
	private ClickOneBlSer  clickOne;
	private List<StockPO> clickList;
	
	private List<BarData> observableList;
	
	private CandleStickChart candleStickChart;
	
    private BorderPane list;
    private GridPane grid;
    
    private List<NowDapanPo> po;
	
	
	public Group43(){
		// TODO Auto-generated constructor stub
		group=new Group();
		clickOne = new ClickOne();
		try {
			po = clickOne.showNowDapan();
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
		addtime();
		init();
	}
	

	private void init() {
		// TODO Auto-generated method stub
		
        getNowData();
        list = new BorderPane();
        
        list.setTop(grid);
        list.setLayoutX(1662);  
        list.setLayoutY(176);
        list.setMaxSize(223, 789);
        list.setMinSize(223, 789);
        list.setStyle("-fx-background-color: #333333;");
        
		Line line1 = new Line(1652,176,1652,903);  
		Line line2 = new Line(1652,903,1885,903);  
		line1.setStroke(Color.RED);
		line2.setStroke(Color.RED);
		
		try {
			clickList = clickOne.clickDaPan(time1.getDate(), time2.getDate());
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
		observableList = buildBars(clickList);
        candleStickChart = new CandleStickChart("", observableList);
        candleStickChart.setYAxisFormatter(new DecimalAxisFormatter("#000.00"));
        candleStickChart.setLayoutX(50);  // 1834  789
        candleStickChart.setLayoutY(176);
        candleStickChart.setMinSize(1612, 789);
        candleStickChart.setMaxSize(1612, 789);
        group.getChildren().add(candleStickChart);
        
        group.getChildren().add(list);
        
        group.getChildren().addAll(line1,line2);
	}

	/* (non-Javadoc)
	 * @see presentation.GroupFather#getGroup()
	 */
	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return group;
	}
	
    public List<BarData> buildBars(List<StockPO> clickList) {
  	final List<BarData> bars = new ArrayList<>();
  	for(int i = 0 ; i < clickList.size();i++){
  		double open = Double.parseDouble(clickList.get(i).getStartPrice());
  		double close = Double.parseDouble(clickList.get(i).getEndPrice());
  		double high = Double.parseDouble(clickList.get(i).getMaxPrice());
  		double low = Double.parseDouble(clickList.get(i).getMinPrice());
  		String date = clickList.get(i).getDate();
  		
          BarData bar = new BarData(date, open, high, low, close, 1);
//          now.add(Calendar.MINUTE, 5);
          bars.add(bar);
  	}
  	return bars;
  }
    
    private void addtime(){
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
		yesButton.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				group.getChildren().remove(candleStickChart);
				init();
			}
		});
	}
    
	public void getNowData(){
		grid = new GridPane();
		grid.setMaxSize(204, 680);
		grid.setMinSize(204, 680);
		grid.getColumnConstraints().add(new ColumnConstraints(105));
		grid.getColumnConstraints().add(new ColumnConstraints(100));
		grid.setVgap(2);
		
		get(0,0);
		Line l1 = new Line(0,0,105,0);
		Line l2 = new Line(0,0,100,0);
		l1.setStroke(Color.RED);
		l2.setStroke(Color.RED);
		grid.add(l1, 0, 11);
		grid.add(l2, 1, 11);
		get(1, 12);	
	}
	
	public void get(int sign,int count){
		Label name = new Label("名称：");
		Label name1 = new Label(po.get(sign).getName());
		name.setTextFill(Color.WHITE);
		name.setMinSize(50, 31);
		name1.setTextFill(Color.WHITE);
		name1.setMinSize(150, 31);
		name.setFont(new Font("宋体", 15));
		name1.setFont(new Font("宋体", 12));
		grid.add(name, 0, count);
		grid.add(name1, 1, count);
		
		Label time = new Label("时间：");
		Label time1 = new Label(po.get(sign).getTime());
		time.setTextFill(Color.WHITE);
		time.setMinSize(50, 31);
		time1.setTextFill(Color.WHITE);
		time1.setMinSize(150, 31);
		time.setFont(new Font("宋体", 15));
		time1.setFont(new Font("宋体", 12));
		grid.add(time, 0, count+1);
		grid.add(time1, 1, count+1);
			
		
		Label increPer = new Label("涨跌百分比：");
		Label increPer1 = new Label(po.get(sign).getIncrePer());
		increPer.setTextFill(Color.WHITE);
		increPer.setMinSize(50, 31);
		increPer1.setTextFill(judgeUp(po.get(sign).getIncrePer()));
		increPer1.setMinSize(150, 31);
		increPer.setFont(new Font("宋体", 15));
		increPer1.setFont(new Font("宋体", 12));
		grid.add(increPer, 0, count+2);
		grid.add(increPer1, 1, count+2);

		
		Label increase = new Label("涨跌幅：");
		Label increase1 = new Label(po.get(sign).getIncrease());
		increase.setTextFill(Color.WHITE);
		increase.setMinSize(50, 31);
		increase1.setTextFill(judgeUp(po.get(sign).getIncrePer()));
		increase1.setMinSize(150, 31);
		increase.setFont(new Font("宋体", 15));
		increase1.setFont(new Font("宋体", 12));
		grid.add(increase, 0, count+3);
		grid.add(increase1, 1, count+3);
		
		Label openPri = new Label("今开：");
		Label openPri1 = new Label(po.get(sign).getOpenPri());
		openPri.setTextFill(Color.WHITE);
		openPri.setMinSize(50, 31);
		openPri1.setTextFill(judgeUp(po.get(sign).getYesPri(), po.get(sign).getOpenPri()));
		openPri1.setMinSize(150, 31);
		openPri.setFont(new Font("宋体", 15));
		openPri1.setFont(new Font("宋体", 12));
		grid.add(openPri, 0, count+4);
		grid.add(openPri1, 1, count+4);
		
		Label yesPri = new Label("昨收：");
		Label yesPri1 = new Label(po.get(sign).getYesPri());
		yesPri.setTextFill(Color.WHITE);
		yesPri.setMinSize(50, 31);
		yesPri1.setTextFill(Color.web("#EEEE00"));
		yesPri1.setMinSize(150, 31);
		yesPri.setFont(new Font("宋体", 15));
		yesPri1.setFont(new Font("宋体", 12));
		grid.add(yesPri, 0, count+5);
		grid.add(yesPri1, 1, count+5);
		
		Label nowpri = new Label("当前价格：");
		Label nowpri1 = new Label(po.get(sign).getNowpri());
		nowpri.setTextFill(Color.WHITE);
		nowpri.setMinSize(50, 31);
		nowpri1.setTextFill(judgeUp(po.get(sign).getYesPri(), po.get(sign).getNowpri()));
		nowpri1.setMinSize(150, 31);
		nowpri.setFont(new Font("宋体", 15));
		nowpri1.setFont(new Font("宋体", 12));
		grid.add(nowpri, 0, count+6);
		grid.add(nowpri1, 1, count+6);
		
		Label highPri = new Label("最高：");
		Label highPri1 = new Label(po.get(sign).getHighPri());
		highPri.setTextFill(Color.WHITE);
		highPri.setMinSize(50, 31);
		highPri1.setTextFill(judgeUp(po.get(sign).getYesPri(), po.get(sign).getHighPri()));
		highPri1.setMinSize(150, 31);
		highPri.setFont(new Font("宋体", 15));
		highPri1.setFont(new Font("宋体", 12));
		grid.add(highPri, 0, count+7);
		grid.add(highPri1, 1, count+7);
		
		Label lowpri = new Label("最低：");
		Label lowpri1 = new Label(po.get(sign).getLowpri());
		lowpri.setTextFill(Color.WHITE);
		lowpri.setMinSize(50, 31);
		lowpri1.setTextFill(judgeUp(po.get(sign).getYesPri(), po.get(sign).getLowpri()));
		lowpri1.setMinSize(150, 31);
		lowpri.setFont(new Font("宋体", 15));
		lowpri1.setFont(new Font("宋体", 12));
		grid.add(lowpri, 0, count+8);
		grid.add(lowpri1, 1, count+8);
		
		Label dealNum = new Label("成交量：");
		Label dealNum1 = new Label(po.get(sign).getDealNum());
		dealNum.setTextFill(Color.WHITE);
		dealNum.setMinSize(50, 31);
		dealNum1.setTextFill(Color.web("#97FFFF"));
		dealNum1.setMinSize(150, 31);
		dealNum.setFont(new Font("宋体", 15));
		dealNum1.setFont(new Font("宋体", 12));
		grid.add(dealNum, 0, count+9);
		grid.add(dealNum1, 1, count+9);
		
		Label dealPri = new Label("成交额：");
		Label dealPri1 = new Label(po.get(sign).getDealPri());
		dealPri.setTextFill(Color.WHITE);
		dealPri.setMinSize(50, 31);
		dealPri1.setTextFill(Color.web("#97FFFF"));
		dealPri1.setMinSize(150, 31);
		dealPri.setFont(new Font("宋体", 15));
		dealPri1.setFont(new Font("宋体", 12));
		grid.add(dealPri, 0, count+10);
		grid.add(dealPri1, 1, count+10);
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
