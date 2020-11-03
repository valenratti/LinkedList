public class SortedLinkedListTester {

    public static void main(String args[]){
        SortedLinkedList<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(1);
        sortedLinkedList.add(6);
        sortedLinkedList.add(3);
        sortedLinkedList.delete(2);
        sortedLinkedList.add(224);
        System.out.println(sortedLinkedList.getMax());
        System.out.println(sortedLinkedList.getMin());
        System.out.println(sortedLinkedList.size());

        for(Integer i : sortedLinkedList){
            System.out.println(i);
        }

    }
}
