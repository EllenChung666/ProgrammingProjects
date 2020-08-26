import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;




public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Entry{
        K key;
        V value;

      

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

      
    }








    LinkedList<Entry>[] bins;
    int size;
    double loadFactor;
    HashSet<K> set;






    private int capacity() {
        return this.bins.length;
    }


    MyHashMap() {
        this(16, 0.75);
    }

    MyHashMap (int initialSize) {
        this(initialSize, 0.75);
    }

    MyHashMap(int initialSize, double loadFactor) {
        bins = new LinkedList[initialSize];
        this.loadFactor = loadFactor;
        set = new HashSet();



    }



    private int binNum(K key) {
        return (key.hashCode() & 0x7fffffff) % this.capacity();
    }





    private Entry find1(K key, LinkedList<Entry> l) {
        if (l != null) {
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).key.equals(key)) {
                    return l.get(i);
                }
            }
        }
        return null;
    }


    private Entry find2(K key) {

        return find1(key, bins[binNum(key)]);
    }
















    @Override
    public void clear() {
        bins = new LinkedList[this.capacity()];
        size = 0;
        set.clear();


    }







    @Override
    public boolean containsKey(K key) {
        if (find2(key) == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public V get(K key) {
        Entry result = find2(key);
        if (result == null) {
            return null;
        } else {
            return result.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void put (Entry e) {
        put(e.key, e.value);
    }

    @Override
    public void put(K key, V value) {
        Entry result = find2(key);
        if (result == null) {
            size += 1;
            set.add(key);
            int binNum = binNum(key);
            if (bins[binNum] == null) {
                bins[binNum] = new LinkedList<>();
            }
            bins[binNum].addFirst(new Entry(key, value));

            if (size > loadFactor * this.capacity()) {
                resize();
            }

        } else {
            result.value = value;


        }
    }


    private void resize() {
        MyHashMap<K, V> temp = new MyHashMap<>(this.capacity() * 3, this.loadFactor);
        for (int j = 0; j < this.capacity(); j++) {

            if (bins[j] != null) {
                for (int i = 0; i < bins[j].size(); i++) {
                    temp.put(bins[j].get(i));

                }
            }

        }
        this.bins = temp.bins;


    }



    @Override
    public Set<K> keySet() {
        return set;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}