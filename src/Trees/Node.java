package Trees;

/**
 * A node in a binary tree, containing an <code>int</code> data.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class Node {

  // also called "value" in a binary tree
  // also called "key" in a binary search tree
  String data;
  int s_num;


  Node left;
  Node right;
  Node parent; // used in SimpleBinaryTree + red-black tree

  int height; // used in AVL tree
  boolean color; // used in red-black tree

  /**
   * Constructs a new node with the given data.
   *
   * @param data the data to store in the node
   */
  public Node(int s_num, String data) {
    this.data = data;
    this.s_num = s_num;
  }

  public String getData() {
    return data;
  }
  public int getS_num() {
    return s_num;
  }
}
