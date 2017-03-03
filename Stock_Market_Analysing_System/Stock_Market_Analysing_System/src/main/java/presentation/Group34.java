/**
 * 
 */
package presentation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



import BL.getZhiBiao;
import PO.ZhiBiaoPO;
import uiControl.cuowuLabel;
import uiControl.timeInControl;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

/**
 * @author wang
 *
 */
public class Group34 implements Group3Father{
	private Group group;
	private String codeString;
	private Button yesButton;
	private timeInControl time1;
	private timeInControl time2;
	private XYChart.Series ATRseries;
	private XYChart.Series MAseries;
	private XYChart.Series DIFseries;
	private XYChart.Series DEAseries;
	private ArrayList<Tooltip> ATRtooltipArrayList=new ArrayList<>();
	private ArrayList<Tooltip> MAtooltipArrayList=new ArrayList<>();
	private ArrayList<Tooltip> DIFtooltipArrayList=new ArrayList<>();
	private ArrayList<Tooltip> DEAtooltipArrayList=new ArrayList<>();
	private Button MA_ATR;
	private Button MACD;
	private  LineChart<String,Number> lineChart1;
	private  LineChart<String,Number> lineChart2;
	private  LineChart<String,Number> lineChart3;
	public Group34(String codeString) {
		// TODO Auto-generated constructor stub
		this.codeString=codeString;
		group=new Group();
		MA_ATR=new Button("MA-ATR");
		MACD=new Button("MACD");
		MA_ATR.setMinSize(100, 30);
		MA_ATR.setLayoutX(50);
		MA_ATR.setLayoutY(925);
		MACD.setMinSize(100, 30);
		MACD.setLayoutX(150);
		MACD.setLayoutY(925);
		Label ATRL=new Label("ATR：");
		Label MAL=new Label("MA：");
		Label MACDL=new Label("MACD买卖原则：");
		Label ATRLabel=new Label("   显示市场变化率的指标，该指标价值越高，趋势改变的可能性就越高；该指标价值越低，趋势改变的可能性就越低。\n");
		ATRLabel.setWrapText(true);
		Label MALabel=new Label("   反映短期内股市平均成本的变化情形与趋势，可作为短期进出的依据。\n");
		MALabel.setWrapText(true);
		Label MACDLabel=new Label("   1.DIF、DEA均为正，DIF向上突破DEA，买入信号参考。\n   2.DIF、DEA均为负，DIF向下跌破DEA，卖出信号参考。\n   3.DIF、DEA的值从正数变为负数，或者从负数变成正数并不是交易信号，因为它们落后于市场。");
		MACDLabel.setWrapText(true);
		ATRL.setFont(Font.font("SanSerif", FontWeight.BOLD, 24));
		ATRL.setTextFill(Color.RED);
		MAL.setFont(Font.font("SanSerif", FontWeight.BOLD, 24));
		MAL.setTextFill(Color.RED);
		MACDL.setFont(Font.font("SanSerif", FontWeight.BOLD, 24));
		MACDL.setTextFill(Color.RED);
		ATRLabel.setFont(Font.font("SanSerif", FontWeight.BOLD, 22));
		ATRLabel.setTextFill(Color.web("#FFFFCC"));
		MALabel.setFont(Font.font("SanSerif", FontWeight.BOLD, 22));
		MALabel.setTextFill(Color.web("#FFFFCC"));
		MACDLabel.setFont(Font.font("SanSerif", FontWeight.BOLD, 22));
		MACDLabel.setTextFill(Color.web("#FFFFCC"));
		VBox vBox=new VBox();
		vBox.setStyle("-fx-background-color: #333333;");
		vBox.getChildren().addAll(MAL,MALabel,ATRL,ATRLabel,MACDL,MACDLabel);
		vBox.setLayoutX(1661);
		vBox.setLayoutY(176);
		vBox.setMinWidth(223);
		vBox.setMinHeight(788);
		vBox.setMaxWidth(223);
		vBox.setMaxHeight(788);
		CategoryAxis xAxis1 = new CategoryAxis();
        NumberAxis yAxis1 = new NumberAxis();
        CategoryAxis xAxis2 = new CategoryAxis();
        NumberAxis yAxis2 = new NumberAxis();
        CategoryAxis xAxis3 = new CategoryAxis();
        NumberAxis yAxis3 = new NumberAxis();
        yAxis1.forceZeroInRangeProperty().setValue(Boolean.FALSE);
        yAxis2.forceZeroInRangeProperty().setValue(Boolean.FALSE);
        yAxis3.forceZeroInRangeProperty().setValue(Boolean.FALSE);
        yAxis1.setAnimated(false);
        yAxis2.setAnimated(false);
        yAxis3.setAnimated(false);
        lineChart1= 
                new LineChart<String,Number>(xAxis1,yAxis1);
        lineChart1.setTitle("MA-ATR指标");
        lineChart1.setLayoutX(50);
        lineChart1.setLayoutY(176);
        lineChart1.setMinSize(1611, 394);
        lineChart1.setMaxSize(1611, 394);
        lineChart1.getStylesheets().add(getClass().getResource("LineChartStyles.css").toExternalForm()); 
        lineChart2= 
                new LineChart<String,Number>(xAxis2,yAxis2);
        lineChart2.setTitle("MACD指标");
        lineChart2.setLayoutX(50);
        lineChart2.setLayoutY(176);
        lineChart2.setMinSize(1611, 789);
        lineChart2.setMaxSize(1611, 789);
        lineChart2.getStylesheets().add(getClass().getResource("LineChartStyles.css").toExternalForm());
        lineChart3= 
                new LineChart<String,Number>(xAxis3,yAxis3);
        lineChart3.setLayoutX(50);
        lineChart3.setLayoutY(570);
        lineChart3.setMinSize(1611, 394);
        lineChart3.setMaxSize(1611, 394);
        lineChart3.getStylesheets().add(getClass().getResource("LineChartStyles.css").toExternalForm()); 
        
        ATRseries= new XYChart.Series();
        ATRseries.setName("ATR");
        MAseries = new XYChart.Series();
        MAseries.setName("MA");
        DIFseries = new XYChart.Series();
        DIFseries.setName("DIF");
        DEAseries = new XYChart.Series();
        DEAseries.setName("DEA");
        addtime();
        addData();
        group.getChildren().add(vBox);
        group.getChildren().add(lineChart1);
        group.getChildren().add(lineChart3);
        group.getChildren().add(MACD);
        group.getChildren().add(MA_ATR);
        MACD.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				
				group.getChildren().clear();
				addtime();
				addData();
		        group.getChildren().add(vBox);
				group.getChildren().add(lineChart2);
		        group.getChildren().add(MACD);
		        group.getChildren().add(MA_ATR);
			}
		});
        MA_ATR.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				
				group.getChildren().clear();
				addtime();
				addData();
		        group.getChildren().add(vBox);
				group.getChildren().add(lineChart1);
				group.getChildren().add(lineChart3);
		        group.getChildren().add(MACD);
		        group.getChildren().add(MA_ATR);
			}
		});
	}
	/* (non-Javadoc)
	 * @see presentation.Group3Father#getGroup()
	 */
	@Override
	public Group getGroup() {
		// TODO Auto-generated method stub
		return group;
	}
	private void addData(){
		ATRseries.getData().clear();
		MAseries.getData().clear();
		DIFseries.getData().clear();
		DEAseries.getData().clear();
		lineChart1.getData().clear();
		lineChart2.getData().clear();
		lineChart3.getData().clear();
		ATRtooltipArrayList.clear();
		MAtooltipArrayList.clear();
		DIFtooltipArrayList.clear();
		DEAtooltipArrayList.clear();
		getZhiBiao getZhiBiao=new getZhiBiao();
		try {
			List<ZhiBiaoPO> zhiBiaoPOs=getZhiBiao.getZhiBiao(time1.getDate(), time2.getDate(), codeString);
			
			for (int i = 0; i < zhiBiaoPOs.size(); i++) {
				ATRseries.getData().add(new XYChart.Data(zhiBiaoPOs.get(i).getDate(),zhiBiaoPOs.get(i).getATR()));
				Tooltip atrTooltip=new Tooltip();
				atrTooltip.setGraphic(new TooltipContent("ATR", zhiBiaoPOs.get(i).getATR(), zhiBiaoPOs.get(i).getDate()));
				MAseries.getData().add(new XYChart.Data(zhiBiaoPOs.get(i).getDate(),zhiBiaoPOs.get(i).getMA()));
				Tooltip maTooltip=new Tooltip();
				maTooltip.setGraphic(new TooltipContent("MA", zhiBiaoPOs.get(i).getMA(), zhiBiaoPOs.get(i).getDate()));
				DIFseries.getData().add(new XYChart.Data(zhiBiaoPOs.get(i).getDate(),zhiBiaoPOs.get(i).getDIF()));
				Tooltip difTooltip=new Tooltip();
				difTooltip.setGraphic(new TooltipContent("DIF", zhiBiaoPOs.get(i).getDIF(), zhiBiaoPOs.get(i).getDate()));
				DEAseries.getData().add(new XYChart.Data(zhiBiaoPOs.get(i).getDate(),zhiBiaoPOs.get(i).getDEA()));
				Tooltip deaTooltip=new Tooltip();
				deaTooltip.setGraphic(new TooltipContent("DEA", zhiBiaoPOs.get(i).getDEA(), zhiBiaoPOs.get(i).getDate()));
				ATRtooltipArrayList.add(atrTooltip);
				MAtooltipArrayList.add(maTooltip);
				DIFtooltipArrayList.add(difTooltip);
				DEAtooltipArrayList.add(deaTooltip);
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
		lineChart1.getData().addAll(MAseries);
		lineChart2.getData().addAll(DIFseries,DEAseries);
		lineChart3.getData().addAll(ATRseries);
		for (int i = 0; i < ATRtooltipArrayList.size(); i++) {
			XYChart.Data aData=(XYChart.Data) ATRseries.getData().get(i);
			Tooltip.install(aData.getNode(),ATRtooltipArrayList.get(i));
			XYChart.Data bData=(XYChart.Data) MAseries.getData().get(i);
			Tooltip.install(bData.getNode(),MAtooltipArrayList.get(i));
			XYChart.Data cData=(XYChart.Data) DIFseries.getData().get(i);
			Tooltip.install(cData.getNode(),DIFtooltipArrayList.get(i));
			XYChart.Data dData=(XYChart.Data) DEAseries.getData().get(i);
			Tooltip.install(dData.getNode(),DEAtooltipArrayList.get(i));
		}
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
				addData();
			}
		});

	}
    private class TooltipContent extends GridPane {

        private final Label Value = new Label();
        private final Label dateValue = new Label();


        private TooltipContent(String type,Double ValueDouble, String date) {
            Label dateLabel = new Label("Date: ");
            Label valueLabel = new Label(type+": ");
            Value.setText(Double.toString(ValueDouble));
            dateValue.setText(date);
            dateLabel.getStyleClass().add("candlestick-tooltip-label");
            valueLabel.getStyleClass().add("candlestick-tooltip-label");
            Value.setFont(new Font("Cambria", 15));
            dateValue.setFont(new Font("Cambria", 15));
            setConstraints(dateLabel, 0, 0);
            setConstraints(valueLabel, 0, 1);
            setConstraints(dateValue, 1, 0);
            setConstraints(Value, 1, 1);

            getChildren().addAll(Value, dateValue, dateLabel, valueLabel);
        }

    }
}
