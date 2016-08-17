package ru.sbt.les10_LambdaStream;

import org.junit.Assert;
import ru.sbt.les10_LambdaStream.HomeWork.WordTrimmer;

public class Test {

    @org.junit.Test
    public void Test_Trim_1(){
        Assert.assertEquals(WordTrimmer.trim(""), "");
        Assert.assertEquals(WordTrimmer.trim(" "), "");
        Assert.assertEquals(WordTrimmer.trim("(),"), "");
        Assert.assertEquals(WordTrimmer.trim("ABCD"), "ABCD");
        Assert.assertEquals(WordTrimmer.trim("123"), "123");
    }

    @org.junit.Test
    public void Test_Trim_2(){
        Assert.assertEquals(WordTrimmer.trim(" A"), "A");
        Assert.assertEquals(WordTrimmer.trim("A "), "A");
        Assert.assertEquals(WordTrimmer.trim(" A "), "A");
        Assert.assertEquals(WordTrimmer.trim(",,,(Abcd"), "Abcd");
        Assert.assertEquals(WordTrimmer.trim("Abcd)]."), "Abcd");
        Assert.assertEquals(WordTrimmer.trim(",.(Abcd)]."), "Abcd");
        Assert.assertEquals(WordTrimmer.trim(",.(123]."), "123");
    }
}
