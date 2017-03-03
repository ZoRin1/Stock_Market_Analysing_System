package PO;

public class pchangePO {
	private String date;
	private double market;//大盘涨跌幅
	private double stock;//个股涨跌幅
	public pchangePO(String date, double market, double stock) {
		super();
		this.date = date;
		this.market = market;
		this.stock = stock;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getMarket() {
		return market;
	}
	public void setMarket(double market) {
		this.market = market;
	}
	public double getStock() {
		return stock;
	}
	public void setStock(double stock) {
		this.stock = stock;
	}
	
	
}
