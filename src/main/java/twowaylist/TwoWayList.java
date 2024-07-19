package twowaylist;

public abstract class TwoWayList<T> extends ParentList<T> {

    public final int LEFT_OK = 1; // последняя left() отработала нормально
    public final int LEFT_ERR = 2; // список пуст

    private int left_status = LEFT_ERR;

    //постусловие: создан новый пустой двунаправленный список
    public TwoWayList() {}

    //команды
    // предусловие: курсов установлен не на head
    // постусловие: курсор сдвинут на один узел влево
    public void left() {
        if(!is_head()) {
            cursor = cursor.prev;
            left_status = RIGHT_OK;
        } else {
            left_status = RIGHT_ERR;
        }
    }

    public int get_left_status() {return left_status;}
}
