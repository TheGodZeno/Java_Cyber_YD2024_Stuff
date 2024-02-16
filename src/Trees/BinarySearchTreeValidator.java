package Trees;

/**
 * Validates if the given binary tree is a binary search tree.
 *
 * @author <a href="sven@happycoders.eu">Sven Woltmann</a>
 */
public final class BinarySearchTreeValidator {

  private BinarySearchTreeValidator() {}

  /**
   * Validates if the given binary tree is a binary search tree (with no duplicates allowed).
   *
   * @param tree the binary tree
   * @return whether the given binary tree is a binary search tree
   */
  public static boolean isBstWithoutDuplicates(BinaryTree tree) {
    return isBstWithoutDuplicates(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBstWithoutDuplicates(Node node, int minAllowedKey, int maxAllowedKey) {
    if (node == null) {
      return true;
    }

    if (node.s_num < minAllowedKey || node.s_num > maxAllowedKey) {
      return false;
    }

    return isBstWithoutDuplicates(node.left, minAllowedKey, node.s_num - 1)
        && isBstWithoutDuplicates(node.right, node.s_num + 1, maxAllowedKey);
  }

  /**
   * Validates if the given binary tree is a binary search tree (with duplicates allowed).
   *
   * @param tree the binary tree
   * @return whether the given binary tree is a binary search tree
   */
  public static boolean isBstWithDuplicates(BinaryTree tree) {
    return isBstWithDuplicates(tree.getRoot(), Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private static boolean isBstWithDuplicates(Node node, int minAllowedKey, int maxAllowedKey) {
    if (node == null) {
      return true;
    }

    if (node.s_num < minAllowedKey || node.s_num > maxAllowedKey) {
      return false;
    }

    return isBstWithDuplicates(node.left, minAllowedKey, node.s_num)
        && isBstWithDuplicates(node.right, node.s_num, maxAllowedKey);
  }
}
