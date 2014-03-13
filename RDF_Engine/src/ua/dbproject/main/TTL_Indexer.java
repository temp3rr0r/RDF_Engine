package ua.dbproject.main;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import ua.dbproject.indexer.FileHandler;
import ua.dbproject.indexer.IndexManager;
import ua.dbproject.indexer.MyBinarySerializer;
import ua.dbproject.indexer.TripleHashMap;


/**
 * This main class invokes the entire indexing workflow. A file or directory with the
 * TTL files should be provided as command-line arguments.
 */
public class TTL_Indexer {

	protected static IndexManager indexManager;

	/**
	 *  
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		TripleHashMap testHashMap = new TripleHashMap();	
		FileHandler.readTTL("C:\\Users\\madks_000\\Downloads\\yagoFacts.ttl", testHashMap);
		testHashMap.generateDictionaries();
		testHashMap.flushMainDict();
		testHashMap.predicateObjectsToDisk("C:\\ft\\");
		

		HashSet<String> asdf = MyBinarySerializer.DeserializeStringHashSet("Mexico");
		
		HashSet<String> hashSetResult =  testHashMap.getAllSubjectsFromDisk("<isCitizenOf>", "<Mexico>");
	}

}
