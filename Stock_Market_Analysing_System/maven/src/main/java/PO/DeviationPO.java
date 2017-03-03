package PO;

public class DeviationPO {
	//预测涨幅相关PO，以折扣率偏离程度为基准，如果偏向下跌方向则负
	private String code;
	private String name;
	private String industry;
	private double deviation;//预估涨幅
	private double possi;//在某个区间的可能性
	private double min;//区间下界
	private double max;//区间上界
	
	public DeviationPO(String code, String name, String industry, double deviation, double possi, double min,
			double max) {
		super();
		this.code = code;
		this.name = name;
		this.industry = industry;
		this.deviation = deviation;
		this.possi = possi;
		this.min = min;
		this.max = max;
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
	public double getDeviation() {
		return deviation;
	}
	public void setDeviation(double deviation) {
		this.deviation = deviation;
	}
	public double getPossi() {
		return possi;
	}
	public void setPossi(double possi) {
		this.possi = possi;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	
	
}
