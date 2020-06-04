import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
/*
 * Memory usage: Object overhead: 16 bytes
 *              inner class overhead: 8 bytes
 *              reference to Node: 8 * 2 bytes
 *              int type: 4 bytes
 *              in total: 44 bytes * n + extra overhead
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of deque
    private Node<Item> last;     // end of deque
    private int n;               // number of elements on deque
    
    // helper linked list class, inner class
    private class Node<Item> {
        private Item item;
        private Node<Item> previous;
        private Node<Item> next;
    }

    /*
     *  Initialize an empty deque
     */
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    /*
     * Returns true if this deque is empty
     * 
     * @return {@code true} if this deque is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return (this.first == null || this.last == null);
    }

    /*
     * Return the number of items on the deque
     * 
     * @return the number of items on the deque
     */
    public int size() {
        return this.n;
    }

    /*
     *  Adds the item at the head of deque
     *  
     *  @param item the item to add at the head of deuqe
     */
    public void addFirst(Item item) {
        validate(item);
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.previous = null;
        if (isEmpty())
            last = first;
        else {
            first.next = oldFirst;
            oldFirst.previous = first;
        }
        this.n++;
    }

    /*
     *  Adds the item at the tail of deque
     *  
     *  @param item the item to add at the tail of deuqe
     */
    public void addLast(Item item) {
        validate(item);
        Node<Item> lastOld = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else {
            lastOld.next = last;
            last.previous = lastOld;
        }
        this.n++;
    }

    /*
     * Removes and returns the item at the head of deque
     * 
     * @return the item that been deleted
     * @throws java.util.NoSuchElementException if the deuqe is empty
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        else
            first.previous = null;// to avoid loitering
        this.n--;
        return item;
    }

    /*
     * Removes and returns the item at the tail of deque
     * 
     * @return the item that been deleted
     * @throws java.util.NoSuchElementException if the deuqe is empty
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;
        Node<Item> oldLast = last;
        last = oldLast.previous;
        if (isEmpty())
            first = null;
        else
            last.next = null;    // to avoid loitering
        this.n--;
        return item;
    }

    /*
     * Returns an iterator that iterates over the items in this deque in order from front to back
     * 
     * @throws NoSuchElementException if next() method in the iterator has been called when there are no more items to return.
     * @throws UnsupportedOperationException if remove() method in the iterator has been called.
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }
    
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current  = first;
        }
        
        public boolean hasNext() {
            return current != null;
        }
        
        public void remove() {
        	throw new UnsupportedOperationException();
        }
        
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /*
     *  Validates the new added item
     *  
     *  @throws IllegalArgumentException if new added node is null
     */
    private void validate(Item temp) {
        if (temp == null)
            throw new IllegalArgumentException();
    }

    /*
     * Unit tests the {@code deque} data type
     * 
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // test constructor of deque
        Deque<Integer> deque = new Deque<Integer>();
//        Deque<String> deque2 = new Deque<String>();
//        // test addLast(), addFirst() method
//        while(!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            deque1.addLast(item);
//            deque2.addFirst(item);
//        }
//        // test isEmpty(), size() method
//        int len1 = 0;
//        int len2 = 0;
//        if (!deque1.isEmpty())
//            len1 = deque1.size();
//        if (!deque2.isEmpty())
//            len2 = deque1.size();
////        // test iterator
//        Iterator<String> i = deque1.iterator();
//        while(i.hasNext()) {
//            StdOut.println(i.next());
//        }
////        //test removeFirst(), removeLast() method
//        while(!deque1.isEmpty()) {
//            deque1.removeFirst();
//        }
//        while(!deque2.isEmpty()) {
//            deque1.removeLast();
//        }
        System.out.println(deque.isEmpty());
        deque.addFirst(2);
        deque.addFirst(3);
        System.out.println(deque.isEmpty());
        System.out.println(deque.isEmpty());
        deque.removeLast();
        deque.removeLast();
    }
}
