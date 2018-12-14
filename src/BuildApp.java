import java.util.*;

// Author: Rabia Mohiuddin
public class BuildApp {
		ArrayList<Rank> URLObjects = new ArrayList<Rank>();			// ArrayList that stores search results
		BST urlBST = new BST();				// BST for the manipulate search results
		ArrayList<Rank> searchOccurrences = new ArrayList<Rank>();			// ArrayList for search terms and their occurrences
		ArrayList<Rank> top10searchTerms = new ArrayList<Rank>();			// ArrayList to store top 10 search terms
		BucketSort bucketSort = new BucketSort();			// Bucket sort for first search term URLs
		ArrayList<Rank> firstSearchResults = new ArrayList<Rank>();		// Array of alphabetically sorted URLs from first search

		/**
		 * private search method takes search string as input and returns ArrayList of WebPageURL objects
		 * 
		 * @param search
		 *              String -> search term
		 * @return ArrayList<Rank> -> ArrayList of type WebPageURL (implementation of Rank)
		 */
		private ArrayList<Rank> search(String search) {
			FunnyCrawler obj = new FunnyCrawler();			// Create web crawler object
			ArrayList<String> result = obj.getDataFromGoogle(search);		// Set of unique URLs from search results
			result.remove("");		// Remove any empty URLs or strings

			ArrayList<Rank> URLobjects = new ArrayList<Rank>();		// Create ArrayList of WebPageURL objects
			URLobjects.clear();		// Clear the local ArrayList to make sure nothing is inside
			int index = 1;
			for (String temp : result) {			// Iterate through unique URL results, create WebPageURL objects and add to ArrayList
					URLobjects.add(new WebPageURL(temp, index));			// Create WebPageURL object and add to URLobjects ArrayList
					index++;					// Increment index for next for loop iteration
			}

			return URLobjects;		// Return ArrayList of URLobjects
		}

		/**
		 * public search method that retrieves search string from user, calls search and retrieves results, then builds BST, and stores the results if first
		 * time being searched
		 * 
		 * @param searchTerm
		 *              String -> search term
		 * @return none
		 */
		public void enterSearch(String searchTerm) {
			addSearchTerm(searchTerm);			// Given searh term user entered, call method to add term to the search term heap

			URLObjects.clear();			// Clear the public URLObjects ArrayList to ensure no old results left behind

			URLObjects = search(searchTerm);		// Get new search results and store them in public ArrayList

			System.out.println("Your term  '" + searchTerm + "' found " + URLObjects.size() + " results");		// Print # of results to user

			for (Rank urlobj : URLObjects) {			// For every Rank (url) object in the ArrayList, insert it into BST by creating new node
					urlBST.treeInsert(new Node(urlobj));			// Create new node with Rank object, insert in BST
			}

			if (searchOccurrences.size() == 1 && searchOccurrences.get(0).getTotalScore() == 1) {
					for (Rank obj : URLObjects) {
						firstSearchResults.add(obj.clone());
					}
					bucketSort.bucketSort(firstSearchResults);			// Bucketsort the cloned list
			}
		}

		/**
		 * Quicksort original search results from URLObjects by Total score
		 * 
		 * @param none
		 * @return none
		 */
		public void quicksortResults() {
			Quicksort qsort = new Quicksort();				// Instantiate quicksort object
			qsort.quicksort(URLObjects, 0, URLObjects.size() - 1);			// Call quicksort on URLobjects from index 0 to size
		}

		/**
		 * Prints column headers or URL results: Rank | Total Score | Index | URL
		 * 
		 * @param none
		 * @return none
		 */
		public void printHeader() {
			System.out.println();			// Empty line for clarity
			System.out.format("\n%-15s%-20s%-20s%-15s\n", "Rank", "Total Score", "Index", "URL");		// Print header given space
		}

		/**
		 * print attributes for given ArrayList in numbered fashion
		 * 
		 * @param arrlist
		 *              ArrayList<Rank> -> ArrayList of type Rank (WebPageURL, SearchTerms)
		 * @return none
		 */
		public void print(ArrayList<Rank> arrlist) {
			for (Rank obj : arrlist) {			// For each Rank object in the ArrayList, print
					obj.printAttributes();			// Print respective attributes depending if WebPageURL or SearchTerms
			}
			System.out.println();
		}

		/**
		 * Retreive index of search term in the search occurrences ArrayList
		 * 
		 * @param searchTerm
		 *              String -> Search term user had initially entered
		 * @return index of search term in sesrchOccurences ArrayList
		 */
		public int getIndexByname(String searchTerm) {
			for (Rank item : searchOccurrences) {		// For every Rank object in searchOccurrences
					if (item.getName().equals(searchTerm))                                       		// Check if Rank objects name is equal to the search
                                      		// term
						return searchOccurrences.indexOf(item);		// If so return index of Rank object
			}
			return -1;		// If can't find search term in arrayList return -1 to show not found
		}

		/**
		 * Add search term to searchOccurrences ArrayList whenever search method is called or increment value of searchTerm
		 * 
		 * @param searchTerm
		 *              String -> Search term user had initially entered
		 * @return none
		 */
		private void addSearchTerm(String searchTerm) {
			int index = getIndexByname(searchTerm);		// Call getIndexByName function to retrieve index in ArrayList, return -1 if not
			if (index != -1) {					// If search term is in ArrayList
					searchOccurrences.get(index).increase(1);		// Retrieve object from searchOccurrences and increase value by 1
			} else {			// If not in ArrayList
					searchOccurrences.add(new SearchTerms(searchTerm));		// If not in searchOccurrences then add it
			}
		}

		/**
		 * Retreive top 10 searches and print to the user
		 * 
		 * @param none
		 * @return none
		 */
		public void getTop10searches() {
			System.out.println("Top 10 search terms today: ");		// Header to print to the user

			BST bstTopSearches = new BST();			// Create bst object to sort searchOccurrences
			for (Rank searchOcc : searchOccurrences) {			// For every rank object in searchOccurrences
					bstTopSearches.treeInsert(new Node(searchOcc));		// Create a node from Rank and insert into BST for top searches
			}

			top10searchTerms.clear();			// Clear top 10 search terms arraylist in case populated

			for (int i = 0; i < 10 && i < searchOccurrences.size(); i++) {		// Iterate 10 times or size of searchOccurrences to extract top 10
					top10searchTerms.add(bstTopSearches.search(i).key);		// add to top10searches ArrayList
			}

			print(top10searchTerms);			// Print top 10 searches and their attributes to the user

		}

		/**
		 * Creates user interface that displays options to user, reads in their choice and calls respective functions and loops back to options
		 * 
		 * @param none
		 * @return none
		 */
		public void userInterface() {
			Scanner reader = new Scanner(System.in);  // Reading from System.in

			System.out.print("Enter a search term: ");			// Ask user for a search term to start application
			enterSearch(reader.nextLine());			// Read the search term in and call search function

			String option = null;			// Set option to null so can enter while loop
			while (!"0".equals(option)) {		// Option 0 represents Quit - As long as not quit, loop through options
					// Ask user what they want to do - display 9 options
					System.out.println("What would you like to do next?");
					System.out.println("1. View search results from Quicksort (displays original search results)");
					System.out.println("2. View search results from Binary Search Tree Inorder Walk (displays any modifications to data)");
					System.out.println("3. Search for a specific page rank");
					System.out.println("4. Insert a URL (based on Total Score) into search results (BST)");
					System.out.println("5. Delete a URL (based on Page Rank) from search results (BST)");
					System.out.println("6. View the top 10 searches of the day");
					System.out.println("7. View search results from first search today (Will display in URL alphabetical order)");
					System.out.println("8. Run another search");
					System.out.println("0. Quit");

					System.out.print("\nOption: ");		// Ask user to select one of the above options

					option = reader.nextLine().trim();		// Read in their option choice

					if (!"0".equals(option)) {		// If option is not quit, go through switch statement for respective options
						switch (option) {
						case "1":			// View search results from Quicksort
								quicksortResults();		// Show original search results sorted by quicksort by total score
								printHeader();				// Print header columns
								print(URLObjects);		// Call print function on the URLObjects that are now sorted
								break;

						case "2":			// View search results from BST inorder walk
								printHeader();				// Print header columns
								urlBST.inorderTreeWalk(urlBST.getRoot());		// Call BST inorder walk on root of tree
								break;

						case "3":		// Search for a specific page rank
								System.out.println("Which Rank item would you like to display?");
								int size = urlBST.getSize();			// Get size (number of nodes) of current BST
								System.out.println("There are currently " + size + "URLs");			// Print size of BST to the user
								System.out.print("\nSearch URL Rank: ");		// Ask user to select a URL rank
								try {
									int rankNode = Integer.parseInt(reader.nextLine());			// Read in user rank choice
									if (rankNode > size || rankNode < 0) {				// Make sure choice is within the BST size
											throw new InputMismatchException("Out of range");		// If not in BST size, throw exception
									}
									Node rankItem = urlBST.search(rankNode - 1);			// Retrieve the node returned from searching for rank
									printHeader();			// Print header columns and print object attributes with given format
									System.out.format("%-25d%-20d%-15d%-15s\n", rankItem.key.getRanking(), rankItem.key.getTotalScore(), rankItem.key.getIndex(), rankItem.key.getName());

								} catch (InputMismatchException e) {			// Exception thrown if not in range or not integer
									System.out.println(e.getMessage());			// Print message to the console
								} catch (NumberFormatException e) {			// If not integer, catch exception
									System.out.println("Invalid rank - " + e.getMessage());			// Print message to the console
								}
								break;

						case "4":		// Insert a URL (based on Total Score) into search results (BST)
								System.out.println("Enter a URL that starts with http:// or https://");
								System.out.print("URL: ");
								String insertURL = reader.nextLine().trim();			// Read in user's URL
								WebPageURL insertLink = new WebPageURL(insertURL, urlBST.getSize());		// Create WebPageURL and set index
								System.out.print("Total Score of URL: ");
								try {
									int insertURLScore = Integer.parseInt(reader.nextLine());		// Read in total score of URL
									insertLink.setTotalScore(insertURLScore);		// Set WebPageURL object's total score
									urlBST.treeInsert(new Node(insertLink));		// Create Node with WebPageURL object and insert into URL BST
								} catch (InputMismatchException e) {			// Exception thrown if not integer
									System.out.println(e.getMessage());			// Print message to the console
								} catch (NumberFormatException e) {			// If user doesnt enter integer, catch exception
									System.out.println("Invalid score - " + e.getMessage());			// Print message to the console
								}

								break;

						case "5":		// Delete a URL (based on Page Rank) from search results (BST)
								System.out.println("Which Rank item would you like to delete?");
								int sizeTree = urlBST.getSize();			// Get size (number of nodes) of current BST
								System.out.println("There are currently " + sizeTree + "URLs");			// Print size of BST to the user
								System.out.print("\nDelete URL Rank: ");		// Ask user to select a URL rank
								try {
									int rankNode = Integer.parseInt(reader.nextLine());			// Read in user rank choice
									if (rankNode > sizeTree || rankNode < 0) {				// Make sure choice is within the BST size
											throw new InputMismatchException("Out of range");		// If not in BST size, throw exception
									}
									Node delete = urlBST.search(rankNode - 1);			// Retrieve the node returned from searching for rank
									urlBST.treeDelete(delete);
									System.out.println("Rank item " + rankNode + " was deleted");
								} catch (InputMismatchException e) {			// Exception thrown if not in range or not integer
									System.out.println(e.getMessage());			// Print message to the console
								} catch (NumberFormatException e) {
									System.out.println("Invalid rank - " + e.getMessage());			// Print message to the console
								}
								break;

						case "6":		// View the top 10 searches of the day
								getTop10searches();		// Call top10searches function to display to the user
								break;

						case "7":		// View search results from first search today
								printHeader();			// Print column header
								print(firstSearchResults);		// Print search results sorted by URL from first search
								break;

						case "8":		// Run another search
								System.out.print("Enter a search term: ");		// Ask for new search term
								enterSearch(reader.nextLine());			// Call search function with the search term
								break;

						default:		// If not options 0-5, ask user to reselect an option
								System.out.println("Please select one of the above options. Input must be a digit between 0 and 8\n");
						}
						System.out.println();
					} else {		// User has selected 0 meaning quit, say Goodbye
						System.out.println("Goodbye!");
					}

			}

			reader.close();		// Close scanner object

		}

		/**
		 * Welcomes user and calls the userInterface method
		 * 
		 * @param none
		 * @return none
		 */
		public static void main(String[] args) {
			BuildApp app = new BuildApp();			// Create BuildApp object to call userInterface
			System.out.println("Welcome to my mock Google search simulator!");
			System.out.println("Author: Rabia Mohiuddin\n");
			app.userInterface();		// Call userInterface to start application
		}
}
