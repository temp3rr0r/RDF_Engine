package ua.dbproject.db;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DbControllerTest {
	
	private static DbController testDbController;
	
	@Before
	public void setUp() throws Exception {
		testDbController = new DbController();
	}
		
	@Test(timeout = 500)
	public void testConnect() throws SQLException {
		assertTrue(testDbController.connect());
	}

	@Test(timeout = 1000)
	public void testInsert() throws SQLException {
		testDbController.connect();
		assertTrue(testDbController.executeUpdate("INSERT INTO triple (subject, predicate, object) values ('1', '2', '3');"));
		testDbController.executeUpdate("DELETE FROM triple WHERE subject = '1';");
	}
	
	@Test(timeout = 1000)
	public void testDelete() throws SQLException {
		testDbController.connect();
		testDbController.executeUpdate("INSERT INTO triple (subject, predicate, object) values ('4', '5', '6');");
		assertTrue(testDbController.executeUpdate("DELETE FROM triple WHERE subject = '4';"));
	}
	
	@Test
	public void testGetTriplets() throws SQLException {
		testDbController.connect();
		testDbController.executeUpdate("DELETE FROM triple;");
		testDbController.executeUpdate("INSERT INTO triple (subject, predicate, object) values ('7', '8', '9');");
		ArrayList<Triple> testTripletList = new ArrayList<Triple>();
		testTripletList.add(new Triple("7", "8", "9"));
		
		ArrayList<Triple> testTripletList2 = testDbController.getTriplets("SELECT * FROM triple WHERE object = '9';");
		
		String tripletString1 = testTripletList.get(0).getPredicate();
		String tripletString2 = testTripletList2.get(0).getPredicate();

		assertEquals(tripletString1, tripletString2);
		testDbController.executeUpdate("DELETE FROM triple;");
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {		
		testDbController.close();
	}
	
	/*
	@Test (expected = SQLException.class)
	public void testClose() throws SQLException {
		testDbController.close();
	}

	@Test
	public void testSelectLastVal() {
		fail("Not yet implemented");
	}
	
	@Test (expected = SQLException.class)
	public void testSQLException() throws SQLException {
		//DbController testDbController2 = new DbController("in", "valid", "arg", "ume", "nts");
		//testDbController2.connect();
	}*/

}
