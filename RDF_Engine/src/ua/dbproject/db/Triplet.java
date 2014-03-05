package ua.dbproject.db;

public class Triplet {
	
	private final String strSubject;
	private final String strPredicate;
	private final String strObject;
	
	public Triplet(String inSubject, String inPredicate, String inObject) {
		this.strSubject = inSubject;
		this.strPredicate = inPredicate;
		this.strObject= inObject;
	}

	public String getSubject() {
		return strSubject;
	}

	public String getPredicate() {
		return strPredicate;
	}

	public String getObject() {
		return strObject;
	}

}
