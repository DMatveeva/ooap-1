package hashtable;

public abstract class AbstractHashTable<T> {

    public final static int PUT_ERR = 1; // успешно
    public final static int PUT_OK = 2; // хэш-таблица заполнена

    public AbstractHashTable() {}

    // команды

    // предусловие: хэш-таблица не заполнена
    // постусловие: в таблицу добавлен новый элемент
    public abstract void put(T value);

    // постусловие: из таблицы удален элемент value
    public abstract void remove(T value);

    // запросы
    public abstract boolean contains(T value);

    public abstract int getPutStatus();
}
