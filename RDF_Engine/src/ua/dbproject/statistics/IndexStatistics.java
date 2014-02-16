package ua.dbproject.statistics;

/**
 * The class IndexStatistics represents index statistics which are required by
 * the query optimizer.
 */
public class IndexStatistics {
	
	public static String provideStatistics (String optimizer) {
		return optimizer + " -> provide statistics";
	}

}
