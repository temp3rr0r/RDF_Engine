package ua.dbproject.indexer;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class MyBinarySerializerTest {

	@Test
	public void testSerialize() {

	}

	@Test
	public void testDeserializeStringHashSet() {
		HashSet<String> asdf = MyBinarySerializer.DeserializeStringHashSet("Mexico");
		assertNotNull(asdf);
		
	}


}
