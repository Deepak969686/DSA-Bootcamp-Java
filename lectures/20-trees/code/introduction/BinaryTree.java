import java.util.Scanner;

class BinaryTree {

  // Constructor (currently empty)
  public BinaryTree() {
  }

  // ------------------------------
  // Node class to represent each node of the tree
  // Each node has an integer value and references to its left and right children
  private static class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
      this.value = value;
    }
  }

  // The root node of the tree
  private Node root;

  // ------------------------------
  // Method to start building the tree using user input
  public void populate(Scanner scanner) {
    System.out.println("Enter the root Node: ");
    int value = scanner.nextInt();     // Read root value
    root = new Node(value);            // Create root node
    populate(scanner, root);           // Start recursive population
  }

  // ------------------------------
  // Recursive function to add children to the current node
  private void populate(Scanner scanner, Node node) {
    // Ask if user wants to add a left child
    System.out.println("Do you want to enter left of " + node.value);
    boolean left = scanner.nextBoolean();
    if (left) {
      System.out.println("Enter the value of the left of " + node.value);
      int value = scanner.nextInt();
      node.left = new Node(value);             // Create left child
      populate(scanner, node.left);            // Recurse for left child
    }

    // Ask if user wants to add a right child
    System.out.println("Do you want to enter right of " + node.value);
    boolean right = scanner.nextBoolean();
    if (right) {
      System.out.println("Enter the value of the right of " + node.value);
      int value = scanner.nextInt();
      node.right = new Node(value);            // Create right child
      populate(scanner, node.right);           // Recurse for right child
    }
  }

  // ------------------------------
  // Display tree in a simple indented format (preorder style)
  public void display() {
    display(this.root, "");
  }

  // Helper function for display
  private void display(Node node, String indent) {
    if (node == null) {
      return;
    }
    System.out.println(indent + node.value); // Print current node with indentation
    display(node.left, indent + "\t");       // Recurse to left child
    display(node.right, indent + "\t");      // Recurse to right child
  }

  // ------------------------------
  // Pretty display the tree (sideways, like real tree structure)
  public void prettyDisplay() {
    prettyDisplay(root, 0);
  }

  // Recursive helper function for pretty display
  private void prettyDisplay(Node node, int level) {
    if (node == null) {
      return;
    }

    // Print right child first (so it appears on top in the output)
    prettyDisplay(node.right, level + 1);

    // Print current node with indent based on depth level
    if (level != 0) {
      for (int i = 0; i < level - 1; i++) {
        System.out.print("|\t\t");       // Draw vertical lines for tree structure
      }
      System.out.println("|------->" + node.value);  // Show node
    } else {
      System.out.println(node.value);    // Root node (no indent)
    }

    // Then print left child
    prettyDisplay(node.left, level + 1);
  }

  // ------------------------------
  // Pre-order traversal: Root → Left → Right
  public void preOrder() {
    preOrder(root);
  }

  private void preOrder(Node node) {
    if (node == null) return;
    System.out.print(node.value + " "); // Visit root
    preOrder(node.left);               // Traverse left
    preOrder(node.right);              // Traverse right
  }

  // ------------------------------
  // ❌ Mistake here: inOrder() is calling preOrder(root) instead of inOrder(root)
  // In-order traversal: Left → Root → Right
  public void inOrder() {
    inOrder(root); // ✅ Corrected from preOrder(root)
  }

  private void inOrder(Node node) {
    if (node == null) return;
    inOrder(node.left);                // Traverse left
    System.out.print(node.value + " "); // Visit root
    inOrder(node.right);               // Traverse right
  }

  // ------------------------------
  // ❌ Mistake here too: postOrder() is calling preOrder(root) instead of postOrder(root)
  // Post-order traversal: Left → Right → Root
  public void postOrder() {
    postOrder(root); // ✅ Corrected from preOrder(root)
  }

  private void postOrder(Node node) {
    if (node == null) return;
    postOrder(node.left);             // Traverse left
    postOrder(node.right);            // Traverse right
    System.out.print(node.value + " "); // Visit root
  }

}
