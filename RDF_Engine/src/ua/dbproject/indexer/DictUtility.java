package ua.dbproject.indexer;

/** Static class that helps in string conversions */
public class DictUtility {

	/** Strip all filesystem-illegal characters */
	public static String removeInvalidFsChars(String inString) {
		return inString.replaceAll("[^a-zA-Z0-9.-]", "");
	}

	/** Remove greater/less characters along with stripping filesystem naming illegal characters */
	public static String stripToValidPathString(String inString) {
		return removeEqualityChars(removeInvalidFsChars(inString));
	}
    
	public static String removeDot(String inString) {
		return inString.replace(".", "");
	}
	
	public static String removeEqualityChars(String inString) {
		return inString.replace("<", "").replace(">", "");
	}
	
	
	
	public static boolean isTTLPrefix(String inString) {
		if (inString.equals("@base") || inString.equals("@prefix") || inString.equals("#@") || inString.equals("#"))
			return true;
		else
			return false;
	}
	
}
