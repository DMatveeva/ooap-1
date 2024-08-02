package deque;

import java.util.List;

public class Deque<T> extends ParentQueue<T> {

    private int remove_tail_status;
    private int get_tail_status;

    public int REMOVE_TAIL_OK; // успешно
    public int REMOVE_TAIL_ERR; // очередь пуста

    public int GET_TAIL_OK; // успешно
    public int GET_TAIL_ERR; // очередь пуста


    // создана новая двухсторонняя очередь
    public Deque() {
    }

    // команды
    // постусловие: в голову очереди добавлен новый элемент
    public void addFront(T value) {
        queue.add(0, value);
    }

    // предусловие: очередь не пустая
    // постусловие: из хвоста очереди удален элемент
    public void removeTail() {
        if (size() == 0) {
            remove_tail_status = REMOVE_TAIL_ERR;
            return;
        }
        remove_tail_status = REMOVE_TAIL_OK;
        queue.remove(size() - 1);
    }

    // запросы
    // предусловие: очередь не пустая
    public T getTail() {
        if (size() == 0) {
            get_tail_status = GET_TAIL_ERR;
            return null;
        }
        get_tail_status = GET_TAIL_OK;
        return queue.get(size() - 1);
    }

    public int get_remove_tail_status() { return remove_tail_status; }
    public int get_get_tail_status() { return get_tail_status; }
}
