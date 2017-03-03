/**
 * 
 */
package BL;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author YangHua'an
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(testGetStockList.class);
		suite.addTestSuite(testRaise.class);
		suite.addTestSuite(testSearchOne.class);
		//$JUnit-END$
		return suite;
	}

}
