package DataSer;

import java.util.List;

import PO.StockListPO;

public interface GetIndustryDataSer {
	/**
	 * 获得某市场的某行业所有单只股票的最新信息PO
	 * @param market 市场代号，为sha或sza
	 * @param industry	行业名称，为金属材料，综合类，文化传播，机械仪表，生物医药，石油化工，纺织服装，造纸印刷，食品饮料，电子
	 * @return 对应的行业中的股票的最新信息po
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<StockListPO> getIndustryData(String market,String industry);
}
