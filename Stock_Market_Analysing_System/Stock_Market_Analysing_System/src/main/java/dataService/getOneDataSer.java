package dataService;

import java.util.Date;
import java.util.List;

import PO.StockPO;

public interface getOneDataSer {
	/**
	 * 获得单只股票详细历史信息
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO> getOne(String start,String end,String code) throws Exception;
	/**
	 * 获得大盘详细历史信息
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO> getDaPan(String start,String end) throws Exception;
}
