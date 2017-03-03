package DataSer;

import java.util.List;

public interface LoginDataSer {
	/**
	 * 用户注册
	 * @param cid 为用户名
	 * @param password 密码
	 * @return 如果用户名已经存在或者密码大于12位，就返回false，否则返回true
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public boolean signup(String cid,String password);
	
	/**
	 * 用户登录
	 * @param cid 为用户名
	 * @param password 密码
	 * @return 如果数据库用户列表中不存在与之相符合的行，返回false,否则返回true,得true在cookie中设置用户身份
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public boolean login(String cid,String password);
	
	/**
	 * 获得用户自选股列表
	 * @param cid 为用户名,此时必须是用户身份已经确认
	 * @return 返回所有自选股编号
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<String> getOwnStock(String cid);
	
	/**
	 * 获得用户自选股的初始设定资产与设定日期
	 * @param cid 为用户名
	 * @param sid 股票编号
	 * @return 返回大小为3的字符串数组，第一个为格式为“****-**-**”的日期，第2，3个为转为String的money和price
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public String[] getOwnSet(String cid,String sid);
	
	/**
	 * 选定或更新自选股，必须是cid已被认证
	 * @param cid 为用户名
	 * @param sid 股票编号
	 * @param date 当日日期，格式为“****-**-**”
	 * @param money 初始资产
	 * @param price 当前股价
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void raise(String cid,String sid,String date,double money,double price);
	
	/**
	 * 修改密码
	 * @param cid 为用户名
	 * @param password 密码
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void changePass(String cid,String password);
	
	/**
	 * 取消自选股
	 * @param cid 为用户名
	 * @param sid 为股票代号
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void delete(String cid,String sid);
}
