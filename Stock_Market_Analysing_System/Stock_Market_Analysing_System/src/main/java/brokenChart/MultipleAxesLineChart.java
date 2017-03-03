package brokenChart;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PO.StockPO;

public class MultipleAxesLineChart extends StackPane {

    private final LineChart baseChart;
    private final ObservableList<LineChart> backgroundCharts = FXCollections.observableArrayList();
    private final Map<LineChart, Color> chartColorMap = new HashMap<>();

    private final double yAxisWidth = 85;
    private final AnchorPane detailsWindow;

    private final double yAxisSeparation = 20;
    private double strokeWidth = 2;  //折线的宽度
    
    private List<StockPO> ceshi;
    private boolean sign = true;
    
    private Color baseColor;

    public MultipleAxesLineChart(LineChart baseChart, Color lineColor) {
        this(baseChart, lineColor, null);
    	this.baseColor = lineColor;
    }

    public MultipleAxesLineChart(LineChart baseChart, Color lineColor, Double strokeWidth) {
        if (strokeWidth != null) {
            this.strokeWidth = strokeWidth;
        }
        this.baseChart = baseChart;
        baseChart.verticalGridLinesVisibleProperty().set(false);  //不画垂直网格线

        chartColorMap.put(baseChart, lineColor);

        styleBaseChart(baseChart);
        styleChartLine(baseChart, lineColor);
        setFixedAxisWidth(baseChart);

        setAlignment(Pos.CENTER_LEFT);

        backgroundCharts.addListener((Observable observable) -> rebuildChart());   //通过rebuildChart重新build一个chart

        detailsWindow = new AnchorPane();
        
        bindMouseEvents(baseChart, this.strokeWidth);

        rebuildChart();
    }

    //实时监测并显示 当前纵坐标
    private void bindMouseEvents(LineChart baseChart, Double strokeWidth) {
        final DetailsPopup detailsPopup = new DetailsPopup();
        getChildren().add(detailsWindow);
        detailsWindow.getChildren().add(detailsPopup);
        detailsWindow.prefHeightProperty().bind(heightProperty());
        detailsWindow.prefWidthProperty().bind(widthProperty());
        detailsWindow.setMouseTransparent(true);

        setOnMouseMoved(null);
        setMouseTransparent(false);

        final Axis xAxis = baseChart.getXAxis();
        final Axis yAxis = baseChart.getYAxis();

        final Line xLine = new Line();
        final Line yLine = new Line();
//        yLine.setFill(Color.GRAY);
//        xLine.setFill(Color.GRAY);
        xLine.setStyle("-fx-stroke:#FF0033");
        yLine.setStyle("-fx-stroke:#FF0033");
        yLine.setStrokeWidth(strokeWidth/2);
        xLine.setStrokeWidth(strokeWidth/2);
        xLine.setVisible(false);
        yLine.setVisible(false);

        final Node chartBackground = baseChart.lookup(".chart-plot-background");
        for (Node n: chartBackground.getParent().getChildrenUnmodifiable()) {
            if (n != chartBackground && n != xAxis && n != yAxis) {
                n.setMouseTransparent(true);
            }
        }
        chartBackground.setCursor(Cursor.CROSSHAIR);
        chartBackground.setOnMouseEntered((event) -> {
            chartBackground.getOnMouseMoved().handle(event);
            detailsPopup.setVisible(true);
            xLine.setVisible(true);
            yLine.setVisible(true);
            detailsWindow.getChildren().addAll(xLine, yLine);
        });
        chartBackground.setOnMouseExited((event) -> {
            detailsPopup.setVisible(false);
            xLine.setVisible(false);
            yLine.setVisible(false);
            detailsWindow.getChildren().removeAll(xLine, yLine);
        });
        chartBackground.setOnMouseMoved(event -> {
            double x = event.getX() + chartBackground.getLayoutX();
            double y = event.getY() + chartBackground.getLayoutY();

            xLine.setStartX(10);
            xLine.setEndX(detailsWindow.getWidth()-10);
            xLine.setStartY(y+5);
            xLine.setEndY(y+5);

            yLine.setStartX(x+5);
            yLine.setEndX(x+5);
            yLine.setStartY(10);
            yLine.setEndY(detailsWindow.getHeight()-10);

            detailsPopup.showChartDescrpition(event);

            if (y + detailsPopup.getHeight() + 10 < getHeight()) {
                AnchorPane.setTopAnchor(detailsPopup, y+10);
            } else {
                AnchorPane.setTopAnchor(detailsPopup, y-10-detailsPopup.getHeight());
            }

            if (x + detailsPopup.getWidth() + 10 < getWidth()) {
                AnchorPane.setLeftAnchor(detailsPopup, x+10);
            } else {
                AnchorPane.setLeftAnchor(detailsPopup, x-10-detailsPopup.getWidth());
            }
        });
    }
    //实时监测并显示 当前纵坐标
    
    private void styleBaseChart(LineChart baseChart) {
//        baseChart.setCreateSymbols(false);  设置是否在坐标点画圆圈（原linechart）
        baseChart.setCreateSymbols(true);
        baseChart.setLegendVisible(false);
        baseChart.getXAxis().setAutoRanging(false);
        baseChart.getXAxis().setAnimated(false);
        baseChart.getYAxis().setAnimated(false);
    }

    private void setFixedAxisWidth(LineChart chart) {
        chart.getYAxis().setPrefWidth(yAxisWidth);
        chart.getYAxis().setMaxWidth(yAxisWidth);
    }

    private void rebuildChart() {
        getChildren().clear();

        getChildren().add(resizeBaseChart(baseChart));
        for (LineChart lineChart : backgroundCharts) {
            getChildren().add(resizeBackgroundChart(lineChart));
        }
        getChildren().add(detailsWindow);
    }

    private Node resizeBaseChart(LineChart lineChart) {
        HBox hBox = new HBox(lineChart);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.prefHeightProperty().bind(heightProperty());
        hBox.prefWidthProperty().bind(widthProperty());

        lineChart.minWidthProperty().bind(widthProperty().subtract((yAxisWidth+yAxisSeparation)*backgroundCharts.size()));
        lineChart.prefWidthProperty().bind(widthProperty().subtract((yAxisWidth+yAxisSeparation)*backgroundCharts.size()));
        lineChart.maxWidthProperty().bind(widthProperty().subtract((yAxisWidth+yAxisSeparation)*backgroundCharts.size()));

        return lineChart;
    }

    private Node resizeBackgroundChart(LineChart lineChart) {
        HBox hBox = new HBox(lineChart);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.prefHeightProperty().bind(heightProperty());
        hBox.prefWidthProperty().bind(widthProperty());
        hBox.setMouseTransparent(true);

        lineChart.minWidthProperty().bind(widthProperty().subtract((yAxisWidth + yAxisSeparation) * backgroundCharts.size()));
        lineChart.prefWidthProperty().bind(widthProperty().subtract((yAxisWidth + yAxisSeparation) * backgroundCharts.size()));
        lineChart.maxWidthProperty().bind(widthProperty().subtract((yAxisWidth + yAxisSeparation) * backgroundCharts.size()));

        lineChart.translateXProperty().bind(baseChart.getYAxis().widthProperty());
        lineChart.getYAxis().setTranslateX((yAxisWidth + yAxisSeparation) * backgroundCharts.indexOf(lineChart));

        return hBox;
    }

    public void addSeries(XYChart.Series series, Color lineColor) {
        NumberAxis yAxis = new NumberAxis();
        NumberAxis xAxis = new NumberAxis();

        // style x-axis
        xAxis.setAutoRanging(false);
        xAxis.setVisible(false);
        xAxis.setOpacity(0.0); // somehow the upper setVisible does not work
        xAxis.lowerBoundProperty().bind(((NumberAxis) baseChart.getXAxis()).lowerBoundProperty());
        xAxis.upperBoundProperty().bind(((NumberAxis) baseChart.getXAxis()).upperBoundProperty());
        xAxis.tickUnitProperty().bind(((NumberAxis) baseChart.getXAxis()).tickUnitProperty());

        // style y-axis
        yAxis.setSide(Side.RIGHT);
        yAxis.setLabel(series.getName());

        // create chart
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setAnimated(false);
        lineChart.setLegendVisible(false);
        lineChart.getData().add(series);

        styleBackgroundChart(lineChart, lineColor);
        setFixedAxisWidth(lineChart);

        chartColorMap.put(lineChart, lineColor);
        backgroundCharts.add(lineChart);
    }

    private void styleBackgroundChart(LineChart lineChart, Color lineColor) {
        styleChartLine(lineChart, lineColor);

        Node contentBackground = lineChart.lookup(".chart-content").lookup(".chart-plot-background");
        contentBackground.setStyle("-fx-background-color: transparent;");

        lineChart.setVerticalZeroLineVisible(false);
        lineChart.setHorizontalZeroLineVisible(false);
        lineChart.setVerticalGridLinesVisible(false);
        lineChart.setHorizontalGridLinesVisible(false);
        lineChart.setCreateSymbols(true);  //这个是 新加的  折线是否画圆点
        
        
        lineChart.getYAxis().autoRangingProperty().set(true);  //范围随机确定
        NumberAxis Axis = (NumberAxis) lineChart.getYAxis();
        Axis.forceZeroInRangeProperty().setValue(Boolean.FALSE);
        Axis = (NumberAxis) baseChart.getYAxis();
        Axis.forceZeroInRangeProperty().setValue(Boolean.FALSE);
        
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    private void styleChartLine(LineChart chart, Color lineColor) {
        chart.getYAxis().lookup(".axis-label").setStyle("-fx-text-fill: " + toRGBCode(lineColor) + "; -fx-font-weight: bold;");
        Node seriesLine = chart.lookup(".chart-series-line");
        seriesLine.setStyle("-fx-stroke: " + toRGBCode(lineColor) + "; -fx-stroke-width: " + strokeWidth + ";");
    }

    public Node getLegend() {
        HBox hBox = new HBox();

        final CheckBox baseChartCheckBox = new CheckBox(baseChart.getYAxis().getLabel());
//        System.out.println(baseChart.getYAxis().getLabel());
        baseChartCheckBox.setSelected(true); //baseChartCheckBox  一开始就设置为选中状态
        baseChartCheckBox.setStyle("-fx-text-fill: " + toRGBCode(chartColorMap.get(baseChart)) + "; -fx-font-weight: bold;");
        baseChartCheckBox.setDisable(false);   //设置 baseChartCheckBox  一开始就不能被选中
        baseChartCheckBox.getStyleClass().add("readonly-checkbox");
        baseChartCheckBox.setOnAction(event -> {
        	Node seriesLine = baseChart.lookup(".chart-series-line");
        	if(sign==true){
        		seriesLine.setStyle("-fx-stroke-width: " + 0 + ";");
        		baseChart.setCreateSymbols(false);
        		sign=false;
        	}else{
        		seriesLine.setStyle("-fx-stroke: " + toRGBCode(baseColor) + "; -fx-stroke-width: " + strokeWidth + ";");
        		baseChart.setCreateSymbols(true);
        		sign=true;
        	}
        });
        hBox.getChildren().add(baseChartCheckBox);

        for (final LineChart lineChart : backgroundCharts) {
            CheckBox checkBox = new CheckBox(lineChart.getYAxis().getLabel());
            checkBox.setStyle("-fx-text-fill: " + toRGBCode(chartColorMap.get(lineChart)) + "; -fx-font-weight: bold");
            checkBox.setSelected(true);
            checkBox.setOnAction(event -> {
                if (backgroundCharts.contains(lineChart)) {
                    backgroundCharts.remove(lineChart);
                } else {
                    backgroundCharts.add(lineChart);
                }
            });
            hBox.getChildren().add(checkBox);
        }

        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(20);
        hBox.setStyle("-fx-padding: 0 10 20 10");

        return hBox;
    }
    
    public void setList(List<StockPO> a){
    	ceshi =a;
    }

    private class DetailsPopup extends VBox {

        private DetailsPopup() {
            setStyle("-fx-border-width: 1px; -fx-padding: 5 5 5 5px; -fx-border-color: gray; -fx-background-color: rgb(238,216,174,0.70);");
            setVisible(false);
        }
        
        //通过HBox来切换折线
        public void showChartDescrpition(MouseEvent event) {
            getChildren().clear();

            Long xValueLong = Math.round((double)baseChart.getXAxis().getValueForDisplay(event.getX()));

            HBox baseChartPopupRow = buildPopupRow(event, xValueLong, baseChart);
            if (baseChartPopupRow != null) {
                getChildren().add(baseChartPopupRow);
            }

            for (LineChart lineChart : backgroundCharts) {
                HBox popupRow = buildPopupRow(event, xValueLong, lineChart);
                if (popupRow == null) continue;

                getChildren().add(popupRow);
            }
        }

        //新建一个提示框  并返回
        private HBox buildPopupRow(MouseEvent event, Long xValueLong, LineChart lineChart) {
            Label seriesName = new Label(lineChart.getYAxis().getLabel());
            seriesName.setTextFill(chartColorMap.get(lineChart));

            Number yValueForChart = getYValueForX(lineChart, xValueLong.intValue());
            String date = getXdate(lineChart, xValueLong.intValue());
            if (yValueForChart == null) {
                return null;
            }
            Number yValueLower = Math.round(normalizeYValue(lineChart, event.getY() - 10));
            Number yValueUpper = Math.round(normalizeYValue(lineChart, event.getY() + 10));
            Number yValueUnderMouse = Math.round((double) lineChart.getYAxis().getValueForDisplay(event.getY()));

            // make series name bold when mouse is near given chart's line
            if (isMouseNearLine(yValueForChart, yValueUnderMouse, Math.abs(yValueLower.doubleValue()-yValueUpper.doubleValue()))) {
                seriesName.setStyle("-fx-font-weight: bold");
            }
            
            
            
            HBox popupRow = new HBox(10, seriesName, new Label("["+yValueForChart+"]"), new Label(date));
            return popupRow;
        }
        //新建一个提示框  并返回

        private double normalizeYValue(LineChart lineChart, double value) {
            Double val = (Double) lineChart.getYAxis().getValueForDisplay(value);
            if (val == null) {
                return 0;
            } else {
                return val;
            }
        }

        private boolean isMouseNearLine(Number realYValue, Number yValueUnderMouse, Double tolerance) {
            return (Math.abs(yValueUnderMouse.doubleValue() - realYValue.doubleValue()) < tolerance);
        }

        //得到与横坐标相对应的 y轴的对应的数值并返回
        public Number getYValueForX(LineChart chart, Number xValue) {
            List<XYChart.Data> dataList = ((List<XYChart.Data>)((XYChart.Series)chart.getData().get(0)).getData());
            for (XYChart.Data data : dataList) {
                if (data.getXValue().equals(xValue)) {
                    return (Number)data.getYValue();
                }
            }
            return null;
        }
        //得到与横坐标相对应的 y轴的对应的数值并返回
        
        //得到与横坐标相对应的StockPO  并返回其日期
        public String getXdate(LineChart chart, Number xValue){
            List<XYChart.Data> dataList = ((List<XYChart.Data>)((XYChart.Series)chart.getData().get(0)).getData());
            for (XYChart.Data data : dataList) {
                if (data.getXValue().equals(xValue)) {
                	StockPO  po = (StockPO)data.getExtraValue();
                    return po.getDate();
                }
            }
			return null;
        }
        //得到与横坐标相对应的StockPO  并返回其日期
    }
}
