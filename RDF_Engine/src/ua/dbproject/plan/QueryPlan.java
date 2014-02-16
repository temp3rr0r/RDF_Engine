package ua.dbproject.plan;

import ua.dbproject.operators.Operator;

/**
 * The abstract class QueryPlan represents a tree of binary join or unary
 * projection operators.
 */
public abstract class QueryPlan {

	public static abstract class QueryPlanNode {

		public boolean isUnaryOperator;
		public Operator leftOperator;
		public Operator rightOperator;
	}

	public QueryPlanNode node;
}
