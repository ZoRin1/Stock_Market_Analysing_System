package PO;

public class IndexPO {
	private double money;
	private String date;
	public IndexPO(double money, String date) {
		super();
		this.money = money;
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
