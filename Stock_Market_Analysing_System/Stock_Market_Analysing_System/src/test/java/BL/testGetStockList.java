/**
 * 
 */
package BL;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import PO.StockPO;
import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author YangHua'an
 *
 */
public class testGetStockList extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	private getStocksList getStocksList;
	protected void setUp() throws Exception {
		getStocksList = new getStocksList();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link BL.getStocksList#getStocksList(java.util.Date, java.lang.String)}.
	 */
	public void testGetStocksList() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 2, 9);
		Date date = calendar.getTime();
		List<StockPO> list = null;
		try {
			list = getStocksList.getStocksList(date, "sh");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StockPO po = list.get(1);
		Assert.assertEquals(po.getName(), "白云机场");
		Assert.assertEquals(po.getStartPrice(), "11.68");
		Assert.assertEquals(po.getMaxPrice(), "11.86");
		Assert.assertEquals(po.getMinPrice(), "11.55");
		Assert.assertEquals(po.getEndPrice(), "11.72");
		Assert.assertEquals(po.getLastPrice(), "11.72");
		Assert.assertEquals(po.getQuantity(), "40748");
		Assert.assertEquals(po.getChangeRate(), "0.35");
		Assert.assertEquals(po.getAllRate(), "1.46");
		Assert.assertEquals(po.getIndustry(), "交通存储");
		
	}

}
