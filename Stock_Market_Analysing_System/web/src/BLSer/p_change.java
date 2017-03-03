package BLSer;

import java.util.List;

import PO.pchangePO;

public interface p_change {
	/**
	 * 获得大盘涨跌幅和个股涨跌幅
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<pchangePO> getpchange(String code);
}
