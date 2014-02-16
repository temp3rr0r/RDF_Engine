package ua.dbproject.main;

import ua.dbproject.indexer.IndexManager;


/**
 * This main class invokes the entire indexing workflow. A file or directory with the
 * TTL files should be provided as command-line arguments.
 */
public class TTL_Indexer {

	protected static IndexManager indexManager;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		indexManager = IndexManager.getInstance();
		indexManager.createIndex(args);
	}

}
