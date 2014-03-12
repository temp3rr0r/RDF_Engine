package ua.dbproject.indexer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

/** Serialize different dictionary objects to binary files */
public class MyBinarySerializer {
	
	/** Serialize a generic serializable object to disk */
	public static void Serialize(Object toSerialize, String filename) {
		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(toSerialize);
			out.close();
			fileOut.close();
 		} catch(IOException i) {
		     i.printStackTrace();
		 }
	}

	/** DeSerialize a HashSet<String> from disk to memory */
	public static HashSet<String> DeserializeStringHashSet(String fileName) {
        // Declare the hashtable reference.
        HashSet<String> returnObject = null;

        try {
        	FileInputStream fileIn = new FileInputStream(fileName);
        	ObjectInputStream inObject = new ObjectInputStream(fileIn);
        	returnObject = (HashSet<String>)inObject.readObject();
        	inObject.close();
        } catch (IOException i) {
        	
        } catch (ClassNotFoundException c) {
        	
        }
         
        return returnObject;
	}
}
