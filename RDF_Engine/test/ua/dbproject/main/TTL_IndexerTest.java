/**
 * 
 */
package ua.dbproject.main;

import static org.junit.Assert.*;
import ua.dbproject.indexer.IndexManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.dbproject.indexer.IndexManager;

/**
 * @author madks_000
 *
 */
public class TTL_IndexerTest {

	/**
	 * @throws java.lang.Exception
	 */
	private static IndexManager testIndexManager;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testIndexManager = IndexManager.getInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String[] testArgs = { "file.in", "file.out"};
		
		testIndexManager.createIndex(testArgs);
	}

}
