import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedListJUnit {
    @Test
    void testAddElement()
    {
        SortedListService<String> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add("As");
        sortedLinkedList.add("Bs");
        sortedLinkedList.add("Cs");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sortedLinkedList.dump();
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("AsBsCs",cleanOutPut);
        Assertions.assertEquals(3,sortedLinkedList.size());

    }

    @Test
    void testRemoveElement()
    {
        SortedListService<String> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add("As");
        sortedLinkedList.add("Bs");
        sortedLinkedList.add("Cs");
        sortedLinkedList.delete("Bs");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sortedLinkedList.dump();
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("AsCs",cleanOutPut);
        Assertions.assertEquals(2,sortedLinkedList.size());
    }

    @Test
    void testGoodGetMinGetMax()
    {
        SortedListService<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(20);
        sortedLinkedList.add(40);
        sortedLinkedList.add(30);
        sortedLinkedList.add(10);
        Assertions.assertEquals(10,sortedLinkedList.getMin());
        Assertions.assertEquals( 40, sortedLinkedList.getMax());
    }

    @Test
    void testBadGetMinGetMax()
    {
        SortedListService<Integer> sortedLinkedList = new SortedLinkedList<>();
        Assertions.assertEquals(null,sortedLinkedList.getMin());
        Assertions.assertEquals( null, sortedLinkedList.getMax());
    }

    @Test
    void testAddRepeated()
    {
        SortedListService<String> sortedLinkedList = new SortedLinkedList<>();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        sortedLinkedList.add("As");
        sortedLinkedList.add("Bs");
        sortedLinkedList.add("Cs");
        sortedLinkedList.add("As");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sortedLinkedList.dump();
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        String cleanErr =errContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("AsBsCs",cleanOutPut);
        Assertions.assertEquals(3,sortedLinkedList.size());
        Assertions.assertEquals("Insertion failed. As repeated",cleanErr);
    }

    @Test
    void testNotFoundDeleteEmpty()
    {
        SortedListService<String> sortedLinkedList = new SortedLinkedList<>();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sortedLinkedList.delete("hola");
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("No se ha encontrado el elemento que desea eliminar",cleanOutPut);
    }

    @Test
    void testNotFoundDelete()
    {
        SortedListService<String> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add("As");
        sortedLinkedList.add("Bs");
        sortedLinkedList.add("Cs");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sortedLinkedList.delete("hola");
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("No se ha encontrado el elemento que desea eliminar",cleanOutPut);
    }

    @Test
    void testNextIterator()
    {
        SortedListService<Integer> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add(10);
        sortedLinkedList.add(30);
        sortedLinkedList.add(5);
        Iterator<Integer> it = sortedLinkedList.iterator();
        Assertions.assertEquals(5,it.next());
    }
    @Test
    void testNoSuchElementException()
    {
        SortedListService<Integer> sortedLinkedList = new SortedLinkedList<>();
        Iterator<Integer> it = sortedLinkedList.iterator();
        Assertions.assertThrows(NoSuchElementException.class,()->{it.next();});
    }

    @Test
    void testDeleteFirstElement()
    {
        SortedListService<String> sortedLinkedList = new SortedLinkedList<>();
        sortedLinkedList.add("As");
        sortedLinkedList.add("Bs");
        sortedLinkedList.add("Cs");
        sortedLinkedList.delete("As");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sortedLinkedList.dump();
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("BsCs",cleanOutPut);
        Assertions.assertEquals(2,sortedLinkedList.size());
    }

    @Test
    void testRemoveElementIterator()
    {
        SortedLinkedList<Integer> lista = new SortedLinkedListAllowsRemove<>();
        lista.add(50); lista.add(30); lista.add(40); lista.add(10);
        lista.add(20); lista.add(60); lista.add(70); lista.add(80);
        for(Iterator<Integer> x= lista.iterator(); x.hasNext();)
        {
            Integer auxi = x.next();
            if(auxi.equals(80) || auxi.equals(10) || auxi.equals(40) )
            {
                x.remove();
                System.out.println(String.format("Deleting %d",auxi));
            }
            else
            {
                System.out.println(auxi);
            }
        }
        Iterator<Integer> it = lista.iterator();
        Assertions.assertEquals(20,it.next());
    }


    @Test
    void testRemoveWithoutNext()
    {
        SortedLinkedList<Integer> lista = new SortedLinkedListAllowsRemove<>();
        lista.add(50); lista.add(30); lista.add(40); lista.add(10);
        lista.add(20); lista.add(60); lista.add(70); lista.add(80);
        Iterator<Integer> it =lista.iterator();
        Assertions.assertThrows(IllegalStateException.class,()->{  it.remove();},"No se invoco a next");
    }

    @Test
    void testRemoveFirstElement()
    {
        SortedLinkedList<Integer> lista = new SortedLinkedListAllowsRemove<>();
        lista.add(50); lista.add(30); lista.add(40); lista.add(10);
        lista.add(20); lista.add(60); lista.add(70); lista.add(80);
        Iterator<Integer> it =lista.iterator();
        it.next();
        it.remove();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        lista.dump();
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("20304050607080",cleanOutPut);
        Assertions.assertEquals(7,lista.size());
    }

    @Test
    void testRemoveLastElement()
    {
        SortedLinkedList<Integer> lista = new SortedLinkedListAllowsRemove<>();
        lista.add(50); lista.add(30); lista.add(40); lista.add(10);
        lista.add(20); lista.add(60); lista.add(70); lista.add(80);
        Iterator<Integer> it =lista.iterator();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        for(Iterator<Integer> x= lista.iterator(); x.hasNext();)
        {
            Integer auxi = x.next();
            if(auxi.equals(80) )
            {
                x.remove();
            }
            else
            {
                System.out.println(auxi);
            }
        }
        String cleanOutPut = outContent.toString().replaceAll("[\n,\r]","");
        Assertions.assertEquals("10203040506070",cleanOutPut);
        Assertions.assertEquals(7,lista.size());
    }

    @Test
    void testRemoveUniqueComponent()
    {
        SortedLinkedList<Integer> lista = new SortedLinkedListAllowsRemove<>();
        lista.add(50);
        for(Iterator<Integer> x= lista.iterator(); x.hasNext();)
        {
            x.next();
            x.remove();
        }
        Assertions.assertEquals(0,lista.size());
    }

    @Test
    void testNormal()
    {
        SortedLinkedList<Integer> lista = new SortedLinkedListAllowsRemove<>();
        lista.add(50);
        lista.add(100);
        Iterator<Integer> it = lista.iterator();
        it.next();
        it.remove();
        it.next();
        it.remove();
        Assertions.assertEquals(0,lista.size());
    }
}
