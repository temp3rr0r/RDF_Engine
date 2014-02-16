package ua.dbproject.processor;

public class MyQueryResult extends QueryResult{

	String results = null;
	
	private void computeResults() {
		this.results += " -> compute results";
	}
	
	public MyQueryResult(String queryResult) {
		// TODO Auto-generated constructor stub
		this.results = queryResult;
	}

	@Override
	public void printResults() {
		
		computeResults();
		System.out.println(results + " -> print results");
	}

}
