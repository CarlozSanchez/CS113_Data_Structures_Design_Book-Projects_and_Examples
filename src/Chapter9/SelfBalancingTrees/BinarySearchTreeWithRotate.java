/**
 * @auhtor Carlos Sanchez
 * @version 1.00
 * @since 4/29/2018
 *
 * BinarySearchTreeWithRotate.java
 */
package Chapter9.SelfBalancingTrees;

public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E>
{
    /**
     * METHOD: to perform a right rotation.
     *
     * PRECONDITION: root is the root of a binary search tree.
     * POSTCONDITION: root.right is the root of a binary search tree,
     *               root.right.right is raised one leve.
     *               root.right.left does not change levelts,
     *               root.left is lowered one level,
     *               the new root is returned.
     *
     * @param root The root of the binary tree to be rotated.
     * @return The new root of the orate tree.
     */
    protected Node<E> rotateRight(Node<E> root)
    {
        Node<E> temp = root.left;
        root.left = temp.right;
        temp.right = root;
        return temp;
    }

    /**
     *
     * @param root
     * @return
     */
    protected Node<E> rotateLeft(Node<E> root)
    {
        Node<E> temp = root.right;
        root.right = temp.left;
        temp.left = root;
        return temp;
    }

}
