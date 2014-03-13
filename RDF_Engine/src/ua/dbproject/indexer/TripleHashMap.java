package ua.dbproject.indexer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ua.dbproject.db.*;

/** The class that holds the Dictionary logic */
public class TripleHashMap {
	
	private long Count = 0;
	private HashSet<Triple> mainDict = new HashSet<Triple>();	
	private HashMap<String, HashSet<Tuple>> predicateDict = new HashMap<String, HashSet<Tuple>>();
    private HashMap<Tuple, HashSet<String>> predicateObjectDict = new HashMap<Tuple, HashSet<String>>();

	public long getCount() {
		return Count;
	}

	public void setCount(long count) {
		Count = count;
	}
	
	public HashSet<Triple> getMainDict() {
		return mainDict;
	}
	
	 public void flushMainDict() {
         mainDict = null;
     }

     public void flushDict(HashMap<String, HashSet<Tuple>> dict) {
         dict = null;
     }

     public void flushPredicateDict() {
         flushDict(predicateDict);
     }
     
     public void setMainDict(HashSet<Triple> newMainDict) {
         mainDict = newMainDict;
     }

	public void add(String inSubject, String inPredicate, String inObject) {
        Count++;
        addMainDict(inSubject, inPredicate, inObject);
    }

    private void addMainDict(String inSubject, String inPredicate, String inObject) {
        mainDict.add(new Triple(inSubject, inPredicate, inObject));
    }
    
    private void addDictionaries(String inSubject, String inPredicate, String inObject) {

		//addTripleString(subjectDict, inSubject, inPredicate, inObject);
		//addTripleTuple(subjectObjectDict, inSubject, inObject, inPredicate);
		//addTripleTuple(objectSubjectDict, inObject, inSubject, inPredicate);
		//addTripleString(predicateDict, inPredicate, inSubject, inObject);
		//addTripleTuple(subjectPredicateDict, inSubject, inPredicate, inObject);
		//addTripleTuple(predicateSubjectDict, inPredicate, inSubject, inObject);
		//addTripleString(objectDict, inObject, inSubject, inPredicate);
		addTriple(predicateObjectDict, inPredicate, inObject, inSubject);
		//addTripleTuple(objectPredicateDict, inObject, inPredicate, inSubject);
    }   
    
    private void addTriple(HashMap<Tuple, HashSet<String>> dict, String pairFirst, String pairSecond, String pairValue) {
        // if Tuple doesn't exist in dict
        Tuple tempTuple = new Tuple(pairFirst, pairSecond);
        if (!dict.containsKey(tempTuple)) {
            // add a new hashset with new string
            //dict.Add(new HashSet<Tuple<string, string>>>(indexString, new HashSet<Tuple<string,string>>(new Tuple<string, string>(pairFirst, pairSecond))));
            //Tuple<string, string> tempTuple = new Tuple<string, string>(pairFirst, pairSecond);
            //HashSet<Tuple<string, string>> tempHashSet = new HashSet<Tuple<string, string>>();
        	HashSet<String> tempHashSet = new HashSet<String>();
        	tempHashSet.add(pairValue);        	
            dict.put(tempTuple, tempHashSet);
        } else {
            //HashSet<Tuple<string, string>> tempHashSet = dict.GetValueOrDefault(indexString);
            dict.get(tempTuple).add(pairValue);
        }

    }
    
    public boolean generateDictionaries() {
    
    	for (Triple currentTriple : mainDict)
    		addDictionaries(currentTriple.getSubject(), currentTriple.getObject(), currentTriple.getPredicate());
    	return true;

    }
    
    public HashSet<String> getAllSubjects(String inPredicate, String inObject) {
        HashSet<String> returnList = new HashSet<String>();

        Tuple tempTuple = new Tuple(inPredicate, inObject);

        if (predicateObjectDict.containsKey(tempTuple))
            returnList = predicateObjectDict.get(tempTuple);
        
        return returnList;
    }
    
    public boolean predicateObjectsToDisk(String folder){
        Set<Tuple> arrayOfAllKeys = predicateObjectDict.keySet();
		
		for(Tuple predicateObject : arrayOfAllKeys) {
			String strippedKey1 = DictUtility.stripToValidPathString(predicateObject.getFirst());
			String strippedKey2 = DictUtility.stripToValidPathString(predicateObject.getSecond());
			
		    String newPath = folder + strippedKey2;
		    File dir = new File(newPath);

		    if (!dir.exists())
		    	if(dir.mkdirs())
		    		MyBinarySerializer.Serialize(getAllSubjects(predicateObject.getFirst(), predicateObject.getSecond()),
		    				newPath + "\\" + strippedKey1);
		}
    	return true;
    }



    public HashSet<String> getAllSubjectsFromDisk(String strPredicate, String strObject)
    {
        //HashSet<Tuple<string, string>> getAllPredicatePairs(string predicateString);
    	String strippedPredicate = DictUtility.stripToValidPathString(strPredicate);
        
        String strippedObject = DictUtility.stripToValidPathString(strObject);
        String newPath = "C:\\ft" + "\\" + strippedPredicate + "\\";
        
        HashSet<String> dictSubjects = MyBinarySerializer.DeserializeStringHashSet(newPath + strippedObject);

        return dictSubjects;
    }
    
	/*
        public void predicatesToDisk(string folder)
        {
            toDisk(predicateDict, folder);
        }

        public void predicateObjectsToDisk(string folder)
        {
            //toDisk(predicateDict, folder);
            // foreach HashSet<string> subject in predicateObject
            // new file to disk\        
            var arrayOfAllKeys = predicateObjectDict.Keys.ToArray();
            try
            {
                foreach (Tuple<string, string> predicateObject in arrayOfAllKeys)
                {
                    //MyBinarySerializer.Serialize(getAllPredicatePairs(currentKey), folder + strippedKey);
                    string strippedKey1 = predicateObject.Item1.Replace("<", string.Empty).Replace(">", string.Empty);
                    strippedKey1 = DictUtility.getValidPathString(strippedKey1);
                    string strippedKey2 = predicateObject.Item2.Replace("<", string.Empty).Replace(">", string.Empty);
                    strippedKey2 = DictUtility.getValidPathString(strippedKey2);

                    // if dire predicate not exists, create directory
                    // add directory to the folder path
                    string newPath = folder + "\\" + strippedKey1 + "\\";
                    if (!System.IO.Directory.Exists(newPath))
                        System.IO.Directory.CreateDirectory(newPath);
                    try
                    {
                        MyBinarySerializer.Serialize(getAllSubjects(predicateObject.Item1, predicateObject.Item2), newPath + strippedKey2);
                    }
                    catch (DirectoryNotFoundException ex2)
                    { }
                }
            }
            catch (Exception ex)
            {
                
            }

        }

        private void toDisk(MyDictionary<string, HashSet<Tuple<string, string>>> dict, string folder)
        {
            //int counter = 0;
            var arrayOfAllKeys = dict.Keys.ToArray();
            foreach (string currentKey in arrayOfAllKeys)
            {
                string strippedKey = currentKey.Replace("<", string.Empty).Replace(">",string.Empty);
                MyBinarySerializer.Serialize(getAllPredicatePairs(currentKey), folder + strippedKey);
            }

        }

        public HashSet<string> getAllSubjectsFromDisk(string strPredicate, string strObject)
        {
            //HashSet<Tuple<string, string>> getAllPredicatePairs(string predicateString);
            string strippedPredicate = strPredicate.Replace("<", string.Empty).Replace(">", string.Empty);
            strippedPredicate = DictUtility.getValidPathString(strippedPredicate);
            string strippedObject = strObject.Replace("<", string.Empty).Replace(">", string.Empty);
            strippedObject = DictUtility.getValidPathString(strippedObject);
            string newPath = "C:\\ft" + "\\" + strippedPredicate + "\\";
            HashSet<string> dictSubjects = MyBinarySerializer.DeserializeStringHashSet(newPath + strippedObject);

            return dictSubjects;
        }

        public HashSet<Tuple<string, string>> getAllPredicatePairsFromDisk(string predicate)
        {
            //HashSet<Tuple<string, string>> getAllPredicatePairs(string predicateString);
            string strippedPredicate = predicate.Replace("<", string.Empty).Replace(">",string.Empty);
            HashSet<Tuple<string, string>> dictPredicatePairs = MyBinarySerializer.DeserializeTupleHashSet(string.Format(@"C:\ft\{0}", strippedPredicate));

            return dictPredicatePairs;
        }

        public List<Tuple<string, string>> getAllPredicatePairsFromDiskList(string predicate)
        {
            //HashSet<Tuple<string, string>> getAllPredicatePairs(string predicateString);
            string strippedPredicate = predicate.Replace("<", string.Empty).Replace(">", string.Empty);
            List<Tuple<string, string>> dictPredicatePairs = MyBinarySerializer.DeserializeTupleList(string.Format(@"C:\ft\{0}", strippedPredicate));

            return dictPredicatePairs;
        }

        public bool containsPredicate(string key)
        {
            return predicateDict.ContainsKey(key);
        }

        public bool containsPredicateValuesWith(string inObject, string inSubject)
        {
            return predicateDict.ContainsValue(new HashSet<Tuple<string, string>> { new Tuple<string, string>(inObject, inSubject) });
        }

        public bool containsObjectValuesWith(string inPredicate, string inSubject)
        {
            return objectDict.ContainsValue(new HashSet<Tuple<string, string>> { new Tuple<string, string>(inPredicate, inSubject) });
        }

        public bool containsSubjectValuesWith(string inObject, string inPredicate)
        {
            return subjectDict.ContainsValue(new HashSet<Tuple<string, string>> { new Tuple<string, string>(inObject, inPredicate) });
        }

        public bool containsSubject(string key)
        {
            return subjectDict.ContainsKey(key);
        }

        public bool containsObject(string key)
        {
            return objectDict.ContainsKey(key);
        }

        public HashSet<Tuple<string, string>> getAllPredicatePairs(string predicateString)
        {
            if (predicateDict.ContainsKey(predicateString))
                return predicateDict.GetValueOrDefault(predicateString);
            else
                return new HashSet<Tuple<string, string>>();
        }

        public HashSet<Tuple<string, string>> getAllObjectPairs(string objectString)
        {
            if (objectDict.ContainsKey(objectString))
                return objectDict.GetValueOrDefault(objectString);
            else
                return new HashSet<Tuple<string, string>>();
        }

        public HashSet<Tuple<string, string>> getAllSubjectPairs(string subjectString)
        {
            if (subjectDict.ContainsKey(subjectString))
                return subjectDict.GetValueOrDefault(subjectString);
            else
                return new HashSet<Tuple<string, string>>();
        }

        public HashSet<string> getAllSubjects(string inPredicate, string inObject)
        {
            HashSet<string> returnList = new HashSet<string>();

            Tuple<string, string> tempTuple = new Tuple<string, string>(inPredicate, inObject);

            if (predicateObjectDict.ContainsKey(tempTuple))
                returnList = predicateObjectDict.GetValueOrDefault(tempTuple);

            return returnList;
        }

        public HashSet<string> getAllPredicates(string inSubject, string inObject)
        {
            HashSet<string> returnList = new HashSet<string>();

            Tuple<string, string> tempTuple = new Tuple<string, string>(inSubject, inObject);

            if (subjectObjectDict.ContainsKey(tempTuple))
                returnList =  subjectObjectDict.GetValueOrDefault(tempTuple);

            return returnList;
        }

        public void generateDictionaries()
        {
            int threadCount = 6;
            int jobSize = mainDict.Count / threadCount;

            System.Diagnostics.Stopwatch populateDicts = new System.Diagnostics.Stopwatch();

            // Clean up dicts
            //subjectDict.Clear();
            //predicateDict.Clear();
            //objectDict.Clear();

            // Start Timer
            populateDicts.Reset();
            populateDicts.Start();

            // 3 threads
            Parallel.For(1, threadCount, i =>
            {
                foreach (Tuple<string, string, string> currentTuple in mainDict)
                    addDictonaries(currentTuple.Item1, currentTuple.Item2, currentTuple.Item3, i);
            });

            // Stop Timer
            populateDicts.Stop();
            System.TimeSpan time = populateDicts.Elapsed;
        }

        private void addDictonaries(string inSubject, string inPredicate, string inObject, int id)
        {
            switch(id)
            {
                case 1:
                    //addTripleString(subjectDict, inSubject, inPredicate, inObject);
                    //addTripleTuple(subjectObjectDict, inSubject, inObject, inPredicate);
                    //break;
                //case 4:
                    //addTripleTuple(objectSubjectDict, inObject, inSubject, inPredicate);
                    break;
                case 2:
                    //addTripleString(predicateDict, inPredicate, inSubject, inObject);
                    //addTripleTuple(subjectPredicateDict, inSubject, inPredicate, inObject);
                    //break;
                //case 5:
                    //addTripleTuple(predicateSubjectDict, inPredicate, inSubject, inObject);
                    break;
                case 3:
                    //addTripleString(objectDict, inObject, inSubject, inPredicate);
                    addTripleTuple(predicateObjectDict, inPredicate, inObject, inSubject);
                    //break;
                //case 6:
                   //addTripleTuple(objectPredicateDict, inObject, inPredicate, inSubject);
            break;
            }
        }

        private void addDictonaries(string inSubject, string inPredicate, string inObject)
        {
            addTripleString(subjectDict, inSubject, inPredicate, inObject);
            addTripleString(predicateDict, inPredicate, inSubject, inObject);
            addTripleString(objectDict, inObject, inSubject, inPredicate);

            addTripleTuple(subjectObjectDict, inSubject, inObject, inPredicate);
            //addTripleTuple(objectSubjectDict, inObject, inSubject, inPredicate);
            addTripleTuple(subjectPredicateDict, inSubject, inPredicate, inObject);
            //addTripleTuple(predicateSubjectDict, inPredicate, inSubject, inObject);
            addTripleTuple(predicateObjectDict, inPredicate, inObject, inSubject);
            //addTripleTuple(objectPredicateDict, inObject, inPredicate, inSubject);
        }

        public void add(string inSubject, string inPredicate, string inObject)
        {
            Count++;
            addMainDict(inSubject, inPredicate, inObject);
        }

        private void addMainDict(string inSubject, string inPredicate, string inObject)
        {
            mainDict.Add(new Tuple<string, string, string>(inSubject, inPredicate, inObject));
        }

        private void addTripleTuple(MyDictionary<Tuple<string, string>, HashSet<string>> dict, string pairFirst, string pairSecond, string pairValue)
        {
            // if Tuple doesn't exist in dict
            Tuple<string, string> tempTuple = new Tuple<string, string>(pairFirst, pairSecond);
            if (!dict.ContainsKey(tempTuple))
            {
                // add a new hashset with new string
                //dict.Add(new HashSet<Tuple<string, string>>>(indexString, new HashSet<Tuple<string,string>>(new Tuple<string, string>(pairFirst, pairSecond))));
                //Tuple<string, string> tempTuple = new Tuple<string, string>(pairFirst, pairSecond);
                //HashSet<Tuple<string, string>> tempHashSet = new HashSet<Tuple<string, string>>();
                dict.Add(tempTuple, new HashSet<string> { pairValue });
            }
            else
            {
                //HashSet<Tuple<string, string>> tempHashSet = dict.GetValueOrDefault(indexString);
                dict.GetValueOrDefault(tempTuple).Add(pairValue);
            }

            // if indexString exists in dict
            // if pairFirst doesn't exist in HashSet
            // if pairFirst exists in HashSet
        }

        private void addTripleString(MyDictionary<string, HashSet<Tuple<string, string>>> dict, string indexString, string pairFirst, string pairSecond)
        {
            // if indexString doesn't exist in dict                
            if (!dict.ContainsKey(indexString))
            {
                // add a new hashset with new Tuples
                dict.Add(indexString, new HashSet<Tuple<string, string>> { new Tuple<string, string>(pairFirst, pairSecond) });
            }
            else
            {
                dict.GetValueOrDefault(indexString).Add(new Tuple<string, string>(pairFirst, pairSecond));
            }

            // if indexString exists in dict
                // if pairFirst doesn't exist in HashSet
                // if pairFirst exists in HashSet
        }
    }
	 * */

}
