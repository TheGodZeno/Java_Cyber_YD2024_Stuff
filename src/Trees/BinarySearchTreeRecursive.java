package Trees;

import java.util.HashMap;
import java.util.Map;

/**
 * A recursive binary search tree implementation with <code>int</code> keys.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public class BinarySearchTreeRecursive extends BaseBinaryTree implements BinarySearchTree {

  public int comp = 0;
  @Override
  public String[] searchNode(int key) {
    return searchNode(key, root);
  }

  private String[] searchNode(int key, Node node) {
    if (node == null) {
//      System.out.println("the number of comparison: " + comp);
      String[] res = {Integer.toString(getComp()), "No Serial Number", "No Key"};
      return null;
    }

    if (key == node.s_num) {
//      System.out.println("the number of comparison: " + comp);
      String[] res = {Integer.toString(getComp()), Integer.toString(node.s_num), node.getData()};
      return res;
    } else if (key < node.s_num) {
      this.comp++;
      return searchNode(key, node.left);
    } else {
      this.comp++;
      return searchNode(key, node.right);
    }
  }

  @Override
  public void insertNode(int s_num, String data) {
    root = insertNode(s_num, data, root);
  }

  public Node insertNode(int s_num, String data, Node node) {
    // No node at current position --> store new node at current position
    if (node == null) {
      node = new Node(s_num, data);
    }

    // Otherwise, traverse the tree to the left or right depending on the key
    else if (s_num < node.s_num) {
      node.left = insertNode(s_num, data, node.left);
    } else if (s_num > node.s_num) {
      node.right = insertNode(s_num, data, node.right);
    } else {
      throw new IllegalArgumentException("BST already contains a node with key " + s_num);
    }

    return node;
  }

  @Override
  public void deleteNode(int key) {
    root = deleteNode(key, root);
  }

  Node deleteNode(int s_num, Node node) {
    // No node at current position --> go up the recursion
    if (node == null) {
      return null;
    }

    // Traverse the tree to the left or right depending on the key
    if (s_num < node.s_num) {
      node.left = deleteNode(s_num, node.left);
    } else if (s_num > node.s_num) {
      node.right = deleteNode(s_num, node.right);
    }

    // At this point, "node" is the node to be deleted

    // Node has no children --> just delete it
    else if (node.left == null && node.right == null) {
      node = null;
    }

    // Node has only one child --> replace node by its single child
    else if (node.left == null) {
      node = node.right;
    } else if (node.right == null) {
      node = node.left;
    }

    // Node has two children
    else {
      deleteNodeWithTwoChildren(node);
    }

    return node;
  }

  private void deleteNodeWithTwoChildren(Node node) {
    // Find minimum node of right subtree ("inorder successor" of current node)
    Node inOrderSuccessor = findMinimum(node.right);

    // Copy inorder successor's data to current node
    node.s_num = inOrderSuccessor.s_num;

    // Delete inorder successor recursively
    node.right = deleteNode(inOrderSuccessor.s_num, node.right);
  }

  private Node findMinimum(Node node) {
    while (node.left != null) {
      node = node.left;
    }
    return node;
  }

  public int getComp() {
    int temp = this.comp;
    this.comp = 0;
    return temp;
  }
}
