package ua.dbproject.plan;

import ua.dbproject.operators.Operator;
import ua.dbproject.plan.QueryPlan.QueryPlanNode;

public class MyQueryPlanNode extends QueryPlanNode {

	public MyQueryPlanNode() {
		this.isUnaryOperator = false;
		this.leftOperator = new Operator();
		this.rightOperator = new Operator();
	}
}
