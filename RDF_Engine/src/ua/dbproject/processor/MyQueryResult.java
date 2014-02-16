package ua.dbproject.processor;

public class MyQueryResult extends QueryResult{

	String results = null;
	
	private void computeResults() {
		this.results += " computed";
	}
	
	public MyQueryResult(String queryResult) {
		// TODO Auto-generated constructor stub
		this.results = queryResult;
	}

	@Override
	public void printResults() {
		
		computeResults();
		System.out.println(results);
	}

}
