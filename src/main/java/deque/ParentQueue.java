package deque;

import queue.Queue;

import java.util.ArrayList;

public abstract class ParentQueue<T> {

    ArrayList<T> queue = new ArrayList<>();

    private int dequeue_status;
    private int get_status;

    // команды
    // постусловие: в хвост очереди добавлен новый элемент
    public void enqueue(T value) {
        queue.add(value);
    }

    // предусловие: очередь не пуста
    // постусловие: из головы очереди удален элемент
    public void dequeue() {
        if(queue.isEmpty()) {
            dequeue_status = DEQUEUE_ERR;
            return;
        }
        dequeue_status = DEQUEUE_OK;
        queue.remove(0);
    }

    // запросы
    // предусловие: очередь не пустая
    public T get() {
        if(queue.isEmpty()) {
            get_status = GET_ERR;
            return null;
        }
        get_status = GET_OK;
        return queue.get(1);
    }

    public int size() {
        return queue.size();
    }

    public final static int DEQUEUE_OK = 1; // успешно
    public final static int DEQUEUE_ERR = 2; // очередь пуста

    public final static int GET_OK = 1;  // успешно
    public final static int GET_ERR = 2;  // очередь пуста

    public int get_dequeue_status() { return dequeue_status; }
    public int get_get_status() { return get_status; };
}
