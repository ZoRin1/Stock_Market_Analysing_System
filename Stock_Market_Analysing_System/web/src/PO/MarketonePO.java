/**
 * 
 */
package PO;


public class MarketonePO {
	private String date;//日期
	private double open;//开盘价
	private double high;//最高价
	private double close;//收盘价
	private double low;//最低价
	private double volume;//成交量
	private double price_change;//价格变动
	private double p_change;//涨跌幅
	private double ma5;//5日均价
	private double ma10;//10日均价
	private double ma20;//20日均价
	private double v_ma5;//5日均量
	private double v_ma10;//10日均量
	private double v_ma20;//20日均量
	/**
	 * @param date
	 * @param open
	 * @param high
	 * @param close
	 * @param low
	 * @param volume
	 * @param price_change
	 * @param p_change
	 * @param ma5
	 * @param ma10
	 * @param ma20
	 * @param v_ma5
	 * @param v_ma10
	 * @param v_ma20
	 */
	public MarketonePO(String date, double open, double high, double close,
			double low, double volume, double price_change, double p_change,
			double ma5, double ma10, double ma20, double v_ma5, double v_ma10,
			double v_ma20) {
		super();
		this.date = date;
		this.open = open;
		this.high = high;
		this.close = close;
		this.low = low;
		this.volume = volume;
		this.price_change = price_change;
		this.p_change = p_change;
		this.ma5 = ma5;
		this.ma10 = ma10;
		this.ma20 = ma20;
		this.v_ma5 = v_ma5;
		this.v_ma10 = v_ma10;
		this.v_ma20 = v_ma20;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the open
	 */
	public double getOpen() {
		return open;
	}
	/**
	 * @param open the open to set
	 */
	public void setOpen(float open) {
		this.open = open;
	}
	/**
	 * @return the high
	 */
	public double getHigh() {
		return high;
	}
	/**
	 * @param high the high to set
	 */
	public void setHigh(float high) {
		this.high = high;
	}
	/**
	 * @return the close
	 */
	public double getClose() {
		return close;
	}
	/**
	 * @param close the close to set
	 */
	public void setClose(float close) {
		this.close = close;
	}
	/**
	 * @return the low
	 */
	public double getLow() {
		return low;
	}
	/**
	 * @param low the low to set
	 */
	public void setLow(float low) {
		this.low = low;
	}
	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}
	/**
	 * @param volume the volume to set
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
	/**
	 * @return the price_change
	 */
	public double getPrice_change() {
		return price_change;
	}
	/**
	 * @param price_change the price_change to set
	 */
	public void setPrice_change(float price_change) {
		this.price_change = price_change;
	}
	/**
	 * @return the p_change
	 */
	public double getP_change() {
		return p_change;
	}
	/**
	 * @param p_change the p_change to set
	 */
	public void setP_change(float p_change) {
		this.p_change = p_change;
	}
	/**
	 * @return the ma5
	 */
	public double getMa5() {
		return ma5;
	}
	/**
	 * @param ma5 the ma5 to set
	 */
	public void setMa5(float ma5) {
		this.ma5 = ma5;
	}
	/**
	 * @return the ma10
	 */
	public double getMa10() {
		return ma10;
	}
	/**
	 * @param ma10 the ma10 to set
	 */
	public void setMa10(float ma10) {
		this.ma10 = ma10;
	}
	/**
	 * @return the ma20
	 */
	public double getMa20() {
		return ma20;
	}
	/**
	 * @param ma20 the ma20 to set
	 */
	public void setMa20(float ma20) {
		this.ma20 = ma20;
	}
	/**
	 * @return the v_ma5
	 */
	public double getV_ma5() {
		return v_ma5;
	}
	/**
	 * @param v_ma5 the v_ma5 to set
	 */
	public void setV_ma5(float v_ma5) {
		this.v_ma5 = v_ma5;
	}
	/**
	 * @return the v_ma10
	 */
	public double getV_ma10() {
		return v_ma10;
	}
	/**
	 * @param v_ma10 the v_ma10 to set
	 */
	public void setV_ma10(float v_ma10) {
		this.v_ma10 = v_ma10;
	}
	/**
	 * @return the v_ma20
	 */
	public double getV_ma20() {
		return v_ma20;
	}
	/**
	 * @param v_ma20 the v_ma20 to set
	 */
	public void setV_ma20(float v_ma20) {
		this.v_ma20 = v_ma20;
	}
	
	
}
