package PO;

public class industryPO {
	private String name;
	private int innum;//上涨股票支数
	private int eqnum;//价格不变支数
	private int ounum;//下跌股票支数，innum+ounum=5;
	private String maxName;//涨幅最大或者说跌幅最小的股票
	private double volume;//成交量
	private double p_change;//涨跌幅，单位为百分比

	public industryPO(String name, int innum, int eqnum, int ounum, String maxName, double volume, double p_change) {
		super();
		this.name = name;
		this.innum = innum;
		this.eqnum = eqnum;
		this.ounum = ounum;
		this.maxName = maxName;
		this.volume = volume;
		this.p_change = p_change;
	}

	public int getEqnum() {
		return eqnum;
	}

	public void setEqnum(int eqnum) {
		this.eqnum = eqnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInnum() {
		return innum;
	}

	public void setInnum(int innum) {
		this.innum = innum;
	}

	public int getOunum() {
		return ounum;
	}

	public void setOunum(int ounum) {
		this.ounum = ounum;
	}

	public String getMaxName() {
		return maxName;
	}

	public void setMaxName(String maxName) {
		this.maxName = maxName;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getP_change() {
		return p_change;
	}

	public void setP_change(double p_change) {
		this.p_change = p_change;
	}
	
	
}
