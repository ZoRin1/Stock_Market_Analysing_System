package BL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataApi.Transfer;
import dataApi.getOneData;
import dataApi.getStocksList;
import PO.StockPO;
import blService.SearchOneBlSer;

public class SearchOne implements SearchOneBlSer{

	public String searchOne(String code) {
		// TODO Auto-generated method stub
//		List<StockPO> result=new ArrayList<StockPO>();
		String result="";
//		Date now = new Date();
//		Date start=new Date(now.getTime() - (long)1 * 24 * 60 * 60 * 1000);
//		Date end=new Date(now.getTime() + (long)1 * 24 * 60 * 60 * 1000);
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String start1=dateFormat.format(start);
//		String end1=dateFormat.format(end);
		Transfer trans=new Transfer();
		if(code.length()<=3){
			result=trans.transfer1(code);
			if(result.equals(""))
				return result;
			result=result+","+code;
			return result;
		}
		String str=code.substring(0, 2);
		if(str.equals("sz")||str.equals("sh")){
			result=trans.transfer2(code);
			if(result.equals(""))
				return result;
			result=code+","+result;
		}
		else{
			result=trans.transfer1(code);
			if(result.equals(""))
				return result;
			result=result+","+code;
		}
//		if(str.equals("sz")||str.equals("sh")){
//			result=get.getOne(start1, end1, code);
//		}
//		else{
//			String code1=trans.transfer1(code);
//			result=get.getOne(start1, end1, code1);
//		}
		return result;
	}

}
