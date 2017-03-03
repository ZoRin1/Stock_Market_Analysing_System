package BL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BLSer.getStockInfoSer;
import DataImp.GetStockInfoDataImp;
import PO.MarketlistPO;
import PO.StockListPO;
import PO.StockOnePO;
import PO.StockPO;
import PO.tempPO;

public class getStockInfo implements getStockInfoSer{
	private GetStockInfoDataImp get;
	public getStockInfo() {
		super();
		// TODO Auto-generated constructor stub
		get = new GetStockInfoDataImp();
	}

	@Override
	public List<StockListPO> getStockList(String code) {
		// TODO Auto-generated method stub
		List<StockListPO> list=get.getStockList(code);
		return list;
	}

	@Override
	public List<StockOnePO> getStockOne(String code) {
		// TODO Auto-generated method stub
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)366 * 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		List<StockOnePO> list=get.getStockOne(code, start, end);
		return list;
	}

	@Override
	public StockListPO getNewStockList(String code) {
		// TODO Auto-generated method stub
		StockListPO po = get.getNewStockList(code);
		return po;
	}
	
	public tempPO getCode(String code){
		String c = null;
		getMarketInfo getMarketInfo = new getMarketInfo();
		List<StockPO> list = new ArrayList<>();
		List<StockListPO> list1 = this.getStockList("sha");
		List<StockListPO> list2 = this.getStockList("shb");
		List<StockListPO> list3 = this.getStockList("sza");
		List<StockListPO> list4 = this.getStockList("szb");
		int size1 = list1.size();
		int size2 = list2.size();
		int size3 = list3.size();
		int size4 = list4.size();
		int size = size1+size2+size3+size4;
		
		for(int i=0;i<size1;i++){
			StockListPO po = list1.get(i);
			StockPO po2 = new StockPO(po.getCode(), po.getName());
			list.add(po2);
		}
		for(int i=0;i<size2;i++){
			StockListPO po = list2.get(i);
			StockPO po2 = new StockPO(po.getCode(), po.getName());
			list.add(po2);
		}
		for(int i=0;i<size3;i++){
			StockListPO po = list3.get(i);
			StockPO po2 = new StockPO(po.getCode(), po.getName());
			list.add(po2);
		}
		for(int i=0;i<size4;i++){
			StockListPO po = list4.get(i);
			StockPO po2 = new StockPO(po.getCode(), po.getName());
			list.add(po2);
		}
		
		for(int i=0;i<size;i++){
			StockPO po = list.get(i);
			if(code.equals(po.getCode())){
				tempPO po2 = new tempPO(1, code);
				return po2;
			}
			else if(code.equals(po.getName())){
				tempPO po2 = new tempPO(1, po.getCode());
				return po2;
			}
		}
		List<MarketlistPO> list5 = getMarketInfo.getMarketList();
		int size5 = list5.size();
		for(int i=0;i<size5;i++){
			MarketlistPO po = list5.get(i);
			if(code.equals(po.getCode())){
				tempPO po2 = new tempPO(0, code);
				return po2;
			}
			else if(code.equals(po.getName())){
				tempPO po2 = new tempPO(0, po.getCode());
				return po2;
			}
		}
		return null;
	}
}
