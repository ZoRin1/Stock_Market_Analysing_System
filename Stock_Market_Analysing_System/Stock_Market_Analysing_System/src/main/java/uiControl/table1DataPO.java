package uiControl;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 * 
 * @author 杨关
 *股票列表的表格模型
 */
public class table1DataPO {
	
	private SimpleStringProperty date; //日期
	private SimpleStringProperty sign; //股票代码
	private SimpleStringProperty name; //股票名称
	private SimpleDoubleProperty open;//开盘价
	private SimpleDoubleProperty high;//最高价
	private SimpleDoubleProperty low;//最低价
	private SimpleDoubleProperty close;//收盘价
	private SimpleDoubleProperty adj_price;//后复权价
	private SimpleDoubleProperty volume;//成交量
	private SimpleDoubleProperty turnover;//换手率
	private SimpleDoubleProperty pb;//市净率
	private SimpleStringProperty industry;//行业
	
	public table1DataPO(String date,String sign,String name,String industry,double open,double high,
			double low,double close,double adj_price,double volume,double turnover,
			double pb){
		this.date = new SimpleStringProperty(date);
		this.sign = new SimpleStringProperty(sign);
		this.name = new SimpleStringProperty(name);
		this.industry = new SimpleStringProperty(industry);
		this.open = new SimpleDoubleProperty(open);
		this.high = new SimpleDoubleProperty(high);
		this.low = new SimpleDoubleProperty(low);
		this.close = new SimpleDoubleProperty(close);
		this.adj_price = new SimpleDoubleProperty(adj_price);
		this.volume = new SimpleDoubleProperty(volume);
		this.turnover = new SimpleDoubleProperty(turnover);
		this.pb = new SimpleDoubleProperty(pb);
	}

	public String getIndustry() {
		return industry.get();
	}

	public void setIndustry(String industry) {
		this.industry.set(industry);
	}

	public String getDate() {
		return date.get();
	}

	public void setDate(String date) {
		this.date.set(date);;
	}

	public String getSign() {
		return sign.get();
	}

	public void setSign(String sign) {
		this.sign.set(sign);;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public double getOpen() {
		return open.get();
	}

	public void setOpen(double open) {
		this.open.set(open);;
	}

	public double getHigh() {
		return high.get();
	}

	public void setHigh(double high) {
		this.high.set(high);;
	}

	public double getLow() {
		return low.get();
	}

	public void setLow(double low) {
		this.low.set(low);;
	}

	public double getClose() {
		return close.get();
	}

	public void setClose(double close) {
		this.close.set(close);
	}

	public double getAdj_price() {
		return adj_price.get();
	}

	public void setAdj_price(double adj_price) {
		this.adj_price.set(adj_price);;
	}

	public double getVolume() {
		return volume.get();
	}

	public void setVolume(double volume) {
		this.volume.set(volume);
	}

	public double getTurnover() {
		return turnover.get();
	}

	public void setTurnover(double turnover) {
		this.turnover.set(turnover);
	}

	public double getPb() {
		return pb.get();
	}

	public void setPb(double pb) {
		this.pb.get();
	}
	
}
