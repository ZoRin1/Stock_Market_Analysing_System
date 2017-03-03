/**
 * 
 */
package dataApi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import PO.StockPO;
import PO.StockPO1;
import dataService.GetStocksListDataSer;

/**
 * @author lenovo
 *
 */
public class getStocksList implements GetStocksListDataSer{

	/* (non-Javadoc)
	 * @see dataService.GetStocksListDataSer#getStocksList(java.util.Date, java.util.Date, java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public List<StockPO> getStocksList(Date start, Date end, String type) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader reader=null;
		List result=new ArrayList<StockPO>();
		setFields fields = new setFields();
		
		fields.setFields(fields.getOpen());
		fields.setFields(fields.getHigh());
		fields.setFields(fields.getLow());
		fields.setFields(fields.getClose());
		fields.setFields(fields.getAdj_price());
		fields.setFields(fields.getVolume());
		fields.setFields(fields.getTurnover());
		fields.setFields(fields.getPb());
		
		if(type.equals("sh")){
			try {
				reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/沪市.txt"),"UTF-8"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type.equals("sz")){
			try {
				reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/深市.txt"),"UTF-8"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/自选股.txt"),"UTF-8"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		connectHttp connection = new connectHttp();
		exchangeJson exJson = new exchangeJson();
		String tempString="";
		StockPO po=null;
		try {
			while((tempString = reader.readLine()) != null){
				String[] s=tempString.split(" ");
				if(s.length<=1){
					break;
				}
				String str="http://121.41.106.89:8010/api/stock/"+type+s[1];
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String start1=dateFormat.format(start);
				String end1=dateFormat.format(end);
				List<String> tempString1 = connection.getQuantInf(str, start1, 
						end1, fields);
				if(tempString1.size()==0){
					po=new StockPO(start1, type+s[1], s[0], "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0",s[2],"0.0");
					result.add(po);
					continue;
				}
				String[] a=(tempString1.get(0)).split(",");
				po=new StockPO(a[0], type+s[1], s[0], a[1], a[2], a[3], a[4], a[5]
						, a[6], a[7], a[8],s[2],"0.0");
				result.add(po);
			}
			
		}finally{
			try {
                reader.close();
            } catch (IOException e1) {
            }
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see dataService.GetStocksListDataSer#getStocksList1(java.util.Date, java.lang.String)
	 */
	@Override
	public List<StockPO1> getStocksList1(Date start,Date end, String type) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader reader=null;
		List result=new ArrayList<StockPO1>();
		setFields fields = new setFields();
		fields.setFields(fields.getVolume());
		if(type.equals("sh")){
			try {
				reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/沪市.txt"),"UTF-8"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(type.equals("sz")){
			try {
				reader=new BufferedReader(new InputStreamReader(new FileInputStream("text/深市.txt"),"UTF-8"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		connectHttp connection = new connectHttp();
		exchangeJson exJson = new exchangeJson();
		String tempString="";
		StockPO1 po=null;
		try {
			while((tempString = reader.readLine()) != null){
				String[] s=tempString.split(" ");
				if(s.length<=1){
					break;
				}
				String str="http://121.41.106.89:8010/api/stock/"+type+s[1];
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String start1=dateFormat.format(start);
				String end1=dateFormat.format(end);
				List<String> tempString1 = connection.getQuantInf(str, start1, 
						end1, fields);
//				if(tempString1.size()==0){
//					po=new StockPO(start1, type+s[1], s[0], "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", "0.0", s[2]);
//					result.add(po);
//					continue;
//				}
				int length=tempString1.size();
				for(int i=0;i<length;i++){
					String[] list3=tempString1.get(i).split(",");
					po=new StockPO1(s[0], list3[1], s[2]);
					result.add(po);
				}
				for(int n=0;n<5-length;n++){
					po=new StockPO1(s[0], "0.0", s[2]);
					result.add(po);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
                reader.close();
            } catch (IOException e1) {
            }
		}
		return result;
	}
	
}
