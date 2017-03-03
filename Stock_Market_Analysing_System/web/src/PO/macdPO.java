package PO;

public class macdPO {
	private String date;
	private double DIF;
	private double DEA;

	public macdPO(String date, double dIF, double dEA) {
		super();
		this.date = date;
		DIF = dIF;
		DEA = dEA;
	}

	public double getDIF() {
		return DIF;
	}

	public void setDIF(double dIF) {
		DIF = dIF;
	}

	public double getDEA() {
		return DEA;
	}

	public void setDEA(double dEA) {
		DEA = dEA;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
