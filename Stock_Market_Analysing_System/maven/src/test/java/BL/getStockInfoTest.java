package BL;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import PO.StockListPO;
import junit.framework.Assert;
import junit.framework.TestCase;

public class getStockInfoTest extends TestCase {
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

}
