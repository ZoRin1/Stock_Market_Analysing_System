package BLSer;

import java.util.List;

import PO.ChangePO;
import PO.DeviationPO;
import PO.StablePO;
import PO.StockOnePO;

public interface predictBlSer {
	/**
	 * 获得对某只股票的预测信息
	 * @param code 股票代号
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public String predict(String code);
	
	/**
	 * 将市场中所有股票对应PO按偏离度排序后返回
	 * @param code 市场代号，为sha,shb,sza,szb
	 */
	public List<DeviationPO> IncreaseMore(String code);
	
	/**
	 * 将市场中所有股票对应PO按增长概率排序后返回
	 * @param code 市场代号，为sha,shb,sza,szb
	 */
	public List<StablePO> Increase(String code);
	
	/**
	 * 将市场中所有股票对应PO按方差排序后返回
	 * @param code 市场代号，为sha,shb,sza,szb
	 */
	public List<ChangePO> ChangeMax(String code);
	
	
	
	
}
