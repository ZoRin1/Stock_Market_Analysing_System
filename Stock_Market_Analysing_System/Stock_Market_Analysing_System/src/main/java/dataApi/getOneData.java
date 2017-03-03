package dataApi;


import java.util.ArrayList;
import java.util.List;

import PO.StockPO;
import dataService.getOneDataSer;

public class getOneData implements getOneDataSer {
	
	@Override
	public List<StockPO> getOne(String start, String end, String code) throws Exception {
		// TODO Auto-generated method stub
		setFields fields = new setFields();
		
		fields.setFields(fields.getOpen());
		fields.setFields(fields.getHigh());
		fields.setFields(fields.getLow());
		fields.setFields(fields.getClose());
		fields.setFields(fields.getAdj_price());
		fields.setFields(fields.getVolume());
		fields.setFields(fields.getTurnover());
		fields.setFields(fields.getPb());
		
		List<StockPO> list=new ArrayList<>();
		//date转换为String的格式是否符合待定
		
		String str="http://121.41.106.89:8010/api/stock/"+code;
		connectHttp connection = new connectHttp();
		List<String> tempString = connection.getQuantInf(str, start, end, fields);
		Transfer trans=new Transfer();
		int temp=0;
		for(String tem:tempString){
			String[] a=tem.split(",");
			StockPO po=new StockPO(a[0], code, trans.transfer2(code), a[1], a[2], a[3], a[4], a[5]
					, a[6], a[7], a[8] ,trans.transfer3(code),"0.0");
			list.add(po);
		}
		return list;
	}

//	@Override
	public List<StockPO> getDaPan(String start, String end) throws Exception {
		// TODO Auto-generated method stub
		setFields fields = new setFields();
		
		fields.setFields(fields.getOpen());
		fields.setFields(fields.getHigh());
		fields.setFields(fields.getLow());
		fields.setFields(fields.getClose());
		fields.setFields(fields.getAdj_price());
		fields.setFields(fields.getVolume());
		
		List<StockPO> list=new ArrayList<StockPO>();;
		connectHttp connection = new connectHttp();
		List<String> tempString = connection.getzhishu(start, end, fields);
		Transfer trans=new Transfer();
		int temp=0;
		for(String tem:tempString){
			String[] a=tem.split(",");
			StockPO po=new StockPO(a[0], "0.0", "0.0", a[1], a[2], a[3], a[4], a[5], a[6], "0.0", "0.0" ,"0.0","0.0");
			list.add(po);
		}
		return list;
	}
}
