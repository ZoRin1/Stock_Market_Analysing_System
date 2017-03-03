package DataImp;

import java.util.List;

import PO.MarketlistPO;
import PO.MarketonePO;
import PO.StockListPO;
import PO.StockOnePO;

public class DataImpTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<MarketonePO> marketone=new GetMarketInfoDataImp().getMarketone("000001", "2016-04-01", "2016-04-08");
//		for (int i = 0; i < marketone.size(); i++) {
//			System.out.println(marketone.get(i).getHigh());
//		}
//		List<StockListPO> list=new GetStockInfoDataImp().getStockList("szb");
//		for (int i = 0; i < list.size(); i++) {
//		System.out.println(list.get(i).getIndustry());
//		}
//		StockListPO StockListPO=new GetStockInfoDataImp().getNewStockList("600022");
//		System.out.println(StockListPO.getName());
//		for (int i = 0; i < stockOne.size(); i++) {
//			System.out.println(stockOne.get(i).getTurnover());
//		}
//		MarketlistPO marketlistPO=new GetMarketInfoDataImp().getNewMarketList("000001");
//		System.out.println(marketlistPO.getTime());
//		List<StockListPO> list=new GetIndustryDataImp().getIndustryData("sha", "金属材料");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i).getName());
//		}
//		LoginDataImp loginDataImp=new LoginDataImp();
////		loginDataImp.raise("a", "600001", "2015-05-12", 500, 20.22);
////		System.out.println(loginDataImp.getOwnStock("a"));
//		loginDataImp.delete("a", "600000");
		GetMarketInfoDataImp getMarketInfoDataImp=new GetMarketInfoDataImp();
		List<MarketonePO> marketonePOs=getMarketInfoDataImp.getMarketone("000001", "2016-05-20", "2016-06-03");
		for(int i=0;i<marketonePOs.size();i++){
			System.out.println(marketonePOs.get(i).getDate());
		}
	}

}
