package com.janita.datastructuresandalgorithms.lc;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode-cn.com/problems/non-overlapping-intervals/
 * 无重叠区间
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 *
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: [ [1,2], [1,2], [1,2] ]
 *
 * 输出: 2
 *
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: [ [1,2], [2,3] ]
 *
 * 输出: 0
 *
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * @author zhucj
 * @since 20201224
 */
public class _434_EraseOverlapIntervals {

}

class Solution {

    /**
     * 找到不相交的区间的数量
     *
     * @param intervals 区间
     * @return 找到不相交的区间的数量
     */
    public static int intervalSchedule(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 按 end 升序排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        // 至少有一个区间不相交
        int count = 1;
        // 排序后，第一个区间就是 x
        int xEnd = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= xEnd) {
                // 找到下一个选择的区间了
                count++;
                xEnd = interval[1];
            }
        }
        return count;
    }

    public static int eraseOverlapIntervals(int[][] intervals) {
        return intervals.length - intervalSchedule(intervals);
    }
}