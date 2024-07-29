package queue;
public class Queue<T> extends AbstractQueue<T> {

    static class Node<T> {
        T value;
        Queue.Node<T> next;
        Queue.Node<T> prev;
        Node(T val) {
            value = val;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    private int dequeue_status;
    private int peek_status;


    // команды
    // постусловие: в хвост очереди добавлен новый элемент itm
    public void enqueue(T itm) {
        Node<T> newItm = new Node<>(itm);
        if (head == null) {
            head = newItm;
            tail = newItm;
            return;
        }
        newItm.prev = tail;
        tail.next = newItm;
        tail = newItm;
    }

    // предусловие: очередь не пуста
    // постусловие: из головы очереди удален элемент
    public void dequeue() {
        if (head == null) {
            dequeue_status = DEQUEUE_ERR;
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
            dequeue_status = DEQUEUE_OK;
            return;
        }
        head = head.next;
        head.prev = null;
        dequeue_status = DEQUEUE_OK;
    }


    // запросы
    // предусловие: очередь не пустая
    public T peek() {
        if (head == null) {
            peek_status = PEEK_ERR;
            return null;
        }
        peek_status = PEEK_OK;
        return head.value;
    }

    public int size() {
        int size = 0;
        if (head == null) {
            return 0;
        }
        Node<T> n = head;
        while (n.next != null) {
            size++;
            n = n.next;
        }
        return size;
    }

    public int get_dequeue_status() {return dequeue_status;}
    public int get_peek_status() {return peek_status;}
}
