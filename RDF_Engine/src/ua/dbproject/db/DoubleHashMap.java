package ua.dbproject.db;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class DoubleHashMap {
	
	private HashMap mMap = null;
	//private Map<String, Map<String, String>> dMap2 = null;
	//private HashMap<String, HashMap<String, String>> dMap = null; 
	
	public DoubleHashMap() {
		mMap = new HashMap();
		//dMap2 = new HashMap<st>
		//dMap = new HashMap<String, HashMap<String, String>>();		
	}
	
	public void add(String key, String key2, String value) {
		mMap.put(key + key2, value);
	}
	
	public String getMapValue(String key) {
		return mMap.get(key).toString();
	}
	
	public void printValue(String key, String key2) {
		System.out.println(key + key2 + " : " + getMapValue(key + key2));
	}
	
	public void printAll() {
		Iterator iter = mMap.entrySet().iterator();
		 
		while (iter.hasNext()) {
			Map.Entry mEntry = (Map.Entry) iter.next();
			System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
		}
	}
	
	public void showHashMap() {
		try {
 
			mMap.put("PostgreSQL", "Free Open Source Enterprise Database");
			mMap.put("PostgreSQL", "Free Open Source Enterprise Database2");
			mMap.put("DB2", "Enterprise Database , It's expensive");
			mMap.put("Oracle", "Enterprise Database , It's expensive");
			mMap.put("MySQL", "Free Open SourceDatabase");
 
			mMap.put("Oracle", "Enterprise Database , It's free now ! (hope)");
 
 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}