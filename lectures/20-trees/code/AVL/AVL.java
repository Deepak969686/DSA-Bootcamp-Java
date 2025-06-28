class AVL {

  // ========== Inner Class Node ==========
  public class Node {
    private int value;     // The value/data stored in the node
    private Node left;     // Reference to the left child
    private Node right;    // Reference to the right child
    private int height;    // Height of the node in the tree

    public Node(int value) {
      this.value = value;  // Set value during creation
    }

    public int getValue() {
      return value;        // Getter for the node's value
    }
  }

  private Node root; // Root node of the AVL tree

  // ====== Constructor ======
  public AVL() {
    // Empty constructor (initializes an empty tree)
  }

  // ================= Height Methods =================

  // Public method to get height of the tree from root
  public int height() {
    return height(root);
  }

  // Private helper: returns height of a given node
  private int height(Node node) {
    if (node == null) {
      return -1; // Base height for empty node
    }
    return node.height;
  }

  // ================= Insertion =================

  // Public insert method for users
  public void insert(int value) {
    root = insert(value, root); // Calls the recursive insert on root
  }

  // Recursive insertion method
  private Node insert(int value, Node node) {
    if (node == null) {
      return new Node(value); // Create a new node if place is empty
    }

    // Traverse to correct subtree
    if (value < node.value) {
      node.left = insert(value, node.left);
    } else if (value > node.value) {
      node.right = insert(value, node.right);
    }

    // Update height after insertion
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    // Balance the node if required
    return rotate(node);
  }

  // ================= Rotations for Balancing =================

  // Determine which rotation (LL, LR, RR, RL) to apply
  private Node rotate(Node node) {
    // Left-heavy condition
    if (height(node.left) - height(node.right) > 1) {
      if (height(node.left.left) - height(node.left.right) > 0) {
        // Left-Left Case
        return rightRotate(node);
      } else {
        // Left-Right Case
        node.left = leftRotate(node.left);
        return rightRotate(node);
      }
    }

    // Right-heavy condition
    if (height(node.left) - height(node.right) < -1) {
      if (height(node.right.left) - height(node.right.right) < 0) {
        // Right-Right Case
        return leftRotate(node);
      } else {
        // Right-Left Case
        node.right = rightRotate(node.right);
        return leftRotate(node);
      }
    }

    // No imbalance, return node as is
    return node;
  }

  // Performs a right rotation (for Left-Left imbalance)
  public Node rightRotate(Node p) {
    Node c = p.left;      // New root after rotation
    Node t = c.right;     // Temporarily hold right subtree of c

    c.right = p;          // Rotate
    p.left = t;

    // Update heights
    p.height = Math.max(height(p.left), height(p.right)) + 1;
    c.height = Math.max(height(c.left), height(c.right)) + 1;

    return c;             // Return new root
  }

  // Performs a left rotation (for Right-Right imbalance)
  public Node leftRotate(Node c) {
    Node p = c.right;     // New root after rotation
    Node t = p.left;      // Temporarily hold left subtree of p

    p.left = c;           // Rotate
    c.right = t;

    // Update heights
    p.height = Math.max(height(p.left), height(p.right)) + 1;
    c.height = Math.max(height(c.left), height(c.right)) + 1;

    return p;             // Return new root
  }

  // ================= Populate Tree =================

  // Populate AVL tree with array values (unsorted)
  public void populate(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      this.insert(nums[i]);
    }
  }

  // Populate AVL tree with sorted array (balanced)
  public void populatedSorted(int[] nums) {
    populatedSorted(nums, 0, nums.length);
  }

  // Recursive helper to insert middle element of subarray for balance
  private void populatedSorted(int[] nums, int start, int end) {
    if (start >= end) {
      return;
    }

    int mid = (start + end) / 2;

    this.insert(nums[mid]);
    populatedSorted(nums, start, mid);      // Left subarray
    populatedSorted(nums, mid + 1, end);    // Right subarray
  }

  // ================= Display Tree =================

  // Public method to display tree starting from root
  public void display() {
    display(this.root, "Root Node: ");
  }

  // Recursive helper to display node and its children
  private void display(Node node, String details) {
    if (node == null) {
      return;
    }

    System.out.println(details + node.value);
    display(node.left, "Left child of " + node.value + " : ");
    display(node.right, "Right child of " + node.value + " : ");
  }

  // ================= Utility Methods =================

  // Check if tree is empty
  public boolean isEmpty() {
    return root == null;
  }

  // Check if entire tree is balanced
  public boolean balanced() {
    return balanced(root);
  }

  // Recursive helper to check balance condition for all nodes
  private boolean balanced(Node node) {
    if (node == null) {
      return true;
    }

    // Check height difference and recurse
    return Math.abs(height(node.left) - height(node.right)) <= 1
        && balanced(node.left)
        && balanced(node.right);
  }

}
