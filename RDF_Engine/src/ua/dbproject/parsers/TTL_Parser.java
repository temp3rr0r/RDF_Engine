package ua.dbproject.parsers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import ua.dbproject.indexer.DictUtility;
import ua.dbproject.indexer.TripleHashMap;

/** The TTL_Parser takes care of parsing TTL files that hold the RDF input data. */
public class TTL_Parser {
	
	public String readTTL(String filePath, TripleHashMap dict) throws FileNotFoundException {
	    BufferedReader br = new BufferedReader(new FileReader(filePath));
	    StringBuilder sb = null;
	    String everything = null;
	    try {
	        sb = new StringBuilder();
	        String line = br.readLine();

	        //TODO: maybe add a method parameter to set the number of lines to read
	        while (line != null) {
	            sb.append(line);
	            //sb.append(System.lineSeparator());
	            line = br.readLine();
	            
	            // if matches # or @, continue
	            String[] temp = line.split(".");
	            temp = temp[0].split(" ");
	            temp = temp[0].split("\t");	            
	            
	            if(temp.length > 0) {
	            	
	            	if (dict.getCount() > 4000)
	            		break;
	            	
	            	if (!(DictUtility.isTTLPrefix(temp[0])))
	            		dict.add(temp[0].replace("<", "").replace(">",  ""), temp[1].replace("<", "").replace(">", ""), temp[2].replace("<", "").replace(">",  ""));
                		  
	            }
	        }
	        everything = sb.toString();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    return everything;
	}

}
