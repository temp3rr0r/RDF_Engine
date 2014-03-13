package ua.dbproject.indexer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** Static class that reads TTL files and indexes them to the main tripleHashMap dictionary */
public class FileHandler {
	
	/** Locate the TTL file in the "filePath" location and return its value as a String.
	 * Remarks: Reads one line at a time 
	 * @param filePath
	 * @param dict
	 * @return String
	 * @throws FileNotFoundException
	 */
	public static boolean readTTL(String filePath, TripleHashMap dict) throws FileNotFoundException {
		if(FileHandler.fileExists(filePath)) {
		    BufferedReader br = new BufferedReader(new FileReader(filePath));
		    StringBuilder sb = null;
		    try {	    	
		        sb = new StringBuilder();
		        String line = br.readLine();
	
		        while (line != null) {
		            sb.append(line);
		            line = br.readLine();
		            
		            if (dict.getCount() > 4000000)
		            	break;
		            
		            String[] temp = line.split("\t");
		            //temp = temp[0].split(" ");
		            //temp = temp[0].split(".");
		            if(temp.length > 0) {	            	
		            
		            	if (!DictUtility.isTTLPrefix(temp[0]) && (temp.length >= 3)) { // if matches # or @, continue
	            			dict.add(DictUtility.removeEqualityChars(temp[0]), DictUtility.removeEqualityChars(temp[1]),
	        					DictUtility.removeDot(DictUtility.removeEqualityChars(temp[2])));
		            	}
	                		  
		            }
		        }
		        //everything = sb.toString();
		    } catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
		        try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
		    }
		    return true;
		}
		else
			return false;
	}

	/** Locate and delete a file using the local file system */
	public void deleteFile(String fileName) {
	   	try {	   	 
    		File file = new File(fileName);
    		file.delete();
 
    	} catch(Exception e) { 
    		e.printStackTrace();
    	}
 
	}
	
	/** Check if file exists on disk */
	private static boolean fileExists(String fileName) {
		
		File f = new File(fileName);
		if(f.exists())
			return true;
		else
			return false;
	}
	
	/** Locate the file in the "filePath" location and return its value as a String.
	 * Remarks: Reads one line at a time */
	public static String readFile(String filePath) throws FileNotFoundException {
	    BufferedReader br = new BufferedReader(new FileReader(filePath));
	    StringBuilder sb = null;
	    String everything = null;
	    try {
	        sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        everything = sb.toString();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    return everything;
	}
	
	//TODO: maybe add read/write chunk of files methods
	//TODO: maybe store the output path or use system's path variables
	
	/** Write to disc the String "source" as "fileName" in the current executable's path location 
	 * @param source
	 * @param fileName
	 */
	public static void writeFile(String source, String fileName) {
		BufferedWriter writer = null;
	    try {
	    	//create a temporary file
	    	File logFile = new File(fileName);
		
	    	// This will output the full path where the file will be written to...
	    	//System.out.println(logFile.getCanonicalPath());
		
		    writer = new BufferedWriter(new FileWriter(logFile, true));
		    writer.write(source);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        // Close the writer regardless of what happens...
		        writer.close();
		    } catch (Exception e) {
		    }
		}
	}
}
