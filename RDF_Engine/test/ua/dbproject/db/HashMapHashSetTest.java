package ua.dbproject.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashMapHashSetTest {

	@Test
	public void testHashMapHashSet() {
		HashMapHashSet asdf = new HashMapHashSet();
		asdf.add("a", "b", "c");

		asdf.add("a", "b", "3");
		asdf.printAll();
	}
	
//	@Test
//	public void testHashMapHashSet2() {
//		HashMapHashSet asdf = new HashMapHashSet();
//		asdf.add("bank", "kbc", "Gent");
//		asdf.add("bank", "kbc", "Antwerp");
//		
//		
//		asdf.printAll();
//	}

}
