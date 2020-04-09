package com.mockito.test;

import org.junit.Assert;
import org.junit.Test;

/**
 * 类说明：
 *
 * @author zhucj
 * @since 20200423
 */
public class SystemTest {

    @Test
    public void testGetEnv() {
        Assert.assertEquals("/Users/janita/itTools/shuiyou-maven",System.getenv("M2_HOME") );
    }
}
