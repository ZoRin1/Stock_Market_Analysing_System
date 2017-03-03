package blService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PO.NowDapanPo;
import PO.NowStockPo;
import PO.StockPO;

public interface ClickOneBlSer {
	/**
	 * 点击单只股票获得详细历史信息
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO> clickOne (Date start,Date end,String code) throws Exception;
	
	/**
	 * 点击大盘获得详细历史信息
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<StockPO> clickDaPan (Date start, Date end) throws Exception;
	
	
	public NowStockPo showNowStock(String code) throws Exception;
	
	public ArrayList<NowDapanPo> showNowDapan() throws Exception;
}
