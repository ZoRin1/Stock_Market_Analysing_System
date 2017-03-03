package BLSer;

import java.util.List;

import PO.MarketlistPO;
import PO.MarketonePO;

public interface getMarketInfoSer {
	/**
	 * 获得实时大盘信息
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<MarketlistPO> getMarketList();
	/**
	 * 获得某支大盘历史信息
	 * @param code 指数代号，如000001，399001
	 * @param start	开始时间	
	 * @param end	结束时间
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<MarketonePO> getMarketone(String code);
	/**
	 * 获得单个市场最新信息，也就是市场列表中该大盘对应的po
	 * @param code 为大盘代号，如 000001，399001
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public MarketlistPO getNewMarketList(String code);
}
