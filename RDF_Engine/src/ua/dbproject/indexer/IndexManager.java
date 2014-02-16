package ua.dbproject.indexer;

/**
 * The abstract class IndexManager takes care of organizing the index structures
 * by invoking the TTL parser and storing the resulting RDF index in appropriate
 * files on disk (or caches the index structures in main-memory if possible).
 */
public abstract class IndexManager {

	private static IndexManager singletonInstance = null;

	private IndexManager() {
	}

	public static IndexManager getInstance() {
		// if (singletonInstance == null)
		// singletonInstance = ... instantiate with your implementation of IndexManager
		return singletonInstance;
	}

	public abstract void createIndex(String[] args);
}
