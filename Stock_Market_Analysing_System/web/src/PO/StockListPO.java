/**
 * 
 */
package PO;


public class StockListPO {
	private String code;//股票代码
	private String name;//股票名
	private String industry;//行业
	private double open;//今日开盘
	private double pre_close;//昨日收盘
	private double price;//当前价格
	private double high;//今日最高
	private double low;//今日最低
	private double b1_p;//竞买价
	private double a1_p;//竞卖价
	private double b2_p;//竞买价
	private double a2_p;//竞卖价
	private double b3_p;//竞买价
	private double a3_p;//竞卖价
	private double b4_p;//竞买价
	private double a4_p;//竞卖价
	private double b5_p;//竞买价
	private double a5_p;//竞卖价
	private double volume;//成交量
	private double account;//成交金额
	private String time;//时间
	/**
	 * @param code
	 * @param name
	 * @param industry
	 * @param open
	 * @param pre_close
	 * @param price
	 * @param high
	 * @param low
	 * @param bid
	 * @param ask
	 * @param volume
	 * @param account
	 * @param time
	 */
	public StockListPO(String code, String name, String industry, double open,
			double pre_close, double price, double high, double low, double b1_p,
			double a1_p,double b2_p,
			double a2_p,double b3_p,
			double a3_p,double b4_p,
			double a4_p,double b5_p,
			double a5_p, double volume, double account, String time) {
		super();
		this.code = code;
		this.name = name;
		this.industry = industry;
		this.open = open;
		this.pre_close = pre_close;
		this.price = price;
		this.high = high;
		this.low = low;
		this.b1_p = b1_p;
		this.a1_p = a1_p;
		this.b2_p = b2_p;
		this.a2_p = a2_p;
		this.b3_p = b3_p;
		this.a3_p = a3_p;
		this.b4_p = b4_p;
		this.a4_p = a4_p;
		this.b5_p = b5_p;
		this.a5_p = a5_p;
		this.volume = volume;
		this.account = account;
		this.time = time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getPre_close() {
		return pre_close;
	}
	public void setPre_close(double pre_close) {
		this.pre_close = pre_close;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getB1_p() {
		return b1_p;
	}
	public void setB1_p(double b1_p) {
		this.b1_p = b1_p;
	}
	public double getA1_p() {
		return a1_p;
	}
	public void setA1_p(double a1_p) {
		this.a1_p = a1_p;
	}
	public double getB2_p() {
		return b2_p;
	}
	public void setB2_p(double b2_p) {
		this.b2_p = b2_p;
	}
	public double getA2_p() {
		return a2_p;
	}
	public void setA2_p(double a2_p) {
		this.a2_p = a2_p;
	}
	public double getB3_p() {
		return b3_p;
	}
	public void setB3_p(double b3_p) {
		this.b3_p = b3_p;
	}
	public double getA3_p() {
		return a3_p;
	}
	public void setA3_p(double a3_p) {
		this.a3_p = a3_p;
	}
	public double getB4_p() {
		return b4_p;
	}
	public void setB4_p(double b4_p) {
		this.b4_p = b4_p;
	}
	public double getA4_p() {
		return a4_p;
	}
	public void setA4_p(double a4_p) {
		this.a4_p = a4_p;
	}
	public double getB5_p() {
		return b5_p;
	}
	public void setB5_p(double b5_p) {
		this.b5_p = b5_p;
	}
	public double getA5_p() {
		return a5_p;
	}
	public void setA5_p(double a5_p) {
		this.a5_p = a5_p;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	
	
}
