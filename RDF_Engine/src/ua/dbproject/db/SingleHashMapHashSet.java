package ua.dbproject.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
 
public class SingleHashMapHashSet {
	
	private HashMap<String, HashSet<Tuple>> mMap = null;
	//private Map<String, Map<String, String>> dMap2 = null;
	//private HashMap<String, HashMap<String, String>> dMap = null; 
	
	public SingleHashMapHashSet() {
		mMap = new HashMap<String, HashSet<Tuple>>();
		//dMap2 = new HashMap<st>
		//dMap = new HashMap<String, HashMap<String, String>>();		
	}
	
	public void add(String object, String predicate, String subject) {
		if(mMap.get(predicate) != null)
			mMap.get(predicate).add(new Tuple(subject, object));
		else {
			HashSet<Tuple> temphashSet = new HashSet<Tuple>();
			temphashSet.add(new Tuple(subject, object));
			mMap.put(predicate, temphashSet);
		}
	}
	
	public HashSet<Tuple> getMapValue(String predicate) {
		return mMap.get(predicate);
	}
	
	public void printValue(String predicate) {
		HashSet<Tuple> values = getMapValue(predicate);
		for (Tuple s: values) {
			System.out.println(predicate + s.getFirst() + s.getSecond());
		}
		
//		if (values != null) {
//			Iterator iter = values.iterator();
//			 
//			while (iter.hasNext()) {
//				Map.Entry mEntry = (Map.Entry) iter.next();
//				System.out.println(mEntry.getKey() + " : " + mEntry.getValue());
//			}
//		}
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