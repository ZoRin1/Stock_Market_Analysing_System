package dataService;

import java.util.Date;
import java.util.List;

import PO.StockPO;
import PO.StockPO1;

public interface GetStocksListDataSer {
	/**
	 * 获得股票列表信息，大盘PO放在list最后
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO> getStocksList (Date start,Date end,String type) throws Exception;
	/**
	 * @param date就是需要获得的数据所在日期，与上面不一致的原因是上面一般只用一天的
	 * 简化版获得方法，仅获得股票名，行业名和成交量，用于行业分析
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO1> getStocksList1 (Date start,Date end,String type) throws Exception;
}
