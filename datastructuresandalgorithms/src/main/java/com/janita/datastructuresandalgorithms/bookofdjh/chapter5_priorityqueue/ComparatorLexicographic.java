package com.janita.datastructuresandalgorithms.bookofdjh.chapter5_priorityqueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * ComparatorLexicographic
 *
 * @author zhucj
 * @since 20201126
 */
public class ComparatorLexicographic implements Comparator<Point2D> {

    @Override
    public int compare(Point2D a, Point2D b) {
        int aX = a.getX();
        int aY = a.getY();
        int bX = b.getX();
        int bY = b.getY();
        return aX != bX ?
                aX - bX ://如x坐标大，则为大
                aY - bY; //如果x坐标一样，则y坐标大为大
    }

    @Test
    public void testCompare() {
        Point2D a = new Point2D(0, 0);
        Point2D b = new Point2D(0, 0);
        Comparator<Point2D> comparator = new ComparatorLexicographic();
        int compare = comparator.compare(a, b);
        Assert.assertEquals(0, compare);

        b = new Point2D(0, 1);
        compare = comparator.compare(a, b);
        Assert.assertEquals(-1, compare);
    }
}
