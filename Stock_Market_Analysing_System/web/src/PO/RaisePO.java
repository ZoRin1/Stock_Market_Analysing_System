package PO;

public class RaisePO {
	private String code;//股票代码
	private String name;//股票名
	private String industry;//行业名称
	private double open;//开盘价
	private double preclose;//昨收
	private double price;//股票当前价格
	private double change;//涨跌幅
	private double now;//当前持有
	private double init;//初始投入
	private String date;//初始投资设定日期
	
	public RaisePO(String code, String name, String industry, double open, double preclose, double price, double change,
			double now, double init, String date) {
		super();
		this.code = code;
		this.name = name;
		this.industry = industry;
		this.open = open;
		this.preclose = preclose;
		this.price = price;
		this.change = change;
		this.now = now;
		this.init = init;
		this.date = date;
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

	public double getPreclose() {
		return preclose;
	}

	public void setPreclose(double preclose) {
		this.preclose = preclose;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public double getNow() {
		return now;
	}
	public void setNow(double now) {
		this.now = now;
	}
	public double getInit() {
		return init;
	}
	public void setInit(double init) {
		this.init = init;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
