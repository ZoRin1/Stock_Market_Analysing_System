package BL;

import java.util.List;

import PO.AtrPO;
import PO.ChangePO;
import PO.DeviationPO;
import PO.IndexPO;
import PO.RaisePO;
import PO.StablePO;
import PO.StockListPO;
import PO.StockOnePO;
import PO.industryPO;
import PO.kdjPO;
import PO.macdPO;
import PO.pchangePO;

public class BLTest {
	public static void main(String[] args){
//		getIndex get = new getIndex();
//		List<AtrPO> list = get.getATR("000005");
//		int size = list.size();
//		for(int i=0;i<size;i++){
//			System.out.print(list.get(i).getDate());
////			System.out.println(list.get(i).getK());
//			System.out.println(list.get(i).getAtr());
//		}
//		getIndustry get = new getIndustry();
//		List<industryPO> list = get.getIndustryList("sza");
//		for(int i=0;i<10;i++){
//			System.out.println(list.get(i).getP_change());
//		}
//		login test = new login();
		
//		test.raise("dva", "000009", "2016-05-23", 10000);
		
//		double[] result = test.getOneStock("dva");
//		System.out.println(result[0]);
//		System.out.println(result[1]);
		//test.change("士兵76", "zhibuluotuo");
//		List<IndexPO> list = test.getOne("dva", "000032");
//		int Size = list.size();
//		for(int i=0;i<Size;i++){
//			System.out.println(list.get(i).getDate());
//			System.out.println(list.get(i).getMoney());
//		}
//		getStockInfo get = new getStockInfo();
//		List<StockOnePO> list = get.getStockOne("000005");
//		int size = list.size();
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).getDate());
//		}
//		List<StockListPO> list = get.getStockList("sha");
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.get(i).getName());
//		}		
		
//		predict predict = new predict();
//	
//		List<DeviationPO> list = predict.IncreaseMore("sha");
//		
//		for(int i=0;i<10;i++){
//			DeviationPO po1 = list.get(i);
//			String code = po1.getCode();
//			System.out.println(code);
//			System.out.println(po1.getDeviation());
//			String string = predict.predict(code);
//			System.out.println(string);
//		}
		
//		get_p_change get_p_change = new get_p_change();
//		List<pchangePO> list = get_p_change.getpchange("000005");
//		int size = list.size();
//		System.out.println(size);
//		for(int i=0;i<size;i++){
//			System.out.println(list.get(i).getDate());
//		}
		getStockInfo getStockInfo = new getStockInfo();
//		String string = test.getCode("山东钢铁");
//		System.out.println(string);
		List<StockListPO> list1 = getStockInfo.getStockList("sha");
		List<StockListPO> list2 = getStockInfo.getStockList("shb");
		String name1 = list1.get(5).getName();
		String name2 = list2.get(6).getName();
		System.out.println(name1);
		System.out.println(name2);
		
	} 
}
