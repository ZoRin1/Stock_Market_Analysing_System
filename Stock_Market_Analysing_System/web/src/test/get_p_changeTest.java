package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.getStockInfo;
import BL.get_p_change;
import PO.StockListPO;
import PO.pchangePO;
import junit.framework.TestCase;

public class get_p_changeTest extends TestCase{
	
	private get_p_change get_p_change;
	@Before
	public void setUp() throws Exception {
		get_p_change = new get_p_change();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetPchange() {
		List<pchangePO> list = get_p_change.getpchange("600022");
		List<pchangePO> list2 = get_p_change.getpchange("900901");
		List<pchangePO> list3 = get_p_change.getpchange("000009");
		List<pchangePO> list4 = get_p_change.getpchange("200011");
		List<pchangePO> list5 = get_p_change.getpchange("490906");
		Assert.assertTrue(list.get(2).getMarket()<=1);
		Assert.assertTrue(list2.get(2).getMarket()<=1);
		Assert.assertTrue(list3.get(2).getMarket()<=1);
		Assert.assertTrue(list4.get(2).getMarket()<=1);
		Assert.assertNull(list5);
	}

}
