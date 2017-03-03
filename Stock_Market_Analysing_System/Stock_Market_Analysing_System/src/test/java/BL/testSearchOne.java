/**
 * 
 */
package BL;

import junit.framework.TestCase;

/**
 * @author YangHua'an
 *
 */
public class testSearchOne extends TestCase {

	private SearchOne searchOne;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		searchOne = new SearchOne();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link BL.SearchOne#searchOne(java.lang.String)}.
	 */
	public void testSearchOne() {
		assertEquals("", searchOne.searchOne("sh"));
		System.out.println(searchOne.searchOne("sh600000"));
		assertEquals("sh600004,白云机场", searchOne.searchOne("sh600004"));
		assertEquals("sh600004,白云机场", searchOne.searchOne("白云机场"));
	}

}
