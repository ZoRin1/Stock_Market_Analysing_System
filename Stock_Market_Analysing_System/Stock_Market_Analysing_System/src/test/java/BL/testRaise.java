/**
 * 
 */
package BL;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author YangHua'an
 *
 */
public class testRaise extends TestCase {

	private Raise raise;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		raise = new Raise();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link BL.Raise#Raise()}.
	 * @throws FileNotFoundException 
	 */
	public void testRaise() throws FileNotFoundException {
		if (raise.checkExise("sh600008")) {
			raise.delete("sh600008");
		}
		raise.raise("sh600008");
		boolean result = false;
		FileReader fileReader = new FileReader("text/自选股.txt");
		Scanner scanner = new Scanner(fileReader);
		while(scanner.hasNext()){
			String s = scanner.nextLine();
			if (s.contains("sh600008")) {
				result = true;
			}
		}
		Assert.assertTrue(result);
		raise.delete("sh600008");
	}


	/**
	 * Test method for {@link BL.Raise#checkExise(java.lang.String)}.
	 * @throws FileNotFoundException 
	 */
	public void testCheckExise() throws FileNotFoundException {
		boolean result =false;
		FileReader fileReader = new FileReader("text/自选股.txt");
		Scanner scanner = new Scanner(fileReader);
		while(scanner.hasNext()){
			String s = scanner.nextLine();
			if (s.contains("sh600008")) {
				result = true;
				break;
			}
		}
		assertEquals(raise.checkExise("sh600008"), result);
	}

	/**
	 * Test method for {@link BL.Raise#delete(java.lang.String)}.
	 */
	public void testDelete() {
		boolean b = false;
		if (!raise.checkExise("sh600008")) {
			raise.raise("sh600008");
			b = true;
		}
		raise.delete("sh600008");
		assertFalse(raise.checkExise("sh600008"));
		if (b) {
			raise.delete("sh600008");
		}
	}

}
