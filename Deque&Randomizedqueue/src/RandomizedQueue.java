import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
/*
 * Memory used: Object overhead: 16 bytes
 *              reference to Item[]: 8 bytes
 *              int type: 4 * 3 bytes
 *              in total: 36 * n bytes + extra overhead
 */
public class RandomizedQueue<Item> implements Iterable<Item>{
    private Item[] q;         // RandomizedQueue elements
    private int n;            // number of elements on RandomizedQueue
    private int lastIndex;    // next available slot
    //private Item[] randomQ;

    /*
     *  Initialize an empty RandomizedQueue
     */
    public RandomizedQueue() {
        this.q = (Item[])new Object[2];
        this.n = 0;
        this.lastIndex = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.n;
    }

    // add the item
    public void enqueue(Item item) {
        validate(item);
        if (this.lastIndex == this.q.length)
            resize(2 * this.q.length);
        this.q[this.lastIndex++] = item;
        this.n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int index;
        do {
            index = StdRandom.uniform(lastIndex);
            }while(this.q[index] == null);
        index = StdRandom.uniform(lastIndex);
        Item item = this.q[index];
        this.q[index] = null;
        if(index == this.lastIndex - 1)
            this.lastIndex--;
        this.n--;
        if(this.n > 0 && this.n == this.q.length / 4)
            resize(this.q.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (isEmpty())
            throw new NoSuchElementException();
    	int index;
    	do {
    		index = StdRandom.uniform(lastIndex);
    	} while(this.q[index] == null);
        return this.q[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
    	resize(this.n);
        return new ResizingArrayIterator();
    }

    private class ResizingArrayIterator implements Iterator<Item> {
    	private int callCount = n;
    	private Item[] randomQ = q.clone();

        public boolean hasNext() {
            return (!isEmpty() && this.callCount > 0);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = dequeueRandom();
            this.callCount--;
            return item;
        }
        
        private Item dequeueRandom() {
        	int index;
        	do {
        		index = StdRandom.uniform(lastIndex);
        	}while(randomQ[index] == null);
        	Item item = randomQ[index];
        	randomQ[index] = null;
        	return item;
        }
    }

    private void validate(Item temp) {
        if(temp == null)
            throw new IllegalArgumentException();
    }
    

    private void resize(int capacity) {
        Item[] copy = (Item[])new Object[capacity];
        int qIndex = 0;
        for(int cIndex = 0; cIndex < capacity; cIndex++) {
        	if(qIndex >= this.lastIndex)
        		continue;
        	while (q[qIndex] == null && qIndex < this.lastIndex) {
        		qIndex++;
        	}
        	copy[cIndex] = q[qIndex];
        	qIndex++;
        }
            
        q = copy;
        this.lastIndex = this.n;
    }

    public static void main(String[] args) {
    	int n = 5;
    	RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    	for (int i = 0; i < n; i++)
    	    rq.enqueue(i);
    	for (int a : rq) {
    	    for (int b : rq)
    	        StdOut.print(a + "-" + b + " ");
    	    StdOut.println();
    	}
    }

}
