/**
 * 
 */
package presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BL.ClickOne;
import PO.StockPO;
import blService.ClickOneBlSer;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import uiControl.WaitLoading;
import uiControl.cuowuLabel;
import uiControl.table2DataPO;
import uiControl.tableControl;
import uiControl.timeInControl;

/**
 * @author wang
 *单只股票的历史行情group
 */
public class Group31 implements Group3Father{
	private String codeString;
	private Button yesButton;
	private timeInControl time1;
	private timeInControl time2;
	private tableControl<table2DataPO> table2;
	private Group group;
	public Group31(String codeString){
		this.codeString=codeString;
		init();
		addtime();
		
	}
	public Group getGroup(){
		return group;
	} 
	private void init(){
		group=new Group();
		//股票图表
		table2= new tableControl<table2DataPO>(get1Table());
		table2.setX(50);
		table2.setY(175);
		table2.setHeight(790);
		table2.setWidth(1835);
		//股票图表
		group.getChildren().add(table2.getTable());
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
		addtableData();
		group.getChildren().add(aLabel);
		group.getChildren().add(yesButton);
		group.getChildren().add(time1.getTime());
		group.getChildren().add(time2.getTime());
		yesButton.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {			
							table2.clear();
							addtableData();
						 Platform.runLater(new Runnable() {
							 @Override public void run() {
								 group.getChildren().remove(waitLoading);
							 }
						 });
					 	}
				}).start();	

			}
		});
	}
	private void addtableData(){
		ClickOneBlSer clickOneBl=new ClickOne();
		try {
			List<StockPO> stockposList=clickOneBl.clickOne(time1.getDate(), time2.getDate(), codeString);

			for (int i = 0; i < stockposList.size(); i++) {
				table2.addData(new table2DataPO(stockposList.get(i).getDate(), Double.valueOf(stockposList.get(i).getStartPrice()), Double.valueOf(stockposList.get(i).getMaxPrice()),Double.valueOf(stockposList.get(i).getMinPrice()) ,Double.valueOf(stockposList.get(i).getEndPrice()) , Double.valueOf(stockposList.get(i).getLastPrice()), Double.valueOf(stockposList.get(i).getQuantity()), Double.valueOf(stockposList.get(i).getChangeRate()), Double.valueOf(stockposList.get(i).getAllRate()),stockposList.get(i).getZhangfu()));
			}
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
	}
	private List<TableColumn> get1Table(){
		List<TableColumn> temp = new ArrayList<TableColumn>();
		TableColumn	t1=new TableColumn("日期");
		t1.setCellValueFactory(new PropertyValueFactory<>("date"));
		t1.setMinWidth(183.5);
		t1.setMaxWidth(183.5);
		TableColumn	t2=new TableColumn("涨跌率（%）");
		t2.setCellValueFactory(new PropertyValueFactory<>("Zhangfu"));
		t2.setMinWidth(183.5);
		t2.setMaxWidth(183.5);
		TableColumn	t3=new TableColumn("开盘价");
		t3.setCellValueFactory(new PropertyValueFactory<>("open"));
		t3.setMinWidth(183.5);
		t3.setMaxWidth(183.5);
		TableColumn	t4=new TableColumn("最高价");
		t4.setCellValueFactory(new PropertyValueFactory<>("high"));
		t4.setMinWidth(183.5);
		t4.setMaxWidth(183.5);
		TableColumn	t5=new TableColumn("最低价");
		t5.setCellValueFactory(new PropertyValueFactory<>("low"));
		t5.setMinWidth(183.5);
		t5.setMaxWidth(183.5);
		TableColumn	t6=new TableColumn("收盘价");
		t6.setCellValueFactory(new PropertyValueFactory<>("close"));
		t6.setMinWidth(183.5);
		t6.setMaxWidth(183.5);
		TableColumn	t7=new TableColumn("后复权价");
		t7.setCellValueFactory(new PropertyValueFactory<>("adj_price"));
		t7.setMinWidth(183.5);
		t7.setMaxWidth(183.5);
		TableColumn	t8=new TableColumn("成交量");
		t8.setCellValueFactory(new PropertyValueFactory<>("volume"));
		t8.setMinWidth(183.5);
		t8.setMaxWidth(183.5);
		TableColumn	t9=new TableColumn("换手率");
		t9.setCellValueFactory(new PropertyValueFactory<>("turnover"));
		t9.setMinWidth(183.5);
		t9.setMaxWidth(183.5);
		TableColumn	t10=new TableColumn("市净率");
		t10.setCellValueFactory(new PropertyValueFactory<>("pb"));
		t10.setMinWidth(183.5);
		t10.setMaxWidth(183.5);
		
		temp.add(t1);
		temp.add(t2);
		temp.add(t3);
		temp.add(t4);
		temp.add(t5);
		temp.add(t6);
		temp.add(t7);
		temp.add(t8);
		temp.add(t9);
		temp.add(t10);
		return temp;
	}
}
