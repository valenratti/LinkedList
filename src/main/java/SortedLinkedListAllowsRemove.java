import java.util.Iterator;

public class SortedLinkedListAllowsRemove<T extends Comparable<? super T>> extends SortedLinkedList<T> {

    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListIteratorRemove();
    }



    private class SortedLinkedListIteratorRemove extends SortedLinkedListIterator {
        public void remove() {
            if(!calledNext) throw new IllegalStateException("No se invoco a next");
            size--;
            calledNext = false;
            if(size == 0){
                firstElement = null;
                return;
            }
            //Si estoy apuntando a null, es porque el anterior es el ultimo elemento
            if(current == null){
                lastElement = lastElement.before;
                lastElement.next = null;
                return;
            }
            //Veo si el que voy a borrar es el primero-
            else if(current.before == firstElement){
                firstElement = current;
                firstElement.before = null;
                return;
            }
            else{
                Node aux = current.before.before;
                aux.next = current;
                current.before = aux;
                return;
            }
        }
    }

}
