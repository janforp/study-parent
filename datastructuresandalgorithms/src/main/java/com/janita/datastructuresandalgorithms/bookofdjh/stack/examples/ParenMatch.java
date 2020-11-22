package com.janita.datastructuresandalgorithms.bookofdjh.stack.examples;

import com.google.common.collect.Sets;
import com.janita.datastructuresandalgorithms.bookofdjh.stack.Stack;
import com.janita.datastructuresandalgorithms.bookofdjh.stack.StackArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * 括号匹配算法
 * 算术表达式中可能出现很多对相互匹配的符号，比如:
 * 小括号:"("和")"
 * 大括号:"{"和"}"
 * 方括号:"["和"]"
 * 下取整括号:"⎣"和"⎦"
 * 上取整括号:"⎡"和"⎤"
 * 所谓括号匹配，就是要求上述左、右括号成对匹配，而且其层次嵌套关系也必须合理。比如:
 * ()({}){([()[]])}:匹配
 * ([]{):不匹配(右大括号缺失)
 * )([()]{}:不匹配(第一对括号左右颠倒)
 * ([())]{}:不匹配(括号虽然能够两两成对，但嵌套关系紊乱)
 * 匹配关系不难理解，我们将其严格的定义作为作业留给读者。
 * 下面，我们将借助一个栈结构 S，通过对算术表达式自左向右的一遍扫描，检查其中的括号是 否匹配。
 *
 * 括号
 *
 * @author zhucj
 * @since 20201126
 */
public class ParenMatch {

    private static final Set<String> BRACKET_SET = Sets.newHashSet("(", ")", "{", "}", "[", "]", "⎣", "⎦", "⎡", "⎤");

    private static final Set<String> LEFT_BRACKET_SET = Sets.newHashSet("(", "{", "[", "⎣", "⎡");

    private static final Set<String> RIGHT_SET = Sets.newHashSet(")", "}", "]", "⎦", "⎤");

    private static boolean isMatch(String left, String right) {
        if ("(".equals(left)) {
            return ")".equals(right);
        }
        if ("{".equals(left)) {
            return "}".equals(right);
        }
        if ("[".equals(left)) {
            return "]".equals(right);
        }
        if ("⎣".equals(left)) {
            return "⎦".equals(right);
        }
        if ("⎡".equals(left)) {
            return "⎤".equals(right);
        }
        return false;
    }

    /**
     * 假设算术表达式为 X = "x0x1x2...xn-1"，其中 xi 可以是括号、常数、变量名或者算术运算符。
     * 我 们依次检查 X 中的各个符号，非括号的符号都可以忽略。若遇到左括号，则将其压入栈 S 中;若遇 到右括号
     * ，则将栈顶符号弹出并与该右括号对比。如果发现某对括号不匹配，或者遇到右括号时栈为空，或者整个表达式扫描过后栈非空，
     * 都可以断定括号不匹配。在按照以上规则扫描完所有字符 后，若栈为空，则说明括号是匹配的
     *
     * 算法:ParenMatch(X, n) 输入:长度为n数组X，对应于某一算术表达式
     * 输出:判断X中的括号是否匹配 {
     * 初始化栈S;
     * for (i=0 to n-1) //依次扫描X中的各个符号
     * if (X[i]为左括号) //若遇到左括号，则 S.push(X[i]); //入栈
     * else if (X[i]为右括号) { //若遇到右括号，则 if (S.isEmpty()) //若此时栈为空，则
     * return false; //不用检查后续字符，即可报告“不匹配”
     * if (S.pop()与X[i]不匹配) //否则，若弹出的栈顶与当前括号不匹配，则
     * return false; //报告“不匹配” }
     * //至此，整个表达式已经扫描完毕
     * if (S.isEmpty()) return true; //若此时栈空，则报告“匹配” else return false; //否则，报告“不匹配”
     */
    private boolean isMatch(String[] express) {
        if (express == null || express.length == 0) {
            throw new RuntimeException();
        }
        Stack stack = new StackArray(express.length);
        for (String bracket : express) {
            if (!BRACKET_SET.contains(bracket)) {
                continue;
            }
            if (LEFT_BRACKET_SET.contains(bracket)) {
                stack.push(bracket);
            }
            if (RIGHT_SET.contains(bracket)) {
                if (stack.isEmpty()) {
                    return false;
                }
                String pop = (String) stack.pop();
                boolean match = isMatch(pop, bracket);
                if (!match) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void isMatchTest() {
        Assert.assertTrue(isMatch("()({}){([()[]])}".split("||")));
        Assert.assertFalse(isMatch("([]{)".split("||")));
        Assert.assertFalse(isMatch(")([()]{}".split("||")));
        Assert.assertFalse(isMatch("([())]{}".split("||")));
        Assert.assertTrue(isMatch(new String[] { "{", "}" }));
    }
}
