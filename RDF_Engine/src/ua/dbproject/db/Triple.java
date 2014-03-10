package ua.dbproject.db;

/** Data class/container, used only to store in RDF triplets within a compact form (like Tuple but for 3 string items
 * Recommended to store the Triplet<String, String, String> object inside an ArrayList*/
public class Triple {
	
	private final String strSubject;
	private final String strPredicate;
	private final String strObject;
	
	/** Default constructor for a Triplet<String, String, String> */
	public Triple(String inSubject, String inPredicate, String inObject) {
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
