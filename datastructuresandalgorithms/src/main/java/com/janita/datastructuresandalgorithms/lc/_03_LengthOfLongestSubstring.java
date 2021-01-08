package com.janita.datastructuresandalgorithms.lc;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 无重复字符的最长子串
 * 示例1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s由英文字母、数字、符号和空格组成
 *
 * @author zhucj
 * @since 20210128
 */
public class _03_LengthOfLongestSubstring {

    private static class Char {

        private Character character;

        private int index;

        public Char(Character character, int index) {
            this.character = character;
            this.index = index;
        }

        public Character getCharacter() {
            return character;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Char aChar = (Char) o;
            return Objects.equals(character, aChar.character);
        }

        @Override
        public int hashCode() {
            return Objects.hash(character);
        }
    }

    public int lengthOfLongestSubstring(String s) {
        List<Char> charList = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            charList.add(new Char(s.charAt(i), i));
        }
        int max = 0;
        Map<Character, List<Char>> map = charList.stream().collect(Collectors.groupingBy(Char::getCharacter));
        for (Map.Entry<Character, List<Char>> entry : map.entrySet()) {
            List<Char> value = entry.getValue();
            if (value.size() == 1) {
                continue;
            }
            for (int i = 1; i < value.size(); i++) {
                int len = value.get(i).index - value.get(i - 1).index;
                if (len > max) {
                    max = len;
                }
            }
        }
        if (max == 0) {
            max = s.length();
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    @Test
    public void test() {
        int aab = lengthOfLongestSubstring3("aab");
        Assert.assertEquals(2, aab);
    }
}
