package PO;

public class AtrPO {
	private String date;
	private double atr;
	public AtrPO(String date, double atr) {
		super();
		this.date = date;
		this.atr = atr;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getAtr() {
		return atr;
	}
	public void setAtr(double atr) {
		this.atr = atr;
	}
	
}
