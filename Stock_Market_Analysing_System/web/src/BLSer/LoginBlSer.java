package BLSer;

import java.util.List;

import PO.IndexPO;
import PO.RaisePO;

//本接口对应所有方法均为登录相关或登录状态才可以使用
public interface LoginBlSer {
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
	 * 选定自选股或者更新自选股信息，必须是cid已被认证，即已登录
	 * @param cid 为用户名
	 * @param sid 股票编号
	 * @param date 当日日期，格式为“****-**-**”
	 * @param money 初始资产
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void raise(String cid,String sid,String date,double money);
	
	/**
	 * 获得用户自选股列表
	 * @param cid 为用户名,此时必须是用户身份已经确认
	 * @return 返回所有自选股相关信息
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<RaisePO> getOwnStock(String cid);
	
	/**
	 * 获得对应股票的PO
	 * @param cid 为用户名,此时必须是用户身份已经确认
	 * @return 返回长度为2的字符串，第一个是初始总投入，后面一个是目前总资产
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public double[] getOneStock(String cid);
	
	/**
	 * 获得用户自选股各行业资产分布,金属材料,机械仪表,电子,综合类,文化传播,生物医药,石油化工,纺织服装,造纸印刷
	 * ,食品饮料,商业贸易，社会服务，采掘业，房地产，交通仓储共15个行业
	 * @param cid 为用户名,此时必须是用户身份已经确认
	 * @return 返回各行业自选股对应资产
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public double[] getIndustry(String cid);
	
	/**
	 * 获得用户总资产随时间的变化
	 * @param cid 为用户名,此时必须是用户身份已经确认
	 * @return 返回小于等于7个总资产与日期构成的po
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<IndexPO> getAll(String cid);
	
	
	/**
	 * 获得用户单个股票资产随时间的变化
	 * @param cid 为用户名,此时必须是用户身份已经确认
	 * @param sid 股票编号,此编号需要在自选股界面中点击相应自选股触发，否则可能在数据库不存在
	 * @return 返回小于等于7个对应股票资产与日期构成的po
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public List<IndexPO> getOne(String cid,String sid);
	
	/**
	 * 修改密码,必须在已登录状态下使用
	 * @param cid 为用户名
	 * @param password 密码
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void change(String cid,String password);
	
	/**
	 * 取消自选股，已登录情况下使用，如果是在个股界面而非自选股界面，需先check是否是自选股
	 * @param cid 为用户名
	 * @param sid 为股票代号
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public void delete(String cid,String sid);
	
	/**
	 * 检验是否是自选股，必须是在已经登录的情况调用，未登录直接提示请登录使用
	 * @param cid 为用户名
	 * @param sid 为股票代号
	 * @author 熊凯奇xiong kaiqi
	 *
	 */
	public boolean check (String cid,String sid);
}
