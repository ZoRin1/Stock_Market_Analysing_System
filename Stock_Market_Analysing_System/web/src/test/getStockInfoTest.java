package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.getStockInfo;
import PO.StockListPO;
import PO.StockOnePO;
import PO.tempPO;
import junit.framework.TestCase;

public class getStockInfoTest extends TestCase{
	private getStockInfo getStockInfo;
	@Before
	public void setUp() throws Exception {
		getStockInfo = new getStockInfo();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetStockList() {
		List<StockListPO> list1 = getStockInfo.getStockList("sha");
		List<StockListPO> list2 = getStockInfo.getStockList("shb");
		String name1 = list1.get(5).getName();
		String name2 = list2.get(6).getName();
		Assert.assertEquals(name1, "新疆天业");
		Assert.assertEquals(name2, "氯碱Ｂ股");
	}
	
	@Test
	public void testGetStockOne() {
		List<StockOnePO> list = getStockInfo.getStockOne("000005");
		int size = list.size();
		Assert.assertFalse(list.get(size-1).getClose()<=0);
	}
	
	@Test
	public void testGetNewStockList() {
		StockListPO po = getStockInfo.getNewStockList("000005");
		Assert.assertEquals(po.getName(), "世纪星源");
	}

	@Test
	public void testGetCode() {
		
		tempPO po = getStockInfo.getCode("000009");
		tempPO po2 = getStockInfo.getCode("保千里");
		tempPO po3 = getStockInfo.getCode("429137");
		Assert.assertEquals(po.getCode(), "000009");
		Assert.assertEquals(po2.getCode(), "600074");
		Assert.assertNull(po3);
	}
}
