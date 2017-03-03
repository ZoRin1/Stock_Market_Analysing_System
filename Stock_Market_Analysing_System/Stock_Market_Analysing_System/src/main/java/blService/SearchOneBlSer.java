package blService;

import java.util.List;

import PO.StockPO;

public interface SearchOneBlSer {
	/**
	 * 用户在搜索框中输入股票名或股票编号
	 * 股票名需通过Transfer转化为股票编号
	 * 返回所搜索股票的历史信息或显示不存在的提示
	 * @author 熊凯奇xiong kaiqi 
	 *
	 */
	public String searchOne (String code);
}
