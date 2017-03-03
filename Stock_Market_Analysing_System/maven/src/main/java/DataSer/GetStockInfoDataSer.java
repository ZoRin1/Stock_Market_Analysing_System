/**
 * 
 */
package DataSer;

import java.util.List;

import PO.StockListPO;
import PO.StockOnePO;

public interface GetStockInfoDataSer {
	/**
	 * 获得股市下实时股票信息
	 * @param code 为市场代号，sha为沪市A股，shb为沪市b股，sza为深市a股，szb为深市b股
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<StockListPO> getStockList(String code);	
	/**
	 * 获得单支股票历史信息
	 * @param code 为股票代号，如 000005
	 * @param start	开始时间	
	 * @param end	结束时间
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<StockOnePO> getStockOne(String code,String start,String end);
	/**
	 * 获得单支股票最新信息，也就是股票列表中该股票对应的po
	 * @param code 为股票代号，如 000005
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public StockListPO getNewStockList(String code);
}
