package dataApi;

/**
 * 
 * @author 杨关
 *
 */
public class setFields {
	private String open = "open";
	private String high = "high";
	private String low = "low";
	private String close = "close";
	private String adj_price = "adj_price";
	private String volume = "volume";
	private String turnover = "turnover";
	private String pe = "pe";
	private String pb = "pb";
	private String fields;
	
	public setFields(){
		fields = "";
	}

	public String getOpen() {
		return open;
	}
	public String getHigh() {
		return high;
	}
	public String getLow() {
		return low;
	}
	public String getClose() {
		return close;
	}
	public String getAdj_price() {
		return adj_price;
	}
	public String getVolume() {
		return volume;
	}
	public String getTurnover() {
		return turnover;
	}
	public String getPe() {
		return pe;
	}
	public String getPb() {
		return pb;
	}
	
	public void setFields(String commend){
		if(fields==""){
			fields = fields + commend;
		}else{
			fields = fields + "+" + commend;
		}
	}
	
	public String getFields(){
		return fields;
	}
	public void cleanFields(){
		fields = "";
	}
}
