package PO;

public class ChangePO {
	
	//股价波动PO，采用方差change作为衡量
	private String code;
	private String name;
	private String industry;
	private double change;
	
	public ChangePO(String code, String name, String industry, double change) {
		super();
		this.code = code;
		this.name = name;
		this.industry = industry;
		this.change = change;
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
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	
}
