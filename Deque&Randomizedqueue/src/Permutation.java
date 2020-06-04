import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        while(!StdIn.isEmpty()) {
            String item = StdIn.readString();
            rq.enqueue(item);
        }
        
        int k = Integer.parseInt(args[0]);

        Iterator<String> i = rq.iterator();
        while(i.hasNext() && k > 0) {
        	StdOut.println(i.next());
        	k--;
        }
    }
}
