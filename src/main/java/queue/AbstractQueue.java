package queue;

public abstract class AbstractQueue<T> {

    // постусловие: создана новая пустая очередь
    public AbstractQueue() {
    }

    // команды
    // постусловие: в хвост очереди добавлен новый элемент itm
    public abstract void enqueue(T itm);

    // предусловие: очередь не пуста
    // постусловие: из головы очереди удален элемент
    public abstract void dequeue();


    // запросы
    // предусловие: очередь не пустая
    public abstract T peek();

    public abstract int size();

    public final static int DEQUEUE_OK = 1;
    public final static int DEQUEUE_ERR = 2;

    public final static int PEEK_OK = 1;
    public final static int PEEK_ERR = 2;

    public abstract int get_dequeue_status();
    public abstract int get_peek_status();
}
