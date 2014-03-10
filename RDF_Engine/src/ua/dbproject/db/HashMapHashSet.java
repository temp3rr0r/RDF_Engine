package ua.dbproject.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class HashMapHashSet {
	
	private HashMap<String, HashSet<String>> mMap = null;
	//private Map<String, Map<String, String>> dMap2 = null;
	//private HashMap<String, HashMap<String, String>> dMap = null; 
	
	public HashMapHashSet() {
		mMap = new HashMap<String, HashSet<String>>();
		//dMap2 = new HashMap<st>
		//dMap = new HashMap<String, HashMap<String, String>>();		
	}
	
	public void add(String key, String key2, String value) {
		if(mMap.get(key + "|" + key2) != null)
			mMap.get(key + "|" + key2).add(value);
		else {
			HashSet<String> temphashSet = new HashSet<String>();
			temphashSet.add(value);
			mMap.put(key + "|" + key2, temphashSet);
		}
	}
	
	public HashSet<String> getMapValue(String key, String key2) {
		return mMap.get(key + key2);
	}
	
	public void printValue(String key, String key2) {
		HashSet<String> values = getMapValue(key, key2);
		if (values != null) {
			Iterator iter = values.iterator();
			 
			while (iter.hasNext()) {
				Map.Entry mEntry = (Map.Entry) iter.next();
				System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
			}
		}
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
// 
//			mMap.put("PostgreSQL", "Free Open Source Enterprise Database");
//			mMap.put("PostgreSQL", "Free Open Source Enterprise Database2");
//			mMap.put("DB2", "Enterprise Database , It's expensive");
//			mMap.put("Oracle", "Enterprise Database , It's expensive");
//			mMap.put("MySQL", "Free Open SourceDatabase");
// 
//			mMap.put("Oracle", "Enterprise Database , It's free now ! (hope)");
 
 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}