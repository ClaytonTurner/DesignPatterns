package com.turner;


import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class implements a List by means of a linked data structure.
 * A List (also known as a <i>sequence</i>) is an ordered collection.
 * Elements in the list can be accessed by their integer index.  The
 * index of the first element in the list is zero.
 */
public class LinkedList<E> implements Iterable<E>
{
    private Node<E> head;
    private int size;


    /**
     * A list node contains the data value and a link to the next
     * node in the linked list.
     */
    private static class Node<E>
    {
        private E data;
        private Node<E> next;


        /**
         * Construct a node with the specified data value and link.
         * No null check on data - you want null data? You got it!
         * Null constructor already exists for Node
         */
        public Node(E data, Node<E> next)
        {
            this.data = data;
            this.next = next;
        }


        /**
         * Construct a node with the given data value
         */
        public Node(E data)
        {
            this(data, null);
        }
    }


    /**
     *  An iterator for this singly-linked list.
     */
    private static class LinkedListIterator<E> implements Iterator<E>
    {
        private Node<E> nextElement;


        /**
         * Construct an iterator initialized to the first element in the list.
         */
        public LinkedListIterator(Node<E> head)
        {
            nextElement = head;
        }


        /**
         * Returns true if the iteration has more elements.
         */
        @Override
        public boolean hasNext()
        {
            boolean returnValue = true;
            if(this.nextElement == null){
                returnValue = false;
            }
            return returnValue;
        }


        /**
         * Returns the next element in the list.
         *
         * throws NoSuchElementException if the iteration has no next element.
         */
        @Override
        public E next()
        {
            if(this.hasNext()){
                // Proceed as normal
                return (E) nextElement.next;
            }
            else{
                NoSuchElementException e = new NoSuchElementException();
                System.out.println("No next element. Throwing exception.");
                throw e;
            }
        }

        // Note: Do not have to implement other methods in interface
        // Iterator since they have default implementations.
        // Unfortunately, IntelliJ throws a fit if I don't
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Helper method: Find the node at a specified index.
     *
     * @return a reference to the node at the specified index
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    private Node<E> getNode(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        Node<E> node = head;

        for (int i = 0;  i < index;  ++i)
            node = node.next;

        return node;
    }


    /**
     * Constructs an empty list.
     * Null head since we have no elements.
     * Size is 0 if no elements are present.
     */
    public LinkedList()
    {
        this.head = null;
        this.size = 0;
    }


    /**
     * Appends the specified element to the end of the list.
     */
    public void add(E element)
    {
        if (isEmpty())
            head = new Node<E>(element);
        else
        {
            Node<E> lastNode = getNode(size - 1);
            lastNode.next = new Node<E>(element);
        }
        ++size;
    }


    /**
     * Inserts the specified element at the specified position in the list.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    public void add(int index, E element)
    {
        if(index > this.size){
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            System.out.println("Index is out of range. Throwing exception.");
            throw e;
        }
        // So we know we fit in the list if we made it this far
        if(head == null){
            add(element);
        }
        else {
            Node<E> current; // init it for the 0 case
            Node<E> toInsert = new Node<E>(element); // init the new node
            // So we know the node to insert is next
            // This isn't the most elegant path, but it's readable
            if (index == 0) {
                // edge case => beginning of list
                current = getNode(index);
                this.head = toInsert;
                toInsert.next = current;
            } else if (index == size) {
                // edge case => end of list
                current = getNode(index-1); // So we don't go out of bounds
                current.next = toInsert;
            } else {
                current = getNode(index);
                Node<E> previous = getNode(index - 1);
                previous.next = toInsert;
                toInsert.next = current;
            }
            size++; // here since head == null case has its own size incrementer
        }

    }


    /**
     * Removes all of the elements from this list.
     */
    public void clear()
    {
        while (head != null)
        {
            Node<E> temp = head;
            head = head.next;

            temp.data = null;
            temp.next = null;
        }

        size = 0;
    }


    /**
     * Returns the element at the specified position in this list.
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E get(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(Integer.toString(index));

        Node<E> node = getNode(index);
        return node.data;
    }


    /**
     * Replaces the element at the specified position in this list
     * with the specified element.
     *
     * @returns The data value previously at index
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E set(int index, E newValue)
    {
        if(index > this.size){
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            System.out.println("Index is out of range. Throwing exception.");
            throw e;
        }
        E oldDataToReturn = get(index);
        Node<E> n = getNode(index);
        n.data = newValue;
        return oldDataToReturn;
    }


    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     */
    public int indexOf(Object obj)
    {
        int index = 0;

        if (obj == null)
        {
            for (Node<E> node = head;  node != null;  node = node.next)
            {
                if (node.data == null)
                    return index;
                else
                    index++;
            }
        }
        else
        {
            for (Node<E> node = head;  node != null;  node = node.next)
            {
                if (obj.equals(node.data))
                    return index;
                else
                    index++;
            }
        }

        return -1;
    }


    /**
     * Returns <tt>true</tt> if this list contains no elements.
     */
    public boolean isEmpty()
    {
        if(this.size == 0){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Removes the element at the specified position in this list.  Shifts
     * any subsequent elements to the left (subtracts one from their indices).
     *
     * @returns the element previously at the specified position
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public E remove(int index)
    {
        if(index > this.size){
            IndexOutOfBoundsException e = new IndexOutOfBoundsException();
            System.out.println("Index is out of range. Throwing exception.");
            throw e;
        }
        E removed_element = get(index);

        if(index == 0){
            // edge case => beginning of list
            if(size == 1){
                this.head = null;
            }
            else{
                this.head = getNode(1);
            }
        }
        else if(index == size-1){
            // edge case => end of list
            Node<E> n = getNode(index-1);
            n.next = null;
        }
        else{
            Node<E> previous = getNode(index-1);
            Node<E> next = getNode(index+1);
            previous.next = next;
        }

        size--;
        return removed_element;
    }


    /**
     * Returns the number of elements in this list.
     */
    public int size()
    {
        int my_size = 0;
        // Better readability this way
        if(head == null){
            return my_size;
        }
        Node current = head;
        while(current != null){
            my_size++;
            current = current.next;
        }
        return my_size;
    }


    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<E> iterator()
    {
        //TODO
        return null;
    }


    /**
     * Returns a string representation of this list.
     */
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node current = head;
        while(current != null){
            s.append(current.data);
            s.append(", ");
            current = current.next;
        }
        if(s.length() > 2) {
            // so we remove that last comma if we added at least 1 element
            s.deleteCharAt(s.length() - 1);
            // and the space that comes with it
            s.deleteCharAt(s.length() - 1);
        }
        s.append("]");
        return s.toString();
    }


    /*
     * Compares the specified object with this list for equality. Returns true
     * if and only if both lists contain the same elements in the same order.
     */
    @Override
    @SuppressWarnings("rawtypes")
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;

        if (!(obj instanceof LinkedList))
            return false;

        // cast obj to a linked list
        LinkedList listObj = (LinkedList) obj;

        // compare elements in order
        Node<E> node1 = head;
        Node    node2 = listObj.head;

        while (node1 != null && node2 != null)
        {
            // check to see if data values are equal
            if (node1.data == null)
            {
                if (node2.data != null)
                    return false;
            }
            else
            {
                if (!node1.data.equals(node2.data))
                    return false;
            }

            node1 = node1.next;
            node2 = node2.next;
        }

        return node1 == null && node2 == null;
    }


    /**
     * Returns the hash code value for this list.
     */
    @Override
    public int hashCode()
    {
        int hashCode = 1;
        Node<E> node = head;

        while (node != null)
        {
            E obj = node.data;
            hashCode = 31 * hashCode + (obj == null ? 0 : obj.hashCode());
            node = node.next;
        }

        return hashCode;
    }
}