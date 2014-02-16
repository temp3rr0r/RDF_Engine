package ua.dbproject.optimizer;

import ua.dbproject.plan.MyQueryPlan;
import ua.dbproject.statistics.*;

/**
 * The QueryOptimizer takes a parsed SPARQL query as input and generates an
 * optimized query plan represented as a QueryPlan object as output.
 */
public class QueryOptimizer {
	
	public static String optimize(String query) {
		return new MyQueryPlan(IndexStatistics.provideStatistics(query)).getString() + " -> optimize";
	}

}
