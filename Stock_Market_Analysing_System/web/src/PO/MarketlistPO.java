/**
 * 
 */
package PO;

public class MarketlistPO {
	private String code;//指数代码
	private String name;//指数名称
	private double open;//开盘点位
	private double pre_close;//昨日收盘点位
	private double price;//当前点位
	private double high;//最高点位
	private double low;//最低点位
	private double volume;//成交量
	private double amount;//成交金额
	private String time;//时间
	/**
	 * @param code
	 * @param name
	 * @param d
	 * @param e
	 * @param f
	 * @param g
	 * @param h
	 * @param i
	 * @param j
	 * @param time
	 */
	public MarketlistPO(String code, String name, double d, double e,
			double f, double g, double h, double i, double j,
			String time) {
		super();
		this.code = code;
		this.name = name;
		this.open = d;
		this.pre_close = e;
		this.price = f;
		this.high = g;
		this.low = h;
		this.volume = i;
		this.amount = j;
		this.time = time;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the pre_close
	 */
	public double getPre_close() {
		return pre_close;
	}
	/**
	 * @param pre_close the pre_close to set
	 */
	public void setPre_close(float pre_close) {
		this.pre_close = pre_close;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
}
