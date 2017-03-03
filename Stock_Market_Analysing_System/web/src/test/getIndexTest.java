package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.getIndex;
import PO.AtrPO;
import PO.kdjPO;
import PO.macdPO;
import PO.volPO;
import junit.framework.TestCase;

public class getIndexTest extends TestCase{
	private getIndex getIndex;
	@Before
	public void setUp() throws Exception {
		getIndex = new getIndex();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetKDJ() {
		List<kdjPO> list = getIndex.getKDJ("000009");
		int size = list.size();
		Assert.assertEquals(size, 30);
	}

	@Test
	public void testGetVOL() {
		List<volPO> list = getIndex.getVOL("000009");
		int size = list.size();
		Assert.assertEquals(size, 30);
	}
	
	@Test
	public void testGetMACD() {
		List<macdPO> list = getIndex.getMACD("000009");
		int size = list.size();
		Assert.assertEquals(size, 30);
	}
	
	@Test
	public void testGetATR() {
		List<AtrPO> list = getIndex.getATR("000009");
		int size = list.size();
		Assert.assertEquals(size, 30);
	}
}
