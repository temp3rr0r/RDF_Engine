package ua.dbproject.parsers;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileReaderTest {

	private FileHandler testFileHandler;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testFileHandler = new FileHandler();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadFile() throws FileNotFoundException {
		testFileHandler.writeFile("asdf", "asdf.txt");
		String strRead = testFileHandler.readFile("asdf.txt");
		assertEquals("Asserting 'asdf' string on FileReader failed", strRead, "asdf");
		testFileHandler.deleteFile("asdf.txt");
	}
	
	@Test (expected=FileNotFoundException.class)
	public void testReadFileNotFound() throws FileNotFoundException {
		String strRead = testFileHandler.readFile("doesn'tExist.txt");
	}

	@Test
	public void testWriteFile() {
		testFileHandler.writeFile("blabla", "test.txt");
		testFileHandler.deleteFile("test.txt");
	}

}
