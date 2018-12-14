/**
 * Interface for WebPageURL and SearchTerms so HeapSort can use ArrayList with both of these objects
 */

// Author: Rabia Mohiuddin
public interface Rank extends Comparable<Rank> {
		public int getTotalScore();		// Total score for WebPageURL or occurrence for SearchTerms

		public int getIndex();		// Index for WebPageURL or occurrence for SearchTerms

		public void setRanking(int r);		// set Ranking used for URL objects

		public int getRanking();			// get Ranking used for URL objects

		default public int compareTo(Rank other) {		// Compare two rank objects by comparing their total score
			if (this.getTotalScore() > other.getTotalScore()) {		// If greater score than other rank object, return positive 1
					return 1;
			} else if (this.getTotalScore() < other.getTotalScore()) {	// If less score than other rank object, return negative 1
					return -1;
			}
			return 0;		// If equal scores, return 0
		}

		public void printAttributes();		// Print attributes function

		public String getName();			// Retrieve URL name or search term name

		public void increase(int value);		// Increase value of object (either total rank or search occurrence)
		public Rank clone();
}
