package twowaylist;

import java.util.Optional;

public abstract class ParentList<T> {
    static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T val) {
            value = val;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    Node<T> cursor = null;

    public final int GET_OK = 1; // последний get() отработал нормально
    public final int GET_ERR = 2; // список пуст

    public final int HEAD_OK = 1; // последняя head() отработала нормально
    public final int HEAD_ERR = 2; // список пуст

    public final int TAIL_OK = 1; // последняя tail() отработала нормально
    public final int TAIL_ERR = 2; // список пуст

    public final int RIGHT_OK = 1; // последняя right() отработала нормально
    public final int RIGHT_ERR = 2; // курсор установлен на tail

    public final int PUT_RIGHT_OK = 1; // последняя put_right() отработала нормально
    public final int PUT_RIGHT_ERR = 2; // список пуст

    public final int PUT_LEFT_OK = 1; // последняя put_left() отработала нормально
    public final int PUT_LEFT_ERR = 2; // список пуст

    public final int REMOVE_OK = 1; // последняя remove() отработала нормально
    public final int REMOVE_ERR = 2; // список пуст

    public final int REPLACE_OK = 1; // последняя replace() отработала нормально
    public final int REPLACE_ERR = 2; // список пуст

    public final int FIND_NIL = 0; // список пуст
    public final int FIND_OK = 1; // последняя find() отработала нормально
    public final int FIND_ERR = 2; // не существует следующего узла со значением value

    private int get_status = GET_ERR;
    private int head_status = HEAD_ERR;
    private int tail_status = TAIL_ERR;
    private int right_status = RIGHT_ERR;
    private int put_right_status = PUT_RIGHT_ERR;
    private int put_left_status = PUT_LEFT_ERR;
    private int remove_status = REMOVE_ERR;
    private int replace_status = REPLACE_ERR;
    private int find_status = FIND_NIL;

    //запросы
    //предусловие: список не пустой
    public T get() {
        if (cursor == null) {
            get_status = GET_ERR;
        } else {
            get_status = GET_OK;
        }
        return cursor.value;
    }

    public boolean is_head() {
        return cursor != null && cursor == head;
    }
    public boolean is_tail() {
        return cursor != null && cursor == tail;
    }
    public boolean is_value() {
        return head != null;
    }
    public int size() {
        int size = 0;
        var sizeCursor = head;
        while (sizeCursor != null) {
            size++;
            sizeCursor = sizeCursor.next;
        }
     return size;
    }

    //команды

    //предусловие: список не пустой
    //постустловие: курсор установлен на первый узел в списке
    public void head() {
        if (cursor != null) {
            cursor = head;
            head_status = HEAD_OK;
        } else {
            head_status = HEAD_ERR;

        }
    }

    //предусловие: список не пустой
    //постусловие: курсор установлен на последний узел в списке
    public void tail() {
        if (cursor != null) {
            cursor = tail;
            tail_status = TAIL_OK;
        } else {
            tail_status = TAIL_ERR;

        }
    }

    //предусловие:курсор установлен не на tail
    //постусловие: курсор сдвинут на один узел вправо
    public void right() {
        if(cursor != tail) {
            cursor = cursor.next;
            right_status = RIGHT_OK;
        } else {
            right_status = RIGHT_ERR;
        }
    }

    //предусловие: список не пустой
    //постусловие: следом за текущим узлом добавлен узел со значением value
    public void put_right(T value) {
        if (cursor == null) {
            put_right_status = PUT_RIGHT_ERR;
            return;
        }
        Node<T> newNode = new Node<>(value);
        if (cursor != tail) {
            var cursorNextOld = cursor.next;
            newNode.next = cursorNextOld;
            cursorNextOld.prev = newNode;
        } else {
            tail = newNode;
        }
        cursor.next = newNode;
        newNode.prev = cursor;
        put_right_status = PUT_RIGHT_OK;
    }

    //предусловие: список не пустой
    //постусловие: перед текущим узлом добавлен узел со значением value
    public void put_left(T value) {
        if (cursor == null) {
            put_left_status = PUT_LEFT_ERR;
            return;
        }
        Node<T> newNode = new Node<>(value);
        if(cursor != head) {
            var cursorPrevOld = cursor.prev;
            newNode.prev = cursorPrevOld;
            cursorPrevOld.next = newNode;
        } else {
            head = newNode;
        }
        cursor.prev = newNode;
        newNode.next = cursor;
        put_left_status = PUT_LEFT_OK;
    }

    //предусловие: список не пустой
    //постусловие: текущий узел удален, курсор переместился на правого соседа, если он есть, иначе на левого, если он есть
    public void remove() {
        if (cursor == null) {
            remove_status = REMOVE_ERR;
            return;
        }
        var cursorNext = cursor.next;
        var cursorPrev = cursor.prev;
        if(cursor == head) {
            cursor = cursorNext;
            head = cursorNext;
        }
        if(cursor == tail) {
            cursor = cursorPrev;
            tail = cursorPrev;
        }
        cursorNext.prev = cursorPrev;
        cursorPrev.next = cursorNext;
        remove_status = REMOVE_OK;

    }

    //постусловие: список пустой
    public void clear() {
        cursor = null;
        head = null;
        tail = null;
    }

    //предусловие: список пустой
    //постусловие: список состоит из одного элемента со значением value
    public void add_to_empty(T value) {
        if (cursor == null) {
            var node = new Node<>(value);
            head = node;
            cursor = node;
            tail = node;
        }
    }

    //постусловие: в хвост списка добавлен элемент value
    public void add_tail(T value) {
        var currentCursor = cursor;
        cursor = tail;
        put_left(value);
        cursor = currentCursor;
    }

    //предусловие: список не пустой
    //постусловие: значение текущего узла заменено на value
    public void replace(T value) {
        if (cursor == null) {
            replace_status = REPLACE_ERR;
            return;
        }
        cursor.value = value;
        replace_status = REPLACE_OK;
    }

    //постусловие: из списка удалены все узлы со значением value
    public void remove_all(T value) {
        var currentCursor = cursor;
        cursor = head;
        while (cursor != null) {
            if(cursor.value == value) {
                remove();
            }
            cursor = cursor.next;
        }
        cursor = currentCursor;
    }

    //постусловие: курсор переставлен на следующий узел со значением value
    public void find(T value) {
        if (cursor == null) {
            find_status = FIND_ERR;
            return;
        }
        var currentCursor = cursor;
        while (cursor != null) {
            if(cursor.value == value) {
                break;
            }
            cursor = cursor.next;
        }
        if(cursor == null) {
            cursor = currentCursor;
        }
        find_status = FIND_OK;

    }

    public int get_head_status() {return head_status;} // возвращает значение HEAD_*
    public int get_tail_status() {return tail_status;} // возвращает значение TAIL_*
    public int get_right_status() {return right_status;} // возвращает значение RIGHT_*
    public int get_put_right_status() {return put_right_status;} // возвращает значение PUT_RIGHT_*
    public int get_put_left_status() {return put_left_status;} // возвращает значение PUT_LEFT_*
    public int get_remove_status() {return remove_status;} // возвращает значение REMOVE_*
    public int get_replace_status() {return replace_status;} // возвращает значение REPLACE_*
    public int get_find_status() {return find_status;} // возвращает значение FIND_*
    public int get_get_status() {return get_status;} // возвращает значение GET_*
}
