package boundedstack;

import java.util.LinkedList;
import java.util.List;

public class BoundedStack<T> extends AbstractBoundedStack<T> {

    private List<T> stack; // основное хранилище стека
    private int peekStatus; // статус запроса peek()
    private int popStatus; // статус команды pop()
    private int pushStatus; // статус команды push()
    private final int maxSize;

    public BoundedStack(int maxSize) {
        if (maxSize > 0) {
            this.maxSize = maxSize;
        } else {
            this.maxSize = 32;
        }
        clear();
    }

    public BoundedStack() {
        this.maxSize = 32;
        clear();
    }

    @Override
    public void push(T value) {
        if (size() < this.maxSize) {
            stack.add(value);
            pushStatus = PUSH_OK;
        } else {
            pushStatus = PUSH_ERR;
        }
    }

    @Override
    public void pop() {
        if (size() > 0) {
            stack.remove(size() - 1);
            popStatus = POP_OK;
        } else {
            popStatus = POP_ERR;
        }
    }

    @Override
    public void clear() {
        stack = new LinkedList<>();
        peekStatus = PEEK_NIL;
        popStatus = POP_NIL;
        pushStatus = PUSH_NIL;
    }

    @Override
    public T peek() {
        T result;
        if (size() > 0) {
            result = stack.get(size() - 1);
            peekStatus = PEEK_OK;
        } else {
            result = null;
            peekStatus = PEEK_ERR;
        }
        return result;
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public int getPopStatus() {
        return popStatus;
    }

    @Override
    public int getPeekStatus() {
        return peekStatus;
    }

    @Override
    public int getPushStatus() {
        return pushStatus;
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }
}