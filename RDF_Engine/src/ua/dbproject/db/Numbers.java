package ua.dbproject.db;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class Numbers {
	
	private Map mMap = null;
	
	public Numbers() {
		mMap = new HashMap();
	}
	
	public void add(String key, String value) {
		mMap.put(key, value);
	}
	
	public String getMapValue(String key) {
		return mMap.get(key).toString();
	}
	
	public void printValue(String key) {
		System.out.println(key + " : " + getMapValue(key));
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