package ua.dbproject.parsers;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FileReaderTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadFile() throws FileNotFoundException {
		FileHandler testFileReader = new FileHandler();
		testFileReader.writeFile("asdf", "asdf.txt");
		String strRead = testFileReader.readFile("asdf.txt");
		assertEquals("Asserting 'asdf' string on FileReader failed", strRead, "asdf");
		testFileReader.deleteFile("asdf.txt");
	}
	
	@Test (expected=FileNotFoundException.class)
	public void testReadFileNotFound() throws FileNotFoundException {
		FileHandler testFileReader = new FileHandler();
		String strRead = testFileReader.readFile("doesn'tExist.txt");
	}

	@Test
	public void testWriteFile() {
		FileHandler testFileWriter = new FileHandler();
		testFileWriter.writeFile("blabla", "test.txt");
		testFileWriter.deleteFile("test.txt");
	}

}
