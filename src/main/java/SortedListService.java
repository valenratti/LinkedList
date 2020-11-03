import java.util.Iterator;

public interface SortedListService <T extends Comparable<? super T>> extends Iterable<T> {
    void dump();

    void delete(T element);

    boolean isEmpty();

    void add(T element);

    int size();

    T getMin();

    T getMax();
}
