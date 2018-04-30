/**
 * @Author Carlos Sanchez
 * @Version 1.00
 * @Since 4/19/2018
 */
package Chapter9.SelfBalancingTrees;

public class BinarySearchTree <E extends Comparable<E>>
    extends BinaryTree<E>
{
    /**************************** CLASS VARIABLES *****************************/
    protected boolean addReturn;
    protected E deleteReturn;

    /**
     * STARTER METHOD: for recursive find(Node<E> localRoot, E target).
     * @param target The Comparable object being sought.
     * @return The object, if fouun, otherwise null.
      */
    public E find(E target)
    {
        return find(root, target);
    }

    /**
     * RECURSIVE METHOD: to find target.
     * @param localRoot The local subtree's root.
     * @param target The object being sought.
     * @return The object, if found, otherwise null.
     */
    private E find(Node<E> localRoot, E target)
    {
        if(localRoot == null)
        {
            return null;
        }

        // Compare the target with the data field at the rood.
        int compareResult = target.compareTo(localRoot.data);
        if(compareResult == 0)
        {
            return localRoot.data;
        }
        else if(compareResult < 0)
        {
            return find(localRoot.left, target);
        }
        else return find(localRoot.right, target);
    }


    /**
     *
     * @param item
     * @return
     */
    public boolean add(E item)
    {
        root = add(root, item);
        return addReturn;
    }

    /**
     *
     * @param localRoot
     * @param item
     * @return
     */
    private Node<E> add(Node<E> localRoot, E item)
    {
        if(localRoot == null)
        {
            // item is not in the tree - insert it.
            addReturn = true;
            return new Node<E>(item);
        }
        else if(item.compareTo(localRoot.data) == 0)
        {
            // item is equals to localRoot.data
            addReturn = false;
            return localRoot;
        }
        else if(item.compareTo(localRoot.data) < 0)
        {
            localRoot.left = add(localRoot.left, item);
            return localRoot;
        }
        else
        {
            // item is greate that local Root.data
            localRoot.right = add(localRoot.right, item);
            return localRoot;
        }
    }

    /**
     *
     * @param localRoot
     * @param item
     * @return
     */
    private Node<E> delete(Node<E> localRoot, E item)
    {
        if(localRoot == null)
        {
            deleteReturn = null;
            return localRoot;
        }

        // Search for item to delete
        int compareResult = item.compareTo(localRoot.data);
        if(compareResult < 0)
        {
            // item is smaller than localRoot.data
            localRoot.left = delete(localRoot.left, item);
            return localRoot;
        }
        else if( compareResult > 0)
        {
            // item is larger than localRoot.data
            localRoot.right = delete(localRoot.right, item);
            return localRoot;
        }
        else
        {
            // item is at local root.
            deleteReturn = localRoot.data;

            if(localRoot.left == null)
            {
                // If there is no left chicl, return right chicld
                // which can also be null.
                return localRoot.right;
            }
            else if(localRoot.right == null)
            {
                // If there is no right child, return left chicld.
                return localRoot.left;
            }
            else
            {
                // Node being deleted has 2 chicldren, replace the data
                // with inorder predecessor.
                if(localRoot.left.right == null)
                {
                    // the left chicld has no right chicld.
                    // replace the data with the data in the left child.
                    localRoot.data = localRoot.left.data;

                    // Replace the left chicl with is left chicl.
                    localRoot.left = localRoot.left.left;
                    return localRoot;
                }
                else
                {
                    // Search for the inorder predecessor(ip) and
                    // replace deleted node's data with ip
                    localRoot.data = findLargestChild(localRoot.left);
                    return localRoot;
                }
            }
        }
    }

    private E findLargestChild(Node<E> parent)
    {
        // If the right chicl has no right chicl, it is
        // the inorder predecessor.
        if(parent.right.right == null)
        {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else
        {
            return findLargestChild(parent.right);
        }
    }
}
