package ua.dbproject.db;

public class Tuple {
	
	private final String strSubject;
	private final String strObject;
	
	/** Default constructor for a Tuple<String, String, String> */
	public Tuple(String inSubject, String inObject) {
		this.strSubject = inSubject;
		this.strObject= inObject;
	}

	public String getSubject() {
		return strSubject;
	}

	public String getObject() {
		return strObject;
	}

}
