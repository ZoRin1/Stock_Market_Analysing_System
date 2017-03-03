package PO;

public class volPO {
	private String date;
	private double v_ma5;//5日均量
	private double v_ma10;//10日均量
	private double v_ma20;//20日均量
	private double volume;//成交量

	public volPO(String date, double v_ma5, double v_ma10, double v_ma20, double volume) {
		super();
		this.date = date;
		this.v_ma5 = v_ma5;
		this.v_ma10 = v_ma10;
		this.v_ma20 = v_ma20;
		this.volume = volume;
	}

	public double getV_ma5() {
		return v_ma5;
	}

	public void setV_ma5(double v_ma5) {
		this.v_ma5 = v_ma5;
	}

	public double getV_ma10() {
		return v_ma10;
	}

	public void setV_ma10(double v_ma10) {
		this.v_ma10 = v_ma10;
	}

	public double getV_ma20() {
		return v_ma20;
	}

	public void setV_ma20(double v_ma20) {
		this.v_ma20 = v_ma20;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
