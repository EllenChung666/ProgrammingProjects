package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {

    List<Point> pointSet;


    public NaivePointSet(List<Point> points) {

        pointSet = points;

    }


    @Override
    public Point nearest(double x, double y) {
        Point center = new Point(x,y);

        Point currentBestPoint = null;
        double currentBestValue = Double.MAX_VALUE;

        for (Point p : pointSet) {
            double thisValue = Point.distance(center, p);
            if (thisValue < currentBestValue) {
                currentBestPoint = p;
                currentBestValue = thisValue;
            }
        }

        return currentBestPoint;
    }
}
