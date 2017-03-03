package presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import BL.getStocksList;
import PO.StockPO;
import blService.getStocksListBlSer;
import uiControl.WaitLoading;
import uiControl.cuowuLabel;
import uiControl.table1DataPO;
import uiControl.tableControl;
import uiControl.timeInControl;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
/**
 * 
 * @author wang
 *股票列表的group
 */
public class Group2 implements GroupFather{
	private Group1 group1;
	private Group group;
	private timeInControl time;
	private Button yesButton;
	private Label label;
	private String jiaoyisuo;//格式 上证市场  深证市场  自选股
	private tableControl<table1DataPO> table1;
	public Group2(String jiaoyisuo){	
		this.jiaoyisuo=jiaoyisuo;
		init();
	}
	public Group getGroup(){
		return group;
	}
	public void init(){
		time= new timeInControl("a");
		time.setX(1650);
		time.setY(55);
		time.setValue(LocalDate.now().minusDays(1));
		//限制时间选择器不能选择未来
		final Callback<DatePicker, DateCell> dayCalFactory = new Callback<DatePicker, DateCell>() {
			
			@Override
			public DateCell call(DatePicker arg0) {
				// TODO Auto-generated method stub
				DateCell cell=new DateCell(){
					public void updateItem(LocalDate item,boolean empty){
						super.updateItem(item, empty);
						if(item.isAfter(LocalDate.now())){
							setDisable(true);
							setStyle("-fx-background-color: #ffc0cb;");
						}					
					}
				};
				return cell;
			}
		};
		time.setDayCellFactory(dayCalFactory);
		//限制时间选择器不能选择未来
		yesButton=new Button("OK");
		yesButton.setMinSize(80, 30);
		yesButton.setLayoutX(1770);
		yesButton.setLayoutY(130);
//		yesButton.setGraphic(new ImageView(new Image("menu_list_bg.gif")));
		group=new Group();
		BorderPane borderPane=new BorderPane();
		borderPane.setLayoutX(750);
		borderPane.setLayoutY(125);
		borderPane.setMinHeight(50);
		borderPane.setMinWidth(400);
		label=new Label(jiaoyisuo);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setTextFill(Color.WHITE);
		label.setFont(Font.font("SimHei", 40));
		borderPane.setCenter(label);

		group.getChildren().add(yesButton);
		group.getChildren().add(time.getTime());
		group.getChildren().add(borderPane);
		yesButton.setOnMouseClicked(new EventHandler<Event>() {

			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				WaitLoading waitLoading=new WaitLoading();
				group.getChildren().add(waitLoading);
				
				new Thread(new Runnable() {
					 	@Override public void run() {			
							table1.clear();
							addtableData(jiaoyisuo);
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

	public void setGroup1(Group1 group1){
		this.group1=group1;
		//股票图表
		table1= new tableControl<table1DataPO>(get1Table(),group1);
		addtableData(jiaoyisuo);
		table1.setX(0);
		table1.setY(175);
		table1.setHeight(790);
		table1.setWidth(1885);
		//股票图表
		group.getChildren().add(table1.getTable());
	}

	private void addtableData(String jiaoyisuo){
		getStocksListBlSer getStocksListBl;
		List<StockPO> stockposList;
		try {
			if (jiaoyisuo.equals("上证市场")) {
				getStocksListBl=new getStocksList();
				stockposList=getStocksListBl.getStocksList(time.getDate(), "sh");
				
			}else if (jiaoyisuo.equals("深证市场")) {
				getStocksListBl=new getStocksList();
				stockposList=getStocksListBl.getStocksList(time.getDate(), "sz");
			}else {
				getStocksListBl=new getStocksList();
				stockposList=getStocksListBl.getStocksList(time.getDate(), "");
			}
			for (int i = 0; i < stockposList.size(); i++) {
				table1.addData(new table1DataPO(stockposList.get(i).getDate(), stockposList.get(i).getCode(), stockposList.get(i).getName(),stockposList.get(i).getIndustry(), Double.valueOf(stockposList.get(i).getStartPrice()), Double.valueOf(stockposList.get(i).getMaxPrice()),Double.valueOf(stockposList.get(i).getMinPrice()) ,Double.valueOf(stockposList.get(i).getEndPrice()) , Double.valueOf(stockposList.get(i).getLastPrice()), Double.valueOf(stockposList.get(i).getQuantity()), Double.valueOf(stockposList.get(i).getChangeRate()), Double.valueOf(stockposList.get(i).getAllRate())));
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
		t1.setMinWidth(157.08);
		t1.setMaxWidth(157.08);
		TableColumn	t2=new TableColumn("股票代码");
		t2.setCellValueFactory(new PropertyValueFactory<>("sign"));
		t2.setMinWidth(157.08);
		t2.setMaxWidth(157.08);
		TableColumn	t3=new TableColumn("股票名称");
		t3.setCellValueFactory(new PropertyValueFactory<>("name"));
		t3.setMinWidth(157.08);
		t3.setMaxWidth(157.08);
		TableColumn	t4=new TableColumn("行业名称");
		t4.setCellValueFactory(new PropertyValueFactory<>("industry"));
		t4.setMinWidth(157.08);
		t4.setMaxWidth(157.08);
		TableColumn	t5=new TableColumn("开盘价");
		t5.setCellValueFactory(new PropertyValueFactory<>("open"));
		t5.setMinWidth(157.08);
		t5.setMaxWidth(157.08);
		TableColumn	t6=new TableColumn("最高价");
		t6.setCellValueFactory(new PropertyValueFactory<>("high"));
		t6.setMinWidth(157.08);
		t6.setMaxWidth(157.08);
		TableColumn	t7=new TableColumn("最低价");
		t7.setCellValueFactory(new PropertyValueFactory<>("low"));
		t7.setMinWidth(157.08);
		t7.setMaxWidth(157.08);
		TableColumn	t8=new TableColumn("收盘价");
		t8.setCellValueFactory(new PropertyValueFactory<>("close"));
		t8.setMinWidth(157.08);
		t8.setMaxWidth(157.08);
		TableColumn	t9=new TableColumn("后复权价");
		t9.setCellValueFactory(new PropertyValueFactory<>("adj_price"));
		t9.setMinWidth(157.08);
		t9.setMaxWidth(157.08);
		TableColumn	t10=new TableColumn("成交量");
		t10.setCellValueFactory(new PropertyValueFactory<>("volume"));
		t10.setMinWidth(157.08);
		t10.setMaxWidth(157.08);
		TableColumn	t11=new TableColumn("换手率");
		t11.setCellValueFactory(new PropertyValueFactory<>("turnover"));
		t11.setMinWidth(157.08);
		t11.setMaxWidth(157.08);
		TableColumn	t12=new TableColumn("市净率");
		t12.setCellValueFactory(new PropertyValueFactory<>("pb"));
		t12.setMinWidth(157.08);
		t12.setMaxWidth(157.08);
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
		temp.add(t11);
		temp.add(t12);
		return temp;
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
