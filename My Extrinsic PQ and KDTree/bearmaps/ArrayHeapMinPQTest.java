package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import bearmaps.ArrayHeapMinPQ;

public class ArrayHeapMinPQTest{

    public static void main(String args[]){

    ArrayHeapMinPQ<Double> test = new ArrayHeapMinPQ<>();




        for (int i = 1; i < 100; i++) {
            double d = Math.random();
            test.add(d,d );
        }

        for (int j = 1; j < 10; j++) {
            System.out.println(test.removeSmallest());
        }

















    }

}
