package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@SuiteClasses({})
/**
 * @author KaiQi'Xiong
 *
 */
public class AllTests {
	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(get_p_changeTest.class);
		suite.addTestSuite(getIndexTest.class);
		suite.addTestSuite(getIndustryTest.class);
		suite.addTestSuite(getMarketInfoTest.class);
		suite.addTestSuite(getStockInfoTest.class);
		suite.addTestSuite(loginTest.class);
		suite.addTestSuite(predictTest.class);
		//$JUnit-END$
		return suite;
	}
}
