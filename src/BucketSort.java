import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

// Author: Rabia Mohiuddin
public class BucketSort {

		/**
		 * public bucket sort method that sorts URLs into buckets and calls insertion sort on each bucket
		 * 
		 * @param ArrayList<Rank>
		 *              array
		 * @return none
		 */
		public void bucketSort(ArrayList<Rank> array) {
			ArrayList<ArrayList<Rank>> buckets = new ArrayList<ArrayList<Rank>>(26);		// ArrayList of ArrayList of Rank objects or list of buckets
			int length = 26;			// 26 buckets for 26 letters

			for (int i = 0; i < length; i++) {		// For every bucket, add a new ArrayList of type Rank
					buckets.add(new ArrayList<Rank>());		// Add ArrayList of Rank objects
			}
			for (Rank url : array) {		// For every Rank object in input array
					String hostname = getHostName(url.getName());		// Retrieve hostname (url without http or www)
					if (hostname != null) {			// As long as hostname is not null
						int bucketNum = ((int) hostname.toLowerCase().charAt(0)) - 97;		// Calculate bucket number through ASCII and 1st char of url
						// To put A in bucket 0, ASCII of A is 97, so subtract 97
						if (bucketNum < 0) {		// If hotname starts with a number (bucket num will be negative)
								bucketNum = 0;		// Put in bucket 0
						}
						buckets.get(bucketNum).add(url);		// Add Rank object (url) to given bucket
					}
			}
			array.clear();		// Clear inputted array to prepare for newly sorted array
			for (ArrayList<Rank> bucket : buckets) {		// For each bucket in the arraylist
					insertion_sort(bucket);			// Call insertion sort on each bucket
					array.addAll(bucket);			// Add sorted bucket contents to array to concatenate
			}
		}

		/**
		 * public host name method that returns name of url without http or www
		 * 
		 * @param String
		 *              url
		 * @return String hostname
		 */
		public String getHostName(String url) {
			String hostname = null;			// Initially set hostname to null
			try {
					URI uri = new URI(url);			// Create a URI object to get hostname
					hostname = uri.getHost();		// Retreive hostname for uri

			} catch (URISyntaxException e) {		// If can't retrieve hostname throw exception
					System.out.println(e.getMessage());		// Print error message to the console
			}

			if (hostname != null) {		// to provide faultproof result, check if not null then return only hostname, without www.
					return hostname.startsWith("www.") ? hostname.substring(4) : hostname;		// If starts with www, get 4th char, or else do nothing
			}
			return hostname;		// Return hostname
		}

		/**
		 * priavte insertion sort method to sort each bucket by url (name)
		 * 
		 * @param ArrayList<Rank>
		 *              vals
		 * @return none
		 */
		private void insertion_sort(ArrayList<Rank> vals) {

			Rank key;		// Key is rank object
			int i;			// i iterates throughout bucket (vals)
			for (int j = 1; j < vals.size(); j++) {		// For every element in bucket (vals)
					key = vals.get(j);		// Return Rank object at index j
					i = j - 1;			// Decrement to be 1 less than j
					// while i is positive and i's URL > key's URL
					while (i >= 0 && vals.get(i).getName().compareToIgnoreCase(key.getName()) > 0) {
						vals.set(i + 1, vals.get(i));		// Set i+1 to object at i
						i = i - 1;			// Decrement i
					}
					vals.set(i + 1, key);		// Set i+1 to key's object
			}
		}

}
