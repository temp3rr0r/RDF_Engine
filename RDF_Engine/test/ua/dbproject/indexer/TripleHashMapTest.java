package ua.dbproject.indexer;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import org.junit.Test;

public class TripleHashMapTest {

	@Test
	public void testClassCreation() {
		TripleHashMap testHashMap = new TripleHashMap();		
	}
	
	@Test
	public void testTTLread() throws IOException {
		TripleHashMap testHashMap = new TripleHashMap();	
		FileHandler.readTTL("C:\\Users\\madks_000\\Downloads\\yagoFacts.ttl", testHashMap);
	}
	
	@Test
	public void testWriteToDisk() throws IOException {
		TripleHashMap testHashMap = new TripleHashMap();	
		FileHandler.readTTL("C:\\Users\\madks_000\\Downloads\\yagoFacts.ttl", testHashMap);
		testHashMap.generateDictionaries();
		testHashMap.flushMainDict();
		testHashMap.predicateObjectsToDisk("C\\ft\\");
		
		HashSet<String> hashSetResult =  testHashMap.getAllSubjectsFromDisk("<isCitizenOf>", "<United_States>");
		
		
	}

}
