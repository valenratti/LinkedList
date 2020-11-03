
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SortedLinkedList <T extends Comparable<? super T>>
        implements SortedListService<T> {

    protected Node firstElement;
    protected Node lastElement;
    protected int size =0;
    protected T max;
    protected T min;

    @Override
    public void dump() {
        for (Node rec= firstElement; rec != null; rec= rec.next) {
            System.out.println((rec.value));
        }

    }

    @Override
    public boolean isEmpty() {
        return size ==0;
    }

    @Override
    public void add(T element) {
        Node prev = firstElement;
        Node rec = firstElement;


        while(rec != null && rec.value.compareTo(element) < 0) {
            // go on
            prev = rec;
            rec = rec.next;
        }

        // repeated?
        if(rec != null && rec.value.compareTo(element) == 0) {
            System.err.println(String.format("Insertion failed. %s repeated", element));
            return;
        }


        // does the first element change?
        if (prev == rec) {
            firstElement = new Node(element, rec, null);
            if(size>0){
                rec.before = firstElement;
            }
        }
        else {
            prev.next = new Node(element, rec, prev);
            if(rec == null){
                lastElement = prev.next;
            }
            else{
                rec.before = prev.next;
            }
        }

        size++;

    }

    @Override
    public void delete(T element) {
        Node current = firstElement;
        if(size == 0){
            System.out.println("No se ha encontrado el elemento que desea eliminar");
            return;
        }
        if(element.compareTo(current.value) == 0){
            firstElement = current.next;
            firstElement.before = null;
            size--;
            return;
        }
        while(current.next != null && element.compareTo(current.next.value) > 0){
            current = current.next;
        }
        if(current.next == null || (element.compareTo(current.next.value) != 0) ){
            System.out.println("No se ha encontrado el elemento que desea eliminar");
            return;
        }
        else if(element.compareTo(current.next.value) == 0){
            //Veo si borro el ultimo
            if(current.next.next == null){
                current.next = null;
                lastElement = current;
            }//Veo si borro uno del medio
            else{
                Node newNext = current.next.next;
                current.next = newNext;
                newNext.before = current;
            }
            size--;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T getMin() {
        if(size == 0) return null;
        return firstElement.value;
    }

    @Override
    public T getMax() {
        if(size == 0) return null;
        return lastElement.value;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListIterator();
    }

    protected class SortedLinkedListIterator implements Iterator<T> {

        protected Node current;
        protected boolean calledNext = false;

        public SortedLinkedListIterator() {
            this.current = firstElement;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(hasNext()){
                if(!calledNext) calledNext = true;
                T aux = current.value;
                current = current.next;
                return aux;
            }
            else throw new NoSuchElementException();
        }
    }


    protected final class Node {

        protected T value;
        protected Node next;
        protected Node before;

        // do not accept nulls in the data
        Node(T theValue, Node theNext, Node theBefore) {
            if (theValue == null)
                throw new RuntimeException("Null is not accepted for data");
            value = theValue;
            next = theNext;
            before = theBefore;
        }

    }
}
