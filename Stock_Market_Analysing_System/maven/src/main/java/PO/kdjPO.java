package PO;

public class kdjPO {
	private String date;
	private double K;
	private double D;
	private double J;

	public kdjPO(String date, double k, double d, double j) {
		super();
		this.date = date;
		K = k;
		D = d;
		J = j;
	}

	public double getK() {
		return K;
	}

	public void setK(double k) {
		K = k;
	}

	public double getD() {
		return D;
	}

	public void setD(double d) {
		D = d;
	}

	public double getJ() {
		return J;
	}

	public void setJ(double j) {
		J = j;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
