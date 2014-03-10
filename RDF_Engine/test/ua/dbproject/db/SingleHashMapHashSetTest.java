package ua.dbproject.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class SingleHashMapHashSetTest {

	@Test
	public void testSingleHashMapHashSet() {
		SingleHashMapHashSet asdf = new SingleHashMapHashSet();
		asdf.add("object", "subject", "predicate");
		asdf.add("object", "subject", "predicate2");
		asdf.add("object2", "subject", "predicate");
		
		//asdf.get
		
		asdf.printValue("subject");
	}

}
