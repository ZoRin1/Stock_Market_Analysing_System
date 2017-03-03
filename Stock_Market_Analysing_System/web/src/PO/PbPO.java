package PO;

public class PbPO {
	private String date;
	private double pb;
	public PbPO(String date, double pb) {
		super();
		this.date = date;
		this.pb = pb;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getPb() {
		return pb;
	}
	public void setPb(double pb) {
		this.pb = pb;
	}
	
}
