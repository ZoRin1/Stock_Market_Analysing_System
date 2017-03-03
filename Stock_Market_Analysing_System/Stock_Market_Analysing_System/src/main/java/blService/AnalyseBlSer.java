/**
 * 
 */
package blService;

/**
 * @author lenovo
 *
 */
public interface AnalyseBlSer {
	/**
	 * @param mar为市场代号，“sz”或“sh”
	 * @return 由“行业名称,行业总成交量”+"最高成交量行业名称,市场总成交量"格式的字符串组成的长度为8的字符串数组
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public String[] getAllIn(String mar) throws Exception;
	/**
	 * @param mar为市场代号，“sz”或“sh”，in为行业名称，见“Stock_Market_Analysing_System/text/沪深行业选择说明.txt”
	 * @return 由“股票名称,股票总成交量”+"最高成交量股票名称,行业总成交量"格式的字符串组成的长度为4的字符串数组
	 * @author 熊凯奇xiong kaiqi
	 * @throws Exception 
	 *
	 */
	public String[] getOneIn(String mar,String in) throws Exception;
}
