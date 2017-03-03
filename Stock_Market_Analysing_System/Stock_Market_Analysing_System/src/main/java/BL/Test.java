/**
 * 
 */
package BL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PO.StockPO;
import PO.ZhiBiaoPO;

/**
 * @author lenovo
 *
 */
public class Test {
	public static void main(String[] args) throws Exception{
//		getStocksList get=new getStocksList();
//		Date now=new Date();
//		List<StockPO> list=get.getStocksList(now, "sh");
		
//		ClickOne click=new ClickOne();
//		Date now =new Date();
//		Date start=new Date(now.getTime() - (long)1 * 24 * 60 * 60 * 1000);
//		Date end=new Date(now.getTime() + (long)1 * 24 * 60 * 60 * 1000);
//		List<StockPO> list=new ArrayList<StockPO>();
//		list=click.clickOne(start, end, "sh600000");
//		System.out.println(list.get(0).getEndPrice());
		
//		SearchOne search=new SearchOne();
//		String str=search.searchOne("特力A");
//		System.out.println(str+"1");
//		List<StockPO> list=search.searchOne("sh600004");
//		System.out.println(list.get(0).getEndPrice());
//		Raise raise=new Raise();
//		raise.raise("sh600000");
//		IndustryAnalyse in=new IndustryAnalyse();
//		String[] list=in.getAllIn("sh");
//		System.out.println(list[7]);
		
//		ClickOne click=new ClickOne();
//		Date now=new Date();
//		Date start=new Date(now.getTime()-(long)20 * 24 * 60 * 60 * 1000);
//		Date end=new Date(now.getTime()-(long)1 * 24 * 60 * 60 * 1000);
//		List<StockPO> list=click.clickDaPan(start, end);
//		System.out.println(list.get(5).getLastPrice());
//		System.out.println(list.get(4).getLastPrice());
//		System.out.println(list.get(5).getZhangfu());
		
		getZhiBiao one =new getZhiBiao();
		Date now=new Date();
		Date start=new Date(now.getTime()-(long)20 * 24 * 60 * 60 * 1000);
		Date end=new Date(now.getTime()-(long)1 * 24 * 60 * 60 * 1000);
		List<ZhiBiaoPO> list=new ArrayList<ZhiBiaoPO>();
		list=one.getZhiBiao(start, end, "sh600004");
		System.out.println(list.get(5).getDEA());
		System.out.println(list.get(6).getDEA());
		System.out.println(list.get(7).getDEA());
		System.out.println(list.get(6).getDIF());
		System.out.println(list.get(7).getDIF());
	}
}
