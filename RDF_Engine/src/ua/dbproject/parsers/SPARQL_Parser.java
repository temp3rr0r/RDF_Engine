package ua.dbproject.parsers;


/** The SPARQL_Parser takes care of parsing SPARQL queries. */
public class SPARQL_Parser {

	public String parse(String query) {
		// TODO Auto-generated method stub
		//Query asdf = new QueryFactory(
		String query1 = "SELECT ?x WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  \"John Smith\" }";
//		  //Model model = null;
//		  Query query2 = QueryFactory.create(query1) ;
//		  
//		  if(query2.isAskType())
//			  System.out.println("It is ask type");
//		  else
//			  System.out.println("It is NOT ask type");
//		  //QueryExecution qexec = QueryExecutionFactory.create(query1, model);
		
		return query + " -> parse";
	}

}
