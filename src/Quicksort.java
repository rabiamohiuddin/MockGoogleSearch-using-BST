import java.util.*;
import java.util.ArrayList;

public class Quicksort {
		/**
		 * Partitions and sorts Rank objects by total score
		 * 
		 * @param ArrayList<Rank>
		 *              array, int first, int last
		 * @return none
		 */
		public void quicksort(ArrayList<Rank> array, int first, int last) {
			if (first < last) {			// first index must be less than the last index
					int q = partition(array, first, last);		// Retrieve pivot value [ ... < pivot > ... ]
					quicksort(array, first, q - 1);			// Call quicksort on first half
					quicksort(array, q + 1, last);			// Call quicksort on second half
			}
		}

		/**
		 * Compares every element to partition element and swaps to correct order
		 * 
		 * @param ArrayList<Rank>
		 *              array, int first, int pivotIndex
		 * @return int pivotIndex -> index of pivot
		 */
		private int partition(ArrayList<Rank> array, int first, int pivotIndex) {
			int pivotRank = array.get(pivotIndex).getRanking();		// Store pivot value to compare elements
			int i = first -1;			// Index comparator
			for (int j = first; j < pivotIndex; j++) {		// From first element to r-1 (element before pivot)
					if (array.get(j).getRanking() <= pivotRank) {			// If array value is less than pivot, increment i and swap i and j
						i += 1;
						Collections.swap(array, i, j);		// Swap i and j
					}
			}

			Collections.swap(array, i + 1, pivotIndex);		// Swap the pivot to its right place

			return i + 1;		// Return index of pivot
		}

}
