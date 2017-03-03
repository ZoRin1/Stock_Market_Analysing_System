package BL;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sun.glass.ui.EventLoop.State;

import PO.StockPO;
import blService.getStocksListBlSer;

public class getStocksList implements getStocksListBlSer{

	public List<StockPO> getStocksList(Date date,String type) throws Exception {
		// TODO Auto-generated method stub
		//沪市对应type为“sh”,深市对应为“sz”,自选股对应“”
		Date end=new Date(date.getTime() + (long)1 * 24 * 60 * 60 * 1000);
		dataApi.getStocksList list=new dataApi.getStocksList();
		return list.getStocksList(date, end, type);
	}
}
