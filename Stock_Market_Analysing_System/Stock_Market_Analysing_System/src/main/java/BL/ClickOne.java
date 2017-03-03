package BL;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dataApi.getNowData;
import dataApi.getOneData;
import dataService.getOneDataSer;
import PO.NowDapanPo;
import PO.NowStockPo;
import PO.StockPO;
import blService.ClickOneBlSer;

public class ClickOne implements ClickOneBlSer{
	getOneData get;
	public ClickOne(){
		get=new getOneData();
	}
	public List<StockPO> clickOne(Date start, Date end, String code) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String start1=dateFormat.format(start);
		String end1=dateFormat.format(end);
		Date start2=new Date(start.getTime()-(long)10 * 24 * 60 * 60 * 1000);
		String start3=dateFormat.format(start2);
		List<StockPO> list1=get.getOne(start1, end1, code);
		int size1=list1.size();
		List<StockPO> list2=get.getOne(start3, end1, code);
		int size2=list2.size();
		int e=size2-size1-1;
		for(int i=0;i<size1;i++){
			StockPO po=list1.get(i);
			StockPO po1=list2.get(i+e);
			String str1=po.getEndPrice();
			String str2=po1.getEndPrice();
			double d1=Double.parseDouble(str1);
			double d2=Double.parseDouble(str2);
//			String s=new DecimalFormat("#.######").format(d1/d2-1);
			double d3=(d1/d2-1)*100;
			String s=new DecimalFormat("#.####").format(d3);
			list1.get(i).setZhangfu(s);
		}
		return list1;
	}

	/* (non-Javadoc)
	 * @see blService.ClickOneBlSer#clickDaPan(java.util.Date, java.util.Date)
	 */
	public List<StockPO> clickDaPan(Date start, Date end) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String start1=dateFormat.format(start);
		String end1=dateFormat.format(end);
		Date start2=new Date(start.getTime()-(long)10 * 24 * 60 * 60 * 1000);
		String start3=dateFormat.format(start2);
		List<StockPO> list1=get.getDaPan(start1, end1);
		int size1=list1.size();
		List<StockPO> list2=get.getDaPan(start3, end1);
		int size2=list2.size();
		int e=size2-size1-1;
		for(int i=0;i<size1;i++){
			StockPO po=list1.get(i);
			StockPO po1=list2.get(i+e);
			String str1=po.getEndPrice();
			String str2=po1.getEndPrice();
			double d1=Double.parseDouble(str1);
			double d2=Double.parseDouble(str2);
			double d3=(d1/d2-1)*100;
			String s=new DecimalFormat("#.####").format(d3);
			list1.get(i).setZhangfu(s);
		}
		return list1;
	}
	public NowStockPo showNowStock(String code) throws Exception{
		getNowData getNowData=new getNowData();
		return getNowData.getResult(code) ;	
	}
	public ArrayList<NowDapanPo> showNowDapan() throws Exception{
		getNowData getNowData=new getNowData();
		ArrayList<NowDapanPo> arrayList=new ArrayList<>();
		arrayList.add(getNowData.getShangzhengResult());
		arrayList.add(getNowData.getShenzhengResult());
		return arrayList;
	}

}
