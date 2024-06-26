package boundedstack;

public abstract class AbstractBoundedStack<T> {
    public final int POP_NIL = 0; // push() ещё не вызывалась
    public final int POP_OK = 1; // последняя pop() отработала нормально
    public final int POP_ERR = 2; // стек пуст

    public final int PEEK_NIL = 0; // peek() ещё не вызывалась
    public final int PEEK_OK = 1; // последняя peek() вернула корректное значение
    public final int PEEK_ERR = 2; // стек пуст

    public final int PUSH_NIL = 0; // push() ещё не вызывалась
    public final int PUSH_OK = 1; // последняя push() отработала нормально
    public final int PUSH_ERR = 2; // количество элементов в стеке = MAX_SIZE

    // предусловие: аргумент maxSize > 0
    // постусловие: создан новый пустой стек с максимальным размером maxSize
    public AbstractBoundedStack(int maxSize) {}

    // постусловие: создан новый пустой стек с максимальным размером 32
    public AbstractBoundedStack() {}

    // предусловие: в стеке количество элементов меньше чем максимальный размер
    // постусловие: в стек добавлено новое значение
    public abstract void push(T value);

    // предусловие: стек не пустой;
    // постусловие: из стека удалён верхний элемент
    public abstract void pop();

    // постусловие: из стека удалятся все значения
    public abstract void clear();

    // предусловие: стек не пустой
    public abstract T peek();

    public abstract int size();

    public abstract int getPopStatus(); // возвращает значение POP_*
    public abstract int getPeekStatus(); // возвращает значение PEEK_*
    public abstract int getPushStatus(); // возвращает значение PUSH_*

    public abstract int getMaxSize(); // возвращает максимальный размер стека
}




