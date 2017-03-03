package BL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import BLSer.getMarketInfoSer;
import DataImp.GetMarketInfoDataImp;
import PO.MarketlistPO;
import PO.MarketonePO;

public class getMarketInfo implements getMarketInfoSer{
	private GetMarketInfoDataImp get;
	
	public getMarketInfo() {
		super();
		// TODO Auto-generated constructor stub
		get = new GetMarketInfoDataImp();
	}

	@Override
	public List<MarketlistPO> getMarketList() {
		// TODO Auto-generated method stub
		List<MarketlistPO> list=get.getMarketList();
		return list;
	}

	@Override
	public List<MarketonePO> getMarketone(String code) {
		// TODO Auto-generated method stub
		Date now = new Date();
		Date last=new Date(now.getTime() - (long)366 * 24 * 60 * 60 * 1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String start = dateFormat.format(last);
		String end = dateFormat.format(now);
		List<MarketonePO> list=get.getMarketone(code, start, end);
		return list;
	}

	@Override
	public MarketlistPO getNewMarketList(String code) {
		// TODO Auto-generated method stub
		MarketlistPO po=get.getNewMarketList(code);
		return po;
	}

}
