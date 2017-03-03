package PO;

public class StablePO {
	//涨势先关PO，采用价值中心数学所占概率及股票周口率偏移方向作为基准,若估计反而会下跌则取负值
	private String code;
	private String name;
	private String industry;
	private double inpossi;//上涨概率，用于图表显示
	private double eqpossi;//持平概率
	private double depossi;//下跌概率
	
	public StablePO(String code, String name, String industry, double inpossi, double eqpossi, double depossi) {
		super();
		this.code = code;
		this.name = name;
		this.industry = industry;
		this.inpossi = inpossi;
		this.eqpossi = eqpossi;
		this.depossi = depossi;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public double getInpossi() {
		return inpossi;
	}
	public void setInpossi(double inpossi) {
		this.inpossi = inpossi;
	}
	public double getEqpossi() {
		return eqpossi;
	}
	public void setEqpossi(double eqpossi) {
		this.eqpossi = eqpossi;
	}
	public double getDepossi() {
		return depossi;
	}
	public void setDepossi(double depossi) {
		this.depossi = depossi;
	}
	
	
}
