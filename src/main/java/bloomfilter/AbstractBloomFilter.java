package bloomfilter;

public abstract class AbstractBloomFilter {

    //конструктор
    //создан новый фильтр Блюма c размером len
    public AbstractBloomFilter(int len) {

    }
    //команды
    public abstract void add(String str1);

    //запросы
    public abstract boolean isValue(String str1);
}
