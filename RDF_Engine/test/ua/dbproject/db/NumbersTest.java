package ua.dbproject.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NumbersTest {

	@Test
	public void testShowHashMap() {
		Numbers asdf = new Numbers();
		asdf.add("lala", "sleepy");
		asdf.showHashMap();
		asdf.printValue("lala");
		asdf.printAll();
		
	}

}
