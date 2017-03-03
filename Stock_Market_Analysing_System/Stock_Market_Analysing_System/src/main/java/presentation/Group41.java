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
import uiControl.WaitLoading;
import uiControl.cuowuLabel;
import uiControl.table3DataPO;
import uiControl.tableControl;
import uiControl.timeInControl;
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

/**
 * @author wang
 *大盘行情的历史行情group
 */
public class Group41 implements Group4Father{
	private timeInControl time1;
	private timeInControl time2;
	private Button yesButton;
	private Group group;
	private tableControl<table3DataPO> table3;
	public  Group41() {
		init();
		addtime();
	}
	/* (non-Javadoc)
	 * @see presentation.Group4Father#getGroup()
	 */
	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return group;
	}
	private void init(){
		group=new Group();
		//股票图表
		table3= new tableControl<table3DataPO>(get1Table());
		table3.setX(50);
		table3.setY(175);
		table3.setHeight(790);
		table3.setWidth(1835);
		//股票图表
		group.getChildren().add(table3.getTable());
	}
	private void addtime(){
		//时间选择控件
		time1= new timeInControl("Start");
		time1.setX(1400);
		time1.setY(55);
		time2= new timeInControl("End");
		time2.setX(1650);
		time2.setY(55);
		time2.setValue(LocalDate.now().minusDays(1));
		//第二个时间选择不能在第一个时间选择之前
		time1.setValue(time2.getValue().minusMonths(1)); // 把time1设为当前时间
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
							table3.clear();
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
			List<StockPO> stockposList=clickOneBl.clickDaPan(time1.getDate(), time2.getDate());

			for (int i = 0; i < stockposList.size(); i++) {
				table3.addData(new table3DataPO(stockposList.get(i).getDate(), Double.valueOf(stockposList.get(i).getStartPrice()), Double.valueOf(stockposList.get(i).getMaxPrice()),Double.valueOf(stockposList.get(i).getMinPrice()) ,Double.valueOf(stockposList.get(i).getEndPrice()) , Double.valueOf(stockposList.get(i).getLastPrice()), Double.valueOf(stockposList.get(i).getQuantity()),stockposList.get(i).getZhangfu()));
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
		t1.setMinWidth(229.3);
		t1.setMaxWidth(229.3);
		TableColumn	t2=new TableColumn("涨跌率（%）");
		t2.setCellValueFactory(new PropertyValueFactory<>("Zhangfu"));
		t2.setMinWidth(229.3);
		t2.setMaxWidth(229.3);
		TableColumn	t3=new TableColumn("开盘价");
		t3.setCellValueFactory(new PropertyValueFactory<>("open"));
		t3.setMinWidth(229.3);
		t3.setMaxWidth(229.3);
		TableColumn	t4=new TableColumn("最高价");
		t4.setCellValueFactory(new PropertyValueFactory<>("high"));
		t4.setMinWidth(229.3);
		t4.setMaxWidth(229.3);
		TableColumn	t5=new TableColumn("最低价");
		t5.setCellValueFactory(new PropertyValueFactory<>("low"));
		t5.setMinWidth(229.3);
		t5.setMaxWidth(229.3);
		TableColumn	t6=new TableColumn("收盘价");
		t6.setCellValueFactory(new PropertyValueFactory<>("close"));
		t6.setMinWidth(229.3);
		t6.setMaxWidth(229.3);
		TableColumn	t7=new TableColumn("后复权价");
		t7.setCellValueFactory(new PropertyValueFactory<>("adj_price"));
		t7.setMinWidth(229.3);
		t7.setMaxWidth(229.3);
		TableColumn	t8=new TableColumn("成交量");
		t8.setCellValueFactory(new PropertyValueFactory<>("volume"));
		t8.setMinWidth(229.3);
		t8.setMaxWidth(229.3);

		temp.add(t1);
		temp.add(t2);
		temp.add(t3);
		temp.add(t4);
		temp.add(t5);
		temp.add(t6);
		temp.add(t7);
		temp.add(t8);
		return temp;
	}
}
