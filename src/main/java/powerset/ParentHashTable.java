package powerset;

import hashtable.AbstractHashTable;

public class ParentHashTable<T> extends AbstractHashTable<T> {

    public int size;
    public int step = 1;
    public T[] slots;

    private int putStatus;

    public ParentHashTable(int sz) {
        size = sz;
        slots = (T[]) new Object[size];
        for (int i = 0; i < size; i+=step) slots[i] = null;
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
            ind+=step;
            if(ind == size) {
                ind = 0;
            }
        }
        return -1;
    }

    // предусловие: хэш-таблица не заполнена
    // постусловие: в таблицу добавлен новый элемент
    public void put(T value) {
        int index = seekSlot(value);
        if (index == -1) {
            putStatus = PUT_ERR;
            return;
        }
        slots[index] = value;
        putStatus = PUT_OK;
    }

    // ПРЕДУСЛОВИЕ: в таблице имеется значение value
    // постусловие: из таблицы удален элемент value
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

    public int size() {
        int size = 0;
        for(T s: slots) {
            if(s != null) {
                size++;
            }
        }
        return size;
    }

    public int getPutStatus() { return putStatus; }
}
