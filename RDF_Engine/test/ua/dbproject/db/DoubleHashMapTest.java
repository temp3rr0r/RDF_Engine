package ua.dbproject.db;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class DoubleHashMapTest {

	@Test
	public void testDoubleHashMap() {
		DoubleHashMap asdf = new DoubleHashMap();
		asdf.add("object", "subject", "predicate");
		asdf.add("object", "subject", "predicate2");
		asdf.add("object2", "subject", "predicate");
		
		//asdf.get
		
		asdf.printValue("object", "subject");
	}

}
