# MockGoogleSearch using Binary Search Trees
Mock Google Search Simulator using Binary Search Trees, Bucket Sort, Quick Sort and web crawlers

<b>How to Run Application</b>
1. Unzip application
2. Open a terminal or console window
3. Change directories to the bin folder of the unzipped project
4. Execute java -jar BuildApp.jar
5. Try all options to view complete functionality of application

<b>Implementation Overview</b>

The Google Search Engine Simulator allows a user to conduct a search of a term they
desire with eight main sub functions. Once the search is submitted, a list of 30 results are
displayed to the user along with options on what to do next. These options include:
1. View search results from Quicksort (displays original search results)
2. View search results from Binary Search Tree Inorder Walk (displays any modifications to
data
3. Search for a specific page rank
4. Insert a URL (based on Total Score) into search results (BST)
5. Delete a URL (based on Page Rank) from search results (BST)
6. View the top 10 searches of the day
7. View search results from first search today (Will display in URL alphabetical order)
8. Run another search

The Simulator consists of seven java classes and one interface: BST, Quicksort,
BucketSort, FunnyCrawler, BuildApp, WebPageURL, SearchTerms, and Rank Interface.

The BuildApp class uses all classes (BST, Quicksort, BucketSort, FunnyCrawler,
BuildApp, WebPageURL, SearchTerms).

BST, Quicksort, and BucketSort use Rank Interface for their ArrayList.
