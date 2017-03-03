package blService;

import java.util.Date;
import java.util.List;

import PO.StockPO;

public interface getStocksListBlSer {
	/**
	 * 进入系统或选择市场获得股票列表
	 * //沪市对应type为“sh”,深市对应为“sz”,自选股对应“”
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO> getStocksList(Date date,String type) throws Exception;
}
