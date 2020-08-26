package bearmaps;
import java.util.List;

public class KDTree {

    private class Node {
        Point item;
        Node left;
        Node right;
        int dimension;


        Node(Point item) {
            this.item = item;
        }


    }

    Node root;


    private void KDTreehelpSearch(Point p, Node n, int dimension) {

        if (relativity(p, n, dimension) < 0) {
            // going to the left
            if (n.left == null) {
                n.left = new Node(p);
            } else {
                KDTreehelpSearch(p, n.left, (-1) * dimension);
            }
        } else {
            if (n.right == null) {
                n.right = new Node(p);
            } else {
                KDTreehelpSearch(p, n.right, (-1) * dimension);
            }

        }

    }


    public KDTree(List<Point> points) {
        root = new Node(points.get(0));
        for (int i = 1; i <= points.size() - 1; i++) {
            KDTreehelpSearch(points.get(i), root, 1);
        }


    }


    // n can not be 0;


    public Point nearest(double x, double y) {
        MySearch specificSearch = new MySearch(x, y);

        specificSearch.helpSearch(root, 1);
        return specificSearch.currentBestPoint;


    }


    // one search class
    private class MySearch {
        Point myPoint; // the point given for you to find the closest distance of which;
        Point currentBestPoint;
        double currentBestDistance;

        MySearch(double x, double y) {
            myPoint = new Point(x, y);
            currentBestDistance = Double.MAX_VALUE;
        }

        // given current state, check if given node.item is a better point and update;
        void helpSearch(Node n, int dimension) {
            if (n != null) {
                double currentDistance = Point.distance(n.item, myPoint);
                if (currentDistance < currentBestDistance) {
                    // update
                    currentBestDistance = currentDistance;
                    currentBestPoint = n.item;
                }
                // dimension plus relative position of n.item and myPoint will decide if we
                // visit it's left child or right child first
                if (relativity(myPoint, n, dimension) < 0) {
                    helpSearch(n.left, -dimension);
                    if (relativity(myPoint, n, dimension) < currentBestDistance) {
                        helpSearch(n.right, -dimension);
                    }
                } else {
                    helpSearch(n.right, -dimension);
                    if (relativity(myPoint, n, dimension) < currentBestDistance) {
                        helpSearch(n.left, -dimension);
                    }
                }

            }
        }


    }

    double relativity(Point p, Node n, int dimension) {
        if (dimension == 1) {
            return p.getX() - n.item.getX();
        } else {
            return p.getY() - n.item.getY();
        }
    }

}





