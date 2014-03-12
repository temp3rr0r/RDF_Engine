package ua.dbproject.indexer;

/** Static class that helps in string conversions */
public class DictUtility {

	/** Strip all filesystem-illegal characters */
	public static String getValidPathString(String inString) {
		return inString.replaceAll("[^a-zA-Z0-9.-]", "");
	}

	/** Remove greater/less characters along with stripping filesystem naming illegal characters */
	public static String stripToValidPathString(String invalidString) {
		return getValidPathString(invalidString).replace("<", "").replace(">", "");
	}
    
}
