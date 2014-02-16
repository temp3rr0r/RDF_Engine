package ua.dbproject.main;

import ua.dbproject.processor.QueryProcessor;

/**
 * This main class invokes the entire query processing workflow. The SPARQL
 * query(s) should be provided as one or more command-line arguments.
 */
public class SPARQL_Processor {

	protected static QueryProcessor queryProcessor;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		queryProcessor = QueryProcessor.getInstance();
		
		String args2 = "asdf";
		
		queryProcessor.processQuery(args).printResults();
	}

}
