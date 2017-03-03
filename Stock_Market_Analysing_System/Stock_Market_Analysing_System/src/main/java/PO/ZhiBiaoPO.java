/**
 * 
 */
package PO;

import java.util.Date;

/**
 * @author lenovo
 *
 */
public class ZhiBiaoPO {
	private String date;
	private double ATR;
	private double MA;
	private double MA2;
	private double MA3;
	private double MA4;
	private double MA5;
	private double MA6;
	private double MA7;
	private double DIF;
	private double DEA;
	private double K;
	private double D;
	private double J;
	/**
	 * @param date
	 * @param aTR
	 * @param mA
	 * @param mA2
	 * @param mA3
	 * @param mA4
	 * @param mA5
	 * @param mA6
	 * @param mA7
	 * @param dIF
	 * @param dEA
	 */
	

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 * @param aTR
	 * @param mA
	 * @param mA2
	 * @param mA3
	 * @param mA4
	 * @param mA5
	 * @param mA6
	 * @param mA7
	 * @param dIF
	 * @param dEA
	 * @param k
	 * @param d
	 * @param j
	 */
	public ZhiBiaoPO(String date, double aTR, double mA, double mA2,
			double mA3, double mA4, double mA5, double mA6, double mA7,
			double dIF, double dEA, double k, double d, double j) {
		super();
		this.date = date;
		ATR = aTR;
		MA = mA;
		MA2 = mA2;
		MA3 = mA3;
		MA4 = mA4;
		MA5 = mA5;
		MA6 = mA6;
		MA7 = mA7;
		DIF = dIF;
		DEA = dEA;
		K = k;
		D = d;
		J = j;
	}

	/**
	 * @return the k
	 */
	public double getK() {
		return K;
	}

	/**
	 * @param k the k to set
	 */
	public void setK(double k) {
		K = k;
	}

	/**
	 * @return the d
	 */
	public double getD() {
		return D;
	}

	/**
	 * @param d the d to set
	 */
	public void setD(double d) {
		D = d;
	}

	/**
	 * @return the j
	 */
	public double getJ() {
		return J;
	}

	/**
	 * @param j the j to set
	 */
	public void setJ(double j) {
		J = j;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the aTR
	 */
	public double getATR() {
		return ATR;
	}

	/**
	 * @param aTR the aTR to set
	 */
	public void setATR(double aTR) {
		ATR = aTR;
	}

	/**
	 * @return the mA
	 */
	public double getMA() {
		return MA;
	}

	/**
	 * @param mA the mA to set
	 */
	public void setMA(double mA) {
		MA = mA;
	}

	/**
	 * @return the mA2
	 */
	public double getMA2() {
		return MA2;
	}

	/**
	 * @param mA2 the mA2 to set
	 */
	public void setMA2(double mA2) {
		MA2 = mA2;
	}

	/**
	 * @return the mA3
	 */
	public double getMA3() {
		return MA3;
	}

	/**
	 * @param mA3 the mA3 to set
	 */
	public void setMA3(double mA3) {
		MA3 = mA3;
	}

	/**
	 * @return the mA4
	 */
	public double getMA4() {
		return MA4;
	}

	/**
	 * @param mA4 the mA4 to set
	 */
	public void setMA4(double mA4) {
		MA4 = mA4;
	}

	/**
	 * @return the mA5
	 */
	public double getMA5() {
		return MA5;
	}

	/**
	 * @param mA5 the mA5 to set
	 */
	public void setMA5(double mA5) {
		MA5 = mA5;
	}

	/**
	 * @return the mA6
	 */
	public double getMA6() {
		return MA6;
	}

	/**
	 * @param mA6 the mA6 to set
	 */
	public void setMA6(double mA6) {
		MA6 = mA6;
	}

	/**
	 * @return the mA7
	 */
	public double getMA7() {
		return MA7;
	}

	/**
	 * @param mA7 the mA7 to set
	 */
	public void setMA7(double mA7) {
		MA7 = mA7;
	}

	/**
	 * @return the dIF
	 */
	public double getDIF() {
		return DIF;
	}

	/**
	 * @param dIF the dIF to set
	 */
	public void setDIF(double dIF) {
		DIF = dIF;
	}

	/**
	 * @return the dEA
	 */
	public double getDEA() {
		return DEA;
	}

	/**
	 * @param dEA the dEA to set
	 */
	public void setDEA(double dEA) {
		DEA = dEA;
	}
	
	
}
