package com.janita.datastructuresandalgorithms.lc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhucj
 * @since 20201224
 */
public class _1_TwoSum {

    /**
     * 暴力
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Node node = map.get(nums[i]);
                node.indexSet.add(i);
                map.put(nums[i], node);
            } else {
                List<Integer> set = new ArrayList<>();
                set.add(i);
                map.put(nums[i], new Node(set, nums[i]));
            }
        }

        int[] result;
        for (Map.Entry<Integer, Node> entry : map.entrySet()) {
            Integer value = entry.getKey();
            Node node = entry.getValue();
            int otherValue = target - value;
            if (otherValue == value) {
                if (node.indexSet.size() > 1) {
                    return new int[] { node.indexSet.get(0), node.indexSet.get(1) };
                }
            }
            if (map.get(otherValue) != null) {
                return new int[] { node.indexSet.get(0), map.get(otherValue).indexSet.get(0) };
            }
        }
        return null;
    }

    static class Node {

        private List<Integer> indexSet;

        private int value;

        public Node(List<Integer> indexSet, int value) {
            this.indexSet = indexSet;
            this.value = value;
        }
    }

    /**
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，
     * 能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)。
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        //key:value
        //value:index
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[] { hashtable.get(target - nums[i]), i };
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    @Test
    public void test1() {

    }
}
