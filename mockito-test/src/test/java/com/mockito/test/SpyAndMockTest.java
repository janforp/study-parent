package com.mockito.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Calculator.class })
public class SpyAndMockTest {

    @Test
    public void testSumXXBySpy_Call_Private_Method() throws Exception {
        Calculator cal = PowerMockito.spy(new Calculator());
        PowerMockito.when(cal, "sumXX", anyInt(), anyInt()).thenReturn(2);
        assertEquals(2, cal.callSumXX(1, 2));
    }

    @Test
    public void testSumXXBySpy_Not_Call_Private_Method() throws Exception {
        Calculator cal = PowerMockito.spy(new Calculator());
        PowerMockito.doReturn(2).when(cal, "sumXX", anyInt(), anyInt());
        assertEquals(2, cal.callSumXX(1, 2));
    }

    @Test
    public void testSumXXByMock_Not_Call_Real_Method() throws Exception {
        Calculator cal = PowerMockito.mock(Calculator.class);
        PowerMockito.when(cal, "sumXX", anyInt(), anyInt()).thenReturn(2);
        assertEquals(0, cal.callSumXX(1, 2));
    }

    @Test
    public void testSumXXByMock_Call_Real_Method() throws Exception {
        Calculator cal = PowerMockito.mock(Calculator.class);
        PowerMockito.when(cal, "sumXX", anyInt(), anyInt()).thenReturn(2);
        PowerMockito.when(cal.callSumXX(anyInt(), anyInt())).thenCallRealMethod();//指明callSumXX调用真实的方法
        assertEquals(2, cal.callSumXX(1, 2));
    }
}
