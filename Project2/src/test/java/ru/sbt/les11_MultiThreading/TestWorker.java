package ru.sbt.les11_MultiThreading;
import com.sun.tracing.dtrace.ArgsAttributes;
import org.junit.Assert;
import org.junit.Test;
import ru.sbt.les11_MultiThreading.HomeWork.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestWorker {
    @Test(expected = NullPointerException.class)
    public void test_Worker_null() {
        Map<Character, Long> map = Worker.ConvertWordsToMap(null);
    }

    @Test
    public void Test_Worker_1(){
        List<String> arg = new ArrayList<>();
        Map<Character, Long> map = Worker.ConvertWordsToMap(arg);
        Assert.assertEquals("Wrong keys count", 0, map.keySet().size());

        arg.add("");
        map = Worker.ConvertWordsToMap(arg);
        Assert.assertEquals("Wrong keys count", 0, map.keySet().size());
    }

    @Test
    public void Test_Worker_2(){
        List<String> arg = new ArrayList<>();
        arg.add("АБВё");
        arg.add("абвЁ");
        Map<Character, Long> map = Worker.ConvertWordsToMap(arg);

        Assert.assertEquals("Wrong keys count", 4, map.keySet().size());
        Assert.assertEquals("Wrong sum of letters", 8L, (long)map.values().stream().reduce(0L, (a, b)->a+b));
        Assert.assertEquals(2L, (long)map.get('а'));
        Assert.assertEquals(2L, (long)map.get('б'));
        Assert.assertEquals(2L, (long)map.get('в'));
        Assert.assertEquals(2L, (long)map.get('ё'));
    }
}
