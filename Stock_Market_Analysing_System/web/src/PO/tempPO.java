package PO;

public class tempPO {
	private int id;//0表示指数，1表示股票
	private String code;//6位代码
	public tempPO(int id, String code) {
		super();
		this.id = id;
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
