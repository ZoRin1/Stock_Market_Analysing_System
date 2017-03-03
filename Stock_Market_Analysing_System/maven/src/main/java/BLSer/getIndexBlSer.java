package BLSer;

import java.util.Date;
import java.util.List;

import PO.AtrPO;
import PO.kdjPO;
import PO.macdPO;
import PO.volPO;

public interface getIndexBlSer {
	/**
	 * 获得指数KDJ的列表
	 * @param code为股票代码
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<kdjPO> getKDJ(String code);
	/**
	 * 获得指数VOL的列表
	 * @param code为股票代码
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<volPO> getVOL(String code);
	/**
	 * 获得指数MACD的列表
	 * @param code为股票代码
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<macdPO> getMACD(String code);
	/**
	 * 获得指数ATR的列表
	 * @param code为股票代码
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<AtrPO> getATR(String code);
}
