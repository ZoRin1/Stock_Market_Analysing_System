package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.getMarketInfo;
import PO.MarketlistPO;
import PO.MarketonePO;
import junit.framework.TestCase;

public class getMarketInfoTest extends TestCase{
	private getMarketInfo getMarketInfo;
	@Before
	public void setUp() throws Exception {
		getMarketInfo = new getMarketInfo();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetMarketList() {
		List<MarketlistPO> list = getMarketInfo.getMarketList();
		Assert.assertEquals(list.get(0).getName(), "上证指数");
		Assert.assertEquals(list.get(1).getName(), "深证成指");
		Assert.assertEquals(list.get(2).getName(), "沪深300");
		Assert.assertEquals(list.get(3).getName(), "上证50");
		Assert.assertEquals(list.get(4).getName(), "中小板指");
		Assert.assertEquals(list.get(5).getName(), "创业板指");
	}
	
	@Test
	public void testGetMarketOne() {
		List<MarketonePO> list = getMarketInfo.getMarketone("399001");
		Assert.assertTrue(list.get(3).getOpen()>0);
	}

	@Test
	public void testGetNewMarketList() {
		MarketlistPO po = getMarketInfo.getNewMarketList("399001");
		Assert.assertEquals(po.getName(), "深证成指");
	}
}
