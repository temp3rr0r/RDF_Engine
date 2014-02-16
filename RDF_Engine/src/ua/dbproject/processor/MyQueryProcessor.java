package ua.dbproject.processor;

import ua.dbproject.optimizer.QueryOptimizer;

public class MyQueryProcessor extends QueryProcessor {

	private String message = null;
	
	private String processQuery(String query) {
		//QueryOptimizer myQueryOptimizer = new QueryOptimizer();
		return QueryOptimizer.optimize(query);
	}
	
	public MyQueryProcessor() {
		
	}
	
	public MyQueryProcessor(String inMessage) {
		this.message = inMessage;
	}
	
	@Override
	public QueryResult processQuery(String[] args) {
		// TODO Auto-generated method stub		
		
		return new MyQueryResult(this.processQuery(args[0]));
	}

}
