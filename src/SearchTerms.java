
public class SearchTerms implements Rank {
		private String searchTerm;		// String of search term
		private int numTimesSearched;		// Occurrences search term has been searched
		private int ranking;		// Dummy variable - equivalent to numTimesSearched

		/**
		 * Constructor with search term sets occurrence to 1
		 * 
		 * @param searchTerm
		 *              String -> What the user has entered
		 * @return none
		 */
		public SearchTerms(String searchTerm) {
			this.searchTerm = searchTerm;
			numTimesSearched = 1;
		}

		/**
		 * Get method for name of search term
		 * 
		 * @param none
		 * @return searchTerm String
		 */
		public String getName() {
			return searchTerm;
		}

		/**
		 * Get method for ranking (number of times term has been searched)
		 * 
		 * @param none
		 * @return numTimesSearched int
		 */
		public int getTotalScore() {
			return numTimesSearched;
		}

		/**
		 * Set method for numTimesSearched
		 * 
		 * @param value
		 *              int
		 * @return none
		 */
		public void increase(int value) {
			numTimesSearched += value;
		}

		/**
		 * Override equals method that says two objects are equal is their search terms are the same
		 * 
		 * @param o
		 *              Object
		 * @return boolean -> true if have same search term. false if different search term
		 */
		@Override
		public boolean equals(Object o) {
			if (o instanceof SearchTerms) {
					SearchTerms p = (SearchTerms) o;
					return this.getName().equalsIgnoreCase(p.getName());
			} else if (o instanceof String) {
					return this.getName().equalsIgnoreCase((String) o);
			}
			return false;
		}

		/**
		 * Print attributes (search term and number of occurrences)
		 * 
		 * @param none
		 * @return none
		 */
		public void printAttributes() {
			System.out.println(searchTerm + " : " + numTimesSearched + " times");
		}

		/**
		 * Retrieves private index variable
		 * 
		 * @param none
		 * @return index int -> current value of index variable
		 */
		public int getIndex() {
			return getTotalScore();
		}

		/**
		 * sets private ranking variable
		 * 
		 * @param int
		 *              r
		 * @return none
		 */
		public void setRanking(int r) {
			numTimesSearched = r;
		}

		/**
		 * Retrieves private ranking variable
		 * 
		 * @param none
		 * @return ranking int -> current value of ranking variable
		 */
		public int getRanking() {
			return ranking;
		}
		
		public Rank clone() {
			SearchTerms newobj = new SearchTerms(this.getName());
			newobj.setRanking(this.getTotalScore());;
			return newobj;
		}

}
