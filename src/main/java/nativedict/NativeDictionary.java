package nativedict;

public class NativeDictionary<T> extends AbstractNativeDictionary<T> {

    public int size;
    public int step = 1;
    public String[] keySlots;
    public T[] valueSlots;

    private int putStatus;
    private int removeStatus;
    private int getStatus;

    public NativeDictionary(int size) {
        this.size = size;
        keySlots = new String[size];
        valueSlots = (T[]) new Object[size];
    }

    private int hashFun(String key) {
        return key.hashCode() % size;
    }

    private int seekSlot(String key) {
        int i = hashFun(key);
        if (keySlots[i].equals(key)) {
            return i;
        }
        int ind = i;
        while (ind !=  i) {
            if(keySlots[ind].equals(key)) {
                return ind;
            }
            ind = ind + step;
            if(ind == size) {
                ind = 0;
            }
        }
        return -1;
    }

    private int seekNewSlot(String key) {
        int i = hashFun(key);
        if (keySlots[i] == null || keySlots[i].equals(key)) {
            return i;
        }
        int ind = i;
        while (ind !=  i) {
            if(keySlots[ind] == null || keySlots[ind].equals(key)) {
                return ind;
            }
            ind = ind + step;
            if(ind == size) {
                ind = 0;
            }
        }
        return -1;
    }

    public void put(String key, T value) {
        int index = seekNewSlot(key);
        if (index == -1) {
            putStatus = PUT_ERR;
            return;
        }
        keySlots[index] = key;
        valueSlots[index] = value;
        putStatus = PUT_OK;
    }

    public void remove(String key) {
        int index = seekSlot(key);
        if (index == -1) {
            removeStatus = REMOVE_ERR;
            return;
        }
        removeStatus = REMOVE_OK;
        keySlots[index] = null;
        valueSlots[index] = null;
    }

    public T get(String key) {
        int index = seekSlot(key);
        if (index == -1) {
            getStatus = GET_ERR;
            return null;
        }
        getStatus = GET_OK;
        return valueSlots[index];
    }

    public boolean isKey(String key) {
        int index = seekSlot(key);
        return index != -1;
    }

    public int size() {
        int size = 0;
        for(String s: keySlots) {
            if(s != null) {
                size++;
            }
        }
        return size;
    }

    public int getPutStatus() { return putStatus; }
    public int getRemoveStatus() { return removeStatus; }
    public int getGetStatus() { return getStatus; }
}
