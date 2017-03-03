package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BL.predict;
import PO.ChangePO;
import PO.DeviationPO;
import PO.StablePO;
import junit.framework.TestCase;

public class predictTest extends TestCase{
	private predict predict;
	@Before
	public void setUp() throws Exception {
		predict = new predict();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testpredict() {
		String string = predict.predict("000005");
		Assert.assertNotNull(string);
	}
	
	@Test
	public void testIncreaseMore() {
		List<DeviationPO> list = predict.IncreaseMore("sha");
		int size = list.size();
		Assert.assertEquals(size, 50);
	}

	@Test
	public void testIncrease() {
		List<StablePO> list = predict.Increase("sha");
		int size = list.size();
		Assert.assertEquals(size, 50);
	}
	
	@Test
	public void testChangeMax() {
		List<ChangePO> list = predict.ChangeMax("sha");
		int size = list.size();
		Assert.assertEquals(size, 50);
	}
}
