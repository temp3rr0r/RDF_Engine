package ua.dbproject.processor;

import ua.dbproject.optimizer.QueryOptimizer;
import ua.dbproject.parsers.SPARQL_Parser;

public class MyQueryProcessor extends QueryProcessor {

	private String message = null;
	
	private String processQuery(String query) {

		query += " -> start process";
		SPARQL_Parser myParser = new SPARQL_Parser();
		String parseResults = myParser.parse(query);
		
		return QueryOptimizer.optimize(parseResults);
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
