package dynarray;

public abstract class AbstractDynArray<T> {

    public static final int INSERT_OK = 1;
    public static final int INSERT_ERR = 2;

    public static final int REMOVE_OK = 1;
    public static final int REMOVE_ERR = 2;

    public static final int GET_ITEM_OK = 1;
    public static final int GET_ITEM_ERR = 2;

    // постусловие: создан динамический массив с емкостью буфера 16
    public AbstractDynArray() {
    }

    // команды

    //постусловие: в конец массива добавлен новый элемент itm. Если полученное количество элементов массива превысило емкость
    //буфера, то буфер увеличивается в 2 раза
    public abstract void append(T itm);

    // предусловие: index неотрицательный, index меньше либо равен количеству элементов в массиве
    // постусловие: в позицию index вставлен элемент itm. Если полученное количество элементов массива превысило емкость
    // буфера, то буфер увеличивается в 2 раза
    public abstract void insert(T itm, int index);

    // предусловие: index неотрицательный, index меньше количества элементов в массиве
    // постусловие: из массива удален элемент с индексом index? если полученное количество элементов массива стало
    // строго меньше 50% размера буфера, то размер буфера делиться на 1.5 и приводится в целому типу. При этом
    // минимальная емкость остается 16
    public abstract void remove(int index);


    // запросы

    // предусловие: index неотрицательный, index меньше количества элементов в массиве
    // предусловие: в динамическом массиве есть элементы
    public abstract T getItem(int index);

    public abstract int get_insert_status();
    public abstract int get_remove_status();
    public abstract int get_get_item_status();
}
