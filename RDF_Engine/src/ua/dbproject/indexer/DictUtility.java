package ua.dbproject.indexer;

public class DictUtility {

	public static String getValidPathString(String inString) {
		return inString.replaceAll("[^a-zA-Z0-9.-]", "");
	}

}
