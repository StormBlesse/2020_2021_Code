import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
 *
 * @author Gabriel Chen
 * @version 1.0
 * @userid gchen337m
 * @GTID 903561077
 *
 * Collaborators: Allison Lu
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is not within 0 and the size of the list");
        } else if (data == null) {
            throw new IllegalArgumentException("Cannot insert null data into data structure");
        }

        if (head == null) {
            head = new CircularSinglyLinkedListNode<>(data);
            head.setNext(head);
        } else if (index == 0) {
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());
            head.setData(data);
            head.setNext(node);
        } else if (index == size) {
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());
            head.setData(data);
            head.setNext(node);
            head = node;
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            int i = 0;
            while (i != index) {
                curr = curr.getNext();
                i++;
            }
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<>(curr.getData(), curr.getNext());
            curr.setData(data);
            curr.setNext(node);
        }
        size++;


    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data");
        }
        if (head == null) {
            head = new CircularSinglyLinkedListNode<>(data);
            head.setNext(head);
        } else {
            CircularSinglyLinkedListNode<T> node = new CircularSinglyLinkedListNode<>(head.getData(), head.getNext());
            head.setData(data);
            head.setNext(node);
        }
        size++;
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not within 0 and the size of the data structure");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else if (size == 1) {
            T ret = head.getData();
            head = null;
            size--;
            return ret;
        } else {
            T ret = null;
            int i = 0;
            CircularSinglyLinkedListNode<T> curr = head;
            while (i != index - 1) {
                curr = curr.getNext();
                i++;
            }
            ret = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return ret;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        } else if (size == 1) {
            T ret = head.getData();
            head = null;
            size--;
            return ret;
        } else {
            T ret = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
            size--;
            return ret;
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (head == null) {
            throw new NoSuchElementException("The list is empty");
        } else if (size == 1) {
            T ret = head.getData();
            head = null;
            size--;
            return ret;
        } else {
            T ret = null;
            CircularSinglyLinkedListNode<T> curr = head;
            while (curr.getNext().getNext() != head) {
                curr = curr.getNext();
            }
            ret = curr.getNext().getData();
            curr.setNext(curr.getNext().getNext());
            size--;
            return ret;
        }
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is not within 0 and the size of the data structure.");
        } else if (index == 0) {
            return head.getData();
        } else {
            CircularSinglyLinkedListNode<T> curr = head;
            int i = 0;
            while (i != index) {
                curr = curr.getNext();
                i++;
            }
            return curr.getData();
        }
    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data from data structure");
        } else if (isEmpty()) {
            throw new NoSuchElementException("Data structure is empty");
        }

        int index = 1;
        int loc = -1;
        CircularSinglyLinkedListNode<T> curr = head;
        CircularSinglyLinkedListNode<T> last = head;
        while (index < size) {
            if (data.equals(curr.getNext().getData())) {
                last = curr;
                loc = index;
            }
            curr = curr.getNext();
            index++;
        }
        if (loc == -1) {
            if (head.getData().equals(data)) {
                return removeFromFront();
            } else {
                throw new NoSuchElementException("The data could not be found within the data structure");
            }
        } else {
            T ret = last.getNext().getData();
            last.setNext(last.getNext().getNext());
            size--;
            return ret;
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> curr = head;
        for (int i = 0; i < size; i++) {
            arr[i] = curr.getData();
            curr = curr.getNext();
        }
        return arr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
