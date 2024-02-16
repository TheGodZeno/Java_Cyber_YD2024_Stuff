package Trees;

import java.util.Map;

/**
 * Interface for various BST implementations.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public interface BinarySearchTree extends BinaryTree {

  /**
   * Searches for a node with the given search key.
   *
   * @param key the search key
   * @return the node or <code>null</code> if no node with the given search key exists
   */
  String[] searchNode(int key);

  /**
   * Inserts a node with the given key.
   *
   * @param s_num the key of the node to be inserted
   */
  void insertNode(int s_num, String data);

  /**
   * Deletes the node with the given key.
   *
   * @param key the key of the node to be deleted
   */
  void deleteNode(int key);
}
