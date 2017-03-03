/**
 * 
 */
package PO;

/**
 * @author lenovo
 *
 */
public class StockPO1 {
	private String name;
	private String quantity;
	private String industry;
	public StockPO1(String name, String quantity, String industry) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.industry = industry;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
}
