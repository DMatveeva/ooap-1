package linkedlist;

public abstract class AbstractLinkedList<T> {

    public final int GET_OK = 1; // последний get() отработал нормально
    public final int GET_ERR = 2; // список пуст

    public final int IS_HEAD_OK = 1; // последний is_head() отработал нормально
    public final int IS_HEAD_ERR = 2; // список пуст

    public final int IS_TAIL_OK = 1; // последний is_tail() отработал нормально
    public final int IS_TAIL_ERR = 2; // список пуст

    public final int HEAD_OK = 1; // последняя head() отработала нормально
    public final int HEAD_ERR = 2; // список пуст

    public final int TAIL_OK = 1; // последняя tail() отработала нормально
    public final int TAIL_ERR = 2; // список пуст

    public final int RIGHT_NIL = 0; // список пуст
    public final int RIGHT_OK = 1; // последняя right() отработала нормально
    public final int RIGHT_ERR = 2; // курсор установлен на tail

    public final int PUT_RIGHT_OK = 1; // последняя put_right() отработала нормально
    public final int PUT_RIGHT_ERR = 2; // список пуст

    public final int PUT_LEFT_OK = 1; // последняя put_left() отработала нормально
    public final int PUT_LEFT_ERR = 2; // список пуст

    public final int REMOVE_OK = 1; // последняя remove() отработала нормально
    public final int REMOVE_ERR = 2; // список пуст

    public final int ADD_TO_EMPTY_OK = 1; // последняя add_to_empty() отработала нормально
    public final int ADD_TO_EMPTY_ERR = 2; // список не пуст

    public final int ADD_TAIL_OK = 1; // последняя add_tail() отработала нормально
    public final int ADD_TAIL_ERR = 2; // список пуст

    public final int REPLACE_OK = 1; // последняя replace() отработала нормально
    public final int REPLACE_ERR = 2; // список пуст

    public final int REMOVE_ALL_OK = 1; // последняя remove_all() отработала нормально
    public final int REMOVE_ALL_ERR = 2; // список пуст

    public final int FIND_NIL = 0; // список пуст
    public final int FIND_OK = 1; // последняя find() отработала нормально
    public final int FIND_ERR = 2; // не существует следующего узла со значением value


    //постусловие: создан новый связный список
    public AbstractLinkedList() {}

    //запросы

    //предусловие: список не пустой
    public abstract T get();

    //предусловие: список не пустой
    public abstract boolean is_head();

    //предусловие: список не пустой
    public abstract boolean is_tail();

    public abstract boolean is_value();

    public abstract int size();

    //команды

    //предусловие: список не пустой
    public abstract void head();

    //предусловие: список не пустой
    public abstract void tail();

    //предусловие: cписок не пуст, курсор установлен не на tail
    public abstract void right();

    //предусловие: список не пустой
    //постусловие: следом за текущим узлом добавлен узел со значением value
    public abstract void put_right(T value);

    //предусловие: список не пустой
    //постусловие: перед текущим узлом добавлен узел со значением value
    public abstract void put_left(T value);

    //предусловие: список не пустой
    //постусловие: текущий узел удален, курсор переместился на правого соседа, если он есть, иначе на левого, если он есть
    public abstract void remove();

    //постусловие: список пустой
    public abstract void clear();

    //предусловие: список пустой
    //постусловие: список состоит из одного элемента со значением value
    public abstract void add_to_empty(T value);

    //предусловие: список не пустой
    //постусловие: в хвост списка добавлен элемент value
    public abstract void add_tail(T value);

    //предусловие: список не пустой
    //постусловие: значение текущего узла заменено на value
    public abstract void replace(T value);

    //предусловие: список не пустой
    //постусловие: из списка удалены все узлы со значением value
    public abstract void remove_all(T value);

    //предусловие: список не пустой, правее существует еще один узел со значением value
    //постусловие: курсор переставлен на следующий узел со значением value
    public abstract void find(T value);

    public abstract int get_get_status(); // возвращает значение GET_*
    public abstract int get_is_head_status(); // возвращает значение IS_HEAD_*
    public abstract int get_is_tail_status(); // возвращает значение IS_TAIL_*
    public abstract int get_head_status(); // возвращает значение HEAD_*
    public abstract int get_tail_status(); // возвращает значение TAIL_*
    public abstract int get_right_status(); // возвращает значение RIGHT_*
    public abstract int get_put_right_status(); // возвращает значение PUT_RIGHT_*
    public abstract int get_put_left_status(); // возвращает значение PUT_LEFT_*
    public abstract int get_remove_status(); // возвращает значение REMOVE_*
    public abstract int get_add_to_empty_status(); // возвращает значение ADD_TO_EMPTY_*
    public abstract int get_add_tail_status(); // возвращает значение ADD_TAIL_*
    public abstract int get_replace_status(); // возвращает значение REPLACE_*
    public abstract int get_remove_all_status(); // возвращает значение REMOVE_ALL_*
    public abstract int get_find_status(); // возвращает значение FIND_*
}
