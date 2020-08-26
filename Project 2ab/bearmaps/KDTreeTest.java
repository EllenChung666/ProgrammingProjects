package bearmaps;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    public static void main(String args[]) {



        List<Point> list= new ArrayList();

       for (int i = 0; i < 10000; i++) {
           list.add(new Point(Math.random(), Math.random()));

       }

       double x = Math.random();
       double y = Math.random();

       KDTree kdt = new KDTree(list);
       NaivePointSet nps = new NaivePointSet(list);
       assertEquals(kdt.nearest(x,y), nps.nearest(x,y));


    }



}
