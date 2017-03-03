package PO;

import java.util.Date;

public class StockPO {
	private String date;//日期
	private String code;//股票编号
	private String name;//股票名称
	private String startPrice;//开盘价
	private String maxPrice;//最高价
	private String minPrice;//最低价
	private String endPrice;//收盘价
	private String LastPrice;//后复权价
	private String quantity;//成交量
	private String changeRate;//换手率
	private String allRate;//市净率
	private String industry;//行业
	private String Zhangfu;//涨幅
	//大盘数据中没有换手率
	
	public StockPO(String date, String code, String name, String startPrice,
			String maxPrice, String minPrice, String endPrice,
			String lastPrice, String quantity, String changeRate, String allRate,
			String industry,String zhangfu) {
		super();
		this.date = date;
		this.code = code;
		this.name = name;
		this.startPrice = startPrice;
		this.maxPrice = maxPrice;
		this.minPrice = minPrice;
		this.endPrice = endPrice;
		LastPrice = lastPrice;
		this.quantity = quantity;
		this.changeRate = changeRate;
		this.allRate = allRate;
		this.industry=industry;
		Zhangfu=zhangfu;
	}
	public String getZhangfu() {
		return Zhangfu;
	}
	public void setZhangfu(String zhangfu) {
		Zhangfu = zhangfu;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	public String getEndPrice() {
		return endPrice;
	}
	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
	public String getLastPrice() {
		return LastPrice;
	}
	public void setLastPrice(String lastPrice) {
		LastPrice = lastPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getChangeRate() {
		return changeRate;
	}
	public void setChangeRate(String changeRate) {
		this.changeRate = changeRate;
	}
	public String getAllRate() {
		return allRate;
	}
	public void setAllRate(String allRate) {
		this.allRate = allRate;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
