package ru.sbrf.javacourse.buildtools;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

final class Key {

    public long getId() {
        return 100L;
    }

}

@RunWith(PowerMockRunner.class)
@PrepareForTest(Key.class)
public class SomeClassTest {

    @Test
    public void testStaticGenerateLong() {
        Key key = PowerMockito.spy(new Key());
        Mockito.when(key.getId()).thenReturn(123L);

        long keyId = key.getId();

        Assert.assertEquals(123, keyId);
    }

}
