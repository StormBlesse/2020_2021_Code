import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 *
 * @author Gabriel Chen
 * @version 1.0
 * @userid gchen337
 * @GTID 903561077
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Cannot add null data into data structure");
        }
        size = 0;
        for (T datum: data) {
            add(datum);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data strucutre");
        } else if (root == null) {
            root = new AVLNode<>(data);
            size++;
        } else {
            root = addHelper(root, data);
        }
    }

    /**
     * Helper method for the add method.
     *
     * @param curr The current node
     * @param data The data to be added
     * @return The current node
     */
    private AVLNode<T> addHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            return new AVLNode<T>(data);
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(curr.getRight(), data));
        } else {
            return curr;
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Perform rebalancing when needed.
     *
     * @param curr the node that is being balanced
     * @return the node that is balanced
     */
    private AVLNode<T> balance(AVLNode<T> curr) {
        if (curr.getBalanceFactor() < -1) {
            if (curr.getRight().getBalanceFactor() > 0) {
                curr.setRight(rotateRight(curr.getRight()));
            }
            curr = rotateLeft(curr);
        } else if (curr.getBalanceFactor() > 1) {
            if (curr.getLeft().getBalanceFactor() < 0) {
                curr.setLeft(rotateLeft(curr.getLeft()));
            }
            curr = rotateRight(curr);
        }
        return curr;
    }

    /**
     * Recalculate height and balance factor.
     *
     * @param curr
     * throws IllegalArgumentException if data is null
     */
    private void update(AVLNode<T> curr) {
        if (curr == null) {
            throw new IllegalArgumentException("Cannot update null data");
        }
        int lh = height(curr.getLeft());
        int rh = height(curr.getRight());
        curr.setHeight(Math.max(lh, rh) + 1);
        curr.setBalanceFactor(lh - rh);
    }




    /**
     * Left rotation operation.
     *
     * @param a the node that will be rotated
     * @return the node that is left rotated
     */
    private AVLNode<T> rotateLeft(AVLNode a) {
        AVLNode b = a.getRight();
        a.setRight(b.getLeft());
        b.setLeft(a);
        update(a);
        update(b);
        return b;
    }

    /**
     * Right rotation operation.
     *
     * @param a the node that will be rotated
     * @return the node that is right rotated
     */
    private AVLNode<T> rotateRight(AVLNode a) {
        AVLNode b = a.getLeft();
        a.setLeft(b.getRight());
        b.setRight(a);
        update(a);
        update(b);
        return b;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from data structure");
        }
        AVLNode<T> dummy = new AVLNode<>(null);
        root = removeHelper(root, data, dummy);
        return dummy.getData();
    }

    /**
     * Helper method for the remove method.
     *
     * @param curr Current node
     * @param data Data to be removed
     * @param dummy Node to store removed data
     * @return parent node of the node to be removed
     */
    private AVLNode<T> removeHelper(AVLNode<T> curr, T data, AVLNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data was not found within the data strucutre");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, dummy));
        } else {
            size--;
            dummy.setData(curr.getData());
            if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Finds the predecessor node to help the remove method.
     *
     * @param curr The current node
     * @param dummy A temp node to store data of a node who's parent will be removed
     * @return The predecessor node of an element that will be removed
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> curr, AVLNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
        update(curr);
        return balance(curr);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data in data structure");
        }
        return getHelper(root, data);
    }

    /**
     * Helper method for the get method.
     *
     * @param node The root node to check
     * @param data The data that is being searched for
     * @return the data that is being searched for if it is found
     */
    private T getHelper(AVLNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException("Data could not be found within data structure");
        }
        if (data.compareTo(node.getData()) < 0) {
            return getHelper(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return getHelper(node.getRight(), data);
        } else {
            return node.getData();
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check data structure for null data");
        }

        return containsHelper(data, root);
    }

    private boolean containsHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            return false;
        }
        if (curr.getData().equals(data)) {
            return true;
        }
        if (data.compareTo(curr.getData()) < 0) {
            return containsHelper(data, curr.getLeft());
        }
        return containsHelper(data, curr.getRight());
    }

    /**
     * Returns the height of the root of the tree.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if(root == null) {
            return -1;
        }
        return root.getHeight();
    }

    /**
     * Returns height of a node
     * @param node the node being checked
     * @return the height of the node
     */
    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        } else {
            return node.getHeight();
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data in the deepest node. If there is more than one node
     * with the same deepest depth, return the rightmost (i.e. largest) node with 
     * the deepest depth. 
     *
     * Must run in O(log n) for all cases.
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (root == null) {
            return null;
        }
        return deepHelper(root).getData();
    }

    /**
     * Finds deepest node.
     * @param curr the node to start the search from
     * @return the deepest node
     */
    private AVLNode<T> deepHelper(AVLNode<T> curr) {
        if (curr == null) {
            return null;
        } else if (curr.getHeight() == 0) {
            return curr;
        } else {
            if (curr.getRight() != null && curr.getLeft() != null) {
                if (curr.getRight().getHeight() > curr.getLeft().getHeight()) {
                    return deepHelper(curr.getRight());
                } else if (curr.getRight().getHeight() == curr.getLeft().getHeight()) {
                    return deepHelper(curr.getRight());
                } else if (curr.getRight().getHeight() < curr.getLeft().getHeight()) {
                    return deepHelper(curr.getLeft());
                }
            } else if (curr.getRight() != null) {
                return deepHelper(curr.getRight());
            } else if (curr.getLeft() != null) {
                return deepHelper(curr.getLeft());
            }
        }
        return null;
    }

    /**
     * In BSTs, you learned about the concept of the successor: the
     * smallest data that is larger than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the successor of the data
     * passed in. There are 2 cases to consider:
     * 1: The right subtree is non-empty. In this case, the successor is the
     * leftmost node of the right subtree.
     * 2: The right subtree is empty. In this case, the successor is the lowest
     * ancestor of the node containing data whose left child is also
     * an ancestor of data.
     * 
     * The second case means the successor node will be one of the node(s) we 
     * traversed left from to find data. Since the successor is the SMALLEST element 
     * greater than data, the successor node is the lowest/last node 
     * we traversed left from on the path to the data node.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *                    76
     *                  /    \
     *                34      90
     *                  \    /
     *                  40  81
     * successor(76) should return 81
     * successor(81) should return 90
     * successor(40) should return 76
     *
     * @param data the data to find the successor of
     * @return the successor of data. If there is no larger data than the
     * one given, return null.
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T successor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot check data structure in relation with null data");
        }
        if (size == 0) {
            throw new NoSuchElementException("Data not contained within data structure");
        }

        AVLNode node = getNode(data);
        if (node.getRight() != null) {
            AVLNode<T> curr = node.getRight();

            while (curr.getLeft() != null) {
                curr = curr.getLeft();
            }
            return curr.getData();
        }

        AVLNode<T> successor = null;
        AVLNode<T> curr = root;

        while (curr != null) {
            if (node.getData().compareTo(curr.getData()) < 0) {
                successor = curr;
                curr = curr.getLeft();
            } else if (node.getData().compareTo(curr.getData()) > 0) {
                curr = curr.getRight();
            } else {
                break;
            }
        }
        if (successor == null) {
            return null;
        }
        return successor.getData();
    }

    /** Returns the node from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException if the data is not in the tree
     */
    private AVLNode<T> getNode(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null data in data structure");
        }
        return getNodeHelper(root, data);
    }

    /**
     * Finds the node associated with the data
     *
     * @param node The root node to check
     * @param data The data that is being searched for
     * @return the node that contains the data
     */
    private AVLNode<T> getNodeHelper(AVLNode<T> node, T data) {
        AVLNode<T> current = node;
        while (current != null && !current.getData().equals(data)) {
            if (data.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (current == null) {
            throw new NoSuchElementException("hello");
        }
        return current;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
