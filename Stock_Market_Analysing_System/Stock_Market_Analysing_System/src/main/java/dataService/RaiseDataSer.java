/**
 * 
 */
package dataService;

/**
 * @author lenovo
 *
 */
public interface RaiseDataSer {
	/**
	 * 系统将股票添加至自选股，返回收藏情况
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void raise(String code);
	
	public boolean checkExist(String code);
	
	public void delete(String code);
}
