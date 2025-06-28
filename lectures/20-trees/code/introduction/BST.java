class BST {

  // -------------------------------
  // Inner class representing a node in the tree
  public class Node {
    private int value;     // Value stored in the node
    private Node left;     // Reference to left child
    private Node right;    // Reference to right child
    private int height;    // Height of the node (used for balance checking)

    public Node(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;        // Getter for node value
    }
  }

  private Node root; // The root of the BST

  public BST() {
    // Empty constructor
  }

  // -------------------------------
  // Helper to get height of a node
  public int height(Node node) {
    if (node == null) {
      return -1;  // Height of null is defined as -1
    }
    return node.height;
  }

  // -------------------------------
  // Check if the tree is empty
  public boolean isEmpty() {
    return root == null;
  }

  // -------------------------------
  // Public method to insert a value
  public void insert(int value) {
    root = insert(value, root); // Start insertion from the root
  }

  // Recursive helper to insert value into the tree
  private Node insert(int value, Node node) {
    if (node == null) {
      node = new Node(value); // Create a new node if spot is empty
      return node;
    }

    // Recurse to the left if value is smaller
    if (value < node.value) {
      node.left = insert(value, node.left);
    }

    // Recurse to the right if value is greater
    if (value > node.value) {
      node.right = insert(value, node.right);
    }

    // Update height of this node
    node.height = Math.max(height(node.left), height(node.right)) + 1;

    return node;
  }

  // -------------------------------
  // Insert multiple values into the tree
  public void populate(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      this.insert(nums[i]); // Insert each element
    }
  }

  // -------------------------------
  // Populate the tree using a sorted array to make it more balanced
  public void populatedSorted(int[] nums) {
    populatedSorted(nums, 0, nums.length);
  }

  // Recursive helper to insert middle of array to balance BST
  private void populatedSorted(int[] nums, int start, int end) {
    if (start >= end) {
      return;
    }

    int mid = (start + end) / 2;

    this.insert(nums[mid]); // Insert middle element
    populatedSorted(nums, start, mid);      // Left half
    populatedSorted(nums, mid + 1, end);    // Right half
  }

  // -------------------------------
  // Public method to check if tree is balanced
  public boolean balanced() {
    return balanced(root);
  }

  // Recursive helper to check if subtree is balanced
  private boolean balanced(Node node) {
    if (node == null) {
      return true;
    }

    // Check height difference of children and recurse for left and right
    return Math.abs(height(node.left) - height(node.right)) <= 1
        && balanced(node.left)
        && balanced(node.right);
  }

  // -------------------------------
  // Public method to display the tree
  public void display() {
    display(this.root, "Root Node: ");
  }

  // Recursive display of node and its children
  private void display(Node node, String details) {
    if (node == null) {
      return;
    }

    System.out.println(details + node.value);
    display(node.left, "Left child of " + node.value + " : ");
    display(node.right, "Right child of " + node.value + " : ");
  }

}
