/**
 * 
 */
package DataSer;

import java.util.List;

import PO.MarketlistPO;
import PO.MarketonePO;


public interface GetMarketInfoDataSer {
	/**
	 * 获得实时大盘信息
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<MarketlistPO> getMarketList();
	/**
	 * 获得某支大盘历史信息
	 * @param code 指数代号，如上证成指000001，深证成指399001 ，沪深300为399300， 上证50为000016，中小板指为399005，创业板指为399006
	 * @param start	开始时间	
	 * @param end	结束时间
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<MarketonePO> getMarketone(String code,String start,String end);
	/**
	 * 获得单个市场最新信息，也就是市场列表中该大盘对应的po
	 * @param code 为大盘代号，如 000001，399001
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public MarketlistPO getNewMarketList(String code);
}
