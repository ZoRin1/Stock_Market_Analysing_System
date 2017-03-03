package dataApi;

import java.util.List;

import dataApi.exchangeJson;
import dataApi.setFields;


/**
 * 
 * @author 杨关
 * 
 */
public class connectHttp {
	exchangeJson ex;
	List<String> list;
	public connectHttp(){
		ex = new exchangeJson();
	}
/**
 * 
 * @param year：选中年份中的所有股票列表； 
 * @param exchange：交易所，目前可以为 sz或sh，代表深交所和上交所
 * @return	 json数据，股票列表和对应的访问url
 * @throws Exception 
 */
	public  String getAnyQuant(String year,String exchange) throws Exception{
		String getURL = "http://121.41.106.89:8010/api/stocks/?year="+year+"&exchange="+exchange;
		URLConnectionControl urlConnectionControl=new URLConnectionControl(getURL);
		return urlConnectionControl.getResult();
	}
	/**
	 * 
	 * @param Link：股票访问的url
	 * @param start：开始日期
	 * @param end：截止日期
	 * @param setCommend：指定数据字段对象
	 * @return	返回指定股票代码的股票交易数据
	 * @throws Exception 
	 */
	public List<String> getQuantInf(String Link,String start,String end,setFields setCommend) throws Exception{
		String URL = Link+"/?start="+start+"&end="+end+"&fields="+setCommend.getFields();
//		String URL = "http://121.41.106.89:8010/api/stock/sh600004/?start=2014-10-10&end=2014-11-10&fields=open+high+close";
		URLConnectionControl urlConnectionControl=new URLConnectionControl(URL);
		String result = urlConnectionControl.getResult();
//		System.out.println(result); 显示json的数据  以后debug用
		list = ex.arrJson(result,setCommend.getFields());
		return list;
	}
	/**
	 * 
	 * @param start：起始时间，格式'YYYY-mm-dd'
	 * @param end：结束时间，格式'YYYY-mm-dd'
	 * @param setCommend：指定数据字段对象
	 * @return 获取沪深300大盘指定的数据字段数据
	 * @throws Exception 
	 */
	public List<String> getzhishu(String start,String end,setFields setCommend) throws Exception{
		String URL = "http://121.41.106.89:8010/api/benchmark/hs300?start="+start+"&end="+end+"&fields="+setCommend.getFields();
		URLConnectionControl urlConnectionControl=new URLConnectionControl(URL);
		String result = urlConnectionControl.getResult();
//		System.out.println(result);
		list = ex.arrJson(result,setCommend.getFields());
		return list;
	}
	
}
