package Chapter9.SelfBalancingTrees; /**
 * @author Carlos Sanchez
 * @version 1.00
 * <p>
 * BinaryTree.java - Store a root node which consists of a left and right Node.
 * The Nodes are a protected inner class which stores a Generic E data type.
 * @since 1/7/2018
 */

import java.io.Serializable;
import java.util.Scanner;


public class BinaryTree<E> implements Serializable
{
    /*************************** CONSTANT VARIABLES ***************************/

    /**************************** CLASS VARIABLES *****************************/
    protected Node<E> root;

    /**************************************************************************/
    /***************************** CONSTRUCTORS *******************************/
    /**************************************************************************/

    /**
     * CONSTRUCTOR : Default constructor.
     */
    public BinaryTree()
    {
        this.root = null;
    }

    /**
     * CONSTRUCTOR: Partial constructor.
     * @param root the root node of this tree.
     */
    protected BinaryTree(Node<E> root)
    {
        this.root = root;
    }

    /**
     * CONSTRUCTOR: Full constructor
     * @param data data to store.
     * @param leftTree The left branch.
     * @param rightTree The right branch.
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
    {
        root = new Node<E>(data);

        root.left = (leftTree != null) ? leftTree.root : null;
        root.right = (rightTree != null) ? rightTree.root : null;
    }

/******************************************************************************/
/********************************* MUTATORS ***********************************/
/******************************************************************************/


/******************************************************************************/
/********************************* ACCESSORS **********************************/
/******************************************************************************/

    /**
     * Return the data in root.
     * @return the data.
     */
    public E getData()
    {
        return this.root.data;
    }


    /**
     * Return the left subtree.
     *
     * @return Left models.BinaryTree<E></E>
     */
    public BinaryTree<E> getLeftSubtree()
    {
        return (root != null && root.left != null)
            ? new BinaryTree<E>(root.left) : null;
    }

    /**
     * Return the right subtree.
     * @return Right models.BinaryTree<E></E>
     */
    public BinaryTree<E> getRightSubtree()
    {
        return (root != null && root.right != null)
            ? new BinaryTree<E>(root.right) : null;

    }

/******************************************************************************/
/********************************* OVERRIDES **********************************/
    /******************************************************************************/

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

/******************************************************************************/
/********************************* INTERFACE **********************************/
/******************************************************************************/


/******************************************************************************/
/****************************** CLASS METHODS *********************************/
/******************************************************************************/

    /**
     * Determines whether this tree is a leaf.
     *
     * @return true if the root has no children.
     */
    public boolean isLeaf()
    {
        return (root.left == null && root.right == null);
    }

/******************************************************************************/
/****************************** STATIC METHODS ********************************/
/******************************************************************************/

    /**
     * METHOD: to read a binary tree.
     * PRECONDITION: The input consists of a preorder traversal of the binary
     * tree. The line "null" indicates a null tree.
     * @param scan The scanner attached to the input file.
     * @return The Binary Tree.
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan)
    {
        String data = scan.next();

        if (data.equals("null"))
        {
            return null;
        }
        else
        {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }
    }


/******************************************************************************/
/****************************** HELPER METHODS ********************************/
/******************************************************************************/

    /**
     * Perform a preorder traversal.
     * @param node The local root.
     * @param depth The depth.
     * @param sb The string buffer to save the output.
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
    {
        for (int i = 0; i < depth; i++)
        {
            sb.append(" ");
        }
        if (node == null)
        {
            sb.append("null\n");
        }
        else
        {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }


/******************************************************************************/
/******************************** INNER CLASS *********************************/
    /******************************************************************************/

    protected static class Node<E> implements Serializable
    {
        protected E data;

        protected Node<E> left;
        protected Node<E> right;


        public Node(E data)
        {
            this.data = data;
            left = null;
            right = null;
        }

        public String toString()
        {
            return data.toString();
        }
    }
}
