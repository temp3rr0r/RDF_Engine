package ua.dbproject.processor;

/**
 * The abstract class QueryProcessor has a method processQuery that takes one or
 * more SPARQL queries as input. It returns a new instance of a QueryResult that
 * holds the results of the entire SPARQL query.
 */
public abstract class QueryProcessor {

	private static QueryProcessor singletonInstance = null;

	private QueryProcessor() {
	}

	public static QueryProcessor getInstance() {
		// if (singletonInstance == null)
		// singletonInstance = ... instantiate with your implementation of QueryProcessor
		return singletonInstance;
	}

	public abstract QueryResult processQuery(String[] args);

}
