
public class WebPageURL implements Rank {
		private int frequency;		// Frequency a site is visited
		private int siteAge;			// Age of the site
		private int linkReference;	// # of times site has been referenced on other webpages
		private int moneyPaid;		// Amount of money paid to increase rank
		private String URL;			// URL string
		private int index; 			// Original index from search query
		private int ranking;

		/**
		 * Constructor method of class with no parameters that creates an empty minimum node Used to create space in the heap tree that will be replaced by new
		 * node
		 * 
		 * @param none
		 * @return none
		 */
		public WebPageURL() {
			frequency = Integer.MIN_VALUE;		// Set frequency to the smallest value possible
			siteAge = Integer.MIN_VALUE;		// Set siteAge to smallest value possible
			linkReference = Integer.MIN_VALUE;		// Set linkReference to smallest value possible
			moneyPaid = Integer.MIN_VALUE;		// Set moneyPaid to smallest value possible
		}

		/**
		 * Constructor method of class that initiliazes node with URL and assigns four ranking values
		 * 
		 * @param URL
		 *              String -> String of URL
		 * @return none
		 */
		public WebPageURL(String uRL, int index) {
			URL = uRL;		// Given string input parameter, set input equal to the URL member variable of object
			this.index = index;		// Original index from search query
			frequency = (int) (Math.random() * 15);		// Generate random value for frequency
			siteAge = (int) (Math.random() * 10);			// Generate random value for siteAge
			linkReference = (int) (Math.random() * 15);	// Generate random value for linkRefernce
			moneyPaid = (int) (Math.random() * 20);		// Generate random value for moneyPaid; ranked a little higher than other variables
		}

		/**
		 * Constructor method of class that initiliazes node with amount paid and assigns other three ranking values Has no URL value, defaults to
		 * "No URL given"
		 * 
		 * @param key
		 *              int -> amount of money paid
		 * @return none
		 */
		public WebPageURL(int key) {
			URL = "No URL given";		// Set URL member variable to default of No URL given
			frequency = (int) (Math.random() * 15);		// Generate random value for frequency
			siteAge = (int) (Math.random() * 10);			// Generate random value for siteAge
			linkReference = (int) (Math.random() * 15);	// Generate random value for linkReference
			moneyPaid = key;			// Set moneyPaid to input parameter given as key
		}

		/**
		 * Retrieves private frequency variable
		 * 
		 * @param none
		 * @return frequency int -> current value of frequency variable
		 */
		public int getFrequency() {
			return frequency;		// return value of frequency member variable
		}

		/**
		 * Retrieves private siteAge variable
		 * 
		 * @param none
		 * @return siteAge int -> current value of siteAge variable
		 */
		public int getSiteAge() {
			return siteAge;		// return value of siteAge member variable
		}

		/**
		 * Retrieves private linkReference variable
		 * 
		 * @param none
		 * @return linkReference int -> current value of linkReference variable
		 */
		public int getLinkReference() {
			return linkReference;		// return value of linkReference mamber variable
		}

		/**
		 * Retrieves private moneyPaid variable
		 * 
		 * @param none
		 * @return moneyPaid int -> current value of moneyPaid variable
		 */
		public int getMoneyPaid() {
			return moneyPaid;		// return value of moneyPaid member variable
		}

		/**
		 * Retrieves index variable
		 * 
		 * @param none
		 * @return index int -> current value of index variable
		 */
		public int getIndex() {
			return index;		// return value of index member variable
		}

		/**
		 * Sets private ranking variable
		 * 
		 * @param int
		 *              r
		 * @return none
		 */
		public void setRanking(int r) {
			ranking = r;		// set ranking
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

		/**
		 * Sets new value of moneyPaid variable
		 * 
		 * @param moneyPaid
		 *              int -> new value of moneyPaid variable
		 * @return none
		 */
		public void increase(int value) {
			this.moneyPaid = value;		// Set moneyPaid member variable to input parameter given
		}

		/**
		 * Retrieves pageRank by summating frequency, siteAge, linkReference, and moneyPaid
		 * 
		 * @param none
		 * @return pageRank int -> current pageRank calculated from current ranking variables
		 */
		public int getTotalScore() {
			return frequency + siteAge + linkReference + moneyPaid;		// Calculate pageRank by summating 4 rank variables
		}

		/**
		 * Sets total score to input and sets all other variables to 0
		 * 
		 * @param paid
		 *              int -> new value of moneyPaid variable
		 * @return none
		 */
		public void setTotalScore(int paid) {
			frequency = siteAge = linkReference = 0;
			moneyPaid = paid;
		}

		/**
		 * Get name of object (url)
		 * 
		 * @param none
		 * @return String url
		 */
		public String getName() {
			return URL;
		}

		/**
		 * Prints all attributes of WebPageURL object including its pageRank
		 * 
		 * @param none
		 * @return none
		 */
		public void printAttributes() {
			// Print URL, frequency, siteAge, linkReference, moneyPaid, and pageRank
			System.out.format("%-25d%-20d%-15d%-15s\n", getRanking(), getTotalScore(), getIndex(), getName());
		}
		
		public Rank clone() {
			WebPageURL newobj = new WebPageURL(this.getName(), this.getIndex());
			newobj.setTotalScore(this.getTotalScore());
			newobj.setRanking(this.getRanking());
			return newobj;
		}

}
