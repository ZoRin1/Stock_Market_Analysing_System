/**
 * 
 */
package blService;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import PO.ZhiBiaoPO;

/**
 * @author lenovo
 *
 */
public interface getZhibiaoBlSer {
	/**
	 * @param code为股票代码，如'sh600000'
	 * 获得单只股票在一段时间内的指标持久化对象
	 * 之所以用栈是因为pop的时候才是正常时间顺序
	 * PO个数取决于用户选择的时间长度
	 * 因为所有指数绑定为一个po的原因
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public List<ZhiBiaoPO> getZhiBiao(Date start,Date end,String code) throws Exception;
	
	
}
