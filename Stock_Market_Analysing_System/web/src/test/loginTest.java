package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.login;
import PO.IndexPO;
import PO.RaisePO;
import junit.framework.TestCase;

public class loginTest extends TestCase{
	private login login;
	@Before
	public void setUp() throws Exception {
		login = new login();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testSignUp() {
		boolean b = login.signup("士兵76", "90909090");
		boolean b1 = login.signup("士兵76", "90909090");
		boolean b2 = login.signup("111", "111");
		Assert.assertTrue(b);
		Assert.assertFalse(b1);
		Assert.assertFalse(b2);
	}

	@Test
	public void testLogin() {
		boolean b = login.signup("士兵76", "90909090");
		boolean l = login.login("士兵76", "90909090");
		boolean l2 = login.login("士兵76", "9009090");
		Assert.assertTrue(l);
		Assert.assertFalse(l2);
	}
	
	@Test
	public void testGetOwnStock() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		List<RaisePO> list = login.getOwnStock("士兵76");
		Assert.assertEquals(list.get(0).getName(), "中国宝安");
	}
	
	@Test
	public void testGetIndustry() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		double[] doubles = login.getIndustry("士兵76");
		Assert.assertEquals(doubles.length, 15);
	}
	
	@Test
	public void testAll() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		login.raise("士兵76", "000005", "2016-6-1", 3000);
		login.raise("士兵76", "600022", "2016-6-1", 3000);
		List<IndexPO> list = login.getAll("士兵76");
		Assert.assertTrue(list.get(1).getMoney()>0);
	}
	
	@Test
	public void testOne() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		login.raise("士兵76", "000005", "2016-6-1", 3000);
		login.raise("士兵76", "600022", "2016-6-1", 3000);
		List<IndexPO> list = login.getOne("士兵76", "000005");
		Assert.assertTrue(list.get(1).getMoney()>0);
	}
	
	@Test
	public void testDelete() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		login.raise("士兵76", "000005", "2016-6-1", 3000);
		login.raise("士兵76", "600022", "2016-6-1", 3000);
		login.delete("士兵76", "000005");
		List<RaisePO> list = login.getOwnStock("士兵76");
		int size = list.size();
		Assert.assertEquals(size, 2);
	}
	
	@Test
	public void testCheck() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		boolean s = login.check("士兵76", "000009");
		boolean s1 = login.check("士兵76", "000004");
		Assert.assertTrue(s);
		Assert.assertFalse(s1);
	}
	
	@Test
	public void testGetOneStock() {
		boolean b = login.signup("士兵76", "90909090");
		login.raise("士兵76", "000009", "2016-6-2", 1000);
		double[] strings = login.getOneStock("士兵76");
		Assert.assertEquals(strings.length, 2);
	}
}
