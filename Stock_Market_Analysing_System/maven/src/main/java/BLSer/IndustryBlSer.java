package BLSer;

import java.util.List;

import PO.industryPO;

public interface IndustryBlSer {
	/**
	 * @param market 市场代号，为sha或sza,即沪深A股
	 * @return 按照行业涨幅进行排序后的从高到底的PO列表
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<industryPO> getIndustryList(String market);
}
