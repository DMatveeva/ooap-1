package hashtable;

public class HashTable<T> extends AbstractHashTable<T> {

    public int size;
    public int step = 1;
    public T[] slots;

    private int putStatus;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = (T[]) new Object[size];
        for (int i = 0; i < size; i++) slots[i] = null;
    }

    public int hashFun(T value) {
        return value.hashCode() % size;
    }

    public int seekSlot(T value) {
        int i = hashFun(value);
        if (slots[i] == null || slots[i] == value) {
            return i;
        }
        int ind = i;
        while (ind !=  i) {
            if(slots[ind] == null || slots[ind] == value) {
                return ind;
            }
            ind++;
            if(ind == size) {
                ind = 0;
            }
        }
        return -1;
    }

    public void put(T value) {
        int index = seekSlot(value);
        if (index == -1) {
            putStatus = PUT_ERR;
            return;
        }
        slots[index] = value;
        putStatus = PUT_OK;
    }

    public void remove(T value) {
        int index = seekSlot(value);
        if (index == -1) {
            putStatus = PUT_ERR;
            return;
        }
        slots[index] = null;
    }

    public boolean contains(T value) {
        int index = seekSlot(value);
        return slots[index] == value;
    }

    public int getPutStatus() { return putStatus; }
}
