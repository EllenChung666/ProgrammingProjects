import java.util.Iterator;
import java.util.Set;

/** A data structure that uses a linked list to store pairs of keys and values.
 *  Any key must appear at most once in the dictionary, but values may appear multiple
 *  times. Key operations are get(key), put(key, value), and contains(key) methods. The value
 *  associated to a key is the value in the last call to put with that key. */
public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {

    private Node root;

    BSTMap() {

    }


    private class Node {
        private K key;
        private V value;
        private Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }




    }



    @Override
    public Iterator<K> iterator() {
        return new iterBST<> ();


    }

   



    private int helpSize(Node n) {
        if (n == null || n.key == null) {
            return 0;
        } else {
            return 1 + helpSize(n.left) + helpSize(n.right);
        }


    }



    @Override
    public int size() {
        return helpSize(root);

    }

    @Override
    public void clear() {
        root = null;


    }

    private boolean helpContains(K key, Node n) {
        if (n == null || n.key == null) {
            return false;
        } else if (key.compareTo(n.key) < 0) {
            return helpContains(key, n.left);
        } else if (key.compareTo(n.key) > 0) {
            return helpContains(key, n.right);
        } else {
            return true;
        }

    }


    @Override
    public boolean containsKey(K key) {
        return helpContains(key, root);



    }

    private V helpGet(K key, Node n) {
        if (n == null || n.key == null) {
            return null;
        } else if (key.compareTo(n.key) < 0) {
            return helpGet(key, n.left);
        } else if (key.compareTo(n.key) > 0) {
            return helpGet(key, n.right);
        } else {
            return n.value;
        }


    }


    @Override
    public V get(K key) {
        return helpGet(key, root);


    }

    private void helpPut(K key, V value, Node n) {

        if (n.key == null || n.key == key) {
            n.key = key;
            n.value = value;
        } else if (key.compareTo(n.key) < 0) {
            if (n.left == null) {
                n.left = new Node(key, value);
            } else {
                helpPut(key, value, n.left);
            }
        } else if (key.compareTo(n.key) > 0) {
            if (n.right == null) {
                n.right = new Node(key, value);
            } else {
                helpPut(key, value, n.right);
            }
        }
    }




    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
        } else {
            helpPut(key, value, root);
        }





    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();

    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


}


