
public class BST {

		private Node root;		// BST has one variable, root, to keep track of all other nodes

		/**
		 * Get root Node object
		 * 
		 * @param none
		 * @return Node root
		 */
		public Node getRoot() {
			return root;
		}

		/**
		 * BST in order walk that prints largest to smallest by going through the right side of tree first, then left side
		 * 
		 * @param Node
		 *              node
		 * @return none
		 */
		public void inorderTreeWalk(Node node) {
			if (node != null) {		// As long as the node is populated
					inorderTreeWalk(node.right);		// Iterate through right side first to display largest values first
					node.key.printAttributes();			// Print attributes of current node
					inorderTreeWalk(node.left);		// Iterate through left side of tree
			}

		}

		/**
		 * BST smallest node in tree
		 * 
		 * @param Node
		 *              node
		 * @return Node -> smallest node in tree
		 */
		public Node treeMinimum(Node node) {
			while (node.left != null) {		// Smallest node is located all the way bottom to the left
					node = node.left;			// Keep going left
			}
			return node;			// Return left node that has no more left children
		}

		/**
		 * BST largest node in tree
		 * 
		 * @param Node
		 *              node
		 * @return Node -> largest node in tree
		 */
		public Node treeMaximum(Node node) {
			while (node.right != null) {		// Largest node is located all the way bottom to the left
					node = node.right;		// Keep going right
			}
			return node;			// Return right node that has no more right children
		}

		/**
		 * Finds the next largest smallest value in the tree
		 * 
		 * @param Node
		 *              node
		 * @return Node -> largest smallest node in tree
		 */
		public Node treeSuccessor(Node node) {
			if (node.right != null) {		// If node has a right subtree
					return treeMinimum(node.right);			// Get the smallest node in right subtree
			}
			Node y = node.parent;		// Node y holds the current node's parent
			while (y != null && node == y.right) {		// While parent is not null and the node is the parent's right child
					node = y;			// node becomes the parent
					y = y.parent;		// y becomes his grandparent
			}
			return y;			// Return next largest smallest node
		}

		/**
		 * Inserts a new node into BST while maintaining BST properties. Updates ranking of each node
		 * 
		 * @param Node
		 *              newNode
		 * @return none
		 */
		public void treeInsert(Node newNode) {
			Node parent = null;			// Parent of root is null
			Node iterator = this.root;		// Start iterator at root of tree
			while (iterator != null) {			// Iterate through nodes till find null
					parent = iterator;			// Store parent to compare new node with children
					if (newNode.compareTo(iterator) == -1) {		// If new node is less than iterator
						iterator = iterator.left;			// Go to left subtree
					} else {			// If new node is greater than iterator
						iterator = iterator.right;			// Go to right subtree
					}
			}
			newNode.parent = parent;		// New node's parent is parent of null node
			if (parent == null) {				// If Tree was empty
					this.root = newNode;			// Set root of tree to newNode
			} else if (newNode.compareTo(parent) == -1) {		// If newNode is less than its parent
					parent.left = newNode;			// Set parent's left node to newNode
			} else {					// If newNode is greater than its parent
					parent.right = newNode;			// Set parent;s right node to newNode
			}
			updateRanking();		// Update ranking of each node
		}

		/**
		 * Transplant subtrees of two given nodes
		 * 
		 * @param Node
		 *              u, Node v
		 * @return none
		 */
		public void transplant(Node u, Node v) {
			if (u.parent == null) {		// If u is the root of the tree
					root = v;			// Set root to v
			} else if (u == u.parent.left) {		// If u is the left child
					u.parent.left = v;			// Set left child to v
			} else {							// If u is the right child
					u.parent.right = v;		// Set right child to v
			}

			if (v != null) {				// If v is not null
					v.parent = u.parent;		// Set v's parent to u's parent
			}
		}

		/**
		 * Deletes a given node from BST while maintaining BST properties. Updates ranking of each node
		 * 
		 * @param Node
		 *              delete
		 * @return none
		 */
		public void treeDelete(Node delete) {
			if (delete.left == null) {		// If node want to delete doesn't have a left child
					transplant(delete, delete.right);		// Transplant delete and delete's right subtree
			} else if (delete.right == null) {			// If delete node doesnt have a right child
					transplant(delete, delete.left);		// Transplant delete and delete's left subtree
			} else {			// If has both left and right children
					Node min = treeMinimum(delete.right);		// Find smallest node in right subtree
					if (min.parent != delete) {		// If smallest node in right subtree's parent is not the node want to delete
						transplant(min, min.right);		// Transplant min and min's right subtree
						min.right = delete.right;		// Set min's right node to delete's right node
						min.right.parent = min;		// Set min's right's parent to min
					}
					transplant(delete, min);		// Transplant min and min's right subtree
					min.left = delete.left;		// Set min's left child to delete's left child
					min.left.parent = min;		// Set min's left childs parent to min
			}
			updateRanking();		// Update ranking of nodes
		}

		/**
		 * Gets size of a particular node's subtree
		 * 
		 * @param Node
		 *              subtree
		 * @return int size
		 */
		private int size(Node subtree) {
			if (subtree == null) {		// If subtree is null, size is 0, return
					return 0;		// Size is 0
			}
			return 1 + size(subtree.left) + size(subtree.right);	// Return current node (size 1) + size of left subtree + size of right subtree
		}

		/**
		 * Gets size of entire bst
		 * 
		 * @param none
		 * @return int size
		 */
		public int getSize() {
			return size(getRoot());		// Get size of root
		}

		/**
		 * Search for rankings 0 to size of tree and set ranking of Rank object
		 * 
		 * @param none
		 * @return none
		 */
		public void updateRanking() {
			for (int i = 0; i < getSize(); i++) {		// Iterate i through size of tree
					Rank searchi = search(i).key;		// Search for ranking of i
					searchi.setRanking(i+1);		// Set ranking of Rank object to i
			}
		}

		/**
		 * Search for k'th largest node in tree
		 * 
		 * @param int
		 *              k
		 * @return Node
		 */
		public Node search(int k) {
			if (k < 0 || k >= getSize()) {		// If number want is less than 0 or greater than size of tree
					throw new IllegalArgumentException("argument to search() is invalid: " + k);		// Throw exception
			}
			Node x = select(root, k);		// Find the node at int k
			return x;		// Return the node found
		}

		/**
		 * Get k'th largest node in tree
		 * 
		 * @param Node
		 *              x, int k
		 * @return Node
		 */
		private Node select(Node x, int k) {
			if (x == null)     		// If x is null
					return null;		// return null
			int t = size(x.right);		// Get size of right subtree
			if (t > k)     		// If right subtree size is greater than rank want to find
					return select(x.right, k);		// Look through right subtree
			else if (t < k)     		// If right subtree size is less than rank want to find
					return select(x.left, k - t - 1);		// Look through left subtree
			else
					return x;		// Return node found at k
		}

}

class Node implements Comparable<Node> {
		Rank key;			// Node holds Rank object
		Node parent, left, right;		// Has parent, left, right pointers

		/**
		 * Construct node with Rank object
		 * 
		 * @param Rank
		 *              obj
		 * @return none
		 */
		public Node(Rank obj) {
			this.key = obj;		// Set key to Rank object
			this.left = this.right = this.parent = null;		// Parent, left, right pointers are null
		}

		/**
		 * Compare two nodes by comparing their Rank objects
		 * 
		 * @param Node
		 *              o
		 * @return int
		 */
		public int compareTo(Node o) {
			return this.key.compareTo(o.key);		// Compare the two keys
		}

}
