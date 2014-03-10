package ua.dbproject.db;

public class Tuple {
	
	private final String strFirst;
	private final String strSecond;
	
	/** Default constructor for a Tuple<String, String, String> */
	public Tuple(String first, String second) {
		this.strFirst = first;
		this.strSecond= second;
	}

	public String getFirst() {
		return strFirst;
	}

	public String getSecond() {
		return strSecond;
	}

}
