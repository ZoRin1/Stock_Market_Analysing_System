/**
 * 
 */
package PO;

/**
 * @author wang
 *
 */
public class NowDapanPo {
	private String name;/*名称*/
	private String time;/*时间*/
	private String increPer;/*涨跌百分比*/
	private String increase;/*涨跌幅*/
	private String openPri;/*今开*/
	private String yesPri;/*昨收*/
	private String nowpri;/*当前价格*/
	private String highPri;/*最高*/
	private String lowpri;/*最低*/
	private String dealNum;/*成交量*/
	private String dealPri;/*成交额*/
	public NowDapanPo(String name, String time, String increPer,
			String increase, String openPri, String yesPri, String nowpri,
			String highPri, String lowpri, String dealNum, String dealPri) {
		super();
		this.name = name;
		this.time = time;
		this.increPer = increPer;
		this.increase = increase;
		this.openPri = openPri;
		this.yesPri = yesPri;
		this.nowpri = nowpri;
		this.highPri = highPri;
		this.lowpri = lowpri;
		this.dealNum = dealNum;
		this.dealPri = dealPri;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public String getOpenPri() {
		return openPri;
	}
	public void setOpenPri(String openPri) {
		this.openPri = openPri;
	}
	public String getYesPri() {
		return yesPri;
	}
	public void setYesPri(String yesPri) {
		this.yesPri = yesPri;
	}
	public String getNowpri() {
		return nowpri;
	}
	public void setNowpri(String nowpri) {
		this.nowpri = nowpri;
	}
	public String getHighPri() {
		return highPri;
	}
	public void setHighPri(String highPri) {
		this.highPri = highPri;
	}
	public String getLowpri() {
		return lowpri;
	}
	public void setLowpri(String lowpri) {
		this.lowpri = lowpri;
	}
	public String getDealNum() {
		return dealNum;
	}
	public void setDealNum(String dealNum) {
		this.dealNum = dealNum;
	}
	public String getDealPri() {
		return dealPri;
	}
	public void setDealPri(String dealPri) {
		this.dealPri = dealPri;
	}
}
