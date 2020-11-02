package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

import org.junit.Assert;
import org.junit.Test;

/**
 * _10_Recursion
 *
 * @author zhucj
 * @since 20201126
 */
public class _10_RecursionTest {

    @Test
    public void testF() {
        int f = _10_Recursion.ComputeRawNumberUseRecursion.computeRawNumberUseRecursion(10);
        Assert.assertEquals(10, f);
    }

    @Test
    public void computeWalkWaysTest() {
        _10_Recursion.ComputeWalkWaysUseRecursion _10_Recursion = new _10_Recursion.ComputeWalkWaysUseRecursion();

        int walkWays = _10_Recursion.computeWalkWaysUseRecursion(1);
        Assert.assertEquals(1, walkWays);

        walkWays = _10_Recursion.computeWalkWaysUseRecursion(2);
        Assert.assertEquals(2, walkWays);

        walkWays = _10_Recursion.computeWalkWaysUseRecursion(3);
        Assert.assertEquals(3, walkWays);

        walkWays = _10_Recursion.computeWalkWaysUseRecursion(7);
        Assert.assertEquals(21, walkWays);
    }

    @Test
    public void computeWalkWaysNotUseRecursionTest() {
        int walkWays = _10_Recursion.ComputeWalkWaysUseRecursion.computeWalkWaysNotUseRecursion(1);
        Assert.assertEquals(1, walkWays);

        walkWays = _10_Recursion.ComputeWalkWaysUseRecursion.computeWalkWaysNotUseRecursion(2);
        Assert.assertEquals(2, walkWays);

        walkWays = _10_Recursion.ComputeWalkWaysUseRecursion.computeWalkWaysNotUseRecursion(3);
        Assert.assertEquals(3, walkWays);

        walkWays = _10_Recursion.ComputeWalkWaysUseRecursion.computeWalkWaysNotUseRecursion(7);
        Assert.assertEquals(21, walkWays);
    }

    @Test
    public void findRootReferrerIdTest() {
        String referrerId = _10_Recursion.FindRootReferrerId.findRootReferrerId("b");
        Assert.assertEquals("a", referrerId);
    }
}