package powerset;

import java.util.Set;

public class PowerSet<T> extends ParentHashTable<T> {

    public PowerSet(int sz) {
        super(sz);
    }
    public final static int INTERSECTION_OK = 1; // успешно
    public final static int INTERSECTION_ERR = 2; // set2 == null

    public final static int UNION_OK = 1; // успешно
    public final static int UNION_ERR = 2; // set2 == null

    public final static int DIFFERENCE_OK = 1; // успешно
    public final static int DIFFERENCE_ERR = 2; // set2 == null

    public final static int ISSUBSET_OK = 1; // успешно
    public final static int ISSUBSET_ERR = 2; // set2 == null

    public int getIntersectionStatus() {
        return intersectionStatus;
    }

    public int getUnionStatus() {
        return unionStatus;
    }

    public int getDifferenceStatus() {
        return differenceStatus;
    }

    public int getIsSubsetStatus() {
        return isSubsetStatus;
    }

    private int intersectionStatus;
    private int unionStatus;
    private int differenceStatus;
    private int isSubsetStatus;

    //запросы

    // предусловие: set2 не null
    public PowerSet<T> intersection(PowerSet<T> set2) {
        if (set2 == null) {
            intersectionStatus = INTERSECTION_ERR;
            return null;
        }
        PowerSet<T> newSet = new PowerSet<>(size);
        T[] set2Values = set2.values();
        for (T val2 : set2Values) {
            if (contains(val2)) {
                newSet.put(val2);
            }
        }
        intersectionStatus = INTERSECTION_OK;
        return newSet;
    }

    // предусловие: set2 не null
    public PowerSet<T> union(PowerSet<T> set2) {
        if (set2 == null) {
            unionStatus = UNION_ERR;
            return null;
        }
        PowerSet<T> newSet = new PowerSet<>(size + set2.size());
        for (T val : slots) {
            if (val != null) {
                newSet.put(val);
            }
        }
        T[] set2Values = set2.values();
        for (T val2 : set2Values) {
            newSet.put(val2);

        }
        unionStatus = UNION_OK;
        return newSet;
    }

    // предусловие: set2 не null
    public PowerSet<T> difference(PowerSet<T> set2) {
        if (set2 == null) {
            differenceStatus = DIFFERENCE_ERR;
            return null;
        }
        PowerSet<T> newSet = new PowerSet<>(size + set2.size());
        T[] set2Values = set2.values();
        for (T val : slots) {
            if (!set2.contains(val)) {
                newSet.put(val);
            }
        }
        for (T val2 : set2Values) {
            if (!this.contains(val2)) {
                newSet.put(val2);
            }
        }
        differenceStatus = DIFFERENCE_OK;
        return newSet;
    }

    // предусловие: set2 не null
    public boolean isSubset(PowerSet<T> set2) {
        if (set2 == null) {
            isSubsetStatus = ISSUBSET_ERR;
            return false;
        }
        boolean isSubset = true;
        T[] set2Values = set2.values();
        for (T val2 : set2Values) {
            if (!this.contains(val2)) {
                isSubset = false;
            }
        }
        isSubsetStatus = ISSUBSET_OK;
        return isSubset;
    }

    public T[] values() {
        T[] values = (T[]) new Object[size];
        int i = 0;
        for (T val : slots) {
            if (val != null) {
                values[i] = val;
                i++;
            }
        }
        return values;
    }
}

