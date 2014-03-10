package ua.dbproject.indexer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	/** Locate the file in the "filePath" location and return its value as a String.
	 * Remarks: Reads one line at a time */
	public String readFile(String filePath, TripleHashMap dict) throws FileNotFoundException {
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
	            	if ((!(temp[0].equals("@base") || temp[0].equals("@prefix") || temp[0].equals("#@") || temp[0].equals("#"))) && (temp.length >= 3))
	            			dict.add(temp[0].replace("<", "").replace(">",  ""), temp[1].replace("<", "").replace(">", ""), temp[2].replace("<", "").replace(">",  ""));
                		  
	            }
	            
//              // if matches # or @, continue
//
//              string[] tempList = line.Split('.');
//              tempList = tempList[0].Split(' '); // need to check if this would be only at the end...
//              tempList = tempList[0].Split('\t');
//              if (tempList.Length > 0)
//              {
//                  //if (dict.Count > 400000)
//                  //    break;
//
//                  if ((!(tempList[0].Equals("@base") || tempList[0].Equals("@prefix") || tempList[0].Equals("#@") || tempList[0].Equals("#"))) && (tempList.Length >= 3))
//                      dict.add(tempList[0].Replace("<", string.Empty).Replace(">", string.Empty), tempList[1].Replace("<", string.Empty).Replace(">", string.Empty), tempList[2].Replace("<", string.Empty).Replace(">", string.Empty));
//
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
//	  public void readTTL(String fullFilePath, TripleHashMap dict) throws FileNotFoundException {
//          try
//          {
//              using (TextReader rdr = new StreamReader(fullFilePath, Encoding.UTF8, true, 65536 * 10))
//              {
//                  String line;
//
//                  while ((line = rdr.ReadLine()) != null)
//                  {
//                      // if matches # or @, continue
//
//                      string[] tempList = line.Split('.');
//                      tempList = tempList[0].Split(' '); // need to check if this would be only at the end...
//                      tempList = tempList[0].Split('\t');
//                      if (tempList.Length > 0)
//                      {
//                          //if (dict.Count > 400000)
//                          //    break;
//
//                          if ((!(tempList[0].Equals("@base") || tempList[0].Equals("@prefix") || tempList[0].Equals("#@") || tempList[0].Equals("#"))) && (tempList.Length >= 3))
//                              dict.add(tempList[0].Replace("<", string.Empty).Replace(">", string.Empty), tempList[1].Replace("<", string.Empty).Replace(">", string.Empty), tempList[2].Replace("<", string.Empty).Replace(">", string.Empty));
//
//                      }
//
//                  }
//              }
//          }
//          catch (Exception ex)
//          {
//             
//          }
//      }
	/** Locate and delete a file using the local file system */
	public void deleteFile(String fileName) {
	   	try {	   	 
    		File file = new File(fileName);
    		file.delete();
 
    	} catch(Exception e) { 
    		e.printStackTrace();
    	}
 
	}
	
	//TODO: maybe add read/write chunk of files methods
	//TODO: maybe store the output path or use system's path variables
	
	/** Write to disc the String "source" as "fileName" in the current executable's path location */	
	public void writeFile(String source, String fileName) {
		BufferedWriter writer = null;
	    try {
	    	//create a temporary file
	    	//String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
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
