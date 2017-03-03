/**
 * 
 */
package BL;

import java.util.Date;
import java.util.List;

import dataApi.getStocksList;
import PO.StockPO1;
import blService.AnalyseBlSer;

/**
 * @author lenovo
 *
 */
public class IndustryAnalyse implements AnalyseBlSer{

	/* (non-Javadoc)
	 * @see blService.AnalyseBlSer#getAllIn(java.lang.String)
	 */
	@Override
	public String[] getAllIn(String mar) throws Exception {
		// TODO Auto-generated method stub
		Date date =new Date();
		Date start=new Date(date.getTime() - (long)6 * 24 * 60 * 60 * 1000);
		Date end=new Date(date.getTime() + (long)1 * 24 * 60 * 60 * 1000);
		getStocksList get=new getStocksList();
		List<StockPO1> list=get.getStocksList1(start, end, mar);
		
		String[] result=new String[8];
		double[] list2=new double[7];
		if(mar.equals("sh")){
			String[] list1={"金融保险","交通存储","金属材料","社会服务","供水供电","机械仪表","商业贸易"};
			for(int i=0;i<105;i++){
				StockPO1 po=list.get(i);
				for(int n=0;n<7;n++){
					if(po.getIndustry().equals(list1[n])){
						double d=Double.parseDouble(po.getQuantity());
						list2[n]=list2[n]+d;
						break;
					}
				}
			}
			for(int m=0;m<7;m++){
				result[m]=list1[m]+","+list2[m];
			}
			double max=0;
			double sum=0;
			int m=0;
			for(int i=0;i<7;i++){
				if(max<list2[i]){
					max=list2[i];
					m=i;
				}
				sum=sum+list2[i];
			}
			result[7]=list1[m]+","+sum;
		}
		else{
			String[] list1={"综合类","房地产","社会服务","金属材料","电子","机械仪表","商业贸易"};
			for(int i=0;i<105;i++){
				StockPO1 po=list.get(i);
				for(int n=0;n<7;n++){
					if(po.getIndustry().equals(list1[n])){
						double d=Double.parseDouble(po.getQuantity());
						list2[n]=list2[n]+d;
						break;
					}
				}
			}
			for(int m=0;m<7;m++){
				result[m]=list1[m]+","+list2[m];
			}
			for(int m=0;m<7;m++){
				result[m]=list1[m]+","+list2[m];
			}
			double max=0;
			double sum=0;
			int m=0;
			for(int i=0;i<7;i++){
				if(max<list2[i]){
					max=list2[i];
					m=i;
				}
				sum=sum+list2[i];
			}
			result[7]=list1[m]+","+sum;
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see blService.AnalyseBlSer#getOneIn(java.lang.String, java.lang.String)
	 */
	@Override
	public String[] getOneIn(String mar, String in) throws Exception {
		// TODO Auto-generated method stub
		Date date =new Date();
		Date start=new Date(date.getTime() - (long)6 * 24 * 60 * 60 * 1000);
		Date end=new Date(date.getTime() + (long)1 * 24 * 60 * 60 * 1000);
		getStocksList get=new getStocksList();
		List<StockPO1> list=get.getStocksList1(start, end, mar);
		
		String[] result=new String[4];
		String[] list1=new String[3];
		double[] list2=new double[3];
		
			StockPO1 po;
			int m=0;
			double temp=0;
			for(int i=0;i<21;i++){
				po=list.get(5*i);
				if(po.getIndustry().equals(in)){
					list1[m]=po.getName();
					for(int n=0;n<5;n++){
						list2[m]+=Double.parseDouble(list.get(5*i+n).getQuantity());
					}
					m++;
				}
				if(m==3)
					break;
			}
			for(int i=0;i<3;i++){
				result[i]=list1[i]+","+list2[i];
			}
			double max=0;
			double sum=0;
			int s=0;
			for(int i=0;i<3;i++){
				if(max<list2[i]){
					max=list2[i];
					s=i;
				}
				sum=sum+list2[i];
			}
			result[3]=list1[s]+","+sum;
		return result;
	}

}
