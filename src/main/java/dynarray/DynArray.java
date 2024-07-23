package dynarray;

import java.lang.reflect.Array;

import static dynarray.AbstractDynArray.GET_ITEM_ERR;
import static dynarray.AbstractDynArray.GET_ITEM_OK;
import static dynarray.AbstractDynArray.INSERT_ERR;
import static dynarray.AbstractDynArray.INSERT_OK;
import static dynarray.AbstractDynArray.REMOVE_ERR;
import static dynarray.AbstractDynArray.REMOVE_OK;

public class DynArray<T> {

    public T[] array;
    public int count; // текущее количество элементов в массиве
    public int capacity; // текущая емкость буфера

    private int insert_status;
    private int remove_status;
    private int get_item_status;

    Class clazz;

    public DynArray(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        var oldArray = array;
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        capacity = new_capacity;
        System.arraycopy(oldArray, 0, array, 0, oldArray.length);
    }

    public void append(T itm) {
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > capacity) {
            insert_status = INSERT_ERR;
            return;
        }
        if (count == capacity) {
            makeArray(capacity * 2);
        }
        System.arraycopy(array, index, array, index + 1, array.length - 1 - index);
        this.array[index] = itm;
        count++;
        insert_status = INSERT_OK;
    }

    public void remove(int index) {
        if (index < 0 || index > capacity) {
            remove_status = REMOVE_ERR;
            return;
        }
        if (count - 1 < capacity * 0.5 && capacity / 1.5 > 16) {
            makeArray((int) (capacity / 1.5));
        }
        if (count - 1 < capacity * 0.5 && capacity / 1.5 <= 16) {
            makeArray(16);
        }
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
        array[count - 1] = null;
        count--;
        remove_status = REMOVE_OK;
    }

    public T getItem(int index) {
        if (index < 0 || index > capacity) {
            get_item_status = GET_ITEM_ERR;
            return null;
        }
        get_item_status = GET_ITEM_OK;
        return array[index];
    }

    public int getInsert_status() {
        return insert_status;
    }

    public int getRemove_status() {
        return remove_status;
    }

    public int getGet_item_status() {
        return get_item_status;
    }
}