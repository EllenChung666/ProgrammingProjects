


import java.util.HashMap;


class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    class Entry {
        double priority;
        T item;

        Entry(T item, double priority) {
            this.priority = priority;
            this.item = item;
        }
    }

    int size;
    Entry[] table;
    //map item to it's position in the table;
    HashMap<T, Integer> n;


    ArrayHeapMinPQ() {
        table = new ArrayHeapMinPQ.Entry[16];
        n = new HashMap<>();
    }



    @Override
    public void add(T item, double priority) {


        if (table.length - 1 == size) {
            sizeUp();
        }
        size += 1;
        table[size] = new Entry(item, priority);
        n.put(item, size);
        swimUp(size);


    }

    void swimUp(int k) {
        if (k > 1) {
            if (checkAndSwap(k/2, k)) {
                swimUp(k / 2);
            }
        }
    }

    void sizeUp(){
        Entry[] newTable = new ArrayHeapMinPQ.Entry[table.length*2];
        for (int i = 1; i <= size; i++) {
            newTable[i] = table[i];
        }
        table = newTable;

    }

    @Override
    public boolean contains(T item) {
        return n.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        return table[1].item;
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }

        T target = table[1].item;


        n.remove(target);

        //last item goes to the front;

            table[1] = table[size];

        //last slot goes to null;
        table[size] = null;
        size -= 1;
        if (size != 0) {
            n.put(table[1].item, 1);
        }

        if ((size != 0) && (table.length/size > 4)) {
            sizeDown();
        }
        //swim down;
        swimDown(1);


        return target;
    }

    void sizeDown() {

        Entry[] newTable = new ArrayHeapMinPQ.Entry[table.length/2];
        for (int i = 1; i <= size; i++) {
            newTable[i] = table[i];
        }
        table = newTable;








    }

    void swimDown(int k) {
        if (2*k > size) {
            return;
        } else if (2*k == size) {
            checkAndSwap(k, 2*k);
        } else {
            if (table[2*k].priority < table[2*k+1].priority) {
                if (checkAndSwap(k, 2*k)) {
                    swimDown(2 * k);
                }
            } else {
                if (checkAndSwap(k, 2*k+1)) {
                    swimDown(2 * k + 1);
                }

            }
        }
    }

    //here i < j, so i.priority should be < then j.priority; returns (if swap happen);
    boolean checkAndSwap(int i, int j) {
        if (table[i].priority > table[j].priority) {
            swap(i,j);
            return true;
        }
        return false;
    }






    void swap(int i, int j) {
        Entry newI = table[j];
        Entry newJ = table[i];
        table[i] = newI;
        table[j] = newJ;

        n.put(table[i].item, i);
        n.put(table[j].item, j);
    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        int i = n.get(item);

        double oldPriority = table[i].priority;
        table[i].priority = priority;

        if (oldPriority < priority) {
            swimDown(n.get(item));
        } else {
            swimUp(n.get(item));
        }



    }
}
