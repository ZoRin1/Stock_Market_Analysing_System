/**
 * 
 */
package PO;

/**
 * @author wang
 *
 */
public class NowStockPo {
	private String date;/*日期*/
	private String time;/*时间*/
	private String gid;/*股票编号*/
	private String increPer;/*涨跌百分比*/
	private String increase;/*涨跌额*/
	private String name;/*股票名称*/
	private String todayStartPri;/*今日开盘价*/
	private String yestodEndPri;/*昨日收盘价*/
	private String nowPri;/*当前价格*/
	private String todayMax;/*今日最高价*/
	private String todayMin;/*今日最低价*/
	private String competitivePri;/*竞买价*/
	private String reservePri;/*竞卖价*/
	private String traNumber;/*成交量*/
	private String traAmount;/*成交金额*/
	
	private String rate;/*大盘涨跌率*/
	private String dot;/*大盘当前点数*/
	private String nowPic;/*大盘当前价格*/
	public NowStockPo(String date, String time, String gid, String increPer,
			String increase, String name, String todayStartPri,
			String yestodEndPri, String nowPri, String todayMax,
			String todayMin, String competitivePri, String reservePri,
			String traNumber, String traAmount, String rate, String dot,
			String nowPic) {
		super();
		this.date = date;
		this.time = time;
		this.gid = gid;
		this.increPer = increPer;
		this.increase = increase;
		this.name = name;
		this.todayStartPri = todayStartPri;
		this.yestodEndPri = yestodEndPri;
		this.nowPri = nowPri;
		this.todayMax = todayMax;
		this.todayMin = todayMin;
		this.competitivePri = competitivePri;
		this.reservePri = reservePri;
		this.traNumber = traNumber;
		this.traAmount = traAmount;
		this.rate = rate;
		this.dot = dot;
		this.nowPic = nowPic;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getIncrePer() {
		return increPer;
	}
	public void setIncrePer(String increPer) {
		this.increPer = increPer;
	}
	public String getIncrease() {
		return increase;
	}
	public void setIncrease(String increase) {
		this.increase = increase;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTodayStartPri() {
		return todayStartPri;
	}
	public void setTodayStartPri(String todayStartPri) {
		this.todayStartPri = todayStartPri;
	}
	public String getYestodEndPri() {
		return yestodEndPri;
	}
	public void setYestodEndPri(String yestodEndPri) {
		this.yestodEndPri = yestodEndPri;
	}
	public String getNowPri() {
		return nowPri;
	}
	public void setNowPri(String nowPri) {
		this.nowPri = nowPri;
	}
	public String getTodayMax() {
		return todayMax;
	}
	public void setTodayMax(String todayMax) {
		this.todayMax = todayMax;
	}
	public String getTodayMin() {
		return todayMin;
	}
	public void setTodayMin(String todayMin) {
		this.todayMin = todayMin;
	}
	public String getCompetitivePri() {
		return competitivePri;
	}
	public void setCompetitivePri(String competitivePri) {
		this.competitivePri = competitivePri;
	}
	public String getReservePri() {
		return reservePri;
	}
	public void setReservePri(String reservePri) {
		this.reservePri = reservePri;
	}
	public String getTraNumber() {
		return traNumber;
	}
	public void setTraNumber(String traNumber) {
		this.traNumber = traNumber;
	}
	public String getTraAmount() {
		return traAmount;
	}
	public void setTraAmount(String traAmount) {
		this.traAmount = traAmount;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getDot() {
		return dot;
	}
	public void setDot(String dot) {
		this.dot = dot;
	}
	public String getNowPic() {
		return nowPic;
	}
	public void setNowPic(String nowPic) {
		this.nowPic = nowPic;
	}
}
