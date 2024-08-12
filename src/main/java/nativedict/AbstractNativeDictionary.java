package nativedict;

public abstract class AbstractNativeDictionary <T> {

    public final static int PUT_OK = 1; // успешно
    public final static int PUT_ERR = 2; // словарь заполнен

    public final static int REMOVE_OK = 1; // успешно
    public final static int REMOVE_ERR = 2; // в словаре нет такого ключа

    public final static int GET_OK = 1; // успешно
    public final static int GET_ERR = 2; // в словаре нет такого ключа

    // постусловие: создан пустой словарь
    public AbstractNativeDictionary() {}

    // команды
    // предусловие: в словаре есть свободный слот для пары
    // постусловие: в таблицу добавлена новая пара ключ-значение
    public abstract void put(String key, T value);

    // предусловие: в таблице имеется ключ key
    // постусловие: из таблицы удалена пара key и соответствующее значение
    public abstract void remove(String key);

    // запросы
    // предусловие: в словаре есть ключ key
    public abstract T get(String key);

    public abstract boolean isKey(String key);

    public abstract int size();

    public abstract int getPutStatus();
    public abstract int getRemoveStatus();
    public abstract int getGetStatus();
}
