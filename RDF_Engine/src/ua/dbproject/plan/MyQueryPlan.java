package ua.dbproject.plan;
import ua.dbproject.operators.*;

public class MyQueryPlan extends QueryPlan {

	private String query = null;
	
	public MyQueryPlan(String query) {
		// TODO Auto-generated constructor stub
		this.query = query;
		generatePlan();
	}
	
	public void generatePlan() {
		//super.node = new QueryPlanNode();
		super.node = new MyQueryPlanNode();
		super.node.isUnaryOperator = false;
		super.node.leftOperator = new BinaryOperator();
		super.node.rightOperator = new BinaryOperator();
		
		this.query += " -> added operators";
		if (super.node.isUnaryOperator)
			this.query += " -> added Unary operator";
		else
			this.query += " -> added Binary operator";
		
		this.query += " -> generate plan";
	}
	
	public String getString() {
		return this.query;
	}

}
