package Kchart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class CandleStickChart extends XYChart<String, Number>{

    SimpleDateFormat sdf = new SimpleDateFormat("yy:MM:dd");  //对时间的格式处理 
//    protected static final Logger logger = Logger.getLogger(CandleStickChart.class.getName());
    protected int maxBarsToDisplay;
    protected ObservableList<XYChart.Series<String, Number>> dataSeries;
    protected BarData lastBar;
    protected NumberAxis yAxis;
    protected CategoryAxis xAxis;
    
    private List<BarData> sublist;

    
    
    /**
     * 
     * @param title The chart title
     * @param bars  The bars data to display in the chart.
     */
    public CandleStickChart(String title, List<BarData> bars) {
        this(title, bars, Integer.MAX_VALUE);
    }

    
    /**
     * 
     * @param title The chart title
     * @param bars The bars to display in the chart
     * @param maxBarsToDisplay The maximum number of bars to display in the chart.
     */
    public CandleStickChart(String title, List<BarData> bars, int maxBarsToDisplay) {
        this(title, new CategoryAxis(), new NumberAxis(), bars, maxBarsToDisplay);
    }

    /**
     * Construct a new CandleStickChart with the given axis.
     *
     * @param title The chart title
     * @param xAxis The x axis to use
     * @param yAxis The y axis to use
     * @param bars The bars to display on the chart
     * @param maxBarsToDisplay The maximum number of bars to display on the chart.
     */
    public CandleStickChart(String title, CategoryAxis xAxis, NumberAxis yAxis, List<BarData> bars, int maxBarsToDisplay) {
        super(xAxis, yAxis);
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.maxBarsToDisplay = maxBarsToDisplay;
        

        yAxis.autoRangingProperty().set(true);  //自动根据数据 确定范围
        yAxis.forceZeroInRangeProperty().setValue(Boolean.FALSE);   //x,y轴不包括0
        setTitle(title);
        setAnimated(true);
        getStylesheets().add(getClass().getResource("CandleStickChartStyles.css").toExternalForm());
        xAxis.setAnimated(true);
        yAxis.setAnimated(true);
        verticalGridLinesVisibleProperty().set(false);  //不画垂直网格线
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        sublist = getSubList(bars, maxBarsToDisplay);
        //对BarData数据的处理
        for (BarData bar : sublist) {
            String label = "";
//            label = sdf.format(bar.getDateTime().getTime());  //这个是横坐标的值。。通过改变label来改变横坐标的值
            label = bar.getDate();
    		String split[] = label.split("-");
    		label = split[1]+"-"+split[2];
            series.getData().add(new XYChart.Data<String, Number>(label, bar.getOpen(), bar));
//            logger.log(Level.INFO, "Adding bar with date/time: {0}", bar.getDateTime().getTime());  //Logger类是对数据的体现  是测试代码类  监听代码运行数据
//            logger.log(Level.INFO, "Adding bar with price: {0}", bar.getOpen());   //Logger类是对数据的体现  是测试代码类  监听代码运行数据
        }
        //对BarData数据的处理
        dataSeries = FXCollections.observableArrayList(series);

        setData(dataSeries);
        lastBar = sublist.get(sublist.size() - 1);
    }

    
    /**
     * Defines a formatter to use when formatting the y-axis values.
     * @param formatter The formatter to use when formatting the y-axis values.
     */
    public void setYAxisFormatter(DecimalAxisFormatter formatter) {
        yAxis.setTickLabelFormatter(formatter);
//        yAxis.settickL
    }

    
    /**
     * Appends a new bar on to the end of the chart.
     * @param bar The bar to append to the chart
     */
    public void addBar(BarData bar) {

        if (dataSeries.get(0).getData().size() >= maxBarsToDisplay) {
            dataSeries.get(0).getData().remove(0);
        }

        int datalength = dataSeries.get(0).getData().size();
        dataSeries.get(0).getData().get(datalength - 1).setYValue(bar.getOpen());
        dataSeries.get(0).getData().get(datalength - 1).setExtraValue(bar);
//        String label = sdf.format(bar.getDateTime().getTime());   //这里原来是 当前时间刻度
        String label = bar.getDate();
//        logger.log(Level.INFO, "Adding bar with actual time:  {0}", bar.getDateTime().getTime());
//        logger.log(Level.INFO, "Adding bar with formated time: {0}", label);

        lastBar = new BarData(bar.getDate(), bar.getClose(), bar.getClose(), bar.getClose(), bar.getClose(), 0);
        javafx.scene.chart.XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(label, lastBar.getOpen(), lastBar);
        dataSeries.get(0).getData().add(data);
        
        
        
    }

    
    /**
     * Update the "Last" price of the most recent bar
     * @param price The Last price of the most recent bar.
     */
    public void updateLast(double price) {
        if (lastBar != null) {
            lastBar.update(price);
//            logger.log(Level.INFO, "Updating last bar with date/time: {0}", lastBar.getDateTime().getTime());

            int datalength = dataSeries.get(0).getData().size();
            dataSeries.get(0).getData().get(datalength - 1).setYValue(lastBar.getOpen());

            dataSeries.get(0).getData().get(datalength - 1).setExtraValue(lastBar);
//            logger.log(Level.INFO, "Updating last bar with formatteddate/time: {0}", dataSeries.get(0).getData().get(datalength - 1).getXValue());
        }
    }

    
    
    protected List<BarData> getSubList(List<BarData> bars, int maxBars) {
        List<BarData> sublist;
        if (bars.size() > maxBars) {
            return bars.subList(bars.size() - 1 - maxBars, bars.size() - 1);
        } else {
            return bars;
        }
    }

    // -------------- METHODS ------------------------------------------------------------------------------------------
    /**
     * Called to update and layout the content for the plot
     */
    @Override
    protected void layoutPlotChildren() {
        // we have nothing to layout if no data is present
        if (getData() == null) {
            return;
        }
        // update candle positions
        for (int seriesIndex = 0; seriesIndex < getData().size(); seriesIndex++) {
            Series<String, Number> series = getData().get(seriesIndex);
            Iterator<Data<String, Number>> iter = getDisplayedDataIterator(series);
            Path seriesPath = null;
            if (series.getNode() instanceof Path) {
                seriesPath = (Path) series.getNode();
                seriesPath.getElements().clear();
            }
            while (iter.hasNext()) {
                Data<String, Number> item = iter.next();
                double x = getXAxis().getDisplayPosition(getCurrentDisplayedXValue(item));
                double y = getYAxis().getDisplayPosition(getCurrentDisplayedYValue(item));
                Node itemNode = item.getNode();
                BarData bar = (BarData) item.getExtraValue();
                if (itemNode instanceof Candle && item.getYValue() != null) {
                    Candle candle = (Candle) itemNode;

                    double close = getYAxis().getDisplayPosition(bar.getClose());
                    double high = getYAxis().getDisplayPosition(bar.getHigh());
                    double low = getYAxis().getDisplayPosition(bar.getLow());
                    double candleWidth = 10;
                    if(sublist.size()>120){
                    	candleWidth = 1200/sublist.size();
                    }
                    // update candle
                    candle.update(close - y, high - y, low - y, candleWidth);

                    // update tooltip content
                    candle.updateTooltip(bar.getOpen(), bar.getClose(), bar.getHigh(), bar.getLow(),bar.getDate());

                    // position the candle
                    candle.setLayoutX(x);
                    candle.setLayoutY(y);
                }

            }
        }
    }

    @Override
    protected void dataItemChanged(Data<String, Number> item) {
    }

    @Override
    protected void dataItemAdded(Series<String, Number> series, int itemIndex, Data<String, Number> item) {
        Node candle = createCandle(getData().indexOf(series), item, itemIndex);
        if (shouldAnimate()) {
            candle.setOpacity(0);
            getPlotChildren().add(candle);
            // fade in new candle
            FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
            ft.setToValue(1);
            ft.play();
        } else {
            getPlotChildren().add(candle);
        }
        // always draw average line on top
        if (series.getNode() != null) {
            series.getNode().toFront();
        }
    }

    @Override
    protected void dataItemRemoved(Data<String, Number> item, Series<String, Number> series) {
    	 final Node candle = item.getNode();
         if (shouldAnimate()) {
             // fade out old candle
             FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
             ft.setToValue(0);
             ft.setOnFinished(new EventHandler<ActionEvent>() {

                 @Override
                 public void handle(ActionEvent actionEvent) {
                     getPlotChildren().remove(candle);
                 }
             });
             ft.play();
         } else {
             getPlotChildren().remove(candle);
         }
    }

    @Override
    protected void seriesAdded(Series<String, Number> series, int seriesIndex) {
        // handle any data already in series
        for (int j = 0; j < series.getData().size(); j++) {
            Data item = series.getData().get(j);
            Node candle = createCandle(seriesIndex, item, j);
            if (shouldAnimate()) {
                candle.setOpacity(0);
                getPlotChildren().add(candle);
                // fade in new candle
                FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
                ft.setToValue(1);
                ft.play();
            } else {
                getPlotChildren().add(candle);
            }
        }
        // create series path
        Path seriesPath = new Path();
        seriesPath.getStyleClass().setAll("candlestick-average-line", "series" + seriesIndex);
        series.setNode(seriesPath);
        getPlotChildren().add(seriesPath);
    }

    @Override
    protected void seriesRemoved(Series<String, Number> series) {
        // remove all candle nodes
    	  for (XYChart.Data<String, Number> d : series.getData()) {
              final Node candle = d.getNode();
              if (shouldAnimate()) {
                  // fade out old candle
                  FadeTransition ft = new FadeTransition(Duration.millis(500), candle);
                  ft.setToValue(0);
                  ft.setOnFinished(new EventHandler<ActionEvent>() {

                      @Override
                      public void handle(ActionEvent actionEvent) {
                          getPlotChildren().remove(candle);
                      }
                  });
                  ft.play();
              } else {
                  getPlotChildren().remove(candle);
              }
          }
    }

    /**
     * Create a new Candle node to represent a single data item
     *
     * @param seriesIndex The index of the series the data item is in
     * @param item The data item to create node for
     * @param itemIndex The index of the data item in the series
     * @return New candle node to represent the give data item
     */
    private Node createCandle(int seriesIndex, final Data item, int itemIndex) {
        Node candle = item.getNode();
        // check if candle has already been created
        if (candle instanceof Candle) {
            ((Candle) candle).setSeriesAndDataStyleClasses("series" + seriesIndex, "data" + itemIndex);
        } else {
            candle = new Candle("series" + seriesIndex, "data" + itemIndex);
            item.setNode(candle);
        }
        return candle;
    }

    /**
     * This is called when the range has been invalidated and we need to update
     * it. If the axis are auto ranging then we compile a list of all data that
     * the given axis has to plot and call invalidateRange() on the axis passing
     * it that data.
     */
    @Override
    protected void updateAxisRange() {
        // For candle stick chart we need to override this method as we need to let the axis know that they need to be able
        // to cover the whole area occupied by the high to low range not just its center data value
        final Axis<String> xa = getXAxis();
        final Axis<Number> ya = getYAxis();
        List<String> xData = null;
        List<Number> yData = null;
        if (xa.isAutoRanging()) {
            xData = new ArrayList<>();
        }
        if (ya.isAutoRanging()) {
            yData = new ArrayList<>();
        }
        if (xData != null || yData != null) {
            for (Series<String, Number> series : getData()) {
                for (Data<String, Number> data : series.getData()) {
                    if (xData != null) {
                        xData.add(data.getXValue());
                    }
                    if (yData != null) {
                        BarData extras = (BarData) data.getExtraValue();
                        if (extras != null) {
                            yData.add(extras.getHigh());
                            yData.add(extras.getLow());
                        } else {
                            yData.add(data.getYValue());
                        }
                    }
                }
            }
            if (xData != null) {
                xa.invalidateRange(xData);
            }
            if (yData != null) {
                ya.invalidateRange(yData);
            }
        }
    }

    /**
     * Candle node used for drawing a candle
     */
    private class Candle extends Group {

        private final Line highLowLine = new Line();
        private final Region bar = new Region();
        private String seriesStyleClass;
        private String dataStyleClass;
        private boolean openAboveClose = true;
        private final Tooltip tooltip = new Tooltip();

        private Candle(String seriesStyleClass, String dataStyleClass) {
            setAutoSizeChildren(false);
            getChildren().addAll(highLowLine, bar);
            this.seriesStyleClass = seriesStyleClass;
            this.dataStyleClass = dataStyleClass;
            updateStyleClasses();
            tooltip.setGraphic(new TooltipContent());
            Tooltip.install(bar, tooltip);
        }

        public void setSeriesAndDataStyleClasses(String seriesStyleClass, String dataStyleClass) {
            this.seriesStyleClass = seriesStyleClass;
            this.dataStyleClass = dataStyleClass;
            updateStyleClasses();
        }

        public void update(double closeOffset, double highOffset, double lowOffset, double candleWidth) {
            openAboveClose = closeOffset > 0;
            updateStyleClasses();
            highLowLine.setStartY(highOffset);
            highLowLine.setEndY(lowOffset);
            if (candleWidth == -1) {
                candleWidth = bar.prefWidth(-1);
            }
            if (openAboveClose) {
                bar.resizeRelocate(-candleWidth / 2, 0, candleWidth, closeOffset);
            } else {
                bar.resizeRelocate(-candleWidth / 2, closeOffset, candleWidth, closeOffset * -1);
            }
        }

        public void updateTooltip(double open, double close, double high, double low,String date) {
            TooltipContent tooltipContent = (TooltipContent) tooltip.getGraphic();
            tooltipContent.update(open, close, high, low,date);
        }

        private void updateStyleClasses() {
            getStyleClass().setAll("candlestick-candle", seriesStyleClass, dataStyleClass);
            highLowLine.getStyleClass().setAll("candlestick-line", seriesStyleClass, dataStyleClass,
                    openAboveClose ? "open-above-close" : "close-above-open");
            bar.getStyleClass().setAll("candlestick-bar", seriesStyleClass, dataStyleClass,
                    openAboveClose ? "open-above-close" : "close-above-open");
        }
    }

    private class TooltipContent extends GridPane {

        private final Label openValue = new Label();
        private final Label closeValue = new Label();
        private final Label highValue = new Label();
        private final Label lowValue = new Label();
        
        private final Label dateValue = new Label();

        private TooltipContent() {
            Label open = new Label("OPEN:");
            Label close = new Label("CLOSE:");
            Label high = new Label("HIGH:");
            Label low = new Label("LOW:");
            Label date = new Label("DATE:");
            open.getStyleClass().add("candlestick-tooltip-label");
            close.getStyleClass().add("candlestick-tooltip-label");
            high.getStyleClass().add("candlestick-tooltip-label");
            low.getStyleClass().add("candlestick-tooltip-label");
            date.getStyleClass().add("candlestick-tooltip-label");
            setConstraints(open, 0, 0);
            setConstraints(openValue, 1, 0);
            setConstraints(close, 0, 1);
            setConstraints(closeValue, 1, 1);
            setConstraints(high, 0, 2);
            setConstraints(highValue, 1, 2);
            setConstraints(low, 0, 3);
            setConstraints(lowValue, 1, 3);    
            setConstraints(date, 0, 4);
            setConstraints(dateValue, 1, 4);
                      
            getChildren().addAll(open, openValue, close, closeValue, high, highValue, low, lowValue,date,dateValue);
        }

        public void update(double open, double close, double high, double low,String date) { 
        	 openValue.setFont(new Font("Cambria", 15));
             closeValue.setFont(new Font("Cambria", 15));
             highValue.setFont(new Font("Cambria", 15));
             lowValue.setFont(new Font("Cambria", 15));
             dateValue.setFont(new Font("Cambria", 15));
            openValue.setText(Double.toString(open));
            closeValue.setText(Double.toString(close));
            highValue.setText(Double.toString(high));
            lowValue.setText(Double.toString(low));
            dateValue.setText(date);
            
        }
    }

    protected static CandleStickChart chart;
}
