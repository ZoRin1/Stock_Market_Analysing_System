package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.getIndustry;
import PO.industryPO;
import junit.framework.TestCase;

public class getIndustryTest extends TestCase{
	private getIndustry getIndustry;
	@Before
	public void setUp() throws Exception {
		getIndustry = new getIndustry();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void test() {
		List<industryPO> list = getIndustry.getIndustryList("sha");
		int size = list.size();
		Assert.assertEquals(size, 10);
	}

}
