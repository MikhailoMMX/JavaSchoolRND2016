package ru.sbt.les8_UnitTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MyLLTest {
    private MyLinkedList<String> myList;
    @Before
    public void init(){
        myList = new MyLinkedList<>();
    }

    @Test
    public void Test1_Add_and_Get(){
        myList.Add("1");
        myList.Add("2");
        myList.Add("3");

        Assert.assertEquals(myList.get(0), "1");
        Assert.assertEquals(myList.get(1), "2");
        Assert.assertEquals(myList.get(2), "3");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestAdd_index_out_of_bounds_1(){
        myList.Add(-1, "1");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestAdd_index_out_of_bounds_2(){
        myList.Add(10, "1");
    }

    @Test
    public void TestAdd_index_insert_before(){
        myList.Add(0, "1");
        myList.Add(0, "2");
        myList.Add(0, "3");

        Assert.assertEquals(myList.get(0), "3");
        Assert.assertEquals(myList.get(1), "2");
        Assert.assertEquals(myList.get(2), "1");
    }

    @Test
    public void TestAdd_index_insert_in_middle(){
        myList.Add("1");
        myList.Add("2");
        myList.Add(1, "3");

        Assert.assertEquals(myList.get(0), "1");
        Assert.assertEquals(myList.get(1), "3");
        Assert.assertEquals(myList.get(2), "2");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Get_index_out_of_range_1(){
        myList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Get_index_out_of_range_2(){
        myList.get(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Get_index_out_of_range_3(){
        myList.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Get_index_out_of_range_4(){
        myList.Add("1");
        myList.get(1);
    }

    @Test
    public void Test_Remove_1(){
        myList.Add("1");
        myList.Add("2");
        myList.Add("3");
        myList.remove(1);

        Assert.assertEquals(myList.get(0), "1");
        Assert.assertEquals(myList.get(1), "3");
    }

    @Test
    public void Test_Remove_2(){
        myList.Add("1");
        myList.Add("2");
        myList.Add("3");
        myList.remove(0);

        Assert.assertEquals(myList.get(0), "2");
        Assert.assertEquals(myList.get(1), "3");
    }

    @Test
    public void Test_Remove_3(){
        myList.Add("1");
        myList.Add("2");
        myList.Add("3");
        myList.remove(2);
        myList.Add("4");

        Assert.assertEquals(myList.get(0), "1");
        Assert.assertEquals(myList.get(1), "2");
        Assert.assertEquals(myList.get(2), "4");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Remove_index_out_of_range_1(){
        myList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Remove_index_out_of_range_2(){
        myList.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Remove_index_out_of_range_3(){
        myList.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void Test_Remove_index_out_of_range_4(){
        myList.Add("1");
        myList.remove(1);
    }

    @Test
    public void Test_getIterator_1(){
        Iterator<String> it = myList.iterator();
        Assert.assertFalse(it.hasNext());
    }

    @Test
    public void Test_getIterator_2(){
        myList.Add("1");
        myList.Add("2");
        myList.Add("3");

        Iterator<String> it = myList.iterator();
        Assert.assertEquals(it.next(), "1");
        Assert.assertEquals(it.next(), "2");
        Assert.assertEquals(it.next(), "3");
        Assert.assertFalse(it.hasNext());
    }

    @Mock
    private List<String> mockedList;

    @Mock
    private Iterator<String> mockedIter;

    @Test
    public void Test_addAll(){
        Mockito.when(mockedIter.hasNext()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(mockedIter.next()).thenReturn("1").thenReturn("2").thenReturn("3").thenThrow(Exception.class);
        Mockito.when(mockedList.iterator()).thenReturn(mockedIter);

        myList.Add("-1");
        myList.AddAll(mockedList);
        myList.Add("+1");
        Assert.assertEquals(myList.get(0), "-1");
        Assert.assertEquals(myList.get(1), "1");
        Assert.assertEquals(myList.get(2), "2");
        Assert.assertEquals(myList.get(3), "3");
        Assert.assertEquals(myList.get(4), "+1");
    }

    @Test
    public void Test_copy(){
        myList.Add("-1");
        myList.Add(" 0");
        myList.Add("+1");

        myList.copy(mockedList);
        Mockito.verify(mockedList).add("-1");
        Mockito.verify(mockedList).add(" 0");
        Mockito.verify(mockedList).add("+1");
        Mockito.verifyNoMoreInteractions(mockedList);
    }

}
